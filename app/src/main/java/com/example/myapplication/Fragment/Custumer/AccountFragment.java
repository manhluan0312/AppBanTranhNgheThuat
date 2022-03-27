package com.example.myapplication.Fragment.Custumer;

import android.app.Dialog;
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
import android.widget.LinearLayout;


import com.example.myapplication.Activity.GioHangActivity;
import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.Activity.TimKiemActivity;
import com.example.myapplication.Activity.ViewInfoActivity;
import com.example.myapplication.R;


public class AccountFragment extends Fragment {

    private View mView;
    MainActivity mainActivity;
    LinearLayout linearLayoutlogout,linearLayoutviewinfo;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        AnhXa();
        setToolbar();
        //bat su kien khi an vao nut dang xuat

        linearLayoutlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenDilog();
            }
        });

        linearLayoutviewinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Viewinfo();
            }
        });

        return mView;
    }

    private void setToolbar(){
        mainActivity = (MainActivity) getActivity();//ep kieu bien moi truong
        mainActivity.setSupportActionBar(toolbar);
        mainActivity.getSupportActionBar().setTitle("Tài khoản");
    }
    private void AnhXa() {
        linearLayoutlogout = mView.findViewById(R.id.linner_dangxuat);
        linearLayoutviewinfo=mView.findViewById(R.id.linner_xemthongtincanhan);
        toolbar = mView.findViewById(R.id.toobar);
    }

    private void OpenDilog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.layout_dialog);
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);//click ra ngoai dilog ko an dc
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
        Intent intent =new Intent(mainActivity, ViewInfoActivity.class);
        startActivity(intent);
    }
}
