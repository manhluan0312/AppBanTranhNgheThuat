package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.Custumer.SanPhamActivity;
import com.example.myapplication.Model.DanhMucSanPham;
import com.example.myapplication.R;

import java.util.ArrayList;


public class DanhMucSanPham_TimKiem_CustumerAdapter extends RecyclerView.Adapter<DanhMucSanPham_TimKiem_CustumerAdapter.DanhMucSanPhamViewHolder> {

   private Context context ;
   ArrayList<DanhMucSanPham> DanhmucSanPhamArrayList;

    public DanhMucSanPham_TimKiem_CustumerAdapter(Context context, ArrayList<DanhMucSanPham> danhmucSanPhamArrayList) {
        this.context = context;
        DanhmucSanPhamArrayList = danhmucSanPhamArrayList;
    }

    //anh xa den file item
    @Override
    public DanhMucSanPhamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dmsp, parent, false);
        return new DanhMucSanPhamViewHolder(view);
    }

    //ham set du lieu
    @Override
    public void onBindViewHolder(DanhMucSanPham_TimKiem_CustumerAdapter.DanhMucSanPhamViewHolder holder, int position) {
        DanhMucSanPham danhMucSanPham = DanhmucSanPhamArrayList.get(position);
        if (danhMucSanPham == null) {
            return;
        }
        holder.btn_tendm.setText(danhMucSanPham.getTendanhmuc());

        holder.btn_tendm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(view.getContext(),SanPhamActivity.class);
                intent.putExtra("danhmuctheosanpham",danhMucSanPham.getIddm());
                intent.putExtra("tendanhmuc",danhMucSanPham.getTendanhmuc());
                view.getContext().startActivity(intent);
            }
        });
     }

    @Override
    public int getItemCount() {
        if (DanhmucSanPhamArrayList != null) {
            return DanhmucSanPhamArrayList.size();
        }
        return 0;
    }

    public class DanhMucSanPhamViewHolder extends RecyclerView.ViewHolder {

         Button btn_tendm;
        //ham khoi tao viewholder
        public DanhMucSanPhamViewHolder(View itemView) {
            super(itemView);
            btn_tendm=itemView.findViewById(R.id.btn_tendm);

        }
    }
}

    