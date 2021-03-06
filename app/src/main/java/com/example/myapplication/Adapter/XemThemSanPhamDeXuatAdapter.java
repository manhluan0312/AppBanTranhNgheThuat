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
import com.example.myapplication.Activity.Custumer.SeeMoreProposeProcductsActivity;
import com.example.myapplication.Interface.IClickProductDetail;
import com.example.myapplication.Model.SanPham;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class XemThemSanPhamDeXuatAdapter extends RecyclerView.Adapter<XemThemSanPhamDeXuatAdapter.XemThemSanPhamViewDeXuatHolder> {

    Context context;
    ArrayList<SanPham> xemthemsanPhamDeXuatArrayList;
    IClickProductDetail iClickProductDetail;

    public XemThemSanPhamDeXuatAdapter(Context context, ArrayList<SanPham> xemthemsanPhamDeXuatArrayList, IClickProductDetail iClickProductDetail) {
        this.context = context;
        this.xemthemsanPhamDeXuatArrayList = xemthemsanPhamDeXuatArrayList;
        this.iClickProductDetail = iClickProductDetail;
    }
//anh xa den file item

    @Override
    public XemThemSanPhamViewDeXuatHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tatcasanphamnoibat, parent, false);
        return new XemThemSanPhamViewDeXuatHolder(view);
    }

    @Override
    public void onBindViewHolder(XemThemSanPhamViewDeXuatHolder holder, int position) {
        SanPham sanPham = xemthemsanPhamDeXuatArrayList.get(position);

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


            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iClickProductDetail.OnClickProductDetail(sanPham);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (xemthemsanPhamDeXuatArrayList != null) {
            return xemthemsanPhamDeXuatArrayList.size();
        }
        return 0;
    }

    public class XemThemSanPhamViewDeXuatHolder extends RecyclerView.ViewHolder {

        ImageView img_anh;
        TextView tv_ten, tv_gia;
        LinearLayout linearLayout;

        //ham khoi tao viewholder
        public XemThemSanPhamViewDeXuatHolder(View itemView) {
            super(itemView);
            img_anh = itemView.findViewById(R.id.anhsp);
            tv_ten = itemView.findViewById(R.id.tv_name);
            tv_gia = itemView.findViewById(R.id.tv_giasp);
            linearLayout = itemView.findViewById(R.id.linner_sanpham);
        }
    }
}
