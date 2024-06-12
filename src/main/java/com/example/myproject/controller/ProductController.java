package com.example.myproject.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.myproject.dal.CategoryDAO;
import com.example.myproject.dal.OrderDao;
import com.example.myproject.dal.OrderSpDAO;
import com.example.myproject.dal.ProductDAO;
import com.example.myproject.model.Category;
import com.example.myproject.model.Order;
import com.example.myproject.model.OrderSp;
import com.example.myproject.model.Product;

import jakarta.validation.Valid;

@Controller
public class ProductController {
	@GetMapping("/controlmode/")
	public String controll(Model model) throws IOException{
		// lấy products
		ProductDAO pDao=new ProductDAO();
		List <Product> products=pDao.search(null, null, null, 0,0);
		model.addAttribute("products",products);
		// lấy orders
		OrderDao oDao=new OrderDao();
		List <Order> orders= oDao.getOrderAll();
		model.addAttribute("orders",orders);
		// Lấy order-sp
		OrderSpDAO oSpDAO=new OrderSpDAO();
		List<OrderSp> ordersps=new ArrayList<>();
		for(Order i:orders) {
			ordersps.addAll(oSpDAO.getOrderSpsByOrder(i));			
		}
		model.addAttribute("ordersps",ordersps);
		// lấy category
		CategoryDAO ctDao=new CategoryDAO();
		List<Category> categorys= ctDao.getAll();
		model.addAttribute("categorys",categorys);
		return "admin_controller";
	}
	@GetMapping("/controlmode/product/{id}")
	public String getProduct(Model model,@PathVariable String id) throws IOException{
		model.addAttribute("id",id);
		ProductDAO pDao=new ProductDAO();
		Product product=pDao.getProductById(Integer.parseInt(id));
		model.addAttribute("product",product);
		
		// category
		CategoryDAO ctDao=new CategoryDAO();
		List<Category> categorys= ctDao.getAll();
		model.addAttribute("categorys",categorys);
		return "product-detail";
	}
	@PutMapping("/controlmode/product/save/{id}")
	public String updateProduct(Model model,@Valid Product product,BindingResult result,@PathVariable String id,
								@RequestParam(value="img",required = false) MultipartFile photo) throws IOException {
		saveFile(photo, product.getAnh());
		if(result.hasErrors()) {
			// category
			CategoryDAO ctDao=new CategoryDAO();
			List<Category> categorys= ctDao.getAll();
			model.addAttribute("categorys",categorys);
			return "product-detail";
		}
		ProductDAO pDao=new ProductDAO();
		pDao.updateProduct(product);
		return "redirect:/controlmode/";
	}
	@PostMapping("/controlmode/product/save/{id}")
	public String addProduct(Model model,@Valid Product product,BindingResult result,@PathVariable String id,
							@RequestParam(value="img",required = false) MultipartFile photo) throws IOException {
		saveFile(photo, product.getAnh());
		if(result.hasErrors()) {
			// category
			CategoryDAO ctDao=new CategoryDAO();
			List<Category> categorys= ctDao.getAll();
			model.addAttribute("categorys",categorys);
			return "product-detail";
		}
		ProductDAO pDao=new ProductDAO();
		pDao.insertProduct(product);
		return "redirect:/controlmode/";
	}
	private String saveFile(MultipartFile file,String nameimg) {
		if(null!=file && !file.isEmpty()) {
			try {
				byte[] bytes=file.getBytes();
				File dirFile=new File("src/main/resources/static/assets/img");
				File serverFile=new File(dirFile.getAbsoluteFile()+File.separator+nameimg);
				System.out.println("=====path of img: "+serverFile.getPath());
				BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				return nameimg;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage()+"siu");
			}
		}else {
			System.out.println("file not exists");
		}
		return null;
	}
	@DeleteMapping("/controlmode/product/delete/{id}")
	@ResponseBody
	public void deleteProduct(@PathVariable String id) throws IOException {
		ProductDAO pDao=new ProductDAO();
		pDao.deleteProduct(Integer.parseInt(id));
	}

}
