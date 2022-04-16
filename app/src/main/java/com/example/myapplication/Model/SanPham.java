package com.example.myapplication.Model;

import java.io.Serializable;

public class SanPham implements Serializable {
    int id_product;
    String name_product;
    String poto_product;
    int price_product;
    String product_material;
    String product_dimensions;
    int year_of_creation;
    String product_description;
    String note_products;
    String name_catalog;

    public SanPham(int id_product, String name_product, String poto_product, int
            price_product, String product_material, String product_dimensions, int year_of_creation, String product_description, String note_products, String name_catalog) {
        this.id_product = id_product;
        this.name_product = name_product;
        this.poto_product = poto_product;
        this.price_product = price_product;
        this.product_material = product_material;
        this.product_dimensions = product_dimensions;
        this.year_of_creation = year_of_creation;
        this.product_description = product_description;
        this.note_products = note_products;
        this.name_catalog = name_catalog;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getPoto_product() {
        return poto_product;
    }

    public void setPoto_product(String poto_product) {
        this.poto_product = poto_product;
    }

    public int getPrice_product() {
        return price_product;
    }

    public void setPrice_product(int price_product) {
        this.price_product = price_product;
    }

    public String getProduct_material() {
        return product_material;
    }

    public void setProduct_material(String product_material) {
        this.product_material = product_material;
    }

    public String getProduct_dimensions() {
        return product_dimensions;
    }

    public void setProduct_dimensions(String product_dimensions) {
        this.product_dimensions = product_dimensions;
    }

    public int getYear_of_creation() {
        return year_of_creation;
    }

    public void setYear_of_creation(int year_of_creation) {
        this.year_of_creation = year_of_creation;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getName_catalog() {
        return name_catalog;
    }

    public void setName_catalog(String name_catalog) {
        this.name_catalog = name_catalog;
    }
}
