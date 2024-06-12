package com.example.myproject.controller;

import java.io.IOException;
import java.util.List;

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
import com.example.myproject.dal.ProductDAO;
import com.example.myproject.model.Category;
import com.example.myproject.model.Product;

import jakarta.validation.Valid;

@Controller
public class CategoryController {
	@GetMapping("/controlmode/category/{id}")
	public String getProduct(Model model,@PathVariable String id) throws IOException{
		model.addAttribute("id",id);
		CategoryDAO ctDao=new CategoryDAO();
		Category category=ctDao.getCategoryById(Integer.parseInt(id));
		model.addAttribute("category",category);
		return "category-detail";
	}
	@PutMapping("/controlmode/category/save/{id}")
	public String updateCategory(Model model,@Valid Category category,BindingResult result,@PathVariable String id) throws IOException {
		if(result.hasErrors()) {
			return "category-detail";
		}
		CategoryDAO ctDao=new CategoryDAO();
		ctDao.update(category);
		return "redirect:/controlmode/";
	}
	@PostMapping("/controlmode/category/save/{id}")
	public String addCategory(Model model,@Valid Category category,BindingResult result,@PathVariable String id) throws IOException {
		if(result.hasErrors()) {
			return "category-detail";
		}
		CategoryDAO ctDao=new CategoryDAO();
		ctDao.insert(category);
		return "redirect:/controlmode/";
	}
	@DeleteMapping("/controlmode/category/delete/{id}")
	@ResponseBody
	public void deleteProduct(@PathVariable String id) throws IOException {
		CategoryDAO ctDao=new CategoryDAO();
		ctDao.delete(Integer.parseInt(id));
	}
}
