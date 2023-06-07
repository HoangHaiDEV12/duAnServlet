package com.tulamweb.dao.impl;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tulamweb.common.DbConnect;
import com.tulamweb.dao.SizeDao;
import com.tulamweb.model.Size;
import com.tulamweb.model.SizeQty;

public class SizeDaoImpl implements SizeDao {
	Connection con = null;

	PreparedStatement st = null;
	DbConnect connect = new DbConnect();
	ResultSet rs = null;

	@Override
	public boolean insertSize(String name, String id) {
		con = connect.getConnection();
		String query = "insert into size (sizename, createby, createdate, parentid) values (?,?,?,?)";

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
		}
		return true;
	}

	@Override
	public List<Size> getSize() {

		List<Size> sizes = new ArrayList<>();
		con = connect.getConnection();
		String query = " select a.sizeid, a.sizename, a.createdate, b.parentscategoryname from  size a, parentscategory b where a.parentid=b.parentid";
		rs = null;
		int count = 0;
		try {
			st = con.prepareStatement(query);
			rs = st.executeQuery();
			while (rs.next()) {
				count++;
				Size size = new Size();

				size.setStt(count);
				size.setSizeID(rs.getInt(1));
				size.setSizeName(rs.getString(2));
				size.setCreateDate(rs.getString(3));
				size.setParentCategoryName(rs.getString(4));

				sizes.add(size);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sizes;
	}

	@Override
	public boolean updateSizeName(String name, String id) {

		con = connect.getConnection();
		String query = "update size set sizename = ? where sizeid = ?";

		try {
			st = con.prepareStatement(query);
			st.setString(1, name);
			st.setInt(2, Integer.parseInt(id));

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public boolean deleteSizeName(String id) {
		con = connect.getConnection();
		String query = "delete from size where sizeid=? ";

		try {
			st = con.prepareStatement(query);
			st.setString(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public List<Size> getSizeById(String id) {

		List<Size> sizes = new ArrayList<>();
		con = connect.getConnection();
		rs = null;
		String query = "select * from  size where parentid=?";
		try {

			st = con.prepareStatement(query);

			st.setString(1, id);
			rs = st.executeQuery();
			while (rs.next()) {
				Size size = new Size();

				size.setSizeID(rs.getInt(1));
				size.setSizeName(rs.getString(2));

				sizes.add(size);
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

		return sizes;
	}

	@Override
	public List<SizeQty> getSizeByProductId(String id) {


		List<SizeQty> sizesQtys = new ArrayList<>();
		con = connect.getConnection();
		rs = null;
		String query = "select a.productid, a.sizeid, a.qty, b.sizename from  product_size a, size b where 1=1 and "
				+ "a.sizeid=b.sizeid"
				+ " and a.productid=?";
		try {

			st = con.prepareStatement(query);

			st.setInt(1, Integer.valueOf(id));
			rs = st.executeQuery();
			while (rs.next()) {
				SizeQty size = new SizeQty();

				size.setProductId(rs.getInt(1));
				size.setSizeId(rs.getInt(2));
				size.setQty(rs.getInt(3));
				size.setSizeName(rs.getString(4));

				sizesQtys.add(size);
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

		return sizesQtys;
	}

	

}