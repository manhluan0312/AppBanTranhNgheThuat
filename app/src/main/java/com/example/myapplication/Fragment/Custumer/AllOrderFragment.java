package com.example.myapplication.Fragment.Custumer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.LichSuDonHangAdapter;
import com.example.myapplication.Model.LichSuDonHang;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AllOrderFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    LichSuDonHangAdapter lichSuDonHangAdapter;
    View view;
    ArrayList<LichSuDonHang> lichSuDonHangArrayList;
    public SharedPreferences sharedPreferences;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView rcv_donhang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_all_order, container, false);
        AnhXa();
        sharedPreferences = this.getActivity().getSharedPreferences("datalogin_custumer", Context.MODE_PRIVATE);

        swipeRefreshLayout.setOnRefreshListener(this);//ham refest du lieu
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.purple_500));//xet mau load

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        rcv_donhang.setLayoutManager(linearLayoutManager);
        GetDaTa();
        return view;
    }

    private void AnhXa() {
        rcv_donhang=view.findViewById(R.id.all_order_custumer);
        swipeRefreshLayout = view.findViewById(R.id.switper_dm);
    }

    private void GetDaTa() {
        lichSuDonHangArrayList=new ArrayList<>();
        int id = sharedPreferences.getInt("id", 0);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.all_lichsumuahang, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray donhang = new JSONArray(response);

                    for (int i = 0; i < donhang.length(); i++) {

                        JSONObject donhangObject = donhang.getJSONObject(i);
                        int madonhang=donhangObject.getInt("id_order");
                        String ngaydathang=donhangObject.getString("Order_date");
                        String hinhthucthanhtoan=donhangObject.getString("Payments");
                        String diachigiaohang =donhangObject.getString("Delivery_address");
                        float tongtien=(float)donhangObject.getInt("Total_money");
                        String trangthaidonhang=donhangObject.getString("Delivery_status");

                        LichSuDonHang lichSuDonHang =new LichSuDonHang(madonhang,ngaydathang,hinhthucthanhtoan,diachigiaohang
                        ,trangthaidonhang,tongtien);
                        lichSuDonHangArrayList.add(lichSuDonHang);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                lichSuDonHangAdapter =new LichSuDonHangAdapter(getContext(),lichSuDonHangArrayList);
                rcv_donhang.setAdapter(lichSuDonHangAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id", String.valueOf(id));
                return hashMap;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(stringRequest);
    }

    @Override
    public void onRefresh() {
        GetDaTa();
        swipeRefreshLayout.setRefreshing(false);//tat di
    }
}