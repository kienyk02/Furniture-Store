package com.example.myproject.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.example.myproject.model.*;

public class UserDAO extends DBContext {
	public User getUser(String username,String password){
		User u=null; 
		try {
			PreparedStatement ps=connection.prepareStatement("select *from khach_hang where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				u=new User(rs.getInt("id"),rs.getString("username"), rs.getString("password"),rs.getString("email"),
						rs.getString("ten"),rs.getString("sdt"),rs.getString("diachi"),rs.getInt("role"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("kết nối thất bại");
		}
		return u;

	}
	public User getUserById(int id){
		User u=null; 
		try {
			PreparedStatement ps=connection.prepareStatement("select *from khach_hang where id=?");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				u=new User(rs.getInt("id"),rs.getString("username"), rs.getString("password"),rs.getString("email"),
						rs.getString("ten"),rs.getString("sdt"),rs.getString("diachi"),rs.getInt("role"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("kết nối thất bại");
		}
		return u;

	}
	public void insert(String username,String password,String email) {
		try {
			PreparedStatement ps=connection.prepareStatement(" insert into khach_hang  values(?,?,?,?,'','','',1) ");
			ps.setInt(1, getMaxID()+1);
			ps.setString(2, username);
			ps.setString(3, password);
			ps.setString(4, email);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("kết nối thất bại");
		}
	}
	public void update(User user) {
		try {
			PreparedStatement ps=connection.prepareStatement("update khach_hang set ten=?,sdt=?,email=?,diachi=? where username=?");
			ps.setString(1, user.getTen());
			ps.setString(2, user.getSdt());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getDiachi());
			ps.setString(5, user.getUsername());
			ps.executeUpdate();
			System.out.println("Update User info success!");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Update User info fail!");
		}
	}
	public void changePassword(String username,String password) {
		try {
			PreparedStatement ps=connection.prepareStatement("update khach_hang set password=? where username=?");
			ps.setString(1, password);
			ps.setString(2, username);
			ps.executeUpdate();
			System.out.println("Change password success!");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Change password fail!");
		}
	}
	public boolean checkUser(String username) {
		try {
			PreparedStatement ps=connection.prepareStatement("select *from khach_hang where username=?");
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	public int getMaxID() {
		int m=0;
		try {
			PreparedStatement ps=connection.prepareStatement("select max(id) as maxid from khach_hang");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				m=rs.getInt("maxid");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("kết nối thất bại");
		}
		return m;
	}
}
