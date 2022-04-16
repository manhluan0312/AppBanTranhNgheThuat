package com.example.myapplication.Activity.Custumer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;

public class GioHangActivity extends AppCompatActivity {


    Toolbar toolbar;
    TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        AnhXa();
        setToolbar();
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toobar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTitle.setText("Giỏ hàng");
        getSupportActionBar().setDisplayShowTitleEnabled(false);//khong hien thi titile ma cdinh cua toorbar

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}