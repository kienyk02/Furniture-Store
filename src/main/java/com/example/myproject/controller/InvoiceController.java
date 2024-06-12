package com.example.myproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.myproject.dal.*;
import com.example.myproject.model.*;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class InvoiceController {
	@GetMapping("/invoice")
	public String showInvoice(Model model,HttpServletRequest request) {
		OrderDao oDao=new OrderDao();
		Order order=oDao.getOrderById(Integer.parseInt(request.getParameter("id")));
		model.addAttribute("order", order);
		OrderSpDAO oSpDAO=new OrderSpDAO();
		List<OrderSp> ordersps=new ArrayList<>();
		ordersps=oSpDAO.getOrderSpsByOrder(order);			
		model.addAttribute("ordersps",ordersps);
		model.addAttribute("tongtien",order.getTong_tien());
		return "invoice";
	}
}
