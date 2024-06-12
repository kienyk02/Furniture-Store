package com.example.myproject.dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.myproject.model.Order;
import com.example.myproject.model.User;

public class OrderDao extends DBContext {
	public void createOrder(int id,User user,Date date) {
		String sql="INSERT INTO `furniture_store`.`order` VALUES (?, ?, ?, 'Chờ xác nhận',0)";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, user.getId());
			ps.setDate(3, date);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("kết nối thất bại");
		}
	}
	public List<Order> getOrderAll() {
		List<Order> orders=new ArrayList<>();
		String sql="select * from `furniture_store`.`order` order by id desc";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				UserDAO uDao=new UserDAO();
				User user=uDao.getUserById(rs.getInt("id_kh"));
				orders.add(new Order(rs.getInt("id"),user,rs.getDate("thoi_gian"),rs.getString("trang_thai"),rs.getInt("tong_tien")));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("kết nối thất bại siuuuuuuuuu");
		}
		return orders;
	}
	public List<Order> getOrdersByUser(User user) {
		List<Order> orders=new ArrayList<>();
		String sql="select * from `furniture_store`.`order` where id_kh=? order by id desc";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				orders.add(new Order(rs.getInt("id"),user,rs.getDate("thoi_gian"),rs.getString("trang_thai"),rs.getInt("tong_tien")));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("kết nối thất bại siuuuuuuuuu");
		}
		return orders;
	}
	public Order getOrderById(int idorder) {
		Order order=new Order();
		String sql="select * from `furniture_store`.`order` where id=?";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, idorder);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				UserDAO uDao=new UserDAO();
				order=new Order(rs.getInt("id"),uDao.getUserById(rs.getInt("id_kh")),rs.getDate("thoi_gian"),rs.getString("trang_thai"),rs.getInt("tong_tien"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("kết nối thất bại siuuuuuuuuu");
		}
		return order;
	}
	public void deleteOrder(int id) {
		String sql="delete from `furniture_store`.`order` where id=?";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("kết nối thất bại siuuuuuuuuu");
		}
	}
	public void updateOrderStatus(int id,String status) {
		String sql="update `furniture_store`.`order` set trang_thai=? where id=?";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("kết nối thất bại siuuuuuuuuu");
		}
	}
	public void updateOrderTotal(int id,int total) {
		String sql="update `furniture_store`.`order` set tong_tien=? where id=?";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, total);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("kết nối thất bại siuuuuuuuuu");
		}
	}
	public Map<Integer, Integer> getRevenueStatistics(){
		Map <Integer,Integer> map=new LinkedHashMap<>();
		String sql="select WEEK(`furniture_store`.order.thoi_gian) as Week ,sum(`furniture_store`.order.tong_tien)"+
				" as 'tong_tien' from `furniture_store`.order "+
				"group by Week";
				try {
					PreparedStatement ps=connection.prepareStatement(sql);
					ResultSet rs=ps.executeQuery();
					while (rs.next()) {
						map.put(rs.getInt("Week"),rs.getInt("tong_tien"));
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
		return map;
	}
	public int getMaxID() {
		int m=0;
		try {
			PreparedStatement ps=connection.prepareStatement("select max(id) as maxid from `furniture_store`.`order`");
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
