package com.example.myproject.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.myproject.dal.CartDAO;
import com.example.myproject.dal.OrderDao;
import com.example.myproject.dal.OrderSpDAO;
import com.example.myproject.dal.ProductDAO;
import com.example.myproject.model.Cart;
import com.example.myproject.model.Order;
import com.example.myproject.model.Product;
import com.example.myproject.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {
	@RequestMapping(value="/payment", method = { RequestMethod.POST, RequestMethod.GET })
	public String payment(Model model,HttpSession session,HttpServletRequest request) throws IOException{
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
			//lấy cart muốn thanh toán
			String idcartoder[]=request.getParameterValues("ordercart");
			if(idcartoder!=null) {
				List<Cart> listCartOrder=new ArrayList<>();
				int tongtien=0;
				for(int i=0;i<idcartoder.length;i++) {
					for(Cart cart:listCart) {
						if(cart.getId()==Integer.parseInt(idcartoder[i])) {
							listCartOrder.add(cart);
							tongtien+=cart.getProduct().getGia_moi()*cart.getSo_luong();
						}
					}
				}
				session.setAttribute("listCartOrder", listCartOrder);
				session.setAttribute("tongtiencartorder", tongtien);				
			}else if(request.getHeader("Referer").equals("http://localhost:8080/cart")) {
				// nếu quay trở lại trag cart nhưg ko tích checkbox nào thì xóa listcartoder
				session.removeAttribute("listCartOrder");
			}
		}
		
		//truyền đối tượng user cho modal và form info
		// nếu updateinfo không trả về user lỗi thì nhận giá trị của account
		User user = (User) model.asMap().get("user");
		model.addAttribute("user", user!=null? user: u);
	    return "payment";
	}
	@GetMapping("handle-payment")
	public String handlePayment(HttpSession session,RedirectAttributes redirect) throws IOException{
		// Tạo order
		OrderDao oDao=new OrderDao();
		int idorder= oDao.getMaxID()+1;
		User u=(User) session.getAttribute("account");
		Date date = new Date(System.currentTimeMillis());
		Order order=new Order(idorder,u,date,"Chờ xác nhận",0);
		oDao.createOrder(idorder, u, date);
		//Tạo order-sp
		List<Cart> listCartOrder=(List<Cart>) session.getAttribute("listCartOrder");
		OrderSpDAO oSpDAO=new OrderSpDAO();
		CartDAO cDao=new CartDAO();
		// kiem tra xem sp co con hang ko
		for(Cart i:listCartOrder) {
			int slconlai=i.getProduct().getSo_luong()-i.getProduct().getDa_ban();
			if(i.getSo_luong()>slconlai) {
				redirect.addFlashAttribute("soldout", i.getId());
				redirect.addFlashAttribute("soldoutmessage", "Sản phẩm này chỉ còn lại "+slconlai+" chiếc!");
				oDao.deleteOrder(idorder);
				return "redirect:/payment";
			}
		}
		// nếu tất cả sp đều còn hàng thì tiến hành thêm cart vào order
		ProductDAO pDao=new ProductDAO();
		int tong_tien=0;
		for(Cart i:listCartOrder) {
			oSpDAO.createAddOrderSP(order, i);
			//thêm số lượng đã bán vào sản phẩm
			pDao.updateProductSale(i.getProduct().getId(), i.getSo_luong());
			// tính tổng tiền cho order
			tong_tien+=i.getSo_luong()*i.getProduct().getGia_moi();
			// xóa những cart đã mua
			cDao.delete(i.getId());
		}
		oDao.updateOrderTotal(idorder, tong_tien);
		//Xóa session listcartorder
		session.removeAttribute("listCartOrder");
		return "redirect:/order-view";
	}
}
