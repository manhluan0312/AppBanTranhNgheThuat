package com.example.myapplication.Activity.Other;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.DonHangChiTietQuanLyAdapter;
import com.example.myapplication.Model.LichsuDonHangChiTiet;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ChiTietDonHangActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    Toolbar toolbar;
    TextView mTitle;
    RecyclerView recyclerView;
    SwipeRefreshLayout switper_dm_admin;

    DonHangChiTietQuanLyAdapter donHangChiTietQuanLyAdapter;
    ArrayList<LichsuDonHangChiTiet> lichsuDonHangChiTietArrayList;
    int madonhang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang);
        AnhXa();

        Intent intent = getIntent();
        madonhang = intent.getIntExtra("madonhang", -1);

        //set giao dien

        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        switper_dm_admin.setOnRefreshListener(this);//ham refest du lieu
        switper_dm_admin.setColorSchemeColors(getResources().getColor(R.color.purple_500));//xet mau load

        setToolbar();
        GetDaTaChiTietDonHang();

    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toobar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        recyclerView = findViewById(R.id.mange_order_admin_detail);
        switper_dm_admin = findViewById(R.id.switper_dm_admin);
    }


    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTitle.setText("Chi tiết đơn hàng");
        getSupportActionBar().setDisplayShowTitleEnabled(false);//khong hien thi titile ma cdinh cua toorbar

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetDaTaChiTietDonHang() {

        lichsuDonHangChiTietArrayList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.GETDONHANGQUANLYCHITIET, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray chitiet = new JSONArray(response);

                    for (int i = 0; i < chitiet.length(); i++) {
                        JSONObject jsonObject = chitiet.getJSONObject(i);
                        String anhsanpham = jsonObject.getString("poto_product");
                        String tensanpham = jsonObject.getString("name_product");
                        float giasanpham = (float) jsonObject.getInt("Price");
                        int soluong = jsonObject.getInt("Amount");

                        LichsuDonHangChiTiet lichsuDonHangChiTiet = new LichsuDonHangChiTiet
                                (anhsanpham, tensanpham, giasanpham, soluong);
                        lichsuDonHangChiTietArrayList.add(lichsuDonHangChiTiet);
                    }

                    donHangChiTietQuanLyAdapter = new DonHangChiTietQuanLyAdapter(lichsuDonHangChiTietArrayList);
                    recyclerView.setAdapter(donHangChiTietQuanLyAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("madonhang", String.valueOf(madonhang));
                return hashMap;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);// tạo request len server
        requestQueue.add(stringRequest);
    }

    @Override
    public void onRefresh() {
        GetDaTaChiTietDonHang();
        switper_dm_admin.setRefreshing(false);//tat di
    }
}