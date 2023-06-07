package com.tulamweb.service.impl;

import java.sql.SQLException;


import com.tulamweb.dao.UserDao;
import com.tulamweb.dao.impl.UserDaoImpl;
import com.tulamweb.model.User;
import com.tulamweb.model.UserRole;
import com.tulamweb.service.UserService;

public class UserServiceImpl implements UserService{

	UserDao userDao = new UserDaoImpl();
	@Override
	public void insertUser(User user) throws SQLException {
		
		
		userDao.insertUser(user);
	}
	@Override
	public UserRole getUserByUseName(String userName) throws Exception {
		
		return userDao.getUserByUseName(userName);
	}
	@Override
	public User findUserByEmail(String userMail) throws Exception {
		return userDao.findUserByEmail(userMail);
	}
	@Override
	public User changePassByEmail(String userPass, String mail) throws Exception {
		return userDao.changePassByEmail(userPass, mail);
	}
	 
}
