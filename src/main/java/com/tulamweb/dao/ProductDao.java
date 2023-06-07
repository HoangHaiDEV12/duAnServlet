package com.tulamweb.dao;

import java.util.List;

import com.tulamweb.model.ProductionTetails;
import com.tulamweb.request.ProductRequest;

public interface ProductDao {

	boolean insertProduct(ProductRequest product);

	public List<ProductionTetails> getAllProductionTetails();
	
	ProductionTetails getProductById(Integer productId);

}
