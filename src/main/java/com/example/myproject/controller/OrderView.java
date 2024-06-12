package com.example.myproject.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.myproject.model.*;
import com.example.myproject.dal.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class OrderView {
	@GetMapping("order-view")
	public String orderView(Model model,HttpSession session) throws IOException{
		//lấy cart
		User u=(User) session.getAttribute("account");
		if(u!=null) {
			CartDAO cartDAO=new CartDAO();
			List<Cart> listCart=cartDAO.getCartsByUserid(u.getId());
			if(listCart.size()>0) {
				int tongtien=0;
				int soluongcart=0;
				for(Cart i:listCart) {
					tongtien+=i.getProduct().getGia_moi()*i.getSo_luong();
					soluongcart+=i.getSo_luong();
				}
				model.addAttribute("cartdata", listCart);			
				model.addAttribute("tongtiencart", tongtien);			
				model.addAttribute("soluongcart", soluongcart);	
			}
		}
		
		//truyền đối tượng user cho modal và form info
		// nếu updateinfo không trả về user lỗi thì nhận giá trị của account
		User user = (User) model.asMap().get("user");
		model.addAttribute("user", user!=null? user: u);		
		
		// Lấy order
		OrderDao oDao=new OrderDao();
		List<Order> orders=oDao.getOrdersByUser(u);
		model.addAttribute("orders",orders);
		// Lấy order-sp
		OrderSpDAO oSpDAO=new OrderSpDAO();
		List<OrderSp> ordersps=new ArrayList<>();
		for(Order i:orders) {
			ordersps.addAll(oSpDAO.getOrderSpsByOrder(i));			
		}
		model.addAttribute("ordersps",ordersps);
		
		return "customer_orders";
	}
}
