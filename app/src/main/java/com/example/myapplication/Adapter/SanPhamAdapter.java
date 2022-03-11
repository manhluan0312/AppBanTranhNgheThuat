package com.example.myapplication.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder> {

    @Override
    public SanPhamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SanPhamAdapter.SanPhamViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SanPhamViewHolder extends RecyclerView.ViewHolder {


        //ham khoi tao viewholder
        public SanPhamViewHolder(View itemView) {
            super(itemView);
        }
    }
}
