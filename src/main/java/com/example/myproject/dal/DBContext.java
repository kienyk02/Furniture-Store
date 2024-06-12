package com.example.myproject.dal;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
	protected Connection connection;
	public DBContext() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/furniture_store", "root", "18082002");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
