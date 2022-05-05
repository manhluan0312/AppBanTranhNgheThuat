package com.example.myapplication.Activity.Other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.myapplication.Activity.Custumer.MainActivity;
import com.example.myapplication.Activity.Custumer.RegistrationActivity;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordActivity extends AppCompatActivity {

    TextInputLayout textInpumatkaucu, textInpumatkhaumoi, textInputnhaplaimkmoi;
    Button btn_capnhatpass;
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        AnhXa();

        sharedPreferences = getSharedPreferences("datalogin_custumer", Context.MODE_PRIVATE);

        btn_capnhatpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confỉmInput();
            }
        });
    }

    private void AnhXa() {
        btn_capnhatpass = findViewById(R.id.btn_capnhatpass);
        textInpumatkaucu = findViewById(R.id.textinput_nhappasscu);
        textInpumatkhaumoi = findViewById(R.id.textinput_nhappassmoi);
        textInputnhaplaimkmoi = findViewById(R.id.textinput_nhalaippassmoi);
    }

    //ham check du lieu mat khau cu khong de dc trong

    private boolean validate_matkhaucu() {
        String mkcu = textInpumatkaucu.getEditText().getText().toString().trim();
        if (mkcu.isEmpty()) {
            textInpumatkaucu.setError("Mật khẩu cũ không để trống");
            textInpumatkaucu.setHelperTextEnabled(true);
            return false;
        } else {
            textInpumatkaucu.setError(null);
            textInpumatkaucu.setErrorEnabled(false);
            textInpumatkaucu.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu mat khau moi khong de dc trong

    private boolean validate_matkhaumoi() {
        String mkmoi = textInpumatkhaumoi.getEditText().getText().toString().trim();
        if (mkmoi.isEmpty()) {
            textInpumatkhaumoi.setError("Mật khẩu mói không để trống");
            return false;
        } else {
            textInpumatkhaumoi.setError(null);
            textInpumatkhaumoi.setErrorEnabled(false);
            textInpumatkhaumoi.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu nhap lai mat khau moi khong de dc trong

    private boolean validate_nhaplaimatkhaumoi() {
        String nhaplaimkmoi = textInputnhaplaimkmoi.getEditText().getText().toString().trim();
        if (nhaplaimkmoi.isEmpty()) {
            textInputnhaplaimkmoi.setError("Mật khẩu mói không để trống");
            return false;
        } else {
            textInputnhaplaimkmoi.setError(null);
            textInputnhaplaimkmoi.setErrorEnabled(false);
            textInputnhaplaimkmoi.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu trong khi nhan button cap nhat

    public void confỉmInput() {
        if (!validate_matkhaucu() | !validate_matkhaumoi() | !validate_nhaplaimatkhaumoi())//du lieu khong con trong
        {
            return;
        }
        ChangePassword();
    }

    private void ChangePassword() {

        String matkhaucu = textInpumatkaucu.getEditText().getText().toString().trim();
        String matkhaumoi = textInpumatkhaumoi.getEditText().getText().toString().trim();
        String nhaplaimatkhaumoi = textInputnhaplaimkmoi.getEditText().getText().toString().trim();

        int id =sharedPreferences.getInt("id",0);

        if (matkhaucu.equals(matkhaumoi)) {
            Toast.makeText(getApplicationContext(), "Mật khẩu mới trùng mật khẫu cũ", Toast.LENGTH_LONG).show();
        } else if (!matkhaumoi.equals(nhaplaimatkhaumoi)) {
            Toast.makeText(getApplicationContext(), "Thông tin về mật khẩu mỡi phải trùng nhau", Toast.LENGTH_LONG).show();
        } else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.change_password_custumer, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String suscess = jsonObject.getString("success");
                        if (suscess.equals("1")) {
                            Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();

                            if (suscess.equals("3")) {
                                Toast.makeText(getApplicationContext(), "Mật khẩu cũ nhập sai", Toast.LENGTH_LONG).show();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(),"Lỗi cập nhật",Toast.LENGTH_LONG).show();

                }
            }) {
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("Passwor_old",matkhaucu);
                    hashMap.put("Password_new", matkhaumoi);
                    hashMap.put("id",String.valueOf(id));
                    return hashMap;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(stringRequest);
        }
    }
}

