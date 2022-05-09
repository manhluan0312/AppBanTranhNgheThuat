package com.example.myapplication.Fragment.Admin;

import android.content.Intent;
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
import com.example.myapplication.Activity.admin.AdminActivity;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddCatalogFragment extends Fragment {

    View mView;
    AdminActivity adminActivity;
    TextInputLayout textInputthemdanhmuc;
    Button btn_themdanhmuc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView=inflater.inflate(R.layout.fragment_add_catalog, container, false);

        AnhXa();

        btn_themdanhmuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confỉmInput();
            }
        });

        return mView;
    }

    private void AnhXa() {
        textInputthemdanhmuc=mView.findViewById(R.id.textinput_themdanhmuc);
        btn_themdanhmuc=mView.findViewById(R.id.btn_themdanhmuc);
    }

    //ham check du lieu danh muc cu khong de dc trong

    private boolean validate_danhmuc() {
        String danhmuc = textInputthemdanhmuc.getEditText().getText().toString().trim();
        if (danhmuc.isEmpty()) {
            textInputthemdanhmuc.setError("Danh mục cũ không để trống");
            textInputthemdanhmuc.setHelperTextEnabled(true);
            return false;
        } else {
            textInputthemdanhmuc.setError(null);
            textInputthemdanhmuc.setErrorEnabled(false);
            textInputthemdanhmuc.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    private void confỉmInput() {
        if(!validate_danhmuc()){
            return;
        }
        ThemDanhMuc();
    }

    private void ThemDanhMuc() {
        String danhmuc = textInputthemdanhmuc.getEditText().getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.add_catalog, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String suscess = jsonObject.getString("success");
                    if (suscess.equals("1")) {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), AdminActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "Lỗi thêm sản phẩm", Toast.LENGTH_LONG).show();

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("name_catalog",danhmuc);
                return hashMap;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);

    }


}