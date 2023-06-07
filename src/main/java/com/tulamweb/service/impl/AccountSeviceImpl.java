package com.tulamweb.service.impl;

import java.util.List;


import com.tulamweb.dao.AccountDao;
import com.tulamweb.dao.impl.AccountDaoImpl;
import com.tulamweb.request.AccountRequest;
import com.tulamweb.request.RoleRequest;
import com.tulamweb.service.AccountSevice;

public class AccountSeviceImpl implements AccountSevice {
	
	AccountDao accountDao = new AccountDaoImpl();
	@Override
	public List<AccountRequest> getAccount() {
		
		return accountDao.getAccount();
	}
	@Override
	public boolean updateAccount(String userid, String roleid) {
		
		return accountDao.updateAccount(userid, roleid);
	}
	@Override
	public List<RoleRequest> getRole() {
		
		return accountDao.getRole();
	}
	@Override
	public boolean deleteAccount(String userid) {
		return accountDao.deleteAccount(userid);
	}
	

}
