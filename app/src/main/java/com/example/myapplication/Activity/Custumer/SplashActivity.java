package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.myapplication.R;
import com.example.myapplication.Utils.ischeckNetwork;
import com.github.ybq.android.spinkit.style.Wave;

public class SplashActivity extends AppCompatActivity {

    ProgressBar progressBar;

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
            //truong hop co mang
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 3000);
        } else {
            //truong hop ko co mang
            Toast.makeText(this, "khong co mang internet,", Toast.LENGTH_LONG).show();
        }

    }
}