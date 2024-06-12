package com.example.myproject.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.myproject.dal.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import com.example.myproject.model.*;


@Controller
public class home {
	@GetMapping(value={"/","/home"})
	public String getBooks(Model model,HttpSession session,HttpServletRequest request) throws IOException {
		String cid_raw=request.getParameter("cid");
		String key=request.getParameter("key");
		String order=request.getParameter("order");
		String orderPrice=request.getParameter("orderPrice");
		String pageIndexString=request.getParameter("page");
		String curUrl=request.getRequestURL().toString() + 
				"?" + (request.getQueryString()==null? "cid=0":request.getQueryString());
		if(curUrl.indexOf("&page")>0) {
			curUrl=curUrl.substring(0, curUrl.indexOf("&page"));
		}
		ProductDAO pDao=new ProductDAO();		
		try {
			int cid=(cid_raw==null)?0:Integer.parseInt(cid_raw);
			int pageIndex= pageIndexString==null ? 1: Integer.parseInt(pageIndexString);
			List<Product> listP=pDao.search(key,order, orderPrice, cid, pageIndex);
			model.addAttribute("listP", listP);
			model.addAttribute("cur_ct", cid);
			model.addAttribute("order", order);
			model.addAttribute("order_price", orderPrice);
			model.addAttribute("pageIndex", pageIndex);
			model.addAttribute("curUrl", curUrl);
			model.addAttribute("pageNumber", pDao.getPageNumber(key, order, orderPrice, cid));
		} catch (Exception e) {
			// TODO: handle exception
		}
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
		//lấy category
		CategoryDAO ctCategoryDAO=new CategoryDAO();
		List<Category> list=ctCategoryDAO.getAll();
		model.addAttribute("ctdata", list);
		//truyền đối tượng user cho modal
		//nếu login hoặc register trả về user thì lấy user ý không thì tạo mới
		User user = (User) model.asMap().get("user");
		model.addAttribute("user", user!=null? user: new User());
		return "index";
	}
	@ResponseBody
	@GetMapping("/api/search")
	public List<Product> getProductsSearch(HttpServletRequest request){
		String content_search=request.getParameter("s");
		List<Product> list=new ArrayList<>();
		ProductDAO pDao=new ProductDAO();
		list=pDao.search(content_search, null, null, 0, 1);
		return list;
	}
}
