package com.example.myproject.controller;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.myproject.dal.CommentDAO;
import com.example.myproject.dal.OrderSpDAO;
import com.example.myproject.model.Comment;
import com.example.myproject.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CommentController {

	@ResponseBody
	@GetMapping("/api/comment/{pid}")
	public List<Comment> getComments(@PathVariable String pid) throws IOException{
		List<Comment> list=new ArrayList<>(); 
		CommentDAO cmtDao=new CommentDAO();
		list=cmtDao.getCommentsByPid(Integer.parseInt(pid));
		return list;
	}
	
	@ResponseBody
	@PostMapping("/api/comment/add/{pid}")
	public boolean addComment(@PathVariable String pid,@RequestBody String content,HttpServletRequest request,HttpSession session) throws IOException{
		int numbstar=Integer.parseInt(request.getParameter("num"));
		User u=(User) session.getAttribute("account");
		OrderSpDAO oSpDAO=new OrderSpDAO();
		if(!oSpDAO.checkSold(u.getId(), Integer.parseInt(pid)) && u.getRole()!=2) {
			// nếu chưa mua sản phẩm và không phải là admin thì không thể cmt
			return false;
		}
		CommentDAO cmtDao=new CommentDAO();
		cmtDao.addComment(Integer.parseInt(pid), u.getId(), numbstar, content);
		return true;
	}
}
