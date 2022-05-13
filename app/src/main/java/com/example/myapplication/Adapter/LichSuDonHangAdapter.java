package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.Other.ChiTietDonHangActivity;
import com.example.myapplication.Model.LichSuDonHang;
import com.example.myapplication.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LichSuDonHangAdapter extends RecyclerView.Adapter<LichSuDonHangAdapter.AllLichSuDonHangViewHolder> {

    Context context;

    ArrayList<LichSuDonHang> lichSuDonHangArrayList;

    public LichSuDonHangAdapter(Context context ,ArrayList<LichSuDonHang> lichSuDonHangArrayList) {
        this.context=context;
        this.lichSuDonHangArrayList = lichSuDonHangArrayList;
    }

    public AllLichSuDonHangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_order_custumer, parent, false);
        return new AllLichSuDonHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LichSuDonHangAdapter.AllLichSuDonHangViewHolder holder, int position) {

        LichSuDonHang lichSuDonHang = lichSuDonHangArrayList.get(position);

        holder.tv_madonhang_all_order.setText("Mã đơn hàng :" + lichSuDonHang.getMadonhang() + " ");
        holder.tv_ngaydat.setText("Ngày đặt hàng :" + lichSuDonHang.getNgayDatHang() + " ");
        holder.tv_hthanhtoan.setText("Hình thức thanh toán :" + lichSuDonHang.getHinhthucThanhToan());
        holder.tv_diachigiaohang.setText("Địa chỉ giao hàng :" + lichSuDonHang.getDiaChiGiaoHang());

        //format gia
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        holder.tv_tongtien.setText("Tổng tiền :" + decimalFormat.format(lichSuDonHang.getTongTien()) + " " + "VND");
        holder.tv_trangthai.setText(lichSuDonHang.getTrangThaiDonHang());

        holder.linner_lichsudonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietDonHangActivity.class);
                intent.putExtra("madonhang", lichSuDonHangArrayList.get(position).getMadonhang());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lichSuDonHangArrayList.size();
    }

    public class AllLichSuDonHangViewHolder extends RecyclerView.ViewHolder {


        TextView tv_ngaydat, tv_hthanhtoan, tv_diachigiaohang, tv_tongtien, tv_trangthai, tv_madonhang_all_order;
        LinearLayout linner_lichsudonhang;

        public AllLichSuDonHangViewHolder(View itemView) {

            super(itemView);
            tv_ngaydat = itemView.findViewById(R.id.tv_ngaydathang_all_order);
            tv_hthanhtoan = itemView.findViewById(R.id.tv_hinhthucthanhtoan_all_order);
            tv_diachigiaohang = itemView.findViewById(R.id.tv_diachigiaohang_all_order);
            tv_tongtien = itemView.findViewById(R.id.tv_tongtien_all_order);
            tv_trangthai = itemView.findViewById(R.id.tv_trangthaidonhang_all_order);
            tv_madonhang_all_order = itemView.findViewById(R.id.tv_madonhang_all_order);
            linner_lichsudonhang = itemView.findViewById(R.id.linner_lichsudonhang);
        }
    }
}
