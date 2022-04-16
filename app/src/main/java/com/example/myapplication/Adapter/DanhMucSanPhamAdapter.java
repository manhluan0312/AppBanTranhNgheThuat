package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.Activity.admin.AdminActivity;
import com.example.myapplication.Model.DanhMucSanPham;
import com.example.myapplication.R;

import java.util.ArrayList;


public class DanhMucSanPhamAdapter extends RecyclerView.Adapter<DanhMucSanPhamAdapter.DanhMucSanPhamViewHolder> {

   private MainActivity mainActivity;
   ArrayList<DanhMucSanPham> DanhmucSanPhamArrayList;

    public DanhMucSanPhamAdapter(MainActivity mainActivity, ArrayList<DanhMucSanPham> mucSanPhamArrayList) {
        this.mainActivity = mainActivity;
        this.DanhmucSanPhamArrayList = mucSanPhamArrayList;
    }

//anh xa den file item
    @Override
    public DanhMucSanPhamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dmsp, parent, false);
        return new DanhMucSanPhamViewHolder(view);
    }

    //ham set du lieu
    @Override
    public void onBindViewHolder(DanhMucSanPhamAdapter.DanhMucSanPhamViewHolder holder, int position) {
        DanhMucSanPham danhMucSanPham = DanhmucSanPhamArrayList.get(position);
        if (danhMucSanPham == null) {
            return;
        }
        holder.btn_tendm.setText(danhMucSanPham.getTendanhmuc());
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

    