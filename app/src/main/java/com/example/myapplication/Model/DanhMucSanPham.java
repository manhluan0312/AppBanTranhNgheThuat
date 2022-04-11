package com.example.myapplication.Model;

import java.io.Serializable;

public class DanhMucSanPham implements Serializable {
    String iddm;
    String tendanhmuc;

    public DanhMucSanPham() {
    }

    public DanhMucSanPham(String iddm, String tendanhmuc) {
        this.iddm = iddm;
        this.tendanhmuc = tendanhmuc;
    }

    public String getIddm() {
        return iddm;
    }

    public void setIddm(String iddm) {
        this.iddm = iddm;
    }

    public String getTendanhmuc() {
        return tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
    }
}


