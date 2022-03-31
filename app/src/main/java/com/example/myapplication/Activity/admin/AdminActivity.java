package com.example.myapplication.Activity.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        AnhXa();
        setToorBar();
        MoNavigationDrawer();//bat su kien mo navidraw
        navigationView.setNavigationItemSelectedListener(this);

    }

     //bat su kien mo navidraw
    private void MoNavigationDrawer() {
        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,drawerLayout,
                toolbar,R.string.Open_navi,R.string.Close_navi);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }



    private void setToorBar() {
       setSupportActionBar(toolbar);
    }



    private void AnhXa() {
        drawerLayout=findViewById(R.id.draw_layout);
        toolbar=findViewById(R.id.toolbar_title);
        navigationView=findViewById(R.id.navigation_view);
    }


    //bat su kien khi nguoi dung an nut back tren dt
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }

    }

    //bat su kien cho cac frament
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return true;
    }
}
