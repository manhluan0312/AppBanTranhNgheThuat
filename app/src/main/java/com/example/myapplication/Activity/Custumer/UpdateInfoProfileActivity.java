package com.example.myapplication.Activity.Custumer;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputLayout;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.IOException;
import java.util.List;

public class UpdateInfoProfileActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView mTitle;
    TextInputLayout textInpuEmail, textInpuhoten, textInpusodienthoai, textInpudiachi;
    AppCompatButton btn_chinhsua;
    ImageView imageView_avartar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_info);
        AnhXa();
        setToolbar();

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
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toobar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textInpuEmail = findViewById(R.id.textinput_email);
        textInpuhoten = findViewById(R.id.textinput_hoten);
        textInpusodienthoai = findViewById(R.id.textinput_sdt);
        textInpudiachi = findViewById(R.id.textinput_diachi);
        btn_chinhsua = findViewById(R.id.btn_chinhsua);
        imageView_avartar = findViewById(R.id.image_avartar);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTitle.setText("Thông tin cá nhân");
        getSupportActionBar().setDisplayShowTitleEnabled(false);//khong hien thi titile ma cdinh cua toorbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
                Toast.makeText(getApplicationContext(), "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")//neu nguoi dung tu choi thi no vao muc setting
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }

    private ActivityResultLauncher<Intent> activityResultLauncher =registerForActivityResult(
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
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
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
