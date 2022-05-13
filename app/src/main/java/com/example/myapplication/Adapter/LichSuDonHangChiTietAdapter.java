package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Model.LichsuDonHangChiTiet;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LichSuDonHangChiTietAdapter extends RecyclerView.Adapter<LichSuDonHangChiTietAdapter.ChiTietViewHolder>{

ArrayList<LichsuDonHangChiTiet> lichsuDonHangChiTietArrayList;

    public LichSuDonHangChiTietAdapter(ArrayList<LichsuDonHangChiTiet> lichsuDonHangChiTietArrayList) {
        this.lichsuDonHangChiTietArrayList = lichsuDonHangChiTietArrayList;
    }

    @Override
    public ChiTietViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_order_custumer_detail, parent, false);
        return new ChiTietViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LichSuDonHangChiTietAdapter.ChiTietViewHolder holder, int position) {
        LichsuDonHangChiTiet lichsuDonHangChiTiet = lichsuDonHangChiTietArrayList.get(position);

        holder.tv_ten.setText(lichsuDonHangChiTiet.getTensanpham());

        //format gia
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        holder.tv_gia.setText("Giá :" +decimalFormat.format(lichsuDonHangChiTiet.getGiasanpham()) + "");
        holder.tv_sl.setText(lichsuDonHangChiTiet.getSoluong() + "");

        String anh = "http://" + Server.HOST + "image/Products/" + lichsuDonHangChiTiet.getHinhanhsanpham();

        if (holder != null) {
            Glide.with(holder.itemView)
                    .load(anh)
                    .centerCrop()
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.anh);

        }
    }

    @Override
    public int getItemCount() {
        return lichsuDonHangChiTietArrayList.size();
    }

    public  class ChiTietViewHolder extends RecyclerView.ViewHolder {

      ImageView anh;
      TextView tv_ten,tv_sl,tv_gia;

        public ChiTietViewHolder(View itemView) {
            super(itemView);

            anh=itemView.findViewById(R.id.anhsp_chitiet);
            tv_ten=itemView.findViewById(R.id.tv_tenspchitiet);
            tv_sl=itemView.findViewById(R.id.tv_soluongchitiet);
            tv_gia=itemView.findViewById(R.id.tv_giachitiet);
        }
    }
}
