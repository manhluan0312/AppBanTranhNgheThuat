package com.example.myapplication.Activity.Other;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Activity.Custumer.UpdateInfoProfileActivity;
import com.example.myapplication.Adapter.DonHangChiTietQuanLyAdapter;
import com.example.myapplication.Adapter.QuanLyDonHangAdapter;
import com.example.myapplication.Model.LichsuDonHangChiTiet;
import com.example.myapplication.Model.QuanLyDonHang;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChITieTDonHangAdminActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView mTitle;
    RecyclerView recyclerView;
    Button btn_capnhat;
    Spinner tinhtrangdonhang;
    ArrayAdapter<String> tinhtrangsanPhamAdapter;

    DonHangChiTietQuanLyAdapter donHangChiTietQuanLyAdapter;
    ArrayList<LichsuDonHangChiTiet> lichsuDonHangChiTietArrayList;
    int madonhang;
    String index;
    QuanLyDonHang quanLyDonHang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch_itie_tdon_hang_admin);
        AnhXa();

        Intent intent = getIntent();
        madonhang = intent.getIntExtra("madonhang", -1);
        index=intent.getStringExtra("tinhtrangdonhang");

        //set giao dien

        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        setToolbar();
        GetDaTaChiTietDonHang();

        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CapNhatTinhTrangDonHang();
            }
        });

        ArrayList<String> tinhtrang = new ArrayList<String>();
//        hinhthucthanhtoan.add("-- Vui lòng nhập hình thức thanh toán --");
        tinhtrang.add("Đang chờ xử lý");
        tinhtrang.add("Đang xử lý");
        tinhtrang.add("Đã xử lý");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tinhtrang);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tinhtrangdonhang.setAdapter(arrayAdapter);
        if (index.equals("Đang chờ xử lý")) {
            tinhtrangdonhang.setSelection(0);//get vi tri hien tai cua tinh trangdonhang
        }
        if (index.equals("Đang xử lý")) {
            tinhtrangdonhang.setSelection(1);//get vi tri hien tai cua tinh trangdonhang
        }
        if (index.equals("Đã xử lý")) {
            tinhtrangdonhang.setSelection(2);//get vi tri hien tai cua tinh trangdonhang
        }
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toobar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        recyclerView = findViewById(R.id.mange_order_admin_detail);
        btn_capnhat = findViewById(R.id.btn_capnhat);
        tinhtrangdonhang = findViewById(R.id.tinhtrangdonhang);
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

    private void CapNhatTinhTrangDonHang() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.CAPNHATTINHTRANGDONHANG, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String sucess = jsonObject.getString("success");
                    if (sucess.equals("1")) {
                        Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Lỗi", Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id", String.valueOf(madonhang));
                hashMap.put("tinhtrang", tinhtrangdonhang.getSelectedItem().toString());
                return hashMap;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);// tạo request len server
        requestQueue.add(stringRequest);
    }

}