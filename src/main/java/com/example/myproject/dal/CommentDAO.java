package com.example.myproject.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.myproject.model.Comment;
import com.example.myproject.model.Product;
import com.example.myproject.model.User;

public class CommentDAO extends DBContext {
	public List<Comment> getCommentsByPid(int pid){
		List<Comment> list=new ArrayList<>();
		String sql="select *from comment where id_sp="+pid+" order by id desc";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				ProductDAO pDao=new ProductDAO();
				Product product=pDao.getProductById(pid);
				UserDAO uDao=new UserDAO();
				User user=uDao.getUserById(rs.getInt("id_kh"));				
				list.add(new Comment(rs.getInt("id"),product,user,rs.getString("content"),rs.getInt("vote"),rs.getDate("date")));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return list;
		
	}
	public void addComment(int pid,int uid,int numbstar,String content) {
		String sql="INSERT INTO `furniture_store`.`comment` "
				+ "(`id_sp`, `id_kh`, `content`, `vote`) "
				+ "VALUES (?,?,?,?)";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, pid);
			ps.setInt(2, uid);
			ps.setString(3, content);
			ps.setInt(4, numbstar);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
