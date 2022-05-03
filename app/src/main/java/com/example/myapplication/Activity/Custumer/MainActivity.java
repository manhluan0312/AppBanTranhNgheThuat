package com.example.myapplication.Activity.Custumer;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.example.myapplication.Adapter.ViewPagerMainAdapter;
import com.example.myapplication.Model.GioHang;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager;
    //sharedPreferences_Login sharedPreferences_login;


    public static ArrayList<GioHang> gioHangArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();//ham anh xa cac view tu file xml sang sang java
        setUpViewPapger();//dong bo viewpager va bottom navigation

        //sharedPreferences_login=new sharedPreferences_Login(this);
        //sharedPreferences_login.checkLoginCustumer();//ham check login

    //neu da co san pham trong gio hang
        if(gioHangArrayList!=null){
            //ko khoi tao
        }
        else {
            gioHangArrayList=new ArrayList<>();
        }

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
                        bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);//tra ve vi tri tung  item trong botttom navigation và sáng icon
                        break;

                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.action_doanhmucsanpham).setChecked(true);//tra ve vi tri tung item trong botttom navigation và sáng icon
                        break;


                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.action_user).setChecked(true);//tra ve vi tri item tung trong botttom navigation và sáng icon
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
