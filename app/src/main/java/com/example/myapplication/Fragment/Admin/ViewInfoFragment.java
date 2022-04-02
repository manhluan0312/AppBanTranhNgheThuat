package com.example.myapplication.Fragment.Admin;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
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

import com.example.myapplication.Activity.admin.AdminActivity;
import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputLayout;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.IOException;
import java.util.List;


public class ViewInfoFragment<adminActivity> extends Fragment {

    View mView;
    AdminActivity adminActivity;
    TextInputLayout textInpuEmail, textInpuhoten, textInpusodienthoai, textInpudiachi;
    AppCompatButton btn_chinhsua;
    ImageView imageView_avartar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_view_info, container, false);
        AnhXa();
        adminActivity =(AdminActivity)getActivity();
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
        if (!validateSdt() | !validateEmail() | validateDiachi() | validateHoten())//du lieu khong con trong
        {
            return;
        }
        UpdateInfoProfile();
    }

    private void UpdateInfoProfile() {

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