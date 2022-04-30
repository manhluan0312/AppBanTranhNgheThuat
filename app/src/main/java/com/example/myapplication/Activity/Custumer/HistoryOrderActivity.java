package com.example.myapplication.Activity.Custumer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.Adapter.ViewPager_Order_HistoryAdapter;
import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HistoryOrderActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView mTitle;
    TabLayout tableLayout;
    ViewPager2 viewPager2;
    ViewPager_Order_HistoryAdapter viewPager_order_historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_order);
        AnhXa();
        setToolbar();
    }
    private void AnhXa() {
        toolbar = findViewById(R.id.toobar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        tableLayout=findViewById(R.id.tablayout);
        viewPager2=findViewById(R.id.view_papager_2);
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTitle.setText("Đơn hàng đã mua");
        getSupportActionBar().setDisplayShowTitleEnabled(false);//khong hien thi titile ma cdinh cua toorbar

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        viewPager_order_historyAdapter =new ViewPager_Order_HistoryAdapter(this);
        viewPager2.setAdapter(viewPager_order_historyAdapter);//add frament vao viewpaper

        //set titile cho tablayout
        new TabLayoutMediator(tableLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText(getString(R.string.tatcadonhang));
                        break;
                    case 1:
                        tab.setText(getString(R.string.choxuly));
                        break;
                    case 2:
                        tab.setText(getString(R.string.dangxuly));
                        break;
                    case 3:
                        tab.setText(getString(R.string.daxuly));
                        break;
                }
            }
        }).attach();
    }

}
