package com.tulamweb.service.impl;

import java.util.List;

import com.tulamweb.dao.ProductDao;
import com.tulamweb.dao.impl.ProductDaoImpl;
import com.tulamweb.model.ProductionTetails;
import com.tulamweb.request.ProductRequest;
import com.tulamweb.service.ProductService;

public class ProductServiceImpl implements ProductService {
	
	ProductDao productDao = new ProductDaoImpl();

	@Override
	public boolean insertProduct(ProductRequest product) {
		
		return productDao.insertProduct(product);
	}

	@Override
	public List<ProductionTetails> getAllProductionTetails() {
		
		return productDao.getAllProductionTetails();
	}

	@Override
	public ProductionTetails getProductById(String id) {
		// TODO Auto-generated method stub
		return productDao.getProductById(Integer.valueOf(id));
	}
	 
	

}
