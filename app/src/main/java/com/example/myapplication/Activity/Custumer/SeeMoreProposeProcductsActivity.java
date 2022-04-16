package com.example.myapplication.Activity.Custumer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.Adapter.XemThemSanPhamDeXuatAdapter;
import com.example.myapplication.Adapter.XemThemSanPhamMoiNhatAdapter;
import com.example.myapplication.Model.SanPham;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SeeMoreProposeProcductsActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView mTitle;
    RecyclerView rcv_dmsp;
    MainActivity mainActivity;
    ArrayList<SanPham> arrayListxemthemsanphamnoibat;
    SeeMoreProposeProcductsActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highlights_procducts);
        AnhXa();
        setToolbar();

        //set giao dien cho san pham de xuat

        GridLayoutManager gridLayoutManager;
        gridLayoutManager=new GridLayoutManager(this,2);
        rcv_dmsp.setLayoutManager(gridLayoutManager);
        GetListProductPropose();
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toobar);
        rcv_dmsp=findViewById(R.id.rcv_dmspdx);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTitle.setText("Sản phẩm đề xuất");
        getSupportActionBar().setDisplayShowTitleEnabled(false);//khong hien thi titile ma cdinh cua toorbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetListProductPropose() {



        arrayListxemthemsanphamnoibat = new ArrayList<>();


        StringRequest StringRequest = new StringRequest(Request.Method.GET, Server.GETMORESANPHAMDEXUAT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray sanpham = new JSONArray(response);
                            for (int i = 0; i < sanpham.length(); i++) {
                                JSONObject jsonObjectRequest = sanpham.getJSONObject(i);
                                int id_product = jsonObjectRequest.getInt("id_product");
                                String name_product = jsonObjectRequest.getString("name_product");
                                String poto_product = jsonObjectRequest.getString("poto_product");
                                int price_product = jsonObjectRequest.getInt("price_product");
                                String product_material = jsonObjectRequest.getString("product_material");
                                String product_dimensions = jsonObjectRequest.getString("product_dimensions");
                                int year_of_creation = jsonObjectRequest.getInt("year_of_creation");
                                String product_description = jsonObjectRequest.getString("product_description");
                                String note_products = jsonObjectRequest.getString("note_products");
                                String name_catalog = jsonObjectRequest.getString("name_catalog");

                                SanPham sanPham = new SanPham(id_product, name_product, poto_product, price_product,
                                        product_material, product_dimensions, year_of_creation, product_description, note_products, name_catalog);
                                arrayListxemthemsanphamnoibat.add(sanPham);
                            }

                            XemThemSanPhamDeXuatAdapter sanPhamMoiNhatAdapter = new XemThemSanPhamDeXuatAdapter(arrayListxemthemsanphamnoibat,activity);
                            rcv_dmsp.setAdapter(sanPhamMoiNhatAdapter);
                            sanPhamMoiNhatAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "lỗi", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());// tạo request len server
        requestQueue.add(StringRequest);

    }
}