package com.example.myapplication.Fragment.Admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputLayout;

public class ChangePasswordFragment extends Fragment {

    View mView;
    TextInputLayout textInpumatkaucu, textInpumatkhaumoi, textInputnhaplaimkmoi;
    Button btn_capnhatpass;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView=inflater.inflate(R.layout.fragment_change_password, container, false);
        AnhXa();

        btn_capnhatpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confỉmInput();
            }
        });

        return mView;
    }

    private void AnhXa() {
        btn_capnhatpass = mView.findViewById(R.id.btn_capnhatpass);
        textInpumatkaucu = mView.findViewById(R.id.textinput_nhappasscu);
        textInpumatkhaumoi = mView.findViewById(R.id.textinput_nhappassmoi);
        textInputnhaplaimkmoi = mView.findViewById(R.id.textinput_nhalaippassmoi);
    }

    //ham check du lieu mat khau cu khong de dc trong

    private boolean validate_matkhaucu() {
        String mkcu = textInpumatkaucu.getEditText().getText().toString().trim();
        if (mkcu.isEmpty()) {
            textInpumatkaucu.setError("Mật khẩu cũ không để trống");
            textInpumatkaucu.setHelperTextEnabled(true);
            return false;
        } else {
            textInpumatkaucu.setError(null);
            textInpumatkaucu.setErrorEnabled(false);
            textInpumatkaucu.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu mat khau moi khong de dc trong

    private boolean validate_matkhaumoi() {
        String mkmoi = textInpumatkhaumoi.getEditText().getText().toString().trim();
        if (mkmoi.isEmpty()) {
            textInpumatkhaumoi.setError("Mật khẩu mói không để trống");
            return false;
        } else {
            textInpumatkhaumoi.setError(null);
            textInpumatkhaumoi.setErrorEnabled(false);
            textInpumatkhaumoi.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }
    //ham check du lieu nhap lai mat khau moi khong de dc trong

    private boolean validate_nhaplaimatkhaumoi() {
        String nhaplaimkmoi = textInputnhaplaimkmoi.getEditText().getText().toString().trim();
        if (nhaplaimkmoi.isEmpty()) {
            textInputnhaplaimkmoi.setError("Mật khẩu mói không để trống");
            return false;
        } else {
            textInputnhaplaimkmoi.setError(null);
            textInputnhaplaimkmoi.setErrorEnabled(false);
            textInputnhaplaimkmoi.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }
    //ham check du lieu trong khi nhan button cap nhat

    public void confỉmInput() {
        if (!validate_matkhaucu() | !validate_matkhaumoi() | !validate_nhaplaimatkhaumoi())//du lieu khong con trong
        {
            return;
        }
        ChangePassword();
    }

    private void ChangePassword() {

    }
}