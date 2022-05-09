package com.example.myapplication.Fragment.Admin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.myapplication.Activity.admin.AdminActivity;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordFragment extends Fragment {

    View mView;
    TextInputLayout textInpumatkaucu, textInpumatkhaumoi, textInputnhaplaimkmoi;
    Button btn_capnhatpass;
    public SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_change_password, container, false);
        AnhXa();

        sharedPreferences = this.getActivity().getSharedPreferences("datalogin_custumer", Context.MODE_PRIVATE);

        btn_capnhatpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confỉmInput();
            }
        });

        return mView;
    }

    private void AnhXa() {
        btn_capnhatpass = mView.findViewById(R.id.btn_capnhatpass);
        textInpumatkaucu = mView.findViewById(R.id.textinput_nhappasscu);
        textInpumatkhaumoi = mView.findViewById(R.id.textinput_nhappassmoi);
        textInputnhaplaimkmoi = mView.findViewById(R.id.textinput_nhalaippassmoi);
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

        int id = sharedPreferences.getInt("id", 0);

        if (matkhaucu.equals(matkhaumoi)) {
            Toast.makeText(getContext(), "Mật khẩu mới trùng mật khẫu cũ", Toast.LENGTH_LONG).show();
        } else if (!matkhaumoi.equals(nhaplaimatkhaumoi)) {
            Toast.makeText(getContext(), "Thông tin về mật khẩu mỡi phải trùng nhau", Toast.LENGTH_LONG).show();
        } else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.change_password_admin, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String suscess = jsonObject.getString("success");
                        if (suscess.equals("1")) {
                            Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(), AdminActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                            matkhaucu.isEmpty();
                            matkhaumoi.isEmpty();
                            nhaplaimatkhaumoi.isEmpty();
                        }

                            if (suscess.equals("3")) {
                                Toast.makeText(getContext(), "Mật khẩu cũ nhập sai", Toast.LENGTH_LONG).show();
                            }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getContext(), "Lỗi cập nhật", Toast.LENGTH_LONG).show();

                }
            }) {
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("Passwor_old", matkhaucu);
                    hashMap.put("Password_new", matkhaumoi);
                    hashMap.put("id", String.valueOf(id));
                    return hashMap;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(getContext());
            queue.add(stringRequest);
        }
    }
}