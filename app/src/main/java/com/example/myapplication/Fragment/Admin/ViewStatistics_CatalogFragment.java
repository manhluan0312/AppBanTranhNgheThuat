package com.example.myapplication.Fragment.Admin;

import android.app.VoiceInteractor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.myapplication.Adapter.ViewStatisticsCatalogAdapter;
import com.example.myapplication.Model.StatisticsCatalog;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;


public class ViewStatistics_CatalogFragment extends Fragment {

    View view;
    AdminActivity adminActivity;
    RecyclerView recyclerView_static;
    ArrayList<StatisticsCatalog> statisticsCatalogArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment_view_statistics_catalog, container, false);


        AnhXa();
        adminActivity = (AdminActivity) getActivity();

        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(adminActivity, RecyclerView.VERTICAL, false);
        recyclerView_static.setLayoutManager(linearLayoutManager);

        GetStatic_Catalog();

        return view;
    }

    private void AnhXa() {
        recyclerView_static=view.findViewById(R.id.rct_thongketheodm);
    }
    private void GetStatic_Catalog() {
        statisticsCatalogArrayList =new ArrayList<>();

        StringRequest stringRequest =new StringRequest(Request.Method.GET, Server.ViewStatistics_Catalog, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray thongke_danhmuc =new JSONArray(response);
                    for (int i = 0; i < thongke_danhmuc.length(); i++){

                        JSONObject jsonObject = thongke_danhmuc.getJSONObject(i);

                        String tendm = jsonObject.getString("Tên danh mục");
                        int soluong = jsonObject.getInt("Số lượng sản phẩm");
                        float giacaonhat = jsonObject.getInt("Giá cao nhất");
                        float giathapnhap = jsonObject.getInt("Giá thấp nhất");
                        float giatrungbinh = jsonObject.getInt("Giá trung bình");

                        StatisticsCatalog statisticsCatalog = new StatisticsCatalog(tendm, soluong, giacaonhat, giathapnhap, giatrungbinh);
                        statisticsCatalogArrayList.add(statisticsCatalog);
                        ViewStatisticsCatalogAdapter viewStatisticsCatalogAdapter = new ViewStatisticsCatalogAdapter(statisticsCatalogArrayList, adminActivity);
                        recyclerView_static.setAdapter(viewStatisticsCatalogAdapter);
                        viewStatisticsCatalogAdapter.notifyDataSetChanged();
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