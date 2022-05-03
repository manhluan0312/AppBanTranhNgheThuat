package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Activity.Custumer.MainActivity;
import com.example.myapplication.Interface.IClickProductDetail;
import com.example.myapplication.Model.SanPham;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamMoiNhatAdapter extends RecyclerView.Adapter<SanPhamMoiNhatAdapter.SanPhamViewMoiNhatHolder> {

    private MainActivity mainActivity;
    ArrayList<SanPham> sanPhamMoiNhatArrayList;
    IClickProductDetail iClickProductDetail;

    public SanPhamMoiNhatAdapter(MainActivity mainActivity, ArrayList<SanPham> sanPhamMoiNhatArrayList, IClickProductDetail iClickProductDetail) {
        this.mainActivity = mainActivity;
        this.sanPhamMoiNhatArrayList = sanPhamMoiNhatArrayList;
        this.iClickProductDetail = iClickProductDetail;
    }


    //anh xa den file item

    @Override
    public SanPhamViewMoiNhatHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanphammoinhat, parent, false);
        return new SanPhamMoiNhatAdapter.SanPhamViewMoiNhatHolder(view);

    }

    @Override
    public void onBindViewHolder(SanPhamMoiNhatAdapter.SanPhamViewMoiNhatHolder holder, int position) {
        SanPham sanPham = sanPhamMoiNhatArrayList.get(position);

        if (sanPham == null) {
            return;
        }

        holder.tv_ten.setText(sanPham.getName_product());

        //format gia
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        holder.tv_gia.setText("Giá :" + decimalFormat.format(sanPham.getPrice_product()) + " " + "VND");

        String anh = "http://" + Server.HOST + "image/Products/" + sanPham.getPoto_product();

        Glide.with(mainActivity)
                .load(anh)
                .centerCrop()
                .error(R.drawable.ic_launcher_background)
                .into(holder.img_anh);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickProductDetail.OnClickProductDetail(sanPham);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (sanPhamMoiNhatArrayList != null) {
            return sanPhamMoiNhatArrayList.size();
        }
        return 0;
    }

    public class SanPhamViewMoiNhatHolder extends RecyclerView.ViewHolder {

        ImageView img_anh;
        TextView tv_ten, tv_gia;
        LinearLayout linearLayout;

        //ham khoi tao viewholder
        public SanPhamViewMoiNhatHolder(View itemView) {
            super(itemView);
            img_anh = itemView.findViewById(R.id.anhsp);
            tv_ten = itemView.findViewById(R.id.tv_name);
            tv_gia = itemView.findViewById(R.id.tv_giasp);
            linearLayout = itemView.findViewById(R.id.linner_sanpham);

        }
    }
}
