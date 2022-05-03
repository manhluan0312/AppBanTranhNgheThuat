package com.example.myapplication.Model;

import java.io.Serializable;

public class GioHang implements Serializable {

    int idsp;
    String tensanpham;
    float giasanpham;
    int soluongsanpham;
    String hinhanhsanpham;

    public GioHang(int idsp, String tensanpham, float giasanpham, int soluongsanpham, String hinhanhsanpham) {
        this.idsp = idsp;
        this.tensanpham = tensanpham;
        this.giasanpham = giasanpham;
        this.soluongsanpham = soluongsanpham;
        this.hinhanhsanpham = hinhanhsanpham;
    }

    public GioHang() {
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public float getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(float giasanpham) {
        this.giasanpham = giasanpham;
    }

    public int getSoluongsanpham() {
        return soluongsanpham;
    }

    public void setSoluongsanpham(int soluongsanpham) {
        this.soluongsanpham = soluongsanpham;
    }

    public String getHinhanhsanpham() {
        return hinhanhsanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        this.hinhanhsanpham = hinhanhsanpham;
    }
}
