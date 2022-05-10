package com.example.myapplication.Fragment.Admin;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.myapplication.Activity.Custumer.UpdateInfoProfileActivity;
import com.example.myapplication.Activity.Other.sharedPreferences_Login;
import com.example.myapplication.Activity.admin.AdminActivity;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;
import com.google.android.material.textfield.TextInputLayout;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import static com.example.myapplication.Activity.LoginActivity.DATALOGIN;


public class ViewInfoFragment<adminActivity> extends Fragment {

    View mView;
    AdminActivity adminActivity;
    TextInputLayout textInputUsername,textInpuEmail, textInpuhoten, textInpusodienthoai, textInpudiachi;
    AppCompatButton btn_chinhsua;
    ImageView imageView_avartar;
    sharedPreferences_Login sharedPreferences_login;

    public SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_view_info, container, false);
        AnhXa();
        adminActivity =(AdminActivity)getActivity();
        sharedPreferences_login = new sharedPreferences_Login(adminActivity);

        sharedPreferences = this.getActivity().getSharedPreferences("datalogin_custumer", Context.MODE_PRIVATE);

        textInputUsername.getEditText().setText((sharedPreferences.getString("Username", "")));
        textInpuhoten.getEditText().setText((sharedPreferences.getString("hoten", "")));
        textInpuEmail.getEditText().setText((sharedPreferences.getString("email", "")));
        textInpusodienthoai.getEditText().setText((sharedPreferences.getString("sdt", "")));
        textInpudiachi.getEditText().setText((sharedPreferences.getString("diachi", "")));

        String anh = sharedPreferences.getString("anh", "");

        Glide.with(this)
                .load(anh)
                .error(Server.IMAGE_AVARTAR)
                .centerCrop()
                .into(imageView_avartar);


        btn_chinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confỉmInput();
            }
        });

        imageView_avartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestPermissons();
            }
        });

        return mView;

    }

    private void AnhXa() {
        textInpuEmail = mView.findViewById(R.id.textinput_email);
        textInpuhoten = mView.findViewById(R.id.textinput_hoten);
        textInpusodienthoai = mView.findViewById(R.id.textinput_sdt);
        textInpudiachi = mView.findViewById(R.id.textinput_diachi);
        btn_chinhsua = mView.findViewById(R.id.btn_chinhsua);
        imageView_avartar = mView.findViewById(R.id.image_avartar);
        textInputUsername=mView.findViewById(R.id.textinput_username);
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

    //ham check du lieu email khong de dc trong

    private boolean validateEmail() {
        String email = textInpuEmail.getEditText().getText().toString().trim();
        if (email.isEmpty()) {
            textInpuEmail.setError("Email không để trống");
            return false;
        } else {
            textInpuEmail.setError(null);
            textInpuEmail.setErrorEnabled(false);
            textInpuEmail.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu hoten khong de dc trong

    private boolean validateHoten() {
        String hoten = textInpuhoten.getEditText().getText().toString().trim();
        if (hoten.isEmpty()) {
            textInpuhoten.setError("Họ tên không để trống");
            return false;
        } else {
            textInpuhoten.setError(null);
            textInpuhoten.setErrorEnabled(false);
            textInpuhoten.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu sdt khong de dc trong

    private boolean validateSdt() {
        String sdt = textInpusodienthoai.getEditText().getText().toString().trim();
        if (sdt.isEmpty()) {
            textInpusodienthoai.setError("Số điện thoại không để trống");
            return false;
        } else if (sdt.length() > 10) {
            textInpusodienthoai.setError("Số điện thoại không dài hơn 10 chữ số");
            return false;
        } else {
            textInpusodienthoai.setError(null);
            textInpusodienthoai.setErrorEnabled(false);
            textInpusodienthoai.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu dia chi khong de dc trong

    private boolean validateDiachi() {
        String diachi = textInpudiachi.getEditText().getText().toString().trim();
        if (diachi.isEmpty()) {
            textInpudiachi.setError("Địa chỉ không để trống");
            return false;
        } else {
            textInpudiachi.setError(null);
            textInpudiachi.setErrorEnabled(false);
            textInpudiachi.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu trong khi nhan button cap nhat

    public void confỉmInput() {
        if (!validateSdt() | !validateEmail() | !validateDiachi() |!validateUsername() | !validateHoten())//du lieu khong con trong
        {
            return;
        }
        UpdateInfoProfile();
    }

    private void UpdateInfoProfile() {

        int id = sharedPreferences.getInt("id", 0);
        String username = textInputUsername.getEditText().getText().toString().trim();
        String hoten = textInpuhoten.getEditText().getText().toString().trim();
        String sdt = textInpusodienthoai.getEditText().getText().toString().trim();
        String email = textInpuEmail.getEditText().getText().toString().trim();
        String diachi = textInpudiachi.getEditText().getText().toString().trim();

        if (email.equals("null")) {
            email.isEmpty();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.update_profile_admin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =new JSONObject(response);
                    String sucess=jsonObject.getString("success");

                    if (sucess.equals("1")) {
                        sharedPreferences_login.PutEditProfile(id, username, hoten, email, sdt, diachi);
                        Toast.makeText(adminActivity, "Cập nhật thành công", Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(adminActivity, "Lỗi cập nhật" + error, Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("username", username);
                hashMap.put("name", hoten);
                hashMap.put("Phone", sdt);
                hashMap.put("Address", diachi);
                hashMap.put("email", email);
                hashMap.put("id", String.valueOf(id));
                return hashMap;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(adminActivity);
        queue.add(stringRequest);

    }

    private void RequestPermissons() {
        PermissionListener permissionlistener = new PermissionListener() {

            // nguoi dung  cho phep truy cap vao permission
            @Override
            public void onPermissionGranted() {
                OpenGlary();//ham cho nguoi dung chon anh
            }

            // nguoi dung khong  cho phep truy cap vao permission
            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(getContext(), "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")//neu nguoi dung tu choi thi no vao muc setting
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }

     private  ActivityResultLauncher<Intent> activityResultLauncher =registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()== Activity.RESULT_OK){
                        Intent data = result.getData();
                        if(data==null){
                            return;
                        }
                        Uri uri =data.getData();// uri la du lieu anh ma nguoi dung chon
                        try {
                            Bitmap bitmap =MediaStore.Images.Media.getBitmap(adminActivity.getContentResolver(),uri);
                            imageView_avartar.setImageBitmap(bitmap);//set anh len bitmap
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
            }
}
    );

    private void OpenGlary() {
        Intent intent =new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activityResultLauncher.launch(Intent.createChooser(intent,"Chọn ảnh"));
    }
}