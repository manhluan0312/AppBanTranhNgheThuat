package com.example.myapplication.Activity;

import android.content.Context;
import android.content.SharedPreferences;

public class sharedPreferences_Login {
    SharedPreferences sharedPreferences;
    public Context context;
    SharedPreferences.Editor editor;

    public sharedPreferences_Login(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences("datalogin_custumer",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void PutDataLoginsharedPreferences(int id, String username, String password, String anh, String hoten, String email, String sdt, String diachi) {
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
}
