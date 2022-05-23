package com.example.myapplication.Model;

public class QuanLyDonHang {
    int madonhang;
    String tenkhachhang,sodienthoai,hinhthucthanhtoan,ngaydathang,diachigiaohang,ghichu,trangthaidonhang;
    float tongtien;

    public QuanLyDonHang(int madonhang, String tenkhachhang, String sodienthoai, String hinhthucthanhtoan,
                         String ngaydathang, String diachigiaohang, String ghichu, String trangthaidonhang, float tongtien) {
        this.madonhang = madonhang;
        this.tenkhachhang = tenkhachhang;
        this.sodienthoai = sodienthoai;
        this.hinhthucthanhtoan = hinhthucthanhtoan;
        this.ngaydathang = ngaydathang;
        this.diachigiaohang = diachigiaohang;
        this.ghichu = ghichu;
        this.trangthaidonhang = trangthaidonhang;
        this.tongtien = tongtien;
    }

    public QuanLyDonHang() {
    }

    public int getMadonhang() {
        return madonhang;
    }

    public void setMadonhang(int madonhang) {
        this.madonhang = madonhang;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getHinhthucthanhtoan() {
        return hinhthucthanhtoan;
    }

    public void setHinhthucthanhtoan(String hinhthucthanhtoan) {
        this.hinhthucthanhtoan = hinhthucthanhtoan;
    }

    public String getNgaydathang() {
        return ngaydathang;
    }

    public void setNgaydathang(String ngaydathang) {
        this.ngaydathang = ngaydathang;
    }

    public String getDiachigiaohang() {
        return diachigiaohang;
    }

    public void setDiachigiaohang(String diachigiaohang) {
        this.diachigiaohang = diachigiaohang;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getTrangthaidonhang() {
        return trangthaidonhang;
    }

    public void setTrangthaidonhang(String trangthaidonhang) {
        this.trangthaidonhang = trangthaidonhang;
    }

    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }
}
