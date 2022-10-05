package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mapper.MasterMapper;
import com.modal.Category;
import com.modal.ResultResponse;
import com.modal.User;
import com.queryConst.QueryConstant;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	private static final Logger LOG = LoggerFactory.getLogger(CategoryDaoImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateNamed;

	@Override
	public Category addCategory(Category category) {
		boolean categoryExist=(checkCategoryExist(category)==null)?false:true;
		LOG.info("inside addCategory m/d start");
		try {
			if(categoryExist==false) {
				int recordInsert = jdbcTemplate.update(QueryConstant.ADD_NEW_CATEGORY, category.getTitle(),category.getDescription());
				if (recordInsert>0) {
					return category ;
				}
			}
			

		} catch (Exception e) {
			LOG.error("addCategory m/d " + e);
		}
		category.setTitle("EXIST");
		category.setDescription("Category already exists. ");
		return category;
	}

	public Category checkCategoryExist(Category category) {
		LOG.info("inside checkCategoryExist m/d start");
		List<Category> categoryList = new ArrayList<>();
		
		try {

			List<Map<String, Object>> catData = jdbcTemplate.queryForList(QueryConstant.CHECK_CATEGORY_EXIST,category.getTitle());
			if (catData.size() > 0) {
				for (Map<String, Object> u : catData) {
					category = (Category) new MasterMapper().mapCategory(u);
				}
				

			}
			else {
				category=null;
			}

		} catch (Exception e) {
			LOG.error("checkCategoryExist m/d" + e);
		}
		return category;
	}

	@Override
	public Category updateCategory(Category category) {
		boolean categoryExist=(checkCategoryExist(category)==null)?false:true;
		LOG.info("inside addCategory m/d start");
		try {
			if(categoryExist==true) {
				int recordUpdate = jdbcTemplate.update(QueryConstant.UPDATE_CATEGORY, category.getTitle(),category.getDescription(),category.getTitle());
				if (recordUpdate>0) {
					return category ;
				}
			}
			

		} catch (Exception e) {
			LOG.error("addCategory m/d " + e);
		}
		category.setTitle("NOT_EXIST");
		category.setDescription("Category does not exists.");
		return category;
	}

	@Override
	public List<Category> getCategory() {
		List<Category> categoryList = new ArrayList<>();
		Category category = new Category();
		LOG.info("inside getCategory m/d");
		try {
			List<Map<String, Object>> catData = jdbcTemplate.queryForList(QueryConstant.GET_ALL_CATEGORY);
			if (catData.size() > 0) {
				for (Map<String, Object> u : catData) {
					category = (Category) new MasterMapper().mapCategory(u);
					categoryList.add(category);
				}
				

			}
		} catch (Exception e) {
			LOG.error("error in getCategory()" + e);
		}
		return categoryList;
	}

	@Override
	public Category getCategoryById(int id) {
		Category category = new Category();
		try {
			LOG.info("inside getCategoryById m/d start");
			List<Map<String, Object>> catData = jdbcTemplate.queryForList(QueryConstant.GET_CATEGORY_BY_ID, id);
			if (catData.size() > 0) {
				for (Map<String, Object> u : catData) {
					category = (Category) new MasterMapper().mapCategory(u);
				}

			}

		} catch (Exception e) {
			LOG.error("getCategoryById m/d" + e);
		}
		LOG.info("inside getCategoryById m/d end");
		return category;
	}

	@Override
	public  ResultResponse deleteCategory(int id) {
		Category category = new Category();
		ResultResponse resultResp=new ResultResponse();
		try {
			LOG.info("inside deleteCategory m/d start");
			int result = jdbcTemplate.update(QueryConstant.DELETE_CATEGORY, id);
			if (result > 0) {
				resultResp.setResponseCode(1);
				resultResp.setValidationStatus("Category deleted sucessfully");
				return resultResp;
			}

		} catch (Exception e) {
			LOG.error("deleteCategory m/d" + e);
		}
		LOG.info("inside deleteCategory m/d end");
		resultResp.setResponseCode(0);
		resultResp.setValidationStatus("Category does not exist");
		return resultResp;
	}

}
