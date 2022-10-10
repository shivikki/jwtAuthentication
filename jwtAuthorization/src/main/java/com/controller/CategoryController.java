package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import com.modal.Category;
import com.modal.ResultResponse;
import com.service.CategoryService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class CategoryController {


	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/getCategoryById/{id}")
	public Category getCategoryById(@PathVariable int id) {
		Category category = new Category();
		category = categoryService.getCategoryById(id);
		return category;
	}
	
	@GetMapping("/getAllCategory")
	public List<Category> getCategory() {
		List<Category> categoryList = new ArrayList<>();
		categoryList = categoryService.getCategory();
		return categoryList;
	}
	
	@PostMapping("/addNewCategory")
	public Category addCategory(@RequestBody Category category) {
		category=categoryService.addCategory(category);
		
		return category;
	}
	
	@PostMapping("/updateCategory")
	public Category updateCategory(@RequestBody Category category) {
		category=categoryService.updateCategory(category);
		
		return category;
	}
	
	@PostMapping("/deleteCategory")
	public ResultResponse deleteCategory(@RequestBody int id) {
		ResultResponse resultResp=categoryService.deleteCategory(id);
		
		return resultResp;
	}
	
}
