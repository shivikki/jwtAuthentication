package com.service;
import java.util.List;
import com.modal.ResultResponse;
import  com.modal.Category;
public interface CategoryService {

	public Category addCategory(Category category);
	
	public Category updateCategory(Category category);
	
	public List<Category> getCategory();
	
	public Category getCategoryById(int id);
	
	public ResultResponse deleteCategory(int id);
}
