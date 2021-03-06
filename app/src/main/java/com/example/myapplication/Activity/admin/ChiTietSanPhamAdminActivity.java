package com.example.myapplication.Activity.admin;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.Model.SanPham;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;

import java.text.DecimalFormat;

public class ChiTietSanPhamAdminActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView mTitle;
    SanPham sanPham;
    Context context;
    TextView tv_tensp, tv_gia_sp, tv_chatlieu, tv_kichthuoc, tv_namsangtac, tv_motasp,tv_danhmuc;
    ImageView img_anh_sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham_admin);
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

        tv_danhmuc.setText("Loại:"+sanPham.getName_catalog());
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
        tv_danhmuc=findViewById(R.id.tv_danhmuc);
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
}