package com.example.myproject.model;

public class Cart {
    private int id;
    private Product product;
    private int so_luong;

    public Cart() {
    }

    public Cart(int id, Product product, int so_luong) {
        this.id = id;
        this.product = product;
        this.so_luong = so_luong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }
    
}
