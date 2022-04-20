package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.Model.SanPham;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class XemThemSanPhamMoiNhatAdapter extends RecyclerView.Adapter<XemThemSanPhamMoiNhatAdapter.XemThemSanPhamViewMoiNhatHolder> {

    private Context context;
    ArrayList<SanPham> xemthemsanPhamMoiNhatArrayList;


    public XemThemSanPhamMoiNhatAdapter(Context context, ArrayList<SanPham> xemthemsanPhamMoiNhatArrayList) {
        this.context = context;
        this.xemthemsanPhamMoiNhatArrayList = xemthemsanPhamMoiNhatArrayList;
    }


    //anh xa den file item

    @Override
    public XemThemSanPhamViewMoiNhatHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tatcasanphammoinhat, parent, false);
        return new XemThemSanPhamViewMoiNhatHolder(view);

    }

    @Override
    public void onBindViewHolder(XemThemSanPhamViewMoiNhatHolder holder, int position) {
        SanPham sanPham = xemthemsanPhamMoiNhatArrayList.get(position);

        if (sanPham == null) {
            return;
        }

        holder.tv_ten.setText(sanPham.getName_product());

        //format gia
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        holder.tv_gia.setText("Giá :" + decimalFormat.format(sanPham.getPrice_product()) + " " + "VND");

        String anh = "http://" + Server.HOST + "image/Products/" + sanPham.getPoto_product();

        if(holder!=null)
        {
        Glide.with(holder.itemView)
                .load(anh)
                .centerCrop()
                .error(R.drawable.ic_launcher_background)
                .into(holder.img_anh);
    }
    }

    @Override
    public int getItemCount() {
        if (xemthemsanPhamMoiNhatArrayList != null) {
            return xemthemsanPhamMoiNhatArrayList.size();
        }
        return 0;
    }

    public class XemThemSanPhamViewMoiNhatHolder extends RecyclerView.ViewHolder {

        ImageView img_anh;
        TextView tv_ten, tv_gia;

        //ham khoi tao viewholder
        public XemThemSanPhamViewMoiNhatHolder(View itemView) {
            super(itemView);
            img_anh = itemView.findViewById(R.id.anhsp);
            tv_ten = itemView.findViewById(R.id.tv_name);
            tv_gia = itemView.findViewById(R.id.tv_giasp);

        }
    }
}
