package com.tulamweb.service;

import java.util.List;


import com.tulamweb.request.AccountRequest;
import com.tulamweb.request.RoleRequest;

public interface AccountSevice {


	
	List<AccountRequest> getAccount();
	
	boolean updateAccount (String userid, String roleid );
	
	List<RoleRequest> getRole();
	
	boolean deleteAccount(String userid);
}
