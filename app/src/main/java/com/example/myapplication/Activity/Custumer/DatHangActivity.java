package com.example.myapplication.Activity.Custumer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatHangActivity extends AppCompatActivity {

    TextInputLayout textinput_diachigiaohang, textInputLayout_ten, textInputLayout_sdt, textInputLayoutghichu;
    Button btn_thanhtoan;
    TextView tv_tongtien;
    public SharedPreferences sharedPreferences;
    Spinner spinner;
    float tongtien;
    int itemspinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_hang);
        AnhXa();

        sharedPreferences = getSharedPreferences("datalogin_custumer", Context.MODE_PRIVATE);

        textInputLayout_ten.getEditText().setText((sharedPreferences.getString("hoten", "")));
        textInputLayout_sdt.getEditText().setText((sharedPreferences.getString("sdt", "")));

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tongtien = getIntent().getFloatExtra("tongtien", 0);
        tv_tongtien.setText("Tổng tiền : " + decimalFormat.format(tongtien) + " " + "VND");

        ArrayList<String> hinhthucthanhtoan = new ArrayList<String>();
//        hinhthucthanhtoan.add("-- Vui lòng nhập hình thức thanh toán --");
        hinhthucthanhtoan.add("Trả tiền mặt");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, hinhthucthanhtoan);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
//                Toast.makeText(getApplicationContext(),"Bạn chưa chọn hình thức thanh toán",Toast.LENGTH_LONG).show();
            }
        });


        btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confỉmInput();
            }
        });
    }


    private void AnhXa() {
        textInputLayout_ten = findViewById(R.id.textinput_hoten);
        textInputLayout_sdt = findViewById(R.id.textinput_sdt);
        textinput_diachigiaohang = findViewById(R.id.textinput_diachigiaohang);
        btn_thanhtoan = findViewById(R.id.btn_thanhtoan);
        spinner = findViewById(R.id.spn_hinhthucthanhtoan);
        tv_tongtien = findViewById(R.id.tv_tongtien);
        textInputLayoutghichu = findViewById(R.id.textinput_ghichu);
    }
    //ham check du lieu dia chi dat hang khong de dc trong

    private boolean validateDiachi() {
        String diachi = textinput_diachigiaohang.getEditText().getText().toString().trim();
        if (diachi.isEmpty()) {
            textinput_diachigiaohang.setError("Địa chỉ không để trống");
            return false;
        } else {
            textinput_diachigiaohang.setError(null);
            textinput_diachigiaohang.setErrorEnabled(false);
            textinput_diachigiaohang.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    private void confỉmInput() {
        if (!validateDiachi())//du lieu khong con trong
        {
            return;
        }

        ThanhToan();

    }

    private void ThanhToan() {

//        itemspinner=spinner.getSelectedItem().toString();

//        if(itemspinner.equals("-- Vui lòng nhập hình thức thanh toán --"))
//        {
//            Toast.makeText(getApplicationContext(),"Bạn chưa chọn hình thức thanh toán",Toast.LENGTH_LONG).show();
//        }

        int id = sharedPreferences.getInt("id", 0);
        String ghichu = textInputLayoutghichu.getEditText().getText().toString().trim();
        String diachigiaohang = textinput_diachigiaohang.getEditText().getText().toString().trim();
        //Log.d("test", new Gson().toJson(MainActivity.gioHangArrayList));


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.Order, new Response.Listener<String>() {
            @Override
            public void onResponse(final String madonhang) {

                Log.d("madonhang", madonhang);
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, Server.Order_detail, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("1")) {
                            MainActivity.gioHangArrayList.clear();//xoa du lieu san pham khi insert thanh cong
                            Toast.makeText(getApplicationContext(), "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        JSONArray jsonArray = new JSONArray();
                        for (int i = 0; i < MainActivity.gioHangArrayList.size(); i++) {
                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put("id_order", madonhang);//dang bi loi
                                jsonObject.put("idsp", MainActivity.gioHangArrayList.get(i).getIdsp());
                                jsonObject.put("soluongsanpham", MainActivity.gioHangArrayList.get(i).getSoluongsanpham());
                                jsonObject.put("giasanpham", MainActivity.gioHangArrayList.get(i).getGiasanpham());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            jsonArray.put(jsonObject);
                        }
                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        hashMap.put("chitiet", jsonArray.toString());
                        return hashMap;
                    }
                };
                requestQueue.add(stringRequest1);
            }
            //}
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id_customer", String.valueOf(id));
                hashMap.put("Payments", spinner.getSelectedItem().toString());
                hashMap.put("Note", ghichu);
                hashMap.put("Delivery_address", diachigiaohang);
                hashMap.put("Total_money", String.valueOf(tongtien));
                return hashMap;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }
}