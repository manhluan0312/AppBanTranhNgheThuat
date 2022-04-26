package com.example.myapplication.Fragment.Custumer;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Activity.Custumer.ChiTietSanPhamActivity;
import com.example.myapplication.Activity.Custumer.GioHangActivity;
import com.example.myapplication.Activity.Custumer.SeeMoreProposeProcductsActivity;
import com.example.myapplication.Activity.Custumer.SeeMoreNewProductsActivity;
import com.example.myapplication.Activity.Custumer.TimKiem_SanPhamActivity;
import com.example.myapplication.Activity.MainActivity;

import com.example.myapplication.Adapter.SanPhamDeXuatAdapter;
import com.example.myapplication.Adapter.SanPhamMoiNhatAdapter;
import com.example.myapplication.Adapter.SliderAdapter;
import com.example.myapplication.Interface.IClickProductDetail;
import com.example.myapplication.Model.SanPham;
import com.example.myapplication.Model.Slider;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;


public class HomeFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private View mView;
    MainActivity mainActivity;
    Toolbar toolbar;
    TextView mTitle, xemthemspmn, xemthemspnb;
    ViewPager2 viewPager2;
    SwipeRefreshLayout swipeRefreshLayout;
    CircleIndicator3 circleIndicator3;
    ArrayList<Slider> sliderArrayList;
    ArrayList<SanPham> sanPhamMoinhatArrayList, getSanPhamdexuatArrayList;
    RecyclerView rcv_spmn, rcv_spdx;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        AnhXa();
        setToolbar();

        //set giao dien cho san pham moi nhat

        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(mainActivity, RecyclerView.HORIZONTAL, false);
        rcv_spmn.setLayoutManager(linearLayoutManager);

        // //set giao dien cho san pham de xuat

        linearLayoutManager = new LinearLayoutManager(mainActivity, RecyclerView.HORIZONTAL, false);
        rcv_spdx.setLayoutManager(linearLayoutManager);

        getListSlider(); // hien thi slider
        getListProductsNew();//hien thi san pham moi nhat
        getListProductsPropose();//hien thi san pham de xuat

        xemthemspmn.setOnClickListener(this);//bat su kien cho nut xem them san pham moi nhat
        xemthemspnb.setOnClickListener(this);//bat su kien cho nut xem them san pham de xuat

        swipeRefreshLayout.setOnRefreshListener(this);//ham refest du lieu
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.purple_500));//xet mau load

        return mView;
    }

    //ham anh xa view tu file xml sang file java
    private void AnhXa() {
        toolbar = mView.findViewById(R.id.toobar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        xemthemspmn = mView.findViewById(R.id.tv_xemthemspmn);
        xemthemspnb = mView.findViewById(R.id.tv_xemthemspnb);
        viewPager2 = mView.findViewById(R.id.view_papager_2);
        circleIndicator3 = mView.findViewById(R.id.cir);
        rcv_spmn = mView.findViewById(R.id.rcv_spmn);
        rcv_spdx = mView.findViewById(R.id.rcv_spdx);
        swipeRefreshLayout = mView.findViewById(R.id.switper_trangchu);
    }

    private void setToolbar() {
        mainActivity = (MainActivity) getActivity();//ep kieu bien moi truong
        mainActivity.setSupportActionBar(toolbar);
        mTitle.setText("Trang chủ");
        mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);//khong hien thi titile ma cdinh cua toorbar
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//Add this sentence to the menu
    }

    //ham khoi tao menu toobar
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toobar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    //ham xu ly su kien khi chon vao tung item menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_cart:
                Intent intent1 = new Intent(mainActivity, GioHangActivity.class);
                startActivity(intent1);
                break;
            case R.id.item_serch:
                Intent intent2 = new Intent(mainActivity, TimKiem_SanPhamActivity.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Auto run slider
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager2.getCurrentItem() == sliderArrayList.size() - 1) {
                viewPager2.setCurrentItem(0);
            } else {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);//ẽext page
            }
        }
    };

    private void getListSlider() {

        sliderArrayList = new ArrayList<>();

        //gọi request
        StringRequest StringRequest = new StringRequest(Request.Method.GET,
                Server.GET_SILDER, new Response.Listener<String>() {

            @Override
            //ket noi thanh cong
            public void onResponse(String response) {

                try {
                    JSONArray slider = new JSONArray(response);

                    for (int i = 0; i < slider.length(); i++) {
                        JSONObject SliderObject = slider.getJSONObject(i);
                        int id_slider = SliderObject.getInt("id_slider");
                        String titile_sider = SliderObject.getString("titile_sider");
                        String image_sider = SliderObject.getString("image_slider");

                        Slider slider1 = new Slider(id_slider, titile_sider, image_sider);
                        sliderArrayList.add(slider1);
                    }
                    SliderAdapter sliderAdapter = new SliderAdapter(mainActivity, sliderArrayList);
                    viewPager2.setAdapter(sliderAdapter);
                    circleIndicator3.setViewPager(viewPager2);

                    viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                        @Override
                        public void onPageSelected(int position) {
                            super.onPageSelected(position);
                            handler.removeCallbacks(runnable);
                            handler.postDelayed(runnable, 3000);//thoi gian chuyen slider

                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            //ket noi that bai
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mainActivity, "lỗi", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());// tạo request len server
        requestQueue.add(StringRequest);

    }

    private void getListProductsNew() {
        sanPhamMoinhatArrayList = new ArrayList<>();


        StringRequest StringRequest = new StringRequest(Request.Method.GET, Server.GETSANPHAMMOINHAT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray sanpham = new JSONArray(response);
                            for (int i = 0; i < sanpham.length(); i++) {
                                JSONObject jsonObjectRequest = sanpham.getJSONObject(i);
                                int id_product = jsonObjectRequest.getInt("id_product");
                                String name_product = jsonObjectRequest.getString("name_product");
                                String poto_product = jsonObjectRequest.getString("poto_product");
                                int price_product = jsonObjectRequest.getInt("price_product");
                                String product_material = jsonObjectRequest.getString("product_material");
                                String product_dimensions = jsonObjectRequest.getString("product_dimensions");
                                int year_of_creation = jsonObjectRequest.getInt("year_of_creation");
                                String product_description = jsonObjectRequest.getString("product_description");
                                String note_products = jsonObjectRequest.getString("note_products");
                                String name_catalog = jsonObjectRequest.getString("name_catalog");

                                SanPham sanPham = new SanPham(id_product, name_product, poto_product, price_product,
                                        product_material, product_dimensions, year_of_creation, product_description, note_products, name_catalog);
                                sanPhamMoinhatArrayList.add(sanPham);
                            }

                            SanPhamMoiNhatAdapter sanPhamMoiNhatAdapter = new SanPhamMoiNhatAdapter(mainActivity, sanPhamMoinhatArrayList, new IClickProductDetail() {
                                @Override
                                public void OnClickProductDetail(SanPham sanPham) {
                                    GotoProductDetail(sanPham);
                                }
                            });
                            rcv_spmn.setAdapter(sanPhamMoiNhatAdapter);
                            sanPhamMoiNhatAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mainActivity, "lỗi", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());// tạo request len server
        requestQueue.add(StringRequest);

    }

    private void getListProductsPropose() {

        getSanPhamdexuatArrayList = new ArrayList<>();


        StringRequest StringRequest = new StringRequest(Request.Method.GET, Server.GETSANPHAMDEXUAT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray sanpham = new JSONArray(response);
                            for (int i = 0; i < sanpham.length(); i++) {
                                JSONObject jsonObjectRequest = sanpham.getJSONObject(i);
                                int id_product = jsonObjectRequest.getInt("id_product");
                                String name_product = jsonObjectRequest.getString("name_product");
                                String poto_product = jsonObjectRequest.getString("poto_product");
                                int price_product = jsonObjectRequest.getInt("price_product");
                                String product_material = jsonObjectRequest.getString("product_material");
                                String product_dimensions = jsonObjectRequest.getString("product_dimensions");
                                int year_of_creation = jsonObjectRequest.getInt("year_of_creation");
                                String product_description = jsonObjectRequest.getString("product_description");
                                String note_products = jsonObjectRequest.getString("note_products");
                                String name_catalog = jsonObjectRequest.getString("name_catalog");

                                SanPham sanPham = new SanPham(id_product, name_product, poto_product, price_product,
                                        product_material, product_dimensions, year_of_creation, product_description, note_products, name_catalog);
                                getSanPhamdexuatArrayList.add(sanPham);
                            }

                            SanPhamDeXuatAdapter sanPhamDeXuatAdapter = new SanPhamDeXuatAdapter(mainActivity, getSanPhamdexuatArrayList, new IClickProductDetail() {
                                @Override
                                public void OnClickProductDetail(SanPham sanPham) {
                                    GotoProductDetail(sanPham);
                                }
                            });

                            rcv_spdx.setAdapter(sanPhamDeXuatAdapter);
                            sanPhamDeXuatAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mainActivity, "lỗi", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());// tạo request len server
        requestQueue.add(StringRequest);

    }


    //bat su kien cac view

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //bat su kien khi an
            case R.id.tv_xemthemspmn:
                Intent intent = new Intent(mainActivity, SeeMoreNewProductsActivity.class);
                startActivity(intent);
                break;
            //bat su kien khi an
            case R.id.tv_xemthemspnb:
                Intent intent1 = new Intent(mainActivity, SeeMoreProposeProcductsActivity.class);
                startActivity(intent1);
                break;
        }
    }

    //giu trang thai slider khi an nut home
    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);//thoi gian chuyen slider
    }

    private void GotoProductDetail(SanPham sanPham) {
        Intent intent = new Intent(mainActivity, ChiTietSanPhamActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("productdetail", sanPham);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        getListSlider();
        getListProductsNew();
        getListProductsPropose();
        swipeRefreshLayout.setRefreshing(false);//tat di
    }
}
