package com.example.myapplication.Activity.admin;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.myapplication.Model.DanhMucSanPham;
import com.example.myapplication.Model.SanPham;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;
import com.google.android.material.textfield.TextInputLayout;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuaSanPhamActivity extends AppCompatActivity {

    TextInputLayout textInputtensp, textInpugiasp, textInputchatlieu,
            textInputkichco, textInputnamst, textInputmota, textInputtenghichu;
    ImageView imageView;
    Button btn_suasanpham;

    SanPham sanPham;
    Spinner spnner_dm;
    ArrayList<DanhMucSanPham> tendamhmList;
    ArrayAdapter<DanhMucSanPham> danhMucSanPhamAdapter;
    int iddanhmuc;
    String encodeImageString;//ten anh sau khi covert

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_san_pham);
        AnhXa();
        GetCatalog();
        Intent intent = getIntent();
        sanPham = (SanPham) intent.getSerializableExtra("sanpham");

        textInputtensp.getEditText().setText(sanPham.getName_product());
        textInpugiasp.getEditText().setText(sanPham.getPrice_product() + "");
        textInputchatlieu.getEditText().setText(sanPham.getProduct_material());
        textInputkichco.getEditText().setText(sanPham.getProduct_dimensions());
        textInputnamst.getEditText().setText(sanPham.getYear_of_creation() + "");
        textInputmota.getEditText().setText(sanPham.getProduct_description());
        textInputtenghichu.getEditText().setText(sanPham.getNote_products());


        String anh = "http://" + Server.HOST + "image/Products/" + sanPham.getPoto_product();

        Glide.with(this)
                .load(anh)
                .centerCrop()
                .error(R.drawable.ic_launcher_background)
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestPermissons();//check quyen nguoi dung
            }
        });


        btn_suasanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conf???mInput();
            }
        });

    }

    private void AnhXa() {
        textInputtensp = findViewById(R.id.textinput_tensanpham);
        textInpugiasp = findViewById(R.id.textinput_giasanpham);
        textInputchatlieu = findViewById(R.id.textinput_chatlieusanpham);
        textInputkichco = findViewById(R.id.textinput_kichcosanpham);
        textInputnamst = findViewById(R.id.textinput_namsangtacsanpham);
        textInputmota = findViewById(R.id.textinput_motasanpham);
        textInputtenghichu = findViewById(R.id.textinput_ghichusanpham);
        imageView = findViewById(R.id.img_anhsp);
        spnner_dm = findViewById(R.id.spnner_dm);
        btn_suasanpham = findViewById(R.id.btn_suasanpham);
    }

    //ham check du lieu tensanpham khong de dc trong

    private boolean validateTenSanpham() {
        String ten = textInputtensp.getEditText().getText().toString().trim();
        if (ten.isEmpty()) {
            textInputtensp.setError("T??n s???n ph???m kh??ng ????? tr???ng");
            return false;
        } else {
            textInputtensp.setError(null);
            textInputtensp.setErrorEnabled(false);
            textInputtensp.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu gia khong de dc trong

    private boolean validateGiaSanpham() {
        String gia = textInpugiasp.getEditText().getText().toString().trim();
        if (gia.isEmpty()) {
            textInpugiasp.setError("Gi?? s???n ph???m kh??ng ????? tr???ng");
            return false;
        } else {
            textInpugiasp.setError(null);
            textInpugiasp.setErrorEnabled(false);
            textInpugiasp.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu chat lieu khong de dc trong

    private boolean validateChatlieuSanpham() {
        String chatlieu = textInputchatlieu.getEditText().getText().toString().trim();
        if (chatlieu.isEmpty()) {
            textInputchatlieu.setError("Ch???t li???u s???n ph???m kh??ng ????? tr???ng");
            return false;
        } else {
            textInputchatlieu.setError(null);
            textInputchatlieu.setErrorEnabled(false);
            textInputchatlieu.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu kich co khong de dc trong

    private boolean validateKichCoSanpham() {
        String kichco = textInputkichco.getEditText().getText().toString().trim();
        if (kichco.isEmpty()) {
            textInputkichco.setError("K??ch c??? s???n ph???m kh??ng ????? tr???ng");
            return false;
        } else {
            textInputkichco.setError(null);
            textInputkichco.setErrorEnabled(false);
            textInputkichco.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu nam khong de dc trong

    private boolean validateNam() {
        String nam = textInputnamst.getEditText().getText().toString().trim();
        if (nam.isEmpty()) {
            textInputnamst.setError("N??m kh??ng ????? tr???ng");
            return false;
        } else if (nam.length() > 4) {
            textInputnamst.setError("N??m kh??ng d??i h??n 4 ch??? s???");
            return false;
        } else {
            textInputnamst.setError(null);
            textInputnamst.setErrorEnabled(false);
            textInputnamst.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    //ham check du lieu mo ta sp khong de dc trong

    private boolean validateMoTaSanpham() {
        String mota = textInputmota.getEditText().getText().toString().trim();
        if (mota.isEmpty()) {
            textInputmota.setError("M?? t??? s???n ph???m kh??ng ????? tr???ng");
            return false;
        } else {
            textInputmota.setError(null);
            textInputmota.setErrorEnabled(false);
            textInputmota.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    private void conf???mInput() {
        if (!validateTenSanpham() | !validateMoTaSanpham() | !validateGiaSanpham() | !validateKichCoSanpham() |
                !validateChatlieuSanpham() | !validateNam())//du lieu khong con trong
        {
            return;
        }
        SuaSanPham();
    }

    private void RequestPermissons() {
        PermissionListener permissionlistener = new PermissionListener() {

            // nguoi dung  cho phep truy cap vao permission
            @Override
            public void onPermissionGranted() {
                OpenGlary();//ham cho nguoi dung chon anh tu thiet bi
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

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        Uri uri = data.getData();// uri la du lieu anh ma nguoi dung chon
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imageView.setImageBitmap(bitmap);//set anh len bitmap
                            getStringImage(bitmap);
                            //getStringImage(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );
    private void OpenGlary() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activityResultLauncher.launch(Intent.createChooser(intent, "Ch???n ???nh"));

        String anh = "http://" + Server.HOST + "image/Products/" + sanPham.getPoto_product();

        Glide.with(this)
                .load(anh)
                .centerCrop()
                .error(R.drawable.ic_launcher_background)
                .into(imageView);
    }

    //ham chuyen bitmap sang string
    public void  getStringImage(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        byte[] image =byteArrayOutputStream.toByteArray();
        encodeImageString=android.util.Base64.encodeToString(image, Base64.DEFAULT);
    }

    private void GetCatalog() {
        tendamhmList = new ArrayList<DanhMucSanPham>();
        //g???i request
        StringRequest StringRequest = new StringRequest(Request.Method.GET,
                Server.GETDANHMUC, new Response.Listener<String>() {

            @Override
            //ket noi thanh cong
            public void onResponse(String response) {

                try {
                    JSONArray danhmuc = new JSONArray(response);

                    for (int i = 0; i < danhmuc.length(); i++) {
                        JSONObject DanhmucObject = danhmuc.getJSONObject(i);
                        iddanhmuc = DanhmucObject.getInt("id_catalog");
                        String tendanhmuc = DanhmucObject.getString("name_catalog");


                        tendamhmList.add(new DanhMucSanPham(iddanhmuc, tendanhmuc));

                        danhMucSanPhamAdapter = new ArrayAdapter<DanhMucSanPham>(getApplicationContext(),
                                android.R.layout.simple_spinner_item, tendamhmList);
                        danhMucSanPhamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnner_dm.setAdapter(danhMucSanPhamAdapter);
                        //spnner_dm.setSelection(sanPham.getId_catlog());//get vi tri hien tai cua danh muc

                        spnner_dm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int postion, long l) {

                                DanhMucSanPham danhMucSanPham=(DanhMucSanPham)adapterView.getItemAtPosition(postion);
                                iddanhmuc=danhMucSanPham.getIddm();
                                //Toast.makeText(getApplication(),String.valueOf(danhMucSanPham.getIddm()),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            //ket noi that bai
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);// t???o request len server
        requestQueue.add(StringRequest);
    }

    private void SuaSanPham() {

        String ten = textInputtensp.getEditText().getText().toString().trim();
        String gia = textInpugiasp.getEditText().getText().toString().trim();
        String chatlieu = textInputchatlieu.getEditText().getText().toString().trim();
        String kichco = textInputkichco.getEditText().getText().toString().trim();
        String nam = textInputnamst.getEditText().getText().toString().trim();
        String mota = textInputmota.getEditText().getText().toString().trim();
        String ghichu = textInputtenghichu.getEditText().getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.SUASANPHAM, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String suscess = jsonObject.getString("success");
                    if (suscess.equals("1")) {
                        Toast.makeText(getApplicationContext(), "S???a th??nh c??ng", Toast.LENGTH_SHORT).show();
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

                Toast.makeText(getApplicationContext(), "L???i s???a s???n ph???m", Toast.LENGTH_LONG).show();

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("name_product", ten);
                hashMap.put("poto_product",encodeImageString);
                hashMap.put("price_product", gia);
                hashMap.put("product_material", chatlieu);
                hashMap.put("product_dimensions", kichco);
                hashMap.put("year_of_creation", nam);
                hashMap.put("product_description", mota);
                hashMap.put("note_products", ghichu);
                hashMap.put("id_catalog", String.valueOf(iddanhmuc));
                hashMap.put("id_product", String.valueOf(sanPham.getId_product()));
                return hashMap;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }

}
