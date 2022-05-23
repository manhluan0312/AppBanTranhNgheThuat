package com.example.myapplication.Fragment.Admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Activity.admin.AdminActivity;
import com.example.myapplication.Activity.admin.ChangeCatalogActivity;
import com.example.myapplication.Activity.admin.ChiTietSanPhamAdminActivity;
import com.example.myapplication.Activity.admin.SuaSanPhamActivity;
import com.example.myapplication.Activity.admin.TimKiemSanPham_AdminActivity;
import com.example.myapplication.Adapter.SanPham_AdminAdapter;
import com.example.myapplication.Interface.IClickProductManageAdmin;
import com.example.myapplication.Model.SanPham;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ViewProductFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    View view;
    AdminActivity adminActivity;
    ArrayList<SanPham> sanPhamArrayList;
    Toolbar toolbar;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView mTitle;
    RecyclerView recyclerView_sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_view_product, container, false);
        AnhXa();
        adminActivity = (AdminActivity) getActivity();


        //set giao dien cho san pham theo danh muc

        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(adminActivity, RecyclerView.VERTICAL, false);
        recyclerView_sp.setLayoutManager(linearLayoutManager);

        swipeRefreshLayout.setOnRefreshListener(this);//ham refest du lieu
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.purple_500));//xet mau load

        GetAllProducts();

        return view;
    }

    private void AnhXa() {
        toolbar = view.findViewById(R.id.toobar);
        recyclerView_sp = view.findViewById(R.id.rcv_allsp_admin);
        swipeRefreshLayout = view.findViewById(R.id.switper_dm_admin);
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

        // an icon menu gio hang
        MenuItem item = menu.findItem(R.id.item_cart);
        if (item != null) {
            item.setVisible(false);
        }
    }


    //ham xu ly su kien khi chon vao tung item menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_serch:
                Intent intent2 = new Intent(adminActivity, TimKiemSanPham_AdminActivity.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void GetAllProducts() {

        sanPhamArrayList = new ArrayList<>();

        StringRequest StringRequest = new StringRequest(Request.Method.GET, Server.GETALLPRODUCT,
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
                                int id_catalog=jsonObjectRequest.getInt("id_catalog");
                                String name_catalog = jsonObjectRequest.getString("name_catalog");

                                SanPham sanPham = new SanPham(id_product, name_product, poto_product, price_product,
                                        product_material, product_dimensions, year_of_creation, product_description, note_products,id_catalog, name_catalog);
                                sanPhamArrayList.add(sanPham);
                            }

                            SanPham_AdminAdapter sanPhamAdapter = new SanPham_AdminAdapter(adminActivity, sanPhamArrayList, new IClickProductManageAdmin() {
                                @Override
                                public void OnClickCatalogCatalogManageAdmin(SanPham sanPham) {
                                    //mo bottomsheet sua
                                    View view = getLayoutInflater().inflate(R.layout.bottom_sheet_sp, null);
                                    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(adminActivity);
                                    bottomSheetDialog.setContentView(view);
                                    bottomSheetDialog.show();

                                    LinearLayout linearLayout_suasp = view.findViewById(R.id.suasp);
                                    linearLayout_suasp.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(adminActivity, SuaSanPhamActivity.class);
                                            intent.putExtra("sanpham", sanPham);
                                            startActivity(intent);
                                            bottomSheetDialog.cancel();
                                        }
                                    });
                                }

                                @Override
                                public void OnClickProductDetail(SanPham sanPham) {
                                    GotoProductDetail(sanPham);
                                }
                            });

                            recyclerView_sp.setAdapter(sanPhamAdapter);
                            sanPhamAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "lỗi", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());// tạo request len server
        requestQueue.add(StringRequest);

    }

    private void GotoProductDetail(SanPham sanPham) {
        Intent intent = new Intent(adminActivity, ChiTietSanPhamAdminActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("productdetail", sanPham);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        GetAllProducts();
        swipeRefreshLayout.setRefreshing(false);//tat di
    }
}
