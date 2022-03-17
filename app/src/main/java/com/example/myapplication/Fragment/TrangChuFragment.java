package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.R;


public class TrangChuFragment extends Fragment {

    private View mView;
    MainActivity mainActivity;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        AnhXa();
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        return  mView;
    }

    private void AnhXa() {
        toolbar=mView.findViewById(R.id.toobar);
        toolbar = mView.findViewById(R.id.toobar);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//Add this sentence to the menu
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toobar,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}