package com.dao;

import java.util.List;

import com.modal.Category;
import com.modal.ResultResponse;
public interface CategoryDao {

public Category addCategory(Category category);
	
	public Category updateCategory(Category category);
	
	public List<Category> getCategory();
	
	public Category getCategoryById(int id);
	
	public ResultResponse deleteCategory(int id);
}
