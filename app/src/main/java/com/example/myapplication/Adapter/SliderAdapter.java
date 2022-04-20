package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Activity.MainActivity;

import com.example.myapplication.Model.Slider;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;

import java.util.ArrayList;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {
    private MainActivity mainActivity;
    ArrayList<Slider> SliderArrayList;

    public SliderAdapter(MainActivity mainActivity, ArrayList<Slider> sliderArrayList) {
        this.mainActivity = mainActivity;
        SliderArrayList = sliderArrayList;
    }

    //anh xa den file item

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, parent, false);
        view.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return new SliderAdapter.SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderAdapter.SliderViewHolder holder, int position) {
        Slider slider = SliderArrayList.get(position);
        if (slider == null) {
            return;
        }
        holder.tv_titile.setText(slider.getTitile_sider());

        String anh = "http://"+Server.HOST+ "image/Slider/" + slider.getImage_sider();

        Glide.with(mainActivity)
                .load(anh)
                .centerCrop()
                .error(R.drawable.ic_launcher_background)
                .into(holder.img_slider);
    }

    @Override
    public int getItemCount() {
        if (SliderArrayList != null) {
            return SliderArrayList.size();
        }
        return 0;
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder {

        TextView tv_titile;
        ImageView img_slider;

        public SliderViewHolder(View itemView) {
            super(itemView);
            tv_titile = itemView.findViewById(R.id.tv_titile);
            img_slider = itemView.findViewById(R.id.image_slider);
        }
        }
    }


