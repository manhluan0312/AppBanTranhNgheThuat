package com.example.myapplication.Activity.Custumer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputLayout;

public class UpdateInfoProfileActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView mTitle;
    TextInputLayout textInpuEmail,textInpuhoten,textInpusodienthoai,textInpudiachi;
    AppCompatButton btn_chinhsua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_info);
        AnhXa();
        setToolbar();

        btn_chinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confỉmInput();
            }
        });
    }
    private void AnhXa() {
        toolbar = findViewById(R.id.toobar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textInpuEmail=findViewById(R.id.textinput_email);
        textInpuhoten=findViewById(R.id.textinput_hoten);
        textInpusodienthoai=findViewById(R.id.textinput_sdt);
        textInpudiachi=findViewById(R.id.textinput_diachi);
        btn_chinhsua=findViewById(R.id.btn_chinhsua);
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTitle.setText("Thông tin cá nhân");
        getSupportActionBar().setDisplayShowTitleEnabled(false);//khong hien thi titile ma cdinh cua toorbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //ham check du lieu email khong de dc trong

    private  boolean validateEmail(){
        String email=textInpuEmail.getEditText().getText().toString().trim();
        if(email.isEmpty()){
            textInpuEmail.setError("Email không để trống");
            return false;
        }else {
            textInpuEmail.setError(null);
            textInpuEmail.setErrorEnabled(false);
            return true;
        }
    }

    //ham check du lieu hoten khong de dc trong

    private  boolean validateHoten(){
        String hoten=textInpuhoten.getEditText().getText().toString().trim();
        if(hoten.isEmpty()){
            textInpuhoten.setError("Họ tên không để trống");
            return false;
        }else {
            textInpuhoten.setError(null);
            textInpuhoten.setErrorEnabled(false);
            return true;
        }
    }

    //ham check du lieu sdt khong de dc trong

    private  boolean validateSdt(){
        String sdt=textInpusodienthoai.getEditText().getText().toString().trim();
        if(sdt.isEmpty()){
            textInpusodienthoai.setError("Số điện thoại không để trống");
            return false;
        }else {
            textInpusodienthoai.setError(null);
            textInpusodienthoai.setErrorEnabled(false);
            return true;
        }
    }

    //ham check du lieu dia chi khong de dc trong

    private  boolean validateDiachi(){
        String diachi=textInpudiachi.getEditText().getText().toString().trim();
        if(diachi.isEmpty()){
            textInpudiachi.setError("Địa chỉ không để trống");
            return false;
        }else {
            textInpudiachi.setError(null);
            textInpudiachi.setErrorEnabled(false);
            return true;
        }
    }

    //ham check du lieu trong khi nhan button cap nhat

    public void confỉmInput(){
         if(!validateSdt()|!validateEmail()|validateDiachi()|validateHoten())//du lieu khong con trong
         {
             return;
         }
    }


}