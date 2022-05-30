package com.example.myapplication.Fragment.Admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Activity.admin.AdminActivity;
import com.example.myapplication.Adapter.ViewStatisticsCatalogAdapter;
import com.example.myapplication.Model.StatisticsCatalog;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class STATISTICS_turnover_Fragment extends Fragment {

    View view;
    AdminActivity adminActivity;
    TextView tv_sodonhang, tv_tongthu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_s_t_a_t_i_s_t_i_c_s_turnover_, container, false);
        AnhXa();
        adminActivity = (AdminActivity) getActivity();
        GetStatic();
        return view;
    }

    private void AnhXa() {
        tv_sodonhang = view.findViewById(R.id.tv_sodonhang);
        tv_tongthu = view.findViewById(R.id.tv_tongthu);
    }
    private void GetStatic() {


        StringRequest stringRequest =new StringRequest(Request.Method.GET, Server.THONGKEDOANHTHU, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray thongke_doanhthu=new JSONArray(response);
                    for (int i = 0; i < thongke_doanhthu.length(); i++){

                        JSONObject jsonObject = thongke_doanhthu.getJSONObject(i);

                        int sodonhang=jsonObject.getInt("Số đơn hàng");
                        float tongthu=(float)jsonObject.getInt("Tổng doanh thu");

                        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                        String đinhdangtongthu=decimalFormat.format(tongthu);
                        tv_sodonhang.setText(sodonhang+"");
                        tv_tongthu.setText(đinhdangtongthu+" "+"VND");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());// tạo request len server
        requestQueue.add(stringRequest);
    }

    }