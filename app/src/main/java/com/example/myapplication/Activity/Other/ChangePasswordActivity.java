package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputLayout;

public class ChangePasswordActivity extends AppCompatActivity {

    TextInputLayout textInpumatkaucu, textInpumatkhaumoi, textInputnhaplaimkmoi;
    Button btn_capnhatpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        AnhXa();

        btn_capnhatpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confỉmInput();
            }
        });
    }

    private void AnhXa() {
        btn_capnhatpass = findViewById(R.id.btn_capnhatpass);
        textInpumatkaucu = findViewById(R.id.textinput_nhappasscu);
        textInpumatkhaumoi = findViewById(R.id.textinput_nhappassmoi);
        textInputnhaplaimkmoi = findViewById(R.id.textinput_nhalaippassmoi);
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
