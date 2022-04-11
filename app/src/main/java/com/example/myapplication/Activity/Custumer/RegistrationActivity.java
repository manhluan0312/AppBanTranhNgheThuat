package com.example.myapplication.Activity.Custumer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputLayout;

public class RegistrationActivity extends AppCompatActivity {


    TextInputLayout textInputUsername, textInputHoten,textInputpass, textInputconfimpass;
    Button btn_dangky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        AnhXa();

        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confỉmInput();
            }
        });
    }

    private void AnhXa() {
        textInputUsername = findViewById(R.id.textinput_username);
        textInputHoten=findViewById(R.id.textinput_tenhienthi);
        textInputpass = findViewById(R.id.textinput_password);
        textInputconfimpass = findViewById(R.id.textinput_nhaplai_password);
        btn_dangky = findViewById(R.id.btn_dangky);
    }


    //ham check du lieu tenhienthi khong de dc trong

    private boolean validateTenHienThi() {
        String ten = textInputHoten.getEditText().getText().toString().trim();
        if (ten.isEmpty()) {
            textInputHoten.setError("Tên hiển thị  không để trống");
            return false;
        } else {
            textInputHoten.setError(null);
            textInputHoten.setErrorEnabled(false);
            textInputHoten.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu username khong de dc trong

    private boolean validateUsername() {
        String username = textInputUsername.getEditText().getText().toString().trim();
        if (username.isEmpty()) {
            textInputUsername.setError("Username không để trống");
            return false;
        } else {
            textInputUsername.setError(null);
            textInputUsername.setErrorEnabled(false);
            textInputUsername.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu mat khau  khong de dc trong

    private boolean validate_password() {
        String password = textInputpass.getEditText().getText().toString().trim();
        if (password.isEmpty()) {
            textInputpass.setError("Mật khẩu không để trống");
            return false;
        } else {
            textInputpass.setError(null);
            textInputpass.setErrorEnabled(false);
            textInputpass.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu nhap lai  khong de dc trong

    private boolean validate_confim_password() {
        String password = textInputconfimpass.getEditText().getText().toString().trim();
        if (password.isEmpty()) {
            textInputconfimpass.setError("Mật khẩu không để trống");
            return false;
        } else {
            textInputconfimpass.setError(null);
            textInputconfimpass.setErrorEnabled(false);
            textInputconfimpass.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    private void confỉmInput() {
        if (!validateUsername() | !validate_password() |!validateTenHienThi() | !validate_confim_password())//du lieu khong con trong
        {
            return;
        }
        Registration();
    }

    private void Registration() {

    }
}



