package com.example.myproject.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.myproject.dal.OrderDao;
import com.example.myproject.dal.ProductDAO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class OrderController {
	@GetMapping("/updateOrder/{id}")
	public String updateOrderTT(@PathVariable String id,HttpServletRequest request) throws IOException{
		int ttint=Integer.parseInt(request.getParameter("tt"));
		String tt="";
		if(ttint==1) {
			tt="Chờ xác nhận";
		}else if(ttint==2) {
			tt="Đã xác nhận";
		}
		else if(ttint==3) {
			tt="Đang giao hàng";
		}else {
			tt="Hoàn thành";
		}
		OrderDao oDao=new OrderDao();
		oDao.updateOrderStatus(Integer.parseInt(id), tt);
		return "redirect:/controlmode/";
	}
	@DeleteMapping("/deleteOrder")
	@ResponseBody
	public void deleteOrder(HttpServletRequest request) throws IOException {
		OrderDao oDao=new OrderDao();
		oDao.deleteOrder(Integer.parseInt(request.getParameter("id")));
	}
}
