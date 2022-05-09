package com.example.myapplication.Activity.Other;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Activity.Custumer.MainActivity;
import com.example.myapplication.Activity.Custumer.RegistrationActivity;
import com.example.myapplication.Activity.Other.LoginActivity;
import com.example.myapplication.Activity.admin.AdminActivity;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Server;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class sharedPreferences_Login {
    SharedPreferences sharedPreferences,sharedPreferences2;
    public Context context;
    SharedPreferences.Editor editor;

    public static final String ISLOGIN = "islogin";

    public sharedPreferences_Login(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("datalogin_custumer", Context.MODE_PRIVATE);
        //sharedPreferences2=context.getSharedPreferences("datalogin_admin",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public boolean hasLoginCustumer() {
        return sharedPreferences.getBoolean(ISLOGIN, false);
    }

    public boolean hasLoginAdmin() {
        return sharedPreferences.getBoolean(ISLOGIN, false);
    }


    public void checkLogin() {
        if (hasLoginCustumer()) {
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
            ((MainActivity) context).finish();
        } else {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            ((MainActivity) context).finish();
        }
        if (hasLoginAdmin()) {
            Intent intent = new Intent(context, AdminActivity.class);
            context.startActivity(intent);
            ((AdminActivity) context).finish();
        } else {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            ((AdminActivity) context).finish();
        }
    }

    public void PutDataLoginsharedPreferences(int id, String username, String password, String anh, String hoten, String email, String sdt, String diachi) {
        editor.putBoolean(ISLOGIN, true);
        editor.putInt("id", id);
        editor.putString("Username", username);
        editor.putString("password", password);
        editor.putString("anh", anh);
        editor.putString("hoten", hoten);
        editor.putString("email", email);
        editor.putString("sdt", sdt);
        editor.putString("diachi", diachi);
        editor.apply();
    }

    public void PutEditProfile(int id, String username, String hoten, String email, String sdt, String diachi) {
        editor.putInt("id", id);
        editor.putString("Username", username);
        editor.putString("hoten", hoten);
        editor.putString("email", email);
        editor.putString("sdt", sdt);
        editor.putString("diachi", diachi);
        editor.apply();
    }
}


