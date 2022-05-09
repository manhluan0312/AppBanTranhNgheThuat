package com.example.myapplication.Fragment.Custumer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Activity.Custumer.MainActivity;
import com.example.myapplication.Adapter.DonHangChoXuLyAdapter;
import com.example.myapplication.Adapter.DonHangDaXuLyAdapter;
import com.example.myapplication.Model.LichSuDonHang;
import com.example.myapplication.Model.LichsuDonHangChiTiet;
import com.example.myapplication.R;

import java.util.ArrayList;

public class WaitingForProgressing_OrderFragment extends Fragment {

    MainActivity mainActivity;
    View view;
    DonHangChoXuLyAdapter donHangChoXuLyAdapter;
    ArrayList<LichSuDonHang> lichSuDonHangArrayList;
    ArrayList<LichsuDonHangChiTiet> lichsuDonHangChiTietArrayList;
    RecyclerView rcv_donhang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_waiting_for_progressing__order, container, false);
        AnhXa();
        return view;
    }

    private void AnhXa() {
        rcv_donhang=view.findViewById(R.id.watting_order_prop_custumer);
    }
}