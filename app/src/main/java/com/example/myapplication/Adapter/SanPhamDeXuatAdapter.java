package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.Interface.IClickProductDetail;
import com.example.myapplication.Model.SanPham;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamDeXuatAdapter extends RecyclerView.Adapter<SanPhamDeXuatAdapter.SanPhamViewDeXuatHolder> {

    private MainActivity mainActivity;
    ArrayList<SanPham> sanPhamDeXuatArrayList;
    IClickProductDetail iClickProductDetail;


    public SanPhamDeXuatAdapter(MainActivity mainActivity, ArrayList<SanPham> sanPhamDeXuatArrayList, IClickProductDetail iClickProductDetail) {
        this.mainActivity = mainActivity;
        this.sanPhamDeXuatArrayList = sanPhamDeXuatArrayList;
        this.iClickProductDetail = iClickProductDetail;
    }

    //anh xa den file item

    @Override
    public SanPhamViewDeXuatHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanphamnoibat, parent, false);
        return new SanPhamViewDeXuatHolder(view);
    }

    @Override
    public void onBindViewHolder(SanPhamViewDeXuatHolder holder, int position) {
        SanPham sanPham = sanPhamDeXuatArrayList.get(position);

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
        if (sanPhamDeXuatArrayList != null) {
            return sanPhamDeXuatArrayList.size();
        }
        return 0;
    }

    public class SanPhamViewDeXuatHolder extends RecyclerView.ViewHolder {

        ImageView img_anh;
        TextView tv_ten, tv_gia;
        LinearLayout linearLayout;

        //ham khoi tao viewholder
        public SanPhamViewDeXuatHolder(View itemView) {
            super(itemView);
            img_anh = itemView.findViewById(R.id.anhsp);
            tv_ten = itemView.findViewById(R.id.tv_name);
            tv_gia = itemView.findViewById(R.id.tv_giasp);
            linearLayout = itemView.findViewById(R.id.linner_sanpham);
        }
    }
}
