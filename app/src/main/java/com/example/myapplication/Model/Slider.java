package com.example.myapplication.Model;

public class Slider {
    int id_slider;
     String titile_sider;
     String image_sider;

    public Slider(int id_slider, String titile_sider, String image_sider) {
        this.id_slider = id_slider;
        this.titile_sider = titile_sider;
        this.image_sider = image_sider;
    }

    public int getId_slider() {
        return id_slider;
    }

    public void setId_slider(int id_slider) {
        this.id_slider = id_slider;
    }

    public String getTitile_sider() {
        return titile_sider;
    }

    public void setTitile_sider(String titile_sider) {
        this.titile_sider = titile_sider;
    }

    public String getImage_sider() {
        return image_sider;
    }

    public void setImage_sider(String image_sider) {
        this.image_sider = image_sider;
    }
}
