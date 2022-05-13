package com.example.myapplication.Adapter;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.Other.ChiTietDonHangActivity;
import com.example.myapplication.Activity.admin.AdminActivity;
import com.example.myapplication.Model.QuanLyDonHang;
import com.example.myapplication.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class QuanLyDonHangAdapter extends  RecyclerView.Adapter<QuanLyDonHangAdapter.QuanLyDonHangViewHolder> {

   ArrayList<QuanLyDonHang> quanLyDonHangArrayList;
   AdminActivity adminActivity;

    public QuanLyDonHangAdapter(ArrayList<QuanLyDonHang> quanLyDonHangArrayList, AdminActivity adminActivity) {
        this.quanLyDonHangArrayList = quanLyDonHangArrayList;
        this.adminActivity = adminActivity;
    }

    @Override
    public QuanLyDonHangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mange_order_admin, parent, false);
        return new QuanLyDonHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuanLyDonHangAdapter.QuanLyDonHangViewHolder holder, int position) {
        QuanLyDonHang quanLyDonHang =quanLyDonHangArrayList.get(position);

        holder.madonhang.setText("Mã đơn hàng : " +quanLyDonHang.getMadonhang()+" ");
        holder.tenkhachhang.setText("Tên Khách hàng : "+quanLyDonHang.getTenkhachhang());
        holder.sodienthoai.setText("Số điện thoại : "+quanLyDonHang.getSodienthoai()+"");
        holder.hinhthucthanhtoan.setText("Hình thức thanh toán : "+quanLyDonHang.getHinhthucthanhtoan());

        holder.ngaydathang.setText("Ngày đặt hàng :" +quanLyDonHang.getNgaydathang()+"");
        holder.diachigiaohang.setText("Địa chỉ giao hàng : " +quanLyDonHang.getDiachigiaohang());

        String ghichu=quanLyDonHang.getGhichu();

        if(ghichu.equals(null)){
            ghichu.equals("Không có ghi chú");
        }else {
            holder.ghichu.setText("Ghi chú: " + ghichu);
        }

        String trangthaidonhang=quanLyDonHang.getTrangthaidonhang();

        holder.trangthaidonhang.setText(trangthaidonhang);

        if(trangthaidonhang.equals("Đang chờ xử lý")){
            holder.trangthaidonhang.setBackgroundColor(Color.red(R.color.red));
        } if((trangthaidonhang.equals("Đang xử lý"))){
            holder.trangthaidonhang.setBackgroundColor(Color.blue(R.color.purple_200));
        }if((trangthaidonhang.equals("Đã xử lý"))){
            holder.trangthaidonhang.setBackgroundColor(Color.green(R.color.teal_700));
        }

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tongtien.setText("Tổng tiền :"+decimalFormat.format(quanLyDonHang.getTongtien())+" "+"VNĐ");


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(adminActivity, ChiTietDonHangActivity.class);
                intent.putExtra("madonhang",quanLyDonHangArrayList.get(position).getMadonhang());
                adminActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quanLyDonHangArrayList.size();
    }

    public  class QuanLyDonHangViewHolder extends RecyclerView.ViewHolder {

        TextView madonhang,tenkhachhang,sodienthoai,
                hinhthucthanhtoan,ngaydathang,diachigiaohang,ghichu,trangthaidonhang,tongtien;

        LinearLayout linearLayout;

        public QuanLyDonHangViewHolder(View itemView) {
            super(itemView);
            madonhang=itemView.findViewById(R.id.tv_madonhang_mange_order);
            tenkhachhang=itemView.findViewById(R.id.tv_tenkhachhang_mange_order);
            sodienthoai=itemView.findViewById(R.id.tv_sdtkh_mange_order);
            hinhthucthanhtoan=itemView.findViewById(R.id.tv_hinhthucthanhtoan_mange_order);
            ngaydathang=itemView.findViewById(R.id.tv_ngaydathang_mange_order);
            diachigiaohang=itemView.findViewById(R.id.tv_diachigiaohang_mange_order);
            ghichu=itemView.findViewById(R.id.tv_ghichu_mange_order);
            trangthaidonhang=itemView.findViewById(R.id.tv_trangthaidonhang_mange_admin_order);
            tongtien=itemView.findViewById(R.id.tv_tongtien_mange_order);

            linearLayout=itemView.findViewById(R.id.itemdonhang);
        }
    }
}
