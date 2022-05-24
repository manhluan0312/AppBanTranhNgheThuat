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
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.myapplication.Activity.admin.AdminActivity;
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

public class AddProductFragment extends Fragment {

    View view;
    AdminActivity adminActivity;


    TextInputLayout textInputtensp, textInpugiasp, textInputchatlieu,
            textInputkichco, textInputnamst, textInputmota, textInputtenghichu;
    ImageView imageView;
    Button btn_themsanpham;

    SanPham sanPham;
    Spinner spnner_dm;
    ArrayList<DanhMucSanPham> tendamhmList;
    ArrayAdapter<DanhMucSanPham> danhMucSanPhamAdapter;
    int iddanhmuc;
    String encodeImageString;//ten anh sau khi covert


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_product, container, false);
        AnhXa();
        GetCatalog();

        btn_themsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confỉmInput();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestPermissons();//check quyen nguoi dung
            }
        });

        return view;
    }

    private void AnhXa() {

        textInputtensp = view.findViewById(R.id.textinput_tensanpham);
        textInpugiasp = view.findViewById(R.id.textinput_giasanpham);
        textInputchatlieu = view.findViewById(R.id.textinput_chatlieusanpham);
        textInputkichco = view.findViewById(R.id.textinput_kichcosanpham);
        textInputnamst = view.findViewById(R.id.textinput_namsangtacsanpham);
        textInputmota = view.findViewById(R.id.textinput_motasanpham);
        textInputtenghichu = view.findViewById(R.id.textinput_ghichusanpham);
        imageView = view.findViewById(R.id.img_anhsp);
        spnner_dm = view.findViewById(R.id.spnner_dm);
        btn_themsanpham = view.findViewById(R.id.btn_themsanpham);
    }

    //ham check du lieu tensanpham khong de dc trong

    private boolean validateTenSanpham() {
        String ten = textInputtensp.getEditText().getText().toString().trim();
        if (ten.isEmpty()) {
            textInputtensp.setError("Tên sản phẩm không để trống");
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
            textInpugiasp.setError("Giá sản phẩm không để trống");
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
            textInputchatlieu.setError("Chất liệu sản phẩm không để trống");
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
            textInputkichco.setError("Kích cỡ sản phẩm không để trống");
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
            textInputnamst.setError("Năm không để trống");
            return false;
        } else if (nam.length() > 4) {
            textInputnamst.setError("Năm không dài hơn 4 chữ số");
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
            textInputmota.setError("Mô tả sản phẩm không để trống");
            return false;
        } else {
            textInputmota.setError(null);
            textInputmota.setErrorEnabled(false);
            textInputmota.setHelperTextEnabled(false);//dong goi y se bien mat neu co nhap du lieu
            return true;
        }
    }

    private void confỉmInput() {
        if (!validateTenSanpham() | !validateMoTaSanpham() | !validateGiaSanpham() | !validateKichCoSanpham() |
                !validateChatlieuSanpham() | !validateNam())//du lieu khong con trong
        {
            return;
        }
        ThemSanPham();
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
                Toast.makeText(getActivity(), "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
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
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
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
        activityResultLauncher.launch(Intent.createChooser(intent, "Chọn ảnh"));

    }

    //ham chuyen bitmap sang string
    public void getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] image = byteArrayOutputStream.toByteArray();
        encodeImageString = android.util.Base64.encodeToString(image, Base64.DEFAULT);
    }


    private void GetCatalog() {
        tendamhmList = new ArrayList<DanhMucSanPham>();
        //gọi request
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

                        danhMucSanPhamAdapter = new ArrayAdapter<DanhMucSanPham>(getActivity(),
                                android.R.layout.simple_spinner_item, tendamhmList);
                        danhMucSanPhamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnner_dm.setAdapter(danhMucSanPhamAdapter);
                        //spnner_dm.setSelection(danhMucSanPhamAdapter.getPosition(sanPham.getName_catalog()));//get vi tri hien tai cua danh muc


                        spnner_dm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int postion, long l) {

                                DanhMucSanPham danhMucSanPham = (DanhMucSanPham) adapterView.getItemAtPosition(postion);
                                iddanhmuc = danhMucSanPham.getIddm();

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

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());// tạo request len server
        requestQueue.add(StringRequest);
    }

    private void ThemSanPham() {

        //int iddanhmuc=;
        String ten = textInputtensp.getEditText().getText().toString().trim();
        String gia = textInpugiasp.getEditText().getText().toString().trim();
        String chatlieu = textInputchatlieu.getEditText().getText().toString().trim();
        String kichco = textInputkichco.getEditText().getText().toString().trim();
        String nam = textInputnamst.getEditText().getText().toString().trim();
        String mota = textInputmota.getEditText().getText().toString().trim();
        String ghichu = textInputtenghichu.getEditText().getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.THEMSANPHAM, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String suscess = jsonObject.getString("success");
                    if (suscess.equals("1")) {
                        Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), AdminActivity.class);
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
                hashMap.put("name_product", ten);
                hashMap.put("poto_product",encodeImageString);
                hashMap.put("price_product", gia);
                hashMap.put("product_material", chatlieu);
                hashMap.put("product_dimensions", kichco);
                hashMap.put("year_of_creation", nam);
                hashMap.put("product_description", mota);
                hashMap.put("note_products", ghichu);
                hashMap.put("id_catalog", String.valueOf(iddanhmuc));
                return hashMap;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);
    }
}
