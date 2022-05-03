package com.example.myapplication.Model;

public class StatisticsCatalog {
    String tendanhmuc;
    int Soluongdanhmuc;
    float Giacaonhat;
    float Giathapnhat;
    float Giatrungbinh;

    public StatisticsCatalog(String tendanhmuc, int soluongdanhmuc, float giacaonhat, float giathapnhat, float giatrungbinh) {
        this.tendanhmuc = tendanhmuc;
        Soluongdanhmuc = soluongdanhmuc;
        Giacaonhat = giacaonhat;
        Giathapnhat = giathapnhat;
        Giatrungbinh = giatrungbinh;
    }

    public String getTendanhmuc() {
        return tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
    }

    public int getSoluongdanhmuc() {
        return Soluongdanhmuc;
    }

    public void setSoluongdanhmuc(int soluongdanhmuc) {
        Soluongdanhmuc = soluongdanhmuc;
    }

    public float getGiathapnhat() {
        return Giathapnhat;
    }

    public void setGiathapnhat(float giathapnhat) {
        Giathapnhat = giathapnhat;
    }

    public float getGiacaonhat() {
        return Giacaonhat;
    }

    public void setGiacaonhat(float giacaonhat) {
        Giacaonhat = giacaonhat;
    }

    public float getGiatrungbinh() {
        return Giatrungbinh;
    }

    public void setGiatrungbinh(float giatrungbinh) {
        Giatrungbinh = giatrungbinh;
    }
}
