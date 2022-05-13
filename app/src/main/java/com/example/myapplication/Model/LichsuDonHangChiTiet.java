package com.example.myapplication.Model;

public class LichsuDonHangChiTiet {
    String hinhanhsanpham,tensanpham;
    float Giasanpham;
    int soluong;

    public LichsuDonHangChiTiet(String hinhanhsanpham, String tensanpham, float giasanpham, int soluong) {
        this.hinhanhsanpham = hinhanhsanpham;
        this.tensanpham = tensanpham;
        Giasanpham = giasanpham;
        this.soluong = soluong;
    }

    public String getHinhanhsanpham() {
        return hinhanhsanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        this.hinhanhsanpham = hinhanhsanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public float getGiasanpham() {
        return Giasanpham;
    }

    public void setGiasanpham(float giasanpham) {
        Giasanpham = giasanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
