package com.example.myapplication.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.GioHangViewHolder> {


    @Override
    public GioHangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(GioHangAdapter.GioHangViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class GioHangViewHolder extends RecyclerView.ViewHolder {


        //ham khoi tao viewholder
        public GioHangViewHolder(View itemView) {
            super(itemView);
        }
    }
}


