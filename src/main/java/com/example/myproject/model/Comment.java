package com.example.myproject.model;

import java.sql.Date;

public class Comment {
	private int id;
	private Product product;
	private User user;
	private String content;
	private int vote;
	private Date date;
	public Comment() {
		
	}

	public Comment(int id, Product product, User user, String content, int vote, Date date) {
		super();
		this.id = id;
		this.product = product;
		this.user = user;
		this.content = content;
		this.vote = vote;
		this.date = date;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
