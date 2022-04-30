package com.example.myapplication.Activity.Custumer;

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
import com.example.myapplication.Activity.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {


    TextInputLayout textInputUsername, textInputHoten, textInputpass, textInputconfimpass, textInputsdt, textInputdiachi;
    Button btn_dangky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        AnhXa();

        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confỉmInput();
            }
        });
    }

    private void AnhXa() {
        textInputUsername = findViewById(R.id.textinput_username);
        textInputHoten = findViewById(R.id.textinput_hoten);
        textInputpass = findViewById(R.id.textinput_password);
        textInputconfimpass = findViewById(R.id.textinput_nhaplai_password);
        textInputsdt = findViewById(R.id.textinput_sdt);
        textInputdiachi = findViewById(R.id.textinput_diachi);
        btn_dangky = findViewById(R.id.btn_dangky);
    }

    //ham check du lieu tenhienthi khong de dc trong

    private boolean validateTenHoten() {
        String ten = textInputHoten.getEditText().getText().toString().trim();
        if (ten.isEmpty()) {
            textInputHoten.setError("Họ tên không để trống");
            return false;
        } else {
            textInputHoten.setError(null);
            textInputHoten.setErrorEnabled(false);
            textInputHoten.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu username khong de dc trong

    private boolean validateUsername() {
        String username = textInputUsername.getEditText().getText().toString().trim();
        if (username.isEmpty()) {
            textInputUsername.setError("Username không để trống");
            return false;
        } else {
            textInputUsername.setError(null);
            textInputUsername.setErrorEnabled(false);
            textInputUsername.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu mat khau  khong de dc trong

    private boolean validate_password() {
        String password = textInputpass.getEditText().getText().toString().trim();
        if (password.isEmpty()) {
            textInputpass.setError("Mật khẩu không để trống");
            return false;
        } else {
            textInputpass.setError(null);
            textInputpass.setErrorEnabled(false);
            textInputpass.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu nhap lai  khong de dc trong

    private boolean validate_confim_password() {
        String confimpassword = textInputconfimpass.getEditText().getText().toString().trim();
        if (confimpassword.isEmpty()) {
            textInputconfimpass.setError("Mật khẩu không để trống");
            return false;
        } else {
            textInputconfimpass.setError(null);
            textInputconfimpass.setErrorEnabled(false);
            textInputconfimpass.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu sdt khong de dc trong

    private boolean validateSdt() {
        String sdt = textInputsdt.getEditText().getText().toString().trim();
        if (sdt.isEmpty()) {
            textInputsdt.setError("Số điện thoại không để trống");
            return false;
        } else if (sdt.length() > 10) {
            textInputsdt.setError("Số điện thoại không dài hơn 10 chữ số");
            return false;
        } else {
            textInputsdt.setError(null);
            textInputsdt.setErrorEnabled(false);
            textInputsdt.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu dia chi khong de dc trong

    private boolean validateDiachi() {
        String diachi = textInputdiachi.getEditText().getText().toString().trim();
        if (diachi.isEmpty()) {
            textInputdiachi.setError("Địa chỉ không để trống");
            return false;
        } else {
            textInputdiachi.setError(null);
            textInputdiachi.setErrorEnabled(false);
            textInputdiachi.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    private void confỉmInput() {
        if (!validateUsername() | !validate_password() | !validateTenHoten() | !validate_confim_password() | !validateSdt() |
                !validateDiachi())//du lieu khong con trong
        {
            return;
        }
        Registration();
    }

    private void Registration() {

        String ten = textInputHoten.getEditText().getText().toString().trim();
        String username = textInputUsername.getEditText().getText().toString().trim();
        String password = textInputpass.getEditText().getText().toString().trim();
        String confimpassword = textInputconfimpass.getEditText().getText().toString().trim();
        String sdt = textInputsdt.getEditText().getText().toString().trim();
        String diachi = textInputdiachi.getEditText().getText().toString().trim();

        if (password.equals(confimpassword)) {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.REGISTRATION, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String suscess = jsonObject.getString("success");
                        if (suscess.equals("1")) {
                            Toast.makeText(RegistrationActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
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
                    Toast.makeText(getApplicationContext(), "lỗi", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("username", username);
                    hashMap.put("name_customer", ten);
                    hashMap.put("Password_customer", password);
                    hashMap.put("Phone", sdt);
                    hashMap.put("Address_customer", diachi);
                    return hashMap;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);// tạo request len server
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(this, "Mật khẩu và xác nhận mật khẩu phải trùng nhau", Toast.LENGTH_SHORT).show();
        }
    }
}



