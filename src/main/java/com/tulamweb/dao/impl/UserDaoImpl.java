package com.tulamweb.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tulamweb.common.DbConnect;
import com.tulamweb.dao.UserDao;
import com.tulamweb.model.User;
import com.tulamweb.model.UserRole;

public class UserDaoImpl implements UserDao {

	Connection con = null;

	PreparedStatement st = null;
	DbConnect connect = new DbConnect();
	ResultSet rs = null;

	@Override
	public void insertUser(User user) throws SQLException {
		con = connect.getConnection();

		try {
			rs = null;
			String query = "insert into user(fullname, username, password, email, createby, createdate, modifydate) values(?,?,?,?,?,?,?)";
			st = con.prepareStatement(query, new String[] { "userid" });

			con.setAutoCommit(false);

			st.setString(1, user.getFullName());
			st.setString(2, user.getUserName());
			st.setString(3, user.getPassWord());
			st.setString(4, user.getEmail());
			st.setString(5, user.getFullName());
			st.setDate(6, new Date(System.currentTimeMillis()));
			st.setDate(7, new Date(System.currentTimeMillis()));

			st.executeUpdate();

			rs = st.getGeneratedKeys();

			if (rs.next()) {
				long userId = rs.getLong(1);
				if (userId == 0) {
					throw new SQLException("Creating user failed, no rows affected.");
				}

				PreparedStatement st2 = con.prepareStatement(
						"insert into user_role(userid, roleid, createby, createdate, modifydate) values(?,?,?,?,?)");
				st2.setInt(1, (int) userId);
				st2.setInt(2, 2);
				st2.setString(3, user.getFullName());
				st2.setDate(4, new Date(System.currentTimeMillis()));
				st2.setDate(5, new Date(System.currentTimeMillis()));
				st2.executeUpdate();

				con.commit();
			} else {
				con.rollback();
			}

		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	@Override
	public UserRole getUserByUseName(String userName) throws SQLException {

		rs = null;
		UserRole userRole = new UserRole();
		String query = "select a.username, a.password, c.rolename, a.fullname from user a, user_role b, role c \r\n"
				+ "where 1=1\r\n" + "and a.username = ?" + "and a.userid = b.userid\r\n" + "and b.roleid = c.roleid";
		con = connect.getConnection();

		try {
			st = con.prepareStatement(query);
			st.setString(1, userName);
			rs = st.executeQuery();
			while (rs.next()) {

				userRole.setPass(rs.getString(2));
				userRole.setUserName(rs.getString(1));
				userRole.setRole(rs.getString(3));
				userRole.setFullName(rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			con.close();
		}
		return userRole;
	}

	@Override
	public User findUserByEmail(String userMail) throws SQLException {
		rs = null;
		User user = null;
		String query = "select * from user where email = ?";
		con = connect.getConnection();

		try {
			st = con.prepareStatement(query);
			st.setString(1, userMail);

			rs = st.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setFullName(rs.getString(2));
				user.setUserName(rs.getString(3));
				user.setEmail(rs.getString(6));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			con.close();
		}
		return user;
	}

	@Override
	public User changePassByEmail(String userPass, String mail ) throws SQLException {
		
		String query = "update user set password = ? where email = ?";
		con = connect.getConnection();
		
		
		
		try {
			con.setAutoCommit(false);
			st = con.prepareStatement(query);
			st.setString(1, userPass);
			st.setString(2, mail);
			
			st.executeUpdate();
			
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			con.rollback();
		} finally {
			con.close();
		}
		
		
		
		
		return null;
	}

}
