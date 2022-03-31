package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.Activity.admin.AdminActivity;
import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout textInputLayout_username, textInputLayoutpass;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confỉmInput();
            }
        });

    }

    private void AnhXa() {
        textInputLayout_username = findViewById(R.id.textinput_username);
        textInputLayoutpass = findViewById(R.id.textinput_password);
        btn_login = findViewById(R.id.btn_dangnhap);
    }

    //ham check du lieu username khong de dc trong

    private boolean validate_username() {
        String username = textInputLayout_username.getEditText().getText().toString().trim();
        if (username.isEmpty()) {
            textInputLayout_username.setError("Username không để trống");
            textInputLayout_username.setHelperTextEnabled(true);
            return false;
        } else {
            textInputLayout_username.setError(null);
            textInputLayout_username.setErrorEnabled(false);
            textInputLayout_username.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu mat khau  khong de dc trong

    private boolean validate_password() {
        String password = textInputLayoutpass.getEditText().getText().toString().trim();
        if (password.isEmpty()) {
            textInputLayoutpass.setError("Mật khẩu không để trống");
            return false;
        } else {
            textInputLayoutpass.setError(null);
            textInputLayoutpass.setErrorEnabled(false);
            textInputLayoutpass.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    public void confỉmInput() {
        if (!validate_username() | !validate_password())//du lieu khong con trong
        {
           return;
        }
        Login();
    }

    private void Login() {

    }
}
