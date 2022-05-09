package com.example.myapplication.Activity.Other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Activity.Custumer.MainActivity;
import com.example.myapplication.Activity.Custumer.RegistrationActivity;
import com.example.myapplication.Activity.admin.AdminActivity;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;
import com.google.android.material.textfield.TextInputLayout;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout textInputLayout_username, textInputLayoutpass;
    Button btn_login;
    TextView txt;
    RadioGroup radioGroup;
    RadioButton rdio_kh, radio_admin;

    sharedPreferences_Login sharedPreferences_login;

    //public static final String DATALOGIN = "datalogin_custumer";
    //SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //sharedPreferences = getSharedPreferences(DATALOGIN, MODE_PRIVATE);

        sharedPreferences_login = new sharedPreferences_Login(this);

        AnhXa();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confỉmInput();
            }
        });

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);

            }
        });
    }

    private void AnhXa() {
        textInputLayout_username = findViewById(R.id.textinput_username);
        textInputLayoutpass = findViewById(R.id.textinput_password);
        btn_login = findViewById(R.id.btn_dangnhap);
        txt = findViewById(R.id.text1);
        radioGroup = findViewById(R.id.rdio_quyendangnhap);
        rdio_kh = findViewById(R.id.rdio_kh);
        radio_admin = findViewById(R.id.rdio_admin);
    }


    //ham check du lieu username khong de dc trong

    private boolean validate_username() {
        String username = textInputLayout_username.getEditText().getText().toString().trim();
        if (username.isEmpty()) {
            textInputLayout_username.setError("Username không để trống");
            textInputLayout_username.setHelperTextEnabled(true);
            return false;
        } else {
            textInputLayout_username.setError(null);
            textInputLayout_username.setErrorEnabled(false);
            textInputLayout_username.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu mat khau  khong de dc trong

    private boolean validate_password() {
        String password = textInputLayoutpass.getEditText().getText().toString().trim();
        if (password.isEmpty()) {
            textInputLayoutpass.setError("Mật khẩu không để trống");
            return false;
        } else {
            textInputLayoutpass.setError(null);
            textInputLayoutpass.setErrorEnabled(false);
            textInputLayoutpass.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    public void confỉmInput() {
        if (!validate_username() | !validate_password())//du lieu khong con trong
        {
            return;
        }
        Login();
    }

    private void Login() {
        if (rdio_kh.isChecked()) {
            LoginCustumer();
        }
        if (radio_admin.isChecked()) {
            LoginAdmin();
        }
    }

    private void LoginAdmin() {
        String username = textInputLayout_username.getEditText().getText().toString().trim();
        String password = textInputLayoutpass.getEditText().getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.LOGIN_ADMIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String suscess = jsonObject.getString("success");
                    if (suscess.equals("1")) {

                        //lay du lieu dang nhap user

                        int id = jsonObject.getInt("id_admin");
                        String username = jsonObject.getString("username").trim();
                        String password = jsonObject.getString("Password_admin").trim();
                        String anh = jsonObject.getString("poto_admin").trim();
                        String hoten = jsonObject.getString("name_admin").trim();
                        String email = jsonObject.getString("email").trim();
                        String sdt = jsonObject.getString("Phone").trim();
                        String diachi = jsonObject.getString("Address").trim();

                        //PutDataLoginsharedPreferences(id, username, password, anh, hoten, email, sdt, diachi);

                        sharedPreferences_login.PutDataLoginsharedPreferences(id, username, password, anh, hoten, email, sdt, diachi);

                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    if (suscess.equals("0")) {
                        Toast.makeText(getApplicationContext(), "Sai tên tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
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
                hashMap.put("Password_admin", password);
                return hashMap;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);// tạo request len server
        requestQueue.add(stringRequest);
    }

    private void LoginCustumer() {
        String username = textInputLayout_username.getEditText().getText().toString().trim();
        String password = textInputLayoutpass.getEditText().getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.LOGIN_CUSTUMER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String suscess = jsonObject.getString("success");
                    if (suscess.equals("1")) {
                        //lay du lieu dang nhap user

                        int id = jsonObject.getInt("id_customer");
                        String username = jsonObject.getString("username").trim();
                        String password = jsonObject.getString("Password_customer").trim();
                        String anh = jsonObject.getString("poto_customer").trim();
                        String hoten = jsonObject.getString("name_customer").trim();
                        String email = jsonObject.getString("email").trim();
                        String sdt = jsonObject.getString("Phone").trim();
                        String diachi = jsonObject.getString("Address_customer").trim();

                        sharedPreferences_login.PutDataLoginsharedPreferences(id, username, password, anh, hoten, email, sdt, diachi);
                        //PutDataLoginsharedPreferences(id, username, password, anh, hoten, email, sdt, diachi);


                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    if (suscess.equals("0")) {
                        Toast.makeText(getApplicationContext(), "Sai tên tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
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
                hashMap.put("Password_customer", password);
                return hashMap;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);// tạo request len server
        requestQueue.add(stringRequest);
    }
}