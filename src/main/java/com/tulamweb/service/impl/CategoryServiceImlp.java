package com.tulamweb.service.impl;

import java.util.List;



import com.tulamweb.dao.CategoryDao;
import com.tulamweb.dao.impl.CategoryDaoImpl;
import com.tulamweb.model.Category;
import com.tulamweb.model.ParentsCategory;
import com.tulamweb.service.CategoryService;

public  class CategoryServiceImlp implements CategoryService {

	CategoryDao categoryDao = new CategoryDaoImpl();
	
	@Override
	public boolean insertParentsCategory(String name) {
		
		
		return categoryDao.insertParentsCategory(name);
	}

	@Override
	public List<ParentsCategory> getParentsCategory() {
		return categoryDao.getParentsCategory();
	}

	@Override
	public boolean updateParentsCategoryName(String id, String name) {
		// TODO Auto-generated method stub
		return categoryDao.updateParentsCategoryName(id, name);
	}

	@Override
	public boolean deleteParentsCategory(String categoryId) {
		return categoryDao.deleteParentsCategory(categoryId);
	}

	@Override
	public boolean insertSubCategory(String id, String name) {
		return categoryDao.insertSubCategory(id, name);
	}

	@Override
	public List<Category> getAllCategory() {
		 return categoryDao.getAllCategory();
	}

	@Override
	public boolean updateCategoryName(String id, String name) {
		return categoryDao.updateCategoryName(id, name);
	}

	@Override
	public boolean deleteCategoryName(String id) {
		// TODO Auto-generated method stub
		return categoryDao.deleteCategoryName(id);
	}

	@Override
	public List<Category> getCategoryById(String id) {
		// TODO Auto-generated method stub
		return categoryDao.getCategoryById(id);
	}

	

	

	
	

	

	
}
