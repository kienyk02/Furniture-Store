package com.example.myproject.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.myproject.model.Cart;
import com.example.myproject.model.Order;
import com.example.myproject.model.OrderSp;
import com.example.myproject.model.Product;
import com.example.myproject.model.User;

public class OrderSpDAO extends DBContext{
	public void createAddOrderSP(Order order, Cart cart) {
		try {
			PreparedStatement ps=connection.prepareStatement("insert into `furniture_store`.`order_sp` values(?,?,?,?)");
			ps.setInt(1, getMaxID()+1);
			ps.setInt(2, order.getId());
			ps.setInt(3, cart.getProduct().getId());
			ps.setInt(4, cart.getSo_luong());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("kết nối thất bại siu");
		}
	}
	public List<OrderSp> getOrderSpsByOrder(Order order) {
		List<OrderSp> ordersps=new ArrayList<>();
		String sql="select * from `furniture_store`.`order_sp` where id_order=? order by id desc";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, order.getId());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				ProductDAO pDao=new ProductDAO();
				Product p=pDao.getProductById(rs.getInt("id_sp"));
				ordersps.add(new OrderSp(rs.getInt("id"),order,p,rs.getInt("so_luong")));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("kết nối thất bại ordersp");
		}
		return ordersps;
	}
	public boolean checkSold(int uid,int pid) {
		String sql="select *from khach_hang"
				+ " inner join furniture_store.order on khach_hang.id=furniture_store.order.id_kh"
				+ " inner join order_sp on furniture_store.order.id=order_sp.id_order"
				+ " where khach_hang.id=? and order_sp.id_sp=?";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, uid);
			ps.setInt(2, pid);
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
			PreparedStatement ps=connection.prepareStatement("select max(id) as maxid from `furniture_store`.`order_sp`");
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
