package com.tulamweb.dao;

import java.util.List;

import com.tulamweb.model.Category;
import com.tulamweb.model.ParentsCategory;

public interface CategoryDao {

	
	boolean insertParentsCategory(String name);
	
	List <ParentsCategory> getParentsCategory();
	
	boolean updateParentsCategoryName(String id, String name);
	
	boolean deleteParentsCategory(String categoryId );
	
	boolean insertSubCategory(String id, String name );
	
	List<Category> getAllCategory();
	
	boolean updateCategoryName(String id, String name);
	
	boolean deleteCategoryName(String id);
	
	List<Category> getCategoryById(String id);
}
