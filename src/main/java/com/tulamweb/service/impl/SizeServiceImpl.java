package com.tulamweb.service.impl;

import java.util.List;


import com.tulamweb.dao.SizeDao;
import com.tulamweb.dao.impl.SizeDaoImpl;
import com.tulamweb.model.Size;
import com.tulamweb.model.SizeQty;
import com.tulamweb.service.SizeService;

public class SizeServiceImpl implements SizeService {
	SizeDao sizeDao = new SizeDaoImpl();
	
	@Override
	public List<Size> getSize() {
		
		return sizeDao.getSize();
	}
	@Override
	public boolean updateSizeName(String name, String id) {
		
		return sizeDao.updateSizeName(name, id);
	}
	@Override
	public boolean insertSize(String name, String id) {
		return sizeDao.insertSize(name, id);
	}
	@Override
	public boolean deleteSizeName(String id) {
		// TODO Auto-generated method stub
		return sizeDao.deleteSizeName(id);
	}
	@Override
	public List<Size> getSizeById(String id) {
		// TODO Auto-generated method stub
		return sizeDao.getSizeById(id);
	}
	@Override
	public List<SizeQty> getSizeByProductId(String id) {
		// TODO Auto-generated method stub
		return sizeDao.getSizeByProductId(id);
	}
	
	

}
