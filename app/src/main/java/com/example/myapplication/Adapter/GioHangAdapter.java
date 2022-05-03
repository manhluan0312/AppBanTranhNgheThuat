package com.example.myapplication.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.Activity.Custumer.GioHangActivity;
import com.example.myapplication.Activity.Custumer.MainActivity;
import com.example.myapplication.EvenBus.TinhTongEvents;
import com.example.myapplication.Interface.IClickChangeNumberCart;
import com.example.myapplication.Model.GioHang;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.GioHangViewHolder> {

    Context context;
    ArrayList<GioHang> gioHangArrayList;

    public GioHangAdapter(Context context, ArrayList<GioHang> gioHangArrayList) {
        this.context = context;
        this.gioHangArrayList = gioHangArrayList;
    }

    @Override
    public GioHangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang, parent, false);
        return new GioHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GioHangAdapter.GioHangViewHolder holder, int position) {

        GioHang gioHang = gioHangArrayList.get(position);
        if (gioHang == null) {
            return;
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        holder.tv_tensp.setText(gioHang.getTensanpham());
        holder.tv_giasp.setText(decimalFormat.format(gioHang.getGiasanpham()) + " " + "VND");
        holder.tv_slsp.setText(gioHang.getSoluongsanpham() + "");

        String anh = "http://" + Server.HOST + "image/Products/" + gioHang.getHinhanhsanpham();

        if (holder != null) {
            Glide.with(holder.itemView)
                    .load(anh)
                    //.apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(12)))//bo goc anh
                    .centerCrop()
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.img_anhsp);


            holder.setiClickChangeNumberCart(new IClickChangeNumberCart() {
                @Override
                public void iclick(View view, int pos, int giatri) {
                    if (giatri == 1)//thuc hien cong sp
                    {
                        int soluongmoi = gioHangArrayList.get(pos).getSoluongsanpham() + 1;///cong sl
                        gioHangArrayList.get(pos).setSoluongsanpham(soluongmoi);//set gia tri

                    } else if (giatri == 2)//tru sp
                    {

                        int soluongmoi = gioHangArrayList.get(pos).getSoluongsanpham() - 1;///tru sl
                        gioHangArrayList.get(pos).setSoluongsanpham(soluongmoi);//set gia tri


                    }
                    if (giatri == 3)//xoa sp trong gio hang
                    {

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getRootView().getContext());
                        alertDialog.setTitle("Thông báo");
                        alertDialog.setMessage(" Bạn có muốn xóa sản phẩm " + MainActivity.gioHangArrayList.get(pos).getTensanpham() + " này không ?");
                        alertDialog.setCancelable(false);

                        alertDialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MainActivity.gioHangArrayList.remove(pos);
                                notifyDataSetChanged();
                                EventBus.getDefault().postSticky(new TinhTongEvents());//tinh lai tong tien

                            }
                        });

                        alertDialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        alertDialog.show();
                    }

                    holder.tv_slsp.setText(gioHangArrayList.get(pos).getSoluongsanpham() + " ");

                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    holder.tv_giasp.setText(decimalFormat.format(gioHangArrayList.get(pos).getSoluongsanpham() * gioHang.getGiasanpham()) + " " + "VND");

                    int sl = gioHangArrayList.get(pos).getSoluongsanpham();

                    if (sl <= 1) {
                        holder.btn_trusp.setVisibility(View.INVISIBLE);//an nut tru
                    } else {
                        holder.btn_trusp.setVisibility(View.VISIBLE);
                        holder.btn_congsp.setVisibility(View.VISIBLE);
                    }
                    EventBus.getDefault().postSticky(new TinhTongEvents());//tinh lai tong tien
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (gioHangArrayList != null) {
            return gioHangArrayList.size();
        }
        return 0;
    }

    public class GioHangViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView tv_tensp, tv_giasp, tv_slsp;
        ImageView img_anhsp, img_xoasp;
        Button btn_trusp, btn_congsp;
        IClickChangeNumberCart iClickChangeNumberCart;


        //ham khoi tao viewholder
        public GioHangViewHolder(View itemView) {
            super(itemView);
            tv_tensp = itemView.findViewById(R.id.tv_tensp_giohang);
            tv_giasp = itemView.findViewById(R.id.tv_giasp_giohang);
            tv_slsp = itemView.findViewById(R.id.tv_soluongsp_giohang);
            img_anhsp = itemView.findViewById(R.id.anhsp_giohang);
            btn_trusp = itemView.findViewById(R.id.btn_truspgiohang);
            btn_congsp = itemView.findViewById(R.id.btn_congspgiohang);
            img_xoasp = itemView.findViewById(R.id.img_xoagiohang);

            btn_congsp.setOnClickListener(this);
            btn_trusp.setOnClickListener(this);
            img_xoasp.setOnClickListener(this);
        }

        public void setiClickChangeNumberCart(IClickChangeNumberCart iClickChangeNumberCart) {
            this.iClickChangeNumberCart = iClickChangeNumberCart;
        }

        @Override
        public void onClick(View view) {
            if (view == btn_congsp) {
                iClickChangeNumberCart.iclick(view, getBindingAdapterPosition(), 1);
            }
            if (view == btn_trusp) {
                iClickChangeNumberCart.iclick(view, getBindingAdapterPosition(), 2);
            }
            if (view == img_xoasp) {
                iClickChangeNumberCart.iclick(view, getBindingAdapterPosition(), 3);
            }
        }
    }
}


