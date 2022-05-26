package com.example.myapplication.Activity.Custumer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.GioHangAdapter;
import com.example.myapplication.EvenBus.TinhTongEvents;
import com.example.myapplication.Model.GioHang;
import com.example.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangActivity extends AppCompatActivity {


    Toolbar toolbar;
    TextView mTitle;
    static TextView tv_tongtien;
    RecyclerView rcv_giohang;
    Button btn_dathang;
    Context context;
    float Tongtien;
    ConstraintLayout linner_giohangtrong;

    GioHangAdapter gioHangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        AnhXa();
        setToolbar();
        CheckDataCart();
        XuLyTongTien();
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toobar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        rcv_giohang = findViewById(R.id.rcv_giohang);
        btn_dathang = findViewById(R.id.btn_dathang);
        tv_tongtien = findViewById(R.id.tv_tongtien);
        linner_giohangtrong = findViewById(R.id.linner_giohangtrong);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTitle.setText("Giỏ hàng");
        getSupportActionBar().setDisplayShowTitleEnabled(false);//khong hien thi titile ma cdinh cua toorbar

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_dathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.gioHangArrayList.size() > 0) {

                    Intent intent = new Intent(getApplicationContext(), DatHangActivity.class);
                    intent.putExtra("tongtien",Tongtien);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Giỏ hàng phải có sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void CheckDataCart() {

        //co du lieu

        if (MainActivity.gioHangArrayList.size() > 0) {
            LinearLayoutManager linearLayoutManager;
            linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            rcv_giohang.setLayoutManager(linearLayoutManager);
            gioHangAdapter = new GioHangAdapter(context, MainActivity.gioHangArrayList);
            rcv_giohang.setAdapter(gioHangAdapter);
            rcv_giohang.setVisibility(View.VISIBLE);
            linner_giohangtrong.setVisibility(View.GONE);

        } else {
            linner_giohangtrong.setVisibility(View.VISIBLE);
            rcv_giohang.setVisibility(View.GONE);
        }
//        gioHangAdapter.notifyDataSetChanged();
    }

    private void XuLyTongTien() //tinh tong tien mang mua hang
    {
         Tongtien = 0;

        for (int i = 0; i < MainActivity.gioHangArrayList.size(); i++) {
            Tongtien = Tongtien + (MainActivity.gioHangArrayList.get(i).getGiasanpham() * MainActivity.gioHangArrayList.get(i).getSoluongsanpham());
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tv_tongtien.setText(decimalFormat.format(Tongtien) + " " + "VND");
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void EventTinhTien(TinhTongEvents events) {
        if (events != null) {
            XuLyTongTien();
        }
    }

}