package com.tulamweb.service;

import java.util.List;

import com.tulamweb.model.Size;
import com.tulamweb.model.SizeQty;


public interface SizeService {
	
	boolean insertSize(String name, String id );
	
	public List< Size> getSize();
	
	boolean updateSizeName(String name, String id);
	
	boolean deleteSizeName(String id);

	List<Size> getSizeById(String id);

	List<SizeQty> getSizeByProductId(String id);
	
}
