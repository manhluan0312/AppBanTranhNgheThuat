package com.example.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.Fragment.DanhMucSanPhamFragment;
import com.example.myapplication.Fragment.TaiKhoanFragment;
import com.example.myapplication.Fragment.TrangChuFragment;

public class ViewPagerMainAdapter extends FragmentStateAdapter {

    public ViewPagerMainAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new DanhMucSanPhamFragment();
            case 2:
                return new TaiKhoanFragment();
            case 0:
            default:
                return new TrangChuFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;//tra ve 3 gia tri trong menu bottom
    }
}
