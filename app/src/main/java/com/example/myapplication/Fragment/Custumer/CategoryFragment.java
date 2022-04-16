package com.example.myapplication.Fragment.Custumer;

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
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Activity.Custumer.GioHangActivity;
import com.example.myapplication.Activity.Custumer.TimKiemDanhMucActivity;
import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.Adapter.DanhMucSanPhamAdapter;
import com.example.myapplication.Model.DanhMucSanPham;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class CategoryFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private View mView;
    MainActivity mainActivity;
    Toolbar toolbar;
    TextView mTitle;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView_dmsp;

    DanhMucSanPhamAdapter danhMucSanPhamAdapter;
    private ArrayList<DanhMucSanPham> danhmucList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_danh_muc_san_pham, container, false);
        mainActivity = (MainActivity) getActivity();//ep kieu bien moi truong
        AnhXa();
        setToolbar();
        recyclerView_dmsp = mView.findViewById(R.id.rcv_dmsp);

        swipeRefreshLayout.setOnRefreshListener(this);//ham refest du lieu

        //set giao dien

        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(mainActivity, RecyclerView.VERTICAL, false);
        recyclerView_dmsp.setLayoutManager(linearLayoutManager);

        //set du lieu
        getListcatalog();

        return mView;
    }

    private void AnhXa() {
        toolbar = mView.findViewById(R.id.toobar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        swipeRefreshLayout = mView.findViewById(R.id.switper_dm);
    }

    private void setToolbar() {
        mainActivity.setSupportActionBar(toolbar);
        mTitle.setText("Danh mục");
        mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);//khong hien thi titile ma cdinh cua toorbar
    }


    //ham khoi tao menu toobar
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toobar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//Add this sentence to the menu
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
                Intent intent2 = new Intent(mainActivity, TimKiemDanhMucActivity.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getListcatalog() {

        danhmucList = new ArrayList<>();//khoi tao array list

        //gọi request
        StringRequest StringRequest = new StringRequest(Request.Method.GET,
                Server.GETDANHMUC, new Response.Listener<String>() {

            @Override
            //ket noi thanh cong
            public void onResponse(String response) {

                try {
                    JSONArray danhmuc = new JSONArray(response);

                    for (int i = 0; i < danhmuc.length(); i++) {
                        JSONObject DanhmucObject = danhmuc.getJSONObject(i);
                        int iddm = DanhmucObject.getInt("id_catalog");
                        String tendanhmuc = DanhmucObject.getString("name_catalog");

                        DanhMucSanPham danhMucSanPham = new DanhMucSanPham(iddm, tendanhmuc);
                        danhmucList.add(danhMucSanPham);
                    }
                    danhMucSanPhamAdapter = new DanhMucSanPhamAdapter(mainActivity, danhmucList);
                    recyclerView_dmsp.setAdapter(danhMucSanPhamAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            //ket noi that bai
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());// tạo request len server
        requestQueue.add(StringRequest);
    }

    //refesh du lieu

    @Override
    public void onRefresh() {
        danhmucList.clear();
        getListcatalog();
        swipeRefreshLayout.setRefreshing(false);//tat di
    }
}