package com.example.myproject.model;

import jakarta.validation.constraints.NotEmpty;

public class User {
    private int id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private String email;
    private String ten;
    @NotEmpty
    private String sdt;
    @NotEmpty
    private String diachi;
    private int role;
    public User() {
    }

    public User(int id, String username, String password, String email, String ten, String sdt, String diachi,int role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.ten = ten;
        this.sdt = sdt;
        this.diachi = diachi;
        this.role=role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
    
    
}

