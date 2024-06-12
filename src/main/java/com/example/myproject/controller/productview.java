package com.example.myproject.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.myproject.dal.CartDAO;
import com.example.myproject.dal.CategoryDAO;
import com.example.myproject.dal.ProductDAO;
import com.example.myproject.model.Cart;
import com.example.myproject.model.Category;
import com.example.myproject.model.Product;
import com.example.myproject.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class productview {
	@GetMapping("/product-view")
	public String viewProduct(Model model,@RequestParam String id,HttpSession session) throws IOException {
		ProductDAO pDao=new ProductDAO();
		Product product=pDao.getProductById(Integer.parseInt(id));
		model.addAttribute("product",product);
		
		User u=(User) session.getAttribute("account");
		//lấy cart
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
		//truyền đối tượng user cho modal
		//nếu login hoặc register trả về user thì lấy user ý không thì tạo mới
		User user = (User) model.asMap().get("user");
		model.addAttribute("user", user!=null? user: new User());
		return "product_view";
	}
}
