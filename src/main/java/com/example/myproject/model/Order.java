package com.example.myproject.model;

import java.sql.Date;

public class Order {
	private int id;
	private User user;
	private Date date;
	private String trang_thai;
	private int tong_tien;
	public Order() {
		
	}
	public Order(int id, User user, Date date, String trang_thai,int tong_tien) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.trang_thai = trang_thai;
		this.tong_tien=tong_tien;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTrang_thai() {
		return trang_thai;
	}
	public void setTrang_thai(String trang_thai) {
		this.trang_thai = trang_thai;
	}
	public int getTong_tien() {
		return tong_tien;
	}
	public void setTong_tien(int tong_tien) {
		this.tong_tien = tong_tien;
	}
	
	
}
