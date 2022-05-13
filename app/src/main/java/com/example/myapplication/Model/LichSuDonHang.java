package com.example.myapplication.Model;

import java.util.ArrayList;

public class LichSuDonHang {
    int madonhang;
    String NgayDatHang, HinhthucThanhToan, DiaChiGiaoHang, TrangThaiDonHang;
    float TongTien;

    public LichSuDonHang(int madonhang, String ngayDatHang, String hinhthucThanhToan, String diaChiGiaoHang, String trangThaiDonHang, float tongTien) {
        this.madonhang = madonhang;
        NgayDatHang = ngayDatHang;
        HinhthucThanhToan = hinhthucThanhToan;
        DiaChiGiaoHang = diaChiGiaoHang;
        TrangThaiDonHang = trangThaiDonHang;
        TongTien = tongTien;
    }

    public String getNgayDatHang() {
        return NgayDatHang;
    }

    public void setNgayDatHang(String ngayDatHang) {
        NgayDatHang = ngayDatHang;
    }

    public String getHinhthucThanhToan() {
        return HinhthucThanhToan;
    }

    public void setHinhthucThanhToan(String hinhthucThanhToan) {
        HinhthucThanhToan = hinhthucThanhToan;
    }

    public String getDiaChiGiaoHang() {
        return DiaChiGiaoHang;
    }

    public void setDiaChiGiaoHang(String diaChiGiaoHang) {
        DiaChiGiaoHang = diaChiGiaoHang;
    }

    public String getTrangThaiDonHang() {
        return TrangThaiDonHang;
    }

    public void setTrangThaiDonHang(String trangThaiDonHang) {
        TrangThaiDonHang = trangThaiDonHang;
    }

    public int getMadonhang() {
        return madonhang;
    }

    public void setMadonhang(int madonhang) {
        this.madonhang = madonhang;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setTongTien(float tongTien) {
        TongTien = tongTien;
    }
}
