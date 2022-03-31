package com.example.myapplication.Fragment.Custumer;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.myapplication.Activity.ChangePasswordActivity;
import com.example.myapplication.Activity.Custumer.GioHangActivity;
import com.example.myapplication.Activity.LoginActivity;
import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.Activity.TimKiemActivity;
import com.example.myapplication.Activity.Custumer.UpdateInfoProfileActivity;
import com.example.myapplication.R;


public class AccountFragment extends Fragment implements View.OnClickListener {

    private View mView;
    MainActivity mainActivity;
    LinearLayout linearLayoutlogout, linearLayoutviewinfo, linearLayoutchangepass,linearLayoutlíchsu;
    Toolbar toolbar;
    TextView mTitle;
    AppCompatButton btn_no, btn_yes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        AnhXa();
        setToolbar();
        linearLayoutlogout.setOnClickListener(this);
        linearLayoutviewinfo.setOnClickListener(this);
        linearLayoutchangepass.setOnClickListener(this);
        linearLayoutlíchsu.setOnClickListener(this);
        return mView;
    }

    private void ChangePassWord() {
        Intent intent = new Intent(mainActivity, ChangePasswordActivity.class);
        startActivity(intent);

    }

    private void setToolbar() {
        mainActivity = (MainActivity) getActivity();//ep kieu bien moi truong
        mainActivity.setSupportActionBar(toolbar);
        mTitle.setText("Tài khoản");
        mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);//khong hien thi titile ma cdinh cua toorbar
    }

    private void AnhXa() {
        linearLayoutlogout = mView.findViewById(R.id.linner_dangxuat);
        linearLayoutviewinfo = mView.findViewById(R.id.linner_xemthongtincanhan);
        toolbar = mView.findViewById(R.id.toobar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        linearLayoutchangepass = mView.findViewById(R.id.linner_doimatkhau);
        linearLayoutlíchsu=mView.findViewById(R.id.linner_lichsudonhang);
    }

    private void OpenDilog() {
        final Dialog dialog = new Dialog(getContext());
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
                Intent intent = new Intent(mainActivity, LoginActivity.class);
                startActivity(intent);
                dialog.dismiss();
                mainActivity.finish();

            }
        });
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

    private void Viewinfo() {
        Intent intent = new Intent(mainActivity, UpdateInfoProfileActivity.class);
        startActivity(intent);
    }

    //ham bat su kien cho tung view
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //bat su kien khi an vao nut dang xuat
            case R.id.linner_dangxuat:
                OpenDilog();
                break;
            //bat su kien khi an vao nut xem thong tin ca nhan
            case R.id.linner_xemthongtincanhan:
                Viewinfo();
                break;
            //bat su kien khi an vao thay doi mat khau
            case R.id.linner_doimatkhau:
                ChangePassWord();
                break;
            //bat su kien khi an vao xem lich su don hang
            case R.id.linner_lichsudonhang:
                HistoryOrder();
                break;
        }
    }

    private void HistoryOrder() {

    }
}
