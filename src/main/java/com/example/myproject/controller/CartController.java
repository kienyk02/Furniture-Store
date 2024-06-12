package com.example.myproject.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.myproject.dal.CartDAO;
import com.example.myproject.model.Cart;
import com.example.myproject.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	@GetMapping("/cart")
	public String getCarts(Model model,HttpSession session) throws IOException{
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
	    return "cart";
	}
	@PutMapping("/add-cart")
	@ResponseBody
	public void addCart(Model model,HttpServletRequest request,HttpSession session) throws IOException{
		int pid=Integer.parseInt(request.getParameter("id"));
		int sl=Integer.parseInt(request.getParameter("soluong"));
		CartDAO cDao=new CartDAO();
		User user=( User )session.getAttribute("account");
		if (cDao.checkCart(user.getId(), pid )) {
			cDao.increase(user.getId(), pid, sl);
		}else {
			cDao.add(user.getId(), pid, sl);
		}
	}
	@DeleteMapping("/delete-cart")
	@ResponseBody
	public void deleteCart(HttpServletRequest request) throws IOException{
		int cartid=Integer.parseInt(request.getParameter("cartid"));
		CartDAO cDao=new CartDAO();
		cDao.delete(cartid);
	}
}
