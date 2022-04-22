package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.admin.AdminActivity;
import com.example.myapplication.Interface.IClickCatalogManageAdmin;
import com.example.myapplication.Model.DanhMucSanPham;
import com.example.myapplication.R;

import java.util.ArrayList;


public class DanhMucSanPham_AdminAdapter extends RecyclerView.Adapter<DanhMucSanPham_AdminAdapter.DanhMucSanPhamViewHolderAdmin> {

    private AdminActivity adminActivity;
    ArrayList<DanhMucSanPham> DanhmucSanPhamAdminArrayList;
    IClickCatalogManageAdmin iClickCatalogManageAdmin;

    public DanhMucSanPham_AdminAdapter(AdminActivity adminActivity, ArrayList<DanhMucSanPham> DanhmucSanPhamAdminArrayList,
                                       IClickCatalogManageAdmin iClickCatalogManageAdmin) {
        this.adminActivity = adminActivity;
        this.DanhmucSanPhamAdminArrayList = DanhmucSanPhamAdminArrayList;
        this.iClickCatalogManageAdmin = iClickCatalogManageAdmin;
    }

    //anh xa den file item
    @Override
    public DanhMucSanPhamViewHolderAdmin onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dmsp_admin, parent, false);
        return new DanhMucSanPhamViewHolderAdmin(view);
    }

    //ham set du lieu
    @Override
    public void onBindViewHolder(DanhMucSanPhamViewHolderAdmin holder, int position) {
        DanhMucSanPham danhMucSanPham = DanhmucSanPhamAdminArrayList.get(position);
        if (danhMucSanPham == null) {
            return;
        }
        holder.btn_tendm.setText(danhMucSanPham.getTendanhmuc());

        holder.img_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickCatalogManageAdmin.OnClickCatalogCatalogManageAdmin(danhMucSanPham);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (DanhmucSanPhamAdminArrayList != null) {
            return DanhmucSanPhamAdminArrayList.size();
        }
        return 0;
    }

    public class DanhMucSanPhamViewHolderAdmin extends RecyclerView.ViewHolder {

        Button btn_tendm;
        ImageView img_more;

        //ham khoi tao viewholder

        public DanhMucSanPhamViewHolderAdmin(View itemView) {
            super(itemView);
            btn_tendm = itemView.findViewById(R.id.btn_tendm);
            img_more = itemView.findViewById(R.id.img_dm);
        }

    }
}

    