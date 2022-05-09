package com.example.myapplication.Activity.Custumer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.XemThemSanPhamMoiNhatAdapter;
import com.example.myapplication.Interface.IClickProductDetail;
import com.example.myapplication.Model.SanPham;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SeeMoreNewProductsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener  {

    Toolbar toolbar;
    TextView mTitle;
    RecyclerView rcv_dmsp;
    ArrayList<SanPham> arrayListxemthemsanphammoinat;
    SwipeRefreshLayout swipeRefreshLayout;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_products);
        AnhXa();
        setToolbar();

        //set giao dien cho san pham moi nhat

        GridLayoutManager gridLayoutManager;
        gridLayoutManager = new GridLayoutManager(this, 2);
        rcv_dmsp.setLayoutManager(gridLayoutManager);
        getListMoreProductNew();//ham get du lieu moi nhat

        swipeRefreshLayout.setOnRefreshListener(this);//ham refest du lieu
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.purple_500));//xet mau load
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toobar);
        rcv_dmsp = findViewById(R.id.rcv_dmsp);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        swipeRefreshLayout = findViewById(R.id.switper_spmn);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTitle.setText("Sản phẩm mới nhất");
        getSupportActionBar().setDisplayShowTitleEnabled(false);//khong hien thi titile ma cdinh cua toorbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toobar, menu);
        return super.onCreateOptionsMenu(menu);

    }

    //ham xu ly su kien khi chon vao tung item menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_cart:
                Intent intent1 = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent1);
                break;
            case R.id.item_serch:
                Intent intent2 = new Intent(getApplicationContext(), TimKiem_SanPhamActivity.class);
                startActivity(intent2);
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    private void getListMoreProductNew() {

        arrayListxemthemsanphammoinat = new ArrayList<>();


        StringRequest StringRequest = new StringRequest(Request.Method.GET, Server.GETMORESANPHAMMOINHAT,
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
                                arrayListxemthemsanphammoinat.add(sanPham);
                            }

                            XemThemSanPhamMoiNhatAdapter sanPhamMoiNhatAdapter = new XemThemSanPhamMoiNhatAdapter(context, arrayListxemthemsanphammoinat, new IClickProductDetail() {

                                @Override
                                public void OnClickProductDetail(SanPham sanPham) {
                                    GotoProductDetail(sanPham);
                                }
                            });
                            rcv_dmsp.setAdapter(sanPhamMoiNhatAdapter);
                            sanPhamMoiNhatAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "lỗi", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());// tạo request len server
        requestQueue.add(StringRequest);

    }

    private void GotoProductDetail(SanPham sanPham) {
        Intent intent = new Intent(this, ChiTietSanPhamActivity.class);
        Bundle bundle =new Bundle();
        bundle.putSerializable("productdetail",sanPham);
        intent.putExtras(bundle);
        startActivity(intent);
        }


    @Override
    public void onRefresh() {
        //arrayListxemthemsanphammoinat.clear();
        getListMoreProductNew();
        swipeRefreshLayout.setRefreshing(false);//tat di
    }
}