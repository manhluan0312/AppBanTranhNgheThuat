package com.example.myapplication.Model;

import java.io.Serializable;

public class DanhMucSanPham implements Serializable {
    int iddm;
    String tendanhmuc;

    public DanhMucSanPham(int iddm, String tendanhmuc) {
        this.iddm = iddm;
        this.tendanhmuc = tendanhmuc;
    }

    public int getIddm() {
        return iddm;
    }

    public void setIddm(int iddm) {
        this.iddm = iddm;
    }

    public String getTendanhmuc() {
        return tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
    }

    @Override
    public String toString() {
        return tendanhmuc;
    }
}
