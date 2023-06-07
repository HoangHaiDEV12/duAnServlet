package com.tulamweb.service;

import java.sql.SQLException;

import com.tulamweb.model.User;
import com.tulamweb.model.UserRole;

public interface UserService {

	void insertUser(User user) throws SQLException;
	
	UserRole getUserByUseName(String userName) throws Exception;
	
	User changePassByEmail(String userPass, String mail) throws Exception;

	User findUserByEmail(String userMail) throws Exception;
	
	
	
}
