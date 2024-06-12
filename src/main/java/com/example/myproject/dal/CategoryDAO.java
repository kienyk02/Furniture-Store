package com.example.myproject.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.loader.ParallelWebappClassLoader;

import com.example.myproject.model.*;


public class CategoryDAO extends DBContext {
	public List<Category> getAll(){
		List<Category> list=new ArrayList<>();
		try {
			PreparedStatement ps=connection.prepareStatement("select *from category");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				Category c=new Category(rs.getInt("id"), rs.getString("name"), rs.getString("describe"));
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return list;
		
	}
	public void insert(Category c) {
		String sql="insert into category values(?,?,?)";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, getMaxID()+1);
			ps.setString(2, c.getName());
			ps.setString(3, c.getDescribe());
			ps.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void update(Category c) {
		String sql="update category set `name`=?, `describe`=? where id=?";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, c.getName());
			ps.setString(2, c.getDescribe());
			ps.setInt(3, c.getId());
			ps.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void delete(int id) {
		String sql="delete from category where id="+id;
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	public Category getCategoryById(int id){
		Category c=new Category();
		try {
			PreparedStatement ps=connection.prepareStatement("select *from category where id="+id);
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {
				c=new Category(rs.getInt("id"), rs.getString("name"), rs.getString("describe"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return c;
		
	}
	public Map<String,Integer> getStatisticalQuantityByCategory() {
		Map <String,Integer> map=new LinkedHashMap<>();
		String sql="select category.name,sum(san_pham.da_ban) as 'so_luong' from category inner"+
		" join san_pham where category.id=san_pham.categoryID group by category.id";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("name"),rs.getInt("so_luong"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}
	public int getMaxID() {
		int m=0;
		try {
			PreparedStatement ps=connection.prepareStatement("select max(id) as maxid from category");
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
	public static void main(String[] args) {
		 CategoryDAO c=new CategoryDAO();
	     List<Category> list=c.getAll();
	     System.out.println(list.get(0).getDescribe());
	}
}
