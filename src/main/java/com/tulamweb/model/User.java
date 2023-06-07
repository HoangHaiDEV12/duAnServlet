package com.tulamweb.model;

public class User {
	private String fullName;
	private String userName;
	private String passWord;
	private String rePassWord;
	public String getRePassWord() {
		return rePassWord;
	}
	public void setRePassWord(String rePassWord) {
		this.rePassWord = rePassWord;
	}
	private String email;
	
	
	public User() {
		super();
	}
	public User(String fullName, String userName, String passWord, String email, String rePassWord) {
		super();
		this.fullName = fullName;
		this.userName = userName;
		this.passWord = passWord;
		this.rePassWord = rePassWord;
		this.email = email;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
