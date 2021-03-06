package com.example.myapplication.Activity.Custumer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.DanhMucSanPham_TimKiem_CustumerAdapter;
import com.example.myapplication.Model.DanhMucSanPham;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TimKiemDanhMucActivity extends AppCompatActivity {


    Toolbar toolbar;
    TextView mTitle;
    Context context;
    ArrayList<DanhMucSanPham> danhMucSanPhamArrayList;
    RecyclerView recyclerView_dmsp;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem_danh_muc);

        AnhXa();
        setToolbar();

        danhMucSanPhamArrayList = new ArrayList<>();

        SearchView searchView = findViewById(R.id.noidungnhap);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getListSearchCatalog(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getListSearchCatalog(newText);
                return true;
            }
        });
    }


    private void AnhXa() {
        toolbar = findViewById(R.id.toobar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        recyclerView_dmsp = findViewById(R.id.rcv_dmsp);
        linearLayout = findViewById(R.id.linner_kotimthayketqua);
    }


    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTitle.setText("T??m ki???m danh m???c ");
        getSupportActionBar().setDisplayShowTitleEnabled(false);//khong hien thi titile ma cdinh cua toorbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getListSearchCatalog(String query) {

        danhMucSanPhamArrayList.clear();

        //g???i request
        StringRequest StringRequest = new StringRequest(Request.Method.POST,
                Server.SEARCH_CATALOG, new Response.Listener<String>() {

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
                        danhMucSanPhamArrayList.add(danhMucSanPham);
                    }
                    if (danhMucSanPhamArrayList.size() > 0) {
                        DanhMucSanPham_TimKiem_CustumerAdapter danhMucSanPhamAdapter = new DanhMucSanPham_TimKiem_CustumerAdapter(context, danhMucSanPhamArrayList);

                        //set giao dien cho san pham theo danh muc

                        LinearLayoutManager linearLayoutManager;
                        linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
                        recyclerView_dmsp.setLayoutManager(linearLayoutManager);

                        //set kieu hien thi

                        recyclerView_dmsp.setAdapter(danhMucSanPhamAdapter);
                        danhMucSanPhamAdapter.notifyDataSetChanged();
                        linearLayout.setVisibility(View.GONE);
                        recyclerView_dmsp.setVisibility(View.VISIBLE);

                    } else//khong tim thay  san pham
                    {
                        linearLayout.setVisibility(View.VISIBLE);
                        recyclerView_dmsp.setVisibility(View.INVISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            //ket noi that bai
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("name_catalog", query);
                return hashMap;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());// t???o request len server
        requestQueue.add(StringRequest);
    }
}