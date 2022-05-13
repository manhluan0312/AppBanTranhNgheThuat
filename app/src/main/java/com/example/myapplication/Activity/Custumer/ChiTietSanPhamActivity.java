package com.example.myapplication.Activity.Custumer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.Model.GioHang;
import com.example.myapplication.Model.SanPham;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;

import java.text.DecimalFormat;

public class ChiTietSanPhamActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    TextView mTitle;
    SanPham sanPham;
    Context context;
    TextView tv_tensp, tv_gia_sp, tv_chatlieu, tv_kichthuoc, tv_namsangtac, tv_motasp;
    ImageView img_anh_sp;
    Button btn_themgiohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        AnhXa();
        NhanDuLieuTuProduct();
        setToolbar();
        SetDuLieuLenManHinhChiTiet();

    }

    private void SetDuLieuLenManHinhChiTiet() {

        String anh = "http://" + Server.HOST + "image/Products/" + sanPham.getPoto_product();

        Glide.with(getApplicationContext())
                .load(anh)
                .apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(30)))//bo goc anh
                .centerCrop()
                .error(R.drawable.ic_launcher_foreground)
                .into(img_anh_sp);

        tv_tensp.setText(sanPham.getName_product());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        tv_gia_sp.setText("Giá sản phẩm :" + decimalFormat.format(sanPham.getPrice_product()) + " " + "VND");

        tv_chatlieu.setText("Chất liệu sản phẩm:" + " " + sanPham.getProduct_material());

        tv_kichthuoc.setText("Kích thước sản phẩm:" + " " + sanPham.getProduct_dimensions());

        tv_namsangtac.setText("Năm sáng tác:" + " " + sanPham.getYear_of_creation());

        tv_motasp.setText(sanPham.getProduct_description());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toobar, menu);
        return super.onCreateOptionsMenu(menu);

    }

    //an menu item
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (menu.findItem(R.id.item_serch) != null) {
            menu.findItem(R.id.item_serch).setVisible(false);
        }
        return true;
    }

    //ham xu ly su kien khi chon vao tung item menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_cart:
                Intent intent1 = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void NhanDuLieuTuProduct() {

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        sanPham = (SanPham) bundle.get("productdetail");


    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toobar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        tv_tensp = findViewById(R.id.tv_ten);
        tv_gia_sp = findViewById(R.id.tv_giasanpham);
        tv_kichthuoc = findViewById(R.id.tv_kichco);
        tv_chatlieu = findViewById(R.id.tv_chatlieu);
        tv_namsangtac = findViewById(R.id.tv_namsangtac);
        tv_motasp = findViewById(R.id.tv_motasp_chitiet);
        img_anh_sp = findViewById(R.id.img_anh_sanpham);
        btn_themgiohang = findViewById(R.id.btn_them_vao_gio_hang);

        btn_themgiohang.setOnClickListener(this);

    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTitle.setText(sanPham.getName_product());
        getSupportActionBar().setDisplayShowTitleEnabled(false);//khong hien thi titile ma cdinh cua toorbar

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_them_vao_gio_hang:
                ThemSanPhamGiohang();
                break;
        }
    }

    private void ThemSanPhamGiohang() {
        //da co sp trong gio hang
        if (MainActivity.gioHangArrayList.size() > 0) {
            int soluong = 1;
            boolean sanphamtrung = false;
            for (int i = 0; i < MainActivity.gioHangArrayList.size(); i++) {
                //xu ly su kien san pham them da co trong gio hang -->cong don
                if (MainActivity.gioHangArrayList.get(i).getIdsp() == sanPham.getId_product()) {
                    MainActivity.gioHangArrayList.get(i).setSoluongsanpham(soluong + MainActivity.gioHangArrayList.get(i).getSoluongsanpham());
                    sanphamtrung = true;
                }
            }

            //san pham them chua co trong gio hang

            if (sanphamtrung == false) {

                float giasanphamtungitemdonhang = sanPham.getPrice_product();
                GioHang gioHang = new GioHang();

                gioHang.setGiasanpham(giasanphamtungitemdonhang);
                gioHang.setSoluongsanpham(1);
                gioHang.setIdsp(sanPham.getId_product());
                gioHang.setTensanpham(sanPham.getName_product());
                gioHang.setHinhanhsanpham(sanPham.getPoto_product());
                MainActivity.gioHangArrayList.add(gioHang);
            }
        } else //ko co san pham trong gio hang
        {
            int soluong = 1;
            float giasanphamtungitemdonhang = sanPham.getPrice_product();

            GioHang gioHang = new GioHang();

            gioHang.setGiasanpham(giasanphamtungitemdonhang);
            gioHang.setSoluongsanpham(1);
            gioHang.setIdsp(sanPham.getId_product());
            gioHang.setTensanpham(sanPham.getName_product());
            gioHang.setHinhanhsanpham(sanPham.getPoto_product());
            MainActivity.gioHangArrayList.add(gioHang);
        }
//        Intent intent =new Intent(this,GioHangActivity.class);
//        startActivity(intent);
    }
}