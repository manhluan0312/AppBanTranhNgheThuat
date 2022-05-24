package com.example.myapplication.Activity.admin;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Activity.Other.LoginActivity;
import com.example.myapplication.Fragment.Admin.AddCatalogFragment;
import com.example.myapplication.Fragment.Admin.AddProductFragment;
import com.example.myapplication.Fragment.Admin.ChangePasswordFragment;
import com.example.myapplication.Fragment.Admin.HomeFragment;
import com.example.myapplication.Fragment.Admin.OrderManagementFragment;
import com.example.myapplication.Fragment.Admin.ViewCatalogFragment;
import com.example.myapplication.Fragment.Admin.ViewInfoFragment;
import com.example.myapplication.Fragment.Admin.ViewProductFragment;
import com.example.myapplication.Fragment.Admin.ViewStatistics_CatalogFragment;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;
import com.google.android.material.navigation.NavigationView;

//import static com.example.myapplication.Activity.LoginActivity.DATALOGIN;

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ImageView imageView_avartar;
    TextView tv_ten, tv_sdt;
    public SharedPreferences sharedPreferences;


    AppCompatButton btn_no, btn_yes;

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_VIEW_CATALOG = 1;
    private static final int FRAGMENT_VIEW_PRODUCT = 2;
    private static final int FRAGMENT_ADD_PRODUCT = 3;
    private static final int FRAGMENT_ADD_CATALOG = 4;
    private static final int FRAGMENT_MANAGEMENT_ORDER = 5;
    private static final int FRAGMENT_VIEW_INFO = 6;
    private static final int FRAGMENT_CHANGE_PASS = 7;
    private static final int FRAGMENT_VIEW_STATISTICS_CATALOG = 8;

    private int CurrentFrament = FRAGMENT_HOME;// gan vi tri mo trang admin la mo trang home


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        AnhXa();

        sharedPreferences = getSharedPreferences("datalogin_custumer", Context.MODE_PRIVATE);

        //set du lieu len headerview

        tv_ten.setText(sharedPreferences.getString("hoten", ""));
        tv_sdt.setText(sharedPreferences.getString("sdt", ""));

        String anh ="http://" + Server.HOST + "upload/"+ sharedPreferences.getString("anh", "");

        Glide.with(this)
                .load(anh)
                .error(anh)
                .centerCrop()
                .into(imageView_avartar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        MoNavigationDrawer();//bat su kien mo navidraw
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());//vao trang quan tri vao luon home frament
        navigationView.getMenu().findItem(R.id.menu_trangchu).setChecked(true);//mo frament nao thi item frament do bao sang
        setToorBar();//mo app set toorbar luon
    }

    //bat su kien mo navidraw
    private void MoNavigationDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.Open_navi, R.string.Close_navi);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }


    private void setToorBar() {
        String titile = "";

        switch (CurrentFrament) {
            case FRAGMENT_HOME:
                titile = "Trang chủ";
                break;
            case FRAGMENT_VIEW_CATALOG:
                titile = "Tất cả danh mục sản phẩm";
                break;
            case FRAGMENT_VIEW_PRODUCT:
                titile = "Tất cả sản phẩm";
                break;
            case FRAGMENT_ADD_PRODUCT:
                titile = "Thêm sản phẩm";
                break;
            case FRAGMENT_ADD_CATALOG:
                titile = "Thêm danh mục sản phẩm";
                break;
            case FRAGMENT_MANAGEMENT_ORDER:
                titile = "Quản lý đơn hàng";
                break;
            case FRAGMENT_VIEW_INFO:
                titile = "Xem thông tin cá nhân";
                break;
            case FRAGMENT_CHANGE_PASS:
                titile = "Đổi mật khẩu";
                break;
            case FRAGMENT_VIEW_STATISTICS_CATALOG:
                titile = "Thống kê sản phẩm theo danh mục";
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(titile);
        }

    }

    private void AnhXa() {
        drawerLayout = findViewById(R.id.draw_layout);
        toolbar = findViewById(R.id.toolbar_title);
        navigationView = findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);
        imageView_avartar = headerView.findViewById(R.id.img_admin);
        tv_ten = headerView.findViewById(R.id.tv_hoten_admin);
        tv_sdt = headerView.findViewById(R.id.tv_sdt_admin);
    }


    //bat su kien khi nguoi dung an nut back tren dt
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    //bat su kien cho cac frament khi click vao tung item cua menu navi
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_trangchu:
                if (CurrentFrament != FRAGMENT_HOME)//vi tri hien tai ko phai fragment home
                {
                    replaceFragment(new HomeFragment());//replace frament
                    CurrentFrament = FRAGMENT_HOME;//gan ve fragment home

                }
                break;

            case R.id.menu_xemsanpham:
                if (CurrentFrament != FRAGMENT_VIEW_PRODUCT)//vi tri hien tai ko phai fragment xemsanpham
                {
                    replaceFragment(new ViewProductFragment());//replace frament
                    CurrentFrament = FRAGMENT_VIEW_PRODUCT;//gan ve fragment xem san pham
                }
                break;

            case R.id.menu_themsanpham:
                if (CurrentFrament != FRAGMENT_ADD_PRODUCT)//vi tri hien tai ko phai fragment them san pham
                {
                    replaceFragment(new AddProductFragment());//replace frament
                    CurrentFrament = FRAGMENT_ADD_PRODUCT;//gan ve fragment them san pham
                }
                break;

            case R.id.menu_xemdmsanpham:
                if (CurrentFrament != FRAGMENT_VIEW_CATALOG)//vi tri hien tai ko phai fragment xemdmsanpham
                {
                    replaceFragment(new ViewCatalogFragment());//replace frament
                    CurrentFrament = FRAGMENT_VIEW_CATALOG;//gan ve fragment xemdmucsanpham
                }
                break;

            case R.id.menu_themdmsanpham:
                if (CurrentFrament != FRAGMENT_ADD_CATALOG)//vi tri hien tai ko phai fragment themdm
                {
                    replaceFragment(new AddCatalogFragment());//replace frament
                    CurrentFrament = FRAGMENT_ADD_CATALOG;//gan ve fragment themdm
                }
                break;

            case R.id.menu_tiepnhan_xulydonhang:
                if (CurrentFrament != FRAGMENT_MANAGEMENT_ORDER)//vi tri hien tai ko phai fragment xuly
                {
                    replaceFragment(new OrderManagementFragment());//replace frament
                    CurrentFrament = FRAGMENT_MANAGEMENT_ORDER;//gan ve fragment xuly
                }
                break;

            case R.id.menu_xemthongtincanhan:
                if (CurrentFrament != FRAGMENT_VIEW_INFO)//vi tri hien tai ko phai fragment xemthongtin
                {
                    replaceFragment(new ViewInfoFragment());//replace frament
                    CurrentFrament = FRAGMENT_VIEW_INFO;//gan ve fragment xemthongtin
                }
                break;

            case R.id.menu_doimatkhau:
                if (CurrentFrament != FRAGMENT_CHANGE_PASS)//vi tri hien tai ko phai fragment doimatkhau
                {
                    replaceFragment(new ChangePasswordFragment());//replace frament
                    CurrentFrament = FRAGMENT_CHANGE_PASS;//gan ve fragment doimatkhau
                }
                break;

            case R.id.menu_dangxuat:
                OpenDilog();
                break;

            case R.id.menu_xemthongke:
                if (CurrentFrament != FRAGMENT_VIEW_STATISTICS_CATALOG)//vi tri hien tai ko phai fragment xem thong ke
                {
                    replaceFragment(new ViewStatistics_CatalogFragment());//replace frament thong ke
                    CurrentFrament = FRAGMENT_VIEW_STATISTICS_CATALOG;//gan ve fragment thong ke
                }
        }
        setToorBar();
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }

    private void OpenDilog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog);
        dialog.setCanceledOnTouchOutside(false);//click ra ngoai dilog ko an dc
        dialog.show();

        btn_no = dialog.findViewById(R.id.btn_no);
        btn_yes = dialog.findViewById(R.id.btn_yes);

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                dialog.dismiss();
                finish();

            }
        });
    }

    //ham replace fragment

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
}
