package com.tulamweb.request;

import java.util.List;

import com.tulamweb.model.SizeQty;

public class ProductRequest {
	
	private String productName;
	private int parentId;
	private int categoryId;
	private List<SizeQty> siteId;
	private String shotTitle;
	private long price;
	private String part;
	private String comment;
	private String detail;
	
	
	
	public List<SizeQty> getSiteId() {
		return siteId;
	}
	public void setSiteId(List<SizeQty> siteId) {
		this.siteId = siteId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getShotTitle() {
		return shotTitle;
	}
	public void setShotTitle(String shotTitle) {
		this.shotTitle = shotTitle;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}

	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

	
	
}
