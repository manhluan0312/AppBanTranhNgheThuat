package com.example.myapplication.Fragment.Admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Activity.admin.AdminActivity;
import com.example.myapplication.Adapter.QuanLyDonHangAdapter;
import com.example.myapplication.Model.QuanLyDonHang;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrderManagementFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    View view;
    RecyclerView rcv_quanly;

    ArrayList<QuanLyDonHang> quanLyDonHangArrayList;
    QuanLyDonHangAdapter quanLyDonHangAdapter;
    AdminActivity adminActivity;
    SwipeRefreshLayout switper_dm_admin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_order_management, container, false);

        AnhXa();
        adminActivity = (AdminActivity) getActivity();


        //set giao dien

        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(adminActivity, RecyclerView.VERTICAL, false);
        rcv_quanly.setLayoutManager(linearLayoutManager);


        switper_dm_admin.setOnRefreshListener(this);//ham refest du lieu
        switper_dm_admin.setColorSchemeColors(getResources().getColor(R.color.purple_500));//xet mau load

        GetDataQuanLyDonHang();
        return view;
    }

    private void AnhXa() {
        rcv_quanly = view.findViewById(R.id.mange_order_admin);
        switper_dm_admin=view.findViewById(R.id.switper_dm_admin);
    }

    private void GetDataQuanLyDonHang() {
        quanLyDonHangArrayList = new ArrayList<>();

        //gọi request
        StringRequest StringRequest = new StringRequest(Request.Method.GET,
                Server.GETDONHANGQUANLY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONArray donhang = null;
                try {
                    donhang = new JSONArray(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < donhang.length(); i++) {
                    try {
                        JSONObject donhangObject = donhang.getJSONObject(i);
                        int madonhang = donhangObject.getInt("id_order");
                        String tenkhachhang = donhangObject.getString("name_customer");
                        String sodienthoai = donhangObject.getString("Phone");
                        String hinhthucthanhtoan = donhangObject.getString("Payments");
                        String ngaydathang = donhangObject.getString("Order_date");
                        String diachigiaohang = donhangObject.getString("Delivery_address");
                        String ghichu = donhangObject.getString("Note");
                        float tongtien = donhangObject.getInt("Total_money");
                        String trangthaidonhang = donhangObject.getString("Delivery_status");


                        QuanLyDonHang quanLyDonHang=new QuanLyDonHang(madonhang,tenkhachhang,sodienthoai,hinhthucthanhtoan,
                                ngaydathang,diachigiaohang,ghichu,trangthaidonhang,tongtien);

                        quanLyDonHangArrayList.add(quanLyDonHang);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    quanLyDonHangAdapter=new QuanLyDonHangAdapter(quanLyDonHangArrayList,adminActivity);
                    rcv_quanly.setAdapter(quanLyDonHangAdapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());// tạo request len server
        requestQueue.add(StringRequest);
    }

    // refresh du lieu

    @Override
    public void onRefresh() {
        GetDataQuanLyDonHang();
        switper_dm_admin.setRefreshing(false);//tat di
    }
}