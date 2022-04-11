package com.example.myapplication.Fragment.Admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Activity.Custumer.ChiTietSanPhamActivity;
import com.example.myapplication.Activity.Custumer.GioHangActivity;
import com.example.myapplication.Activity.TimKiemActivity;
import com.example.myapplication.Activity.admin.AdminActivity;
import com.example.myapplication.R;


public class ViewProductFragment extends Fragment {

    View view;
    AdminActivity adminActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_view_product, container, false);
        AnhXa();
        adminActivity =(AdminActivity)getActivity();
        return view;
    }

    private void AnhXa() {
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
                Intent intent2 = new Intent(adminActivity, GioHangActivity.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}