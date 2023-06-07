package com.tulamweb.model;

public class ParentsCategory {

	
	private int parentsid;
	private String parentsCategoryName;
	private String createdate;
	private String createbyname;
	private int stt;
	
	public int getStt() {
		return stt;
	}


	public void setStt(int stt) {
		this.stt = stt;
	}

	public int getParentsid() {
		return parentsid;
	}
	public void setParentsid(int parentsid) {
		this.parentsid = parentsid;
	}
	public String getParentsCategoryName() {
		return parentsCategoryName;
	}
	public void setParentsCategoryName(String parentsCategoryName) {
		this.parentsCategoryName = parentsCategoryName;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getCreatebyname() {
		return createbyname;
	}
	public void setCreatebyname(String createbyname) {
		this.createbyname = createbyname;
	}
	
	
}
