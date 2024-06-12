package com.example.myproject.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.myproject.dal.CategoryDAO;
import com.example.myproject.dal.OrderDao;
import com.example.myproject.dal.ProductDAO;



@Controller
public class ChartController {
	@ResponseBody
	@GetMapping("/api/piechart")
	public Map<String, Integer> ApiPieChart() {
		Map <String,Integer> map=new LinkedHashMap<>();
		CategoryDAO ctDao=new CategoryDAO();
		map=ctDao.getStatisticalQuantityByCategory();
		return map;
	}
	@ResponseBody
	@GetMapping("/api/divchart")
	public Map<Integer, Integer> ApiDivChart() {
		Map <Integer,Integer> map=new LinkedHashMap<>();
		OrderDao oDao=new OrderDao();
		map=oDao.getRevenueStatistics();
		return map;
	}
}
