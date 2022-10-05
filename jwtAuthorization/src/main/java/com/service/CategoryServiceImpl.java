package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CategoryDao;
import com.modal.Category;
import com.modal.ResultResponse;
@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDao categoryDao; 
	
	@Override
	public Category addCategory(Category category) {
		return categoryDao.addCategory(category);
	}

	@Override
	public Category updateCategory(Category category) {
		return categoryDao.updateCategory(category);
	}

	@Override
	public List<Category> getCategory() {
		return categoryDao.getCategory();
	}

	@Override
	public Category getCategoryById(int id) {
		return categoryDao.getCategoryById(id);
	}

	@Override
	public ResultResponse deleteCategory(int id) {
		return categoryDao.deleteCategory(id);
	}

}
