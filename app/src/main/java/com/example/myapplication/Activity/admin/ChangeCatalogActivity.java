package com.example.myapplication.Activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Model.DanhMucSanPham;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangeCatalogActivity extends AppCompatActivity {


    TextInputLayout textInputsuadanhmuc;
    Button btn_suadanhmuc;
    DanhMucSanPham danhMucSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_catalog);
        AnhXa();

        Intent intent=getIntent();
        danhMucSanPham= (DanhMucSanPham) intent.getSerializableExtra("danhmuc");

        textInputsuadanhmuc.getEditText().setText(danhMucSanPham.getTendanhmuc());

        btn_suadanhmuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confỉmInput();
            }
        });
    }

    private void AnhXa() {
        textInputsuadanhmuc=findViewById(R.id.textinput_suadanhmuc);
        btn_suadanhmuc=findViewById(R.id.btn_suadanhmuc);
    }


    //ham check du lieu danh muc cu khong de dc trong

    private boolean validate_danhmuc() {
        String danhmuc = textInputsuadanhmuc.getEditText().getText().toString().trim();
        if (danhmuc.isEmpty()) {
            textInputsuadanhmuc.setError("Danh mục cũ không để trống");
            textInputsuadanhmuc.setHelperTextEnabled(true);
            return false;
        } else {
            textInputsuadanhmuc.setError(null);
            textInputsuadanhmuc.setErrorEnabled(false);
            textInputsuadanhmuc.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    private void confỉmInput() {
        if(!validate_danhmuc()){
            return;
        }
        SuaDanhMuc();
    }

    private void SuaDanhMuc() {
        String danhmuc = textInputsuadanhmuc.getEditText().getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.update_catalog, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String suscess = jsonObject.getString("success");
                    if (suscess.equals("1")) {
                        Toast.makeText(getApplicationContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(intent);
                        finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Lỗi thêm sản phẩm", Toast.LENGTH_LONG).show();

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("name_catalog",danhmuc);
                hashMap.put("id_catalog",String.valueOf(danhMucSanPham.getIddm()));
                return hashMap;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }

}