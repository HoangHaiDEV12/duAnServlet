package com.tulamweb.service;

import java.util.List;

import com.tulamweb.model.ProductionTetails;
import com.tulamweb.request.ProductRequest;

public interface ProductService {

	
	boolean insertProduct(ProductRequest product);
	
	public List <ProductionTetails> getAllProductionTetails();

	ProductionTetails getProductById(String id);
}
