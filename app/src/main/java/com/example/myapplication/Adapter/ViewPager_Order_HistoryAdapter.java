package com.example.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.Fragment.Custumer.AllOrderFragment;
import com.example.myapplication.Fragment.Custumer.ProcessedFragment;
import com.example.myapplication.Fragment.Custumer.Processing_OrderFragment;
import com.example.myapplication.Fragment.Custumer.WaitingForProgressing_OrderFragment;

public class ViewPager_Order_HistoryAdapter extends FragmentStateAdapter {


    public ViewPager_Order_HistoryAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new WaitingForProgressing_OrderFragment();
            case 2:
                return new Processing_OrderFragment();
            case 3:
                return new ProcessedFragment();
            default:
                return new AllOrderFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
