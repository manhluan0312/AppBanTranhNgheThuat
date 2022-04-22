package com.example.myapplication.Fragment.Admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Activity.Custumer.TimKiemDanhMucActivity;
import com.example.myapplication.Activity.admin.AdminActivity;
import com.example.myapplication.Activity.admin.ChangeCatalogActivity;
import com.example.myapplication.Adapter.DanhMucSanPham_AdminAdapter;
import com.example.myapplication.Fragment.Custumer.HomeFragment;
import com.example.myapplication.Interface.IClickCatalogManageAdmin;
import com.example.myapplication.Model.DanhMucSanPham;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewCatalogFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    View view;
    AdminActivity adminActivity;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView_dmsp;


    DanhMucSanPham_AdminAdapter danhMucSanPhamAdapter;
    private ArrayList<DanhMucSanPham> danhmucList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_view_catalog, container, false);
        AnhXa();
        adminActivity = (AdminActivity) getActivity();

        recyclerView_dmsp = view.findViewById(R.id.rcv_dmsp_admin);
        swipeRefreshLayout.setOnRefreshListener(this);//ham refest du lieu


        //set giao dien

        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(adminActivity, RecyclerView.VERTICAL, false);
        recyclerView_dmsp.setLayoutManager(linearLayoutManager);

        //set du lieu
        getListcatalog();

        return view;
    }

    private void AnhXa() {
        swipeRefreshLayout = view.findViewById(R.id.switper_dm_admin);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//Add this sentence to the menu
    }

    //ham khoi tao menu toobar
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toobar, menu);
        super.onCreateOptionsMenu(menu, inflater);

        // an icon menu gio hang
        MenuItem item = menu.findItem(R.id.item_cart);
        if (item != null) {
            item.setVisible(false);
        }
    }


    //ham xu ly su kien khi chon vao tung item menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_serch:
                Intent intent2 = new Intent(adminActivity, TimKiemDanhMucActivity.class);
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
                    danhMucSanPhamAdapter = new DanhMucSanPham_AdminAdapter(adminActivity, danhmucList, new IClickCatalogManageAdmin() {
                        @Override
                        public void OnClickCatalogCatalogManageAdmin(DanhMucSanPham danhMucSanPham) {
                            OpenBotomSheetDanhMuc();
                        }
                    });
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

    @Override
    public void onRefresh() {
        danhmucList.clear();
        getListcatalog();
        swipeRefreshLayout.setRefreshing(false);//tat di
    }

    private void OpenBotomSheetDanhMuc() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_dmsp, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(adminActivity);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }
}