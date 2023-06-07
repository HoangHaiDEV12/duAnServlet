package com.tulamweb.dao;

import java.sql.SQLException;


import com.tulamweb.model.User;
import com.tulamweb.model.UserRole;

public interface UserDao {
	
	void insertUser(User user) throws SQLException;
	
	UserRole getUserByUseName(String userName) throws Exception;
	

	User findUserByEmail(String userMail) throws Exception;


	User changePassByEmail(String userPass, String email) throws Exception;
}
