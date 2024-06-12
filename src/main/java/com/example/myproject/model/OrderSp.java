package com.example.myproject.model;

public class OrderSp  {
	private int id;
	private Order order;
	private Product product;
	private int soluong;
	public OrderSp() {
		
	}
	public OrderSp(int id, Order order, Product product, int soluong) {
		super();
		this.id = id;
		this.order = order;
		this.product = product;
		this.soluong = soluong;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	
	
}
