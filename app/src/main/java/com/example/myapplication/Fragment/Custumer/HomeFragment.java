package com.example.myapplication.Fragment.Custumer;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.Activity.Custumer.GioHangActivity;
import com.example.myapplication.Activity.Custumer.HighlightsProcductsActivity;
import com.example.myapplication.Activity.Custumer.NewProductsActivity;
import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.Activity.TimKiemActivity;
import com.example.myapplication.Activity.admin.AdminActivity;
import com.example.myapplication.R;


public class HomeFragment extends Fragment {

    private View mView;
    MainActivity mainActivity;
    Toolbar toolbar;
    TextView mTitle,xemthemspmn,xemthemspnb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        AnhXa();
        setToolbar();

        xemthemspmn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(mainActivity, NewProductsActivity.class);
                startActivity(intent);
            }
        });

        xemthemspnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(mainActivity, HighlightsProcductsActivity.class);
                startActivity(intent);
            }
        });

        return mView;
    }

    //ham anh xa view tu file xml sang file java
    private void AnhXa() {
        toolbar = mView.findViewById(R.id.toobar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        xemthemspmn=mView.findViewById(R.id.tv_xemthemspmn);
        xemthemspnb=mView.findViewById(R.id.tv_xemthemspnb);
    }

    private void setToolbar(){
        mainActivity = (MainActivity) getActivity();//ep kieu bien moi truong
        mainActivity.setSupportActionBar(toolbar);
        mTitle.setText("Trang chủ");
        mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);//khong hien thi titile ma cdinh cua toorbar
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
                Intent intent2 = new Intent(mainActivity, TimKiemActivity.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}