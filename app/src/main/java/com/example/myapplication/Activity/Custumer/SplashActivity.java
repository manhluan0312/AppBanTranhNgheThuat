package com.example.myapplication.Activity.Custumer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.Activity.Other.LoginActivity;
import com.example.myapplication.Activity.Other.sharedPreferences_Login;
import com.example.myapplication.R;
import com.example.myapplication.Utils.ischeckNetwork;
import com.github.ybq.android.spinkit.style.Wave;

public class SplashActivity extends AppCompatActivity {

    ProgressBar progressBar;
    sharedPreferences_Login sharedPreferences_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = findViewById(R.id.progre);
        progressBar.setIndeterminateDrawable(new Wave());
        LoadData();
    }

    private void LoadData() {
        if (ischeckNetwork.checkNetworkAvilable(this)) {
            sharedPreferences_login = new sharedPreferences_Login(this);
            //truong hop co mang
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                   // sharedPreferences_login.checkLogin();
                }
            }, 3000);
        } else {
            //truong hop ko co mang
            Toast.makeText(this, "khong co mang internet,", Toast.LENGTH_LONG).show();
        }

    }
}