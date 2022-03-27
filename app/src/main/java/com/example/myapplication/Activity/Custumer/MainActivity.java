package com.example.myapplication.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.Adapter.ViewPagerMainAdapter;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();//ham anh xa cac view tu file xml sang sang java
        setUpViewPapger();//dong bo viewpager va bottom navigation
        //su kien khi click vao tung item trong bottom navaigation
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_home:
                        viewPager.setCurrentItem(0);//thay doi vi tri tung item cua viewpager
                        break;
                    case R.id.action_doanhmucsanpham:
                        viewPager.setCurrentItem(1);//thay doi vi tri tung item cua viewpager
                        break;
                    case R.id.action_user:
                        viewPager.setCurrentItem(2);//thay doi vi tri tung item cua viewpager
                        break;
                }
                return true;
            }

        });

    }

    private void setUpViewPapger() {

        ViewPagerMainAdapter viewPagerMainAdapter = new ViewPagerMainAdapter(this);
        viewPager.setAdapter(viewPagerMainAdapter);
        //su kien chuyen page trong viewpager
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback()//
        {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);//tra ve vi tri tung  item trong botttom navigation
                        break;

                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.action_doanhmucsanpham).setChecked(true);//tra ve vi tri tung item trong botttom navigation
                        break;


                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.action_user).setChecked(true);//tra ve vi tri item tung trong botttom navigation
                        break;
                }
            }
        });

    }

    private void AnhXa() {
        bottomNavigationView = findViewById(R.id.bottom_nav);
        viewPager = findViewById(R.id.viewpapar);
    }


}
