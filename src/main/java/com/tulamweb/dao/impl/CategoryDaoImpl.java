package com.tulamweb.dao.impl;

import java.sql.Connection;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tulamweb.common.DbConnect;
import com.tulamweb.dao.CategoryDao;
import com.tulamweb.model.Category;
import com.tulamweb.model.ParentsCategory;

public class CategoryDaoImpl implements CategoryDao {

	Connection con = null;

	PreparedStatement st = null;
	DbConnect connect = new DbConnect();
	ResultSet rs = null;

	@Override
	public boolean insertParentsCategory(String name) {

		con = connect.getConnection();

		String query = "insert into parentscategory (parentscategoryname,createby,createdate) values (?,?,?)";
		try {
			st = con.prepareStatement(query);
			st.setString(1, name);
			st.setString(2, "admin");
			st.setDate(3, new Date(System.currentTimeMillis()));
			st.executeUpdate();

			return true;
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<ParentsCategory> getParentsCategory() {

		List<ParentsCategory> parentsCategories = new ArrayList<>();

		con = connect.getConnection();

		String query = "select parentid, parentscategoryname, createdate from parentscategory ";
		rs = null;
		
		try {

			st = con.prepareStatement(query);
			rs = st.executeQuery();

			int count = 0;
			while (rs.next()) {
				count++;
				ParentsCategory parentscategory = new ParentsCategory();

				parentscategory.setStt(count);
				parentscategory.setParentsid(rs.getInt(1));
				parentscategory.setParentsCategoryName(rs.getString(2));
				parentscategory.setCreatedate(rs.getString(3));

				parentsCategories.add(parentscategory);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return parentsCategories;
	}

	@Override
	public boolean updateParentsCategoryName(String id, String name) {
		con = connect.getConnection();
		String query = "update parentscategory set parentscategoryname = ? where parentid = ?";

		try {
			st = con.prepareStatement(query);
			st.setString(1, name);
			st.setInt(2, Integer.parseInt(id));
			st.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;
	}

	@Override
	public boolean deleteParentsCategory(String categoryId) {

		con = connect.getConnection();
		

		try {
			con.setAutoCommit(false);
			
			String query = "delete from category where parentid = ?";
			st = con.prepareStatement(query);

			st.setInt(1, Integer.parseInt(categoryId));
			
			st.executeUpdate();
			
			st = null;
			String query2 = " delete from parentscategory where parentid = ? ";
			st = con.prepareStatement(query2);
			st.setInt(1, Integer.parseInt(categoryId));
			st.executeUpdate();
			

			con.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;
	}

	@Override
	public boolean insertSubCategory(String id, String name) {
		con = connect.getConnection();
		String query = "insert into category (categoryname, createby, createdate, parentid) values (?,?,?,?)";

		try {
			st = con.prepareStatement(query);
			st.setString(1, name);
			st.setString(2, "admin");
			st.setDate(3, new Date(System.currentTimeMillis()));
			st.setInt(4, Integer.parseInt(id));

			st.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;
	}

	@Override
	public List<Category> getAllCategory() {
		List<Category> categorys = new ArrayList<>();
		con = connect.getConnection();
		rs = null;
		String query = "select a.categoryid, a.categoryname, a.createdate, b.parentscategoryname from  category a, parentscategory b where a.parentid=b.parentid";
		try {

			st = con.prepareStatement(query);
			rs = st.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
				Category category = new Category();

				category.setStt(count);
				category.setCategoryID(rs.getInt(1));
				category.setCategoryName(rs.getString(2));
				category.setCreateDate(rs.getString(3));
				category.setParentCategoryName(rs.getString(4));

				categorys.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return categorys;

	}

	@Override
	public boolean updateCategoryName(String id, String name) {
		con = connect.getConnection();
		String query = "update category set categoryname = ? where categoryid = ?";

		try {
			st = con.prepareStatement(query);
			st.setString(1, name);
			st.setInt(2, Integer.parseInt(id));
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;
	}

	@Override
	public boolean deleteCategoryName(String id) {
		con = connect.getConnection();
		String query = "delete from category where categoryid = ?";

		try {
			st = con.prepareStatement(query);
			st.setInt(1, Integer.parseInt(id));
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;
	}

	@Override
	public List<Category> getCategoryById(String id) {
		List<Category> categorys = new ArrayList<>();
		con = connect.getConnection();
		rs = null;
		String query = "select * from  category where parentid=?";
		try {

			st = con.prepareStatement(query);
			st.setString(1, id);
			rs = st.executeQuery();
			while (rs.next()) {
				Category category = new Category();

				category.setCategoryID(rs.getInt(1));
				category.setCategoryName(rs.getString(2));
				category.setCreateDate(rs.getString(3));
				category.setParentCategoryName(rs.getString(4));

				categorys.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return categorys;
	}

}
