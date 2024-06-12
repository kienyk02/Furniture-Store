package com.example.myproject.dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.myproject.model.*;

public class ProductDAO extends DBContext{
	public List<Product> getProductsByCid(int cid){
		List<Product> list=new ArrayList<>();
		String sql="select *from san_pham where 1=1";
		try {
			if(cid!=0) {
				sql+=" and categoryID="+cid;
			}
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				CategoryDAO ctCategoryDAO=new CategoryDAO();
				Category c=ctCategoryDAO.getCategoryById(rs.getInt("categoryID"));
				Product p=new Product(
						rs.getInt("id"),rs.getString("anh"), rs.getString("ten"), rs.getInt("gia_cu"),rs.getInt("gia_moi"),
						rs.getInt("yeu_thich"),rs.getInt("so_luong"),rs.getInt("da_ban"),rs.getString("info"),c);
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return list;
		
	}
	public List<Product> search(String key,String order,String orderPrice,int cid,int pageIndex){
		List<Product> list=new ArrayList<>();
		String sql="select *from san_pham where 1=1";
		if(key!=null && !key.equals("")) {
			sql+=" and (ten like '%"+key+"%' or info like '%"+key+"%' )";
		}
		if(cid!=0) {
			sql+=" and categoryID="+cid;
		}
		if(order!=null) {
			switch (order) {
			case "new": {
				sql+=" order by id desc";
				break;
			}
			case "discount": {
				sql+=" and gia_cu>0";
				break;
			}
			case "sales": {
				sql+=" order by da_ban desc";
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + order);
			}
		}
		if(orderPrice!=null && orderPrice!="") {
			sql+=" order by gia_moi " +orderPrice;
		}
		if(pageIndex>0) {
			sql+=" limit "+(pageIndex-1)*12+",12";			
		}
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				CategoryDAO ctCategoryDAO=new CategoryDAO();
				Category c=ctCategoryDAO.getCategoryById(rs.getInt("categoryID"));
				Product p=new Product(
						rs.getInt("id"),rs.getString("anh"), rs.getString("ten"), rs.getInt("gia_cu"),rs.getInt("gia_moi"),
						rs.getInt("yeu_thich"),rs.getInt("so_luong"),rs.getInt("da_ban"),rs.getString("info"),c);
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return list;
	}
	public int getPageNumber(String key,String order,String orderPrice,int cid){
		int count=0;
		String sql="select count(*) as 'sosp' from san_pham where 1=1";
		if(key!=null && !key.equals("")) {
			sql+=" and (ten like '%"+key+"%' or info like '%"+key+"%' )";
		}
		if(cid!=0) {
			sql+=" and categoryID="+cid;
		}
		if(order!=null) {
			switch (order) {
			case "new": {
				sql+=" order by id desc";
				break;
			}
			case "discount": {
				sql+=" and gia_cu>0";
				break;
			}
			case "sales": {
				sql+=" order by da_ban desc";
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + order);
			}
		}
		if(orderPrice!=null && orderPrice!="") {
			sql+=" order by gia_moi " +orderPrice;
		}
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				count =(int)Math.ceil((float)rs.getInt("sosp")/12);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return count;
	}
	public Product getProductById(int id){
		Product p=new Product();
		String sql="select *from san_pham where id="+id;
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				CategoryDAO ctCategoryDAO=new CategoryDAO();
				Category c=ctCategoryDAO.getCategoryById(rs.getInt("categoryID"));
				p=new Product(
						rs.getInt("id"),rs.getString("anh"), rs.getString("ten"), rs.getInt("gia_cu"),rs.getInt("gia_moi"),
						rs.getInt("yeu_thich"),rs.getInt("so_luong"),rs.getInt("da_ban"),rs.getString("info"),c);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return p;
		
	}
	public void updateProduct(Product product){
		String sql="update san_pham set ten=?,anh=?,categoryID=?,info=?,gia_moi=?,gia_cu=?,so_luong=? where id="+product.getId();
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, product.getTen());
			ps.setString(2, product.getAnh());
			ps.setInt(3, product.getCategory().getId());
			ps.setString(4, product.getInfo());
			ps.setInt(5, product.getGia_moi());
			ps.setInt(6, product.getGia_cu());
			ps.setInt(7, product.getSo_luong());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	public void insertProduct(Product product){
		String sql="insert into san_pham values(?,?,?,?,?,0,?,0,?,?) ";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, getMaxID()+1);
			ps.setString(2, product.getAnh());
			ps.setString(3, product.getTen());
			ps.setInt(4, product.getGia_cu());
			ps.setInt(5, product.getGia_moi());
			ps.setInt(6, product.getSo_luong());
			ps.setString(7, product.getInfo());
			ps.setInt(8, product.getCategory().getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	public void deleteProduct(int id) {
		String sql="delete from san_pham where id= "+id;
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	public void updateProductSale(int id,int sl) {
		String sql="update san_pham set da_ban=da_ban+? where id=?";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, sl);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	public int getMaxID() {
		int m=0;
		try {
			PreparedStatement ps=connection.prepareStatement("select max(id) as maxid from san_pham");
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
