package com.example.myproject.dal;

import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.myproject.model.*;

public class CartDAO extends DBContext {
	public List<Cart> getCartsByUserid(int userid){
		List<Cart> list=new ArrayList<>();
		String sql="select *from gio_hang where id_kh="+userid+" order by id desc";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				ProductDAO pDao=new ProductDAO();
				Product p=pDao.getProductById(rs.getInt("id_sp"));
				Cart cart=new Cart(rs.getInt("id"),p,rs.getInt("so_luong"));
				list.add(cart);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return list;
	}
	public Boolean checkCart(int userid,int productid){
		String sql="select *from gio_hang where id_kh=? and id_sp=?";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, userid);
			ps.setInt(2, productid);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return false;
	}
	public void add(int userid,int productid,int soluong){
		String sql="insert into gio_hang values(?,?,?,?)";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, getMaxID()+1);
			ps.setInt(2, userid);
			ps.setInt(3, productid);
			ps.setInt(4, soluong);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	public void increase(int userid,int productid,int soluong){
		String sql="update gio_hang set so_luong=so_luong+? where id_kh=? and id_sp=?";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, soluong);
			ps.setInt(2, userid);
			ps.setInt(3, productid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	public void delete(int cartid) {
		String sql="delete from gio_hang where id=?";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, cartid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	public int getMaxID() {
		int m=0;
		try {
			PreparedStatement ps=connection.prepareStatement("select max(id) as maxid from gio_hang");
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
