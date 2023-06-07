package com.tulamweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tulamweb.common.DbConnect;
import com.tulamweb.dao.AccountDao;
import com.tulamweb.request.AccountRequest;
import com.tulamweb.request.RoleRequest;

public class AccountDaoImpl implements AccountDao {
	Connection con = null;

	PreparedStatement st = null;
	DbConnect connect = new DbConnect();
	ResultSet rs = null;
	@Override
	public List<AccountRequest> getAccount() {
		List<AccountRequest> accountRequests = new ArrayList<>();
		con = connect.getConnection();
		
		String query = " select a.username, a.email, c.rolename, a.createdate,a.userid \r\n"
				+ "from user a\r\n"
				+ "inner join user_role b on a.userid = b.userid\r\n"
				+ "inner join role c on  b.roleid = c.roleid ";
		try {
			int count = 0;
			rs= null;
			st = con.prepareStatement(query);
			rs = st.executeQuery();
			while(rs.next()) {
				count ++;
				AccountRequest accountRequest = new AccountRequest();
				
				accountRequest.setStt(count);
				accountRequest.setUserName(rs.getString(1));
				accountRequest.setEmail(rs.getString(2));
				accountRequest.setRole(rs.getString(3));
				accountRequest.setCreatedate(rs.getString(4));
				accountRequest.setUserId(rs.getInt(5));
				
				accountRequests.add(accountRequest);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accountRequests;
	}
	@Override
	public boolean updateAccount (String userid, String roleid ) {
		con = connect.getConnection();
		String query = "update user_role set roleid = ? where userid = ?";
		
		try {
			st = con.prepareStatement(query);
			st.setString(1, roleid );
			st.setString(2, userid);
			st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
	}
	@Override
	public List<RoleRequest> getRole() {
		List<RoleRequest> roleRequests = new ArrayList<>();
		con = connect.getConnection();
		String query = " select roleid, rolename from role ";
		
		try {
			rs= null;
			st = con.prepareStatement(query);
			rs = st.executeQuery();
			while (rs.next()) {
				RoleRequest request = new RoleRequest();
				request.setRoleId(rs.getInt(1));
				request.setNameRole(rs.getString(2));
				roleRequests.add(request);
			
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		return roleRequests;
	}
	@Override
	public boolean deleteAccount(String userid) {
	con = connect.getConnection();
	String query2 = "delete from user_role where userid = ?";
	
	try {
		st = con.prepareStatement(query2);
		st.setInt(1,Integer.parseInt(userid));
		st.executeUpdate();
		st = null;
		
		String query = "delete from user where userid = ?";
		st = con.prepareStatement(query);
		st.setInt(1,Integer.parseInt(userid));
		st.executeUpdate();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		return true;
	}
	
	
}
