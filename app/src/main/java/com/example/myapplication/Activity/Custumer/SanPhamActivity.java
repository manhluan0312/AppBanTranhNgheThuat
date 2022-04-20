package com.example.myapplication.Activity.Custumer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.SanPhamAdapter;
import com.example.myapplication.Model.SanPham;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SanPhamActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    Toolbar toolbar;
    TextView mTitle;
    int intExtra;
    String ten;
    ArrayList<SanPham> sanPhamArrayList;
    RecyclerView recyclerView_sp;
    SwipeRefreshLayout swipeRefreshLayout;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        AnhXa();
        Intent intent = getIntent();
        intExtra = getIntent().getIntExtra("danhmuctheosanpham", -1);
        ten = intent.getStringExtra("tendanhmuc");
        setToolbar();
        GetProductToCatalog();


        swipeRefreshLayout.setOnRefreshListener(this);//ham refest du lieu
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.purple_500));//xet mau load

        //set giao dien cho san pham theo danh muc

        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView_sp.setLayoutManager(linearLayoutManager);

    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toobar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        recyclerView_sp = findViewById(R.id.rcv_sp);
        swipeRefreshLayout = findViewById(R.id.switper_sp);
    }


    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTitle.setText(ten);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//khong hien thi titile ma cdinh cua toorbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetProductToCatalog() {

        sanPhamArrayList = new ArrayList<>();


        StringRequest StringRequest = new StringRequest(Request.Method.POST, Server.GETSANPHAMTHEODANHMUC,
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
                                sanPhamArrayList.add(sanPham);
                            }

                            SanPhamAdapter sanPhamAdapter = new SanPhamAdapter(context, sanPhamArrayList);
                            recyclerView_sp.setAdapter(sanPhamAdapter);
                            sanPhamAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "lỗi", Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id_catalog", String.valueOf((int) intExtra));
                return hashMap;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());// tạo request len server
        requestQueue.add(StringRequest);

    }


    @Override
    public void onRefresh() {
        GetProductToCatalog();
        swipeRefreshLayout.setRefreshing(false);//tat di
    }
}