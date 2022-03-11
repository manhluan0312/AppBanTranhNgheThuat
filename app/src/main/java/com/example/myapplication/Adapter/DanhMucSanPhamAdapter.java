package com.example.myapplication.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public class DanhMucSanPhamAdapter extends RecyclerView.Adapter<DanhMucSanPhamAdapter.DanhMucSanPhamViewHolder> {


    @Override
    public DanhMucSanPhamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DanhMucSanPhamAdapter.DanhMucSanPhamViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DanhMucSanPhamViewHolder extends RecyclerView.ViewHolder {


        //ham khoi tao viewholder
        public DanhMucSanPhamViewHolder(View itemView) {
            super(itemView);
        }
    }
}

    