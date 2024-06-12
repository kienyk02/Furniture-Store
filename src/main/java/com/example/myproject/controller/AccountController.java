package com.example.myproject.controller;

import java.io.IOException;
import java.security.PublicKey;
import java.util.List;

import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.myproject.dal.*;
import com.example.myproject.model.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class AccountController {
	
	@PostMapping("/register")
	public String register(@Valid User user,BindingResult result,RedirectAttributes redirect,HttpServletRequest request) throws IOException{
		redirect.addFlashAttribute("user", user);
		if (result.hasFieldErrors("username") || result.hasFieldErrors("password")) {
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
			redirect.addFlashAttribute("errorregisterVal", true);
			String referer = request.getHeader("Referer");
		    return "redirect:"+ referer;
        }
		UserDAO userDao=new UserDAO();
		if(userDao.checkUser(user.getUsername())) {
			redirect.addFlashAttribute("errorregister", "Tài khoản "+user.getUsername()+" đã có người đăng kí!");
		}else {
			userDao.insert(user.getUsername(),user.getPassword(),user.getEmail());
			redirect.addFlashAttribute("successregister", "Đăng kí thành công!");
		}		
		String referer = request.getHeader("Referer");
	    return "redirect:"+ referer;
	}
	
	@PostMapping("/login")
	public String login(@Valid User user,BindingResult result,HttpSession session,RedirectAttributes redirect,HttpServletRequest request) throws IOException{
		redirect.addFlashAttribute("user", user);
		// kiểm tra lỗi trên 2 trường u và p
		if (result.hasFieldErrors("username") || result.hasFieldErrors("password") ) {
			// gửi bindingresult sang để check display error
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
			redirect.addFlashAttribute("errorloginVal", true);
			String referer = request.getHeader("Referer");
		    return "redirect:"+ referer;
        }
		UserDAO userDao=new UserDAO();
		User u=userDao.getUser(user.getUsername(), user.getPassword());
		if(u==null) {
			redirect.addFlashAttribute("errorlogin", "Tài khoản hoặc mật khẩu không chính xác!");
		}else {
			session.setAttribute("account", u);
		}
		String referer = request.getHeader("Referer");
	    return "redirect:"+ referer;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session,HttpServletRequest request) throws IOException{
		session.removeAttribute("account");
		String referer = request.getHeader("Referer");
		if(
				referer.equals("http://localhost:8080/address-receive") 
				|| referer.equals("http://localhost:8080/change-password")
				|| referer.equals("http://localhost:8080/payment")
				|| referer.equals("http://localhost:8080/cart")
				|| referer.equals("http://localhost:8080/order-view")
				) {
			return "redirect:/home";
		}
	    return "redirect:"+ referer;
	}
	
	//change password
	@GetMapping("/change-password")
	public String changePassword(Model model,HttpSession session,HttpServletRequest request) throws IOException{
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
		//truyền đối tượng user cho modal
		model.addAttribute("user",new User());
	    return "change_password";
	}
	@PostMapping("/handle-changepassword")
	public String handleChange(HttpServletRequest request,RedirectAttributes redirect,HttpSession session) throws IOException{
		String oldpassword=request.getParameter("oldpassword");
		String newpassword=request.getParameter("newpassword");
		User u=(User) session.getAttribute("account");
		UserDAO userDao=new UserDAO();
		User user=userDao.getUser(u.getUsername(), oldpassword);
		if(user==null) {
			redirect.addFlashAttribute("errorchangepassword", "Mật khẩu cũ sai!");
		}else {
			userDao.changePassword(u.getUsername(), newpassword);
			// Cập nhật lại session account
			session.setAttribute("account", userDao.getUser(u.getUsername(), newpassword));
			redirect.addFlashAttribute("succeschangepassword", "Đổi mật khẩu thành công!");
		}
		return "redirect:/change-password";
	}
	//Thông tin nhận hàng
	@GetMapping("/address-receive")
	public String getInfo(Model model,HttpSession session,HttpServletRequest request) throws IOException{
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
	    return "address_receive";
	}
	
	@GetMapping("/updateInfo")
	public String updateInfo(@Valid User user,BindingResult result,HttpSession session,RedirectAttributes redirect,HttpServletRequest request) throws IOException{
		// kiểm tra lỗi trên 2 trường sdt và diachi
		if (result.hasFieldErrors("sdt") || result.hasFieldErrors("diachi") ) {
			// gửi user lại về address receive
			redirect.addFlashAttribute("user", user);
			// gửi bindingresult sang để check display error
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
			String referer = request.getHeader("Referer");
		    return "redirect:"+ referer;
        }		
		UserDAO uDao=new UserDAO();
		// lưu thông tin user vào CSDL
		uDao.update(user);
		// Cập nhật lại session account
		session.setAttribute("account", uDao.getUser(user.getUsername(), user.getPassword()));
		String referer = request.getHeader("Referer");
	    return "redirect:"+ referer;
	}
	
}
