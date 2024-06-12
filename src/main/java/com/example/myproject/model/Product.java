package com.example.myproject.model;

import org.springframework.boot.context.properties.bind.DefaultValue;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class Product {
    private int id;
    @NotEmpty
    private String anh;
    @NotEmpty
    private String ten;
    private int gia_cu;
    @Min(1)
    private int gia_moi=1;
    private int yeu_thich;
    @Min(1)
    private int so_luong=1;
    private int da_ban;
    private String info;
    private Category category;

    public Product() {
    	
    }
    public Product(int id, String anh, String ten, int gia_cu, int gia_moi, int yeu_thich, int so_luong, int da_ban, String info, Category category) {
        this.id = id;
        this.anh = anh;
        this.ten = ten;
        this.gia_cu = gia_cu;
        this.gia_moi = gia_moi;
        this.yeu_thich = yeu_thich;
        this.so_luong = so_luong;
        this.da_ban = da_ban;
        this.info = info;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getGia_cu() {
        return gia_cu;
    }

    public void setGia_cu(int gia_cu) {
        this.gia_cu = gia_cu;
    }

    public int getGia_moi() {
        return gia_moi;
    }

    public void setGia_moi(int gia_moi) {
        this.gia_moi = gia_moi;
    }

    public int getYeu_thich() {
        return yeu_thich;
    }

    public void setYeu_thich(int yeu_thich) {
        this.yeu_thich = yeu_thich;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    public int getDa_ban() {
        return da_ban;
    }

    public void setDa_ban(int da_ban) {
        this.da_ban = da_ban;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
	@Override
	public String toString() {
		return "Product [id=" + id + ", anh=" + anh + ", ten=" + ten + ", gia_cu=" + gia_cu + ", gia_moi=" + gia_moi
				+ ", yeu_thich=" + yeu_thich + ", so_luong=" + so_luong + ", da_ban=" + da_ban + ", info=" + info
				+ ", category=" + category.getId() + "]";
	}
    
    
    
}
