package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.admin.AdminActivity;
import com.example.myapplication.Model.StatisticsCatalog;
import com.example.myapplication.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ViewStatisticsCatalogAdapter extends RecyclerView.Adapter<ViewStatisticsCatalogAdapter.ViewStatisticsCatalogViewHolder> {

    ArrayList<StatisticsCatalog> statisticsCatalogArrayList;
    AdminActivity adminActivity;

    public ViewStatisticsCatalogAdapter(ArrayList<StatisticsCatalog> statisticsCatalogArrayList, AdminActivity adminActivity) {
        this.statisticsCatalogArrayList = statisticsCatalogArrayList;
        this.adminActivity = adminActivity;
    }

    @Override
    public ViewStatisticsCatalogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_get_view_statistics_catalog, parent, false);
        return new ViewStatisticsCatalogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewStatisticsCatalogAdapter.ViewStatisticsCatalogViewHolder holder, int position) {
        StatisticsCatalog statisticsCatalog = statisticsCatalogArrayList.get(position);
        if (statisticsCatalog == null) {
            return;
        }

        //format gia
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        holder.tv_tendanhmuc.setText(statisticsCatalog.getTendanhmuc());
        holder.tv_soluong.setText(statisticsCatalog.getSoluongdanhmuc() + "");
        holder.tv_giacaonhat.setText(decimalFormat.format(statisticsCatalog.getGiacaonhat()) + " " + "VND");
        holder.tv_giathapnhat.setText(decimalFormat.format(statisticsCatalog.getGiathapnhat()) + " " + "VND");
        holder.tv_giatb.setText(decimalFormat.format(statisticsCatalog.getGiatrungbinh()) + " " + "VND");
    }

    @Override
    public int getItemCount() {
        if (statisticsCatalogArrayList != null) {
            return statisticsCatalogArrayList.size();
        }
        return 0;
    }

    public class ViewStatisticsCatalogViewHolder extends RecyclerView.ViewHolder {

        TextView tv_tendanhmuc, tv_soluong, tv_giacaonhat, tv_giathapnhat, tv_giatb;

        public ViewStatisticsCatalogViewHolder(View itemView) {
            super(itemView);
            tv_tendanhmuc = itemView.findViewById(R.id.tv_tendanhmuc);
            tv_soluong = itemView.findViewById(R.id.tv_soluong);
            tv_giacaonhat = itemView.findViewById(R.id.tv_giacaonhat);
            tv_giathapnhat = itemView.findViewById(R.id.tv_giathapnhat);
            tv_giatb = itemView.findViewById(R.id.tv_giatrung_binh);
        }
    }
}
