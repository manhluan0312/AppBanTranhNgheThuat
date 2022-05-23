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
import com.example.myapplication.Interface.IClickProductDetail;
import com.example.myapplication.Interface.IClickProductManageAdmin;
import com.example.myapplication.Model.SanPham;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPham_AdminAdapter extends RecyclerView.Adapter<SanPham_AdminAdapter.SanPhamViewtHolder> {

    Context context;
    ArrayList<SanPham> sanPhamArrayList;
    IClickProductManageAdmin iClickProductManageAdmin;


    public SanPham_AdminAdapter(Context context, ArrayList<SanPham> sanPhamArrayList, IClickProductManageAdmin iClickProductManageAdmin){
        this.context = context;
        this.sanPhamArrayList = sanPhamArrayList;
        this.iClickProductManageAdmin = iClickProductManageAdmin;
    }

    //anh xa den file item
    @Override
    public SanPhamViewtHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham_admin, parent, false);
        return new SanPham_AdminAdapter.SanPhamViewtHolder(view);
    }

    @Override
    public void onBindViewHolder(SanPham_AdminAdapter.SanPhamViewtHolder holder, int position) {


        SanPham sanPham = sanPhamArrayList.get(position);

        if (sanPham == null) {
            return;
        }

        holder.tv_ten.setText(sanPham.getName_product());

        //format gia
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        holder.tv_gia.setText("Giá :" + decimalFormat.format(sanPham.getPrice_product()) + " " + "VND");

        String anh = "http://" + Server.HOST + "image/Products/" + sanPham.getPoto_product();

        if (holder != null) {
            Glide.with(holder.itemView)
                    .load(anh)
                    .centerCrop()
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.img_anh);

        }
        holder.tv_mota.setText(sanPham.getProduct_description());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickProductManageAdmin.OnClickCatalogCatalogManageAdmin(sanPham);
            }
        });

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickProductManageAdmin.OnClickProductDetail(sanPham);
            }
        });


    }



    @Override
    public int getItemCount() {
        if (sanPhamArrayList != null) {
            return sanPhamArrayList.size();
        }
        return 0;
    }

    public class SanPhamViewtHolder extends RecyclerView.ViewHolder {

        //ham khoi tao viewholder
        ImageView img_anh;
        TextView tv_ten, tv_gia, tv_mota;
        LinearLayout linearLayout;
        ImageView imageView;

        public SanPhamViewtHolder(View itemView) {
            super(itemView);

            img_anh = itemView.findViewById(R.id.img_sanpham);
            tv_ten = itemView.findViewById(R.id.tv_name);
            tv_gia = itemView.findViewById(R.id.tv_giasp);
            tv_mota = itemView.findViewById(R.id.tv_motasp);
            linearLayout = itemView.findViewById(R.id.linner_sanpham);
            imageView = itemView.findViewById(R.id.ic_more);
        }
    }
}
