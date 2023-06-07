package com.tulamweb.model;

public class Size {

	private String sizeName;
	private String createDate;
	private int sizeID;
	private int stt;
	private String parentCategoryName;
	
	
	
	public String getParentCategoryName() {
		return parentCategoryName;
	}
	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getSizeID() {
		return sizeID;
	}
	public void setSizeID(int sizeID) {
		this.sizeID = sizeID;
	}
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	
	
}
