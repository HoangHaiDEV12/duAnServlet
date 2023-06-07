package com.tulamweb.dao.impl;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tulamweb.common.DbConnect;
import com.tulamweb.dao.ProductDao;
import com.tulamweb.model.Image;
import com.tulamweb.model.ProductionTetails;
import com.tulamweb.request.ProductRequest;

public class ProductDaoImpl implements ProductDao {

	Connection con = null;

	PreparedStatement st = null;
	DbConnect connect = new DbConnect();
	ResultSet rs = null;

	@Override
	public boolean insertProduct(ProductRequest product) {
		con = connect.getConnection();

		int qty = 0;
		String query = " insert into product (categoryid,nameproduct,createby,createdate,parentid) values (?,?,?,?,?)";
		try {
			con.setAutoCommit(false);

			st = con.prepareStatement(query, new String[] { "productid" });
			st.setInt(1, product.getCategoryId());
			st.setString(2, product.getProductName());
			st.setString(3, "admin");
			st.setDate(4, new Date(System.currentTimeMillis()));
			st.setInt(5, product.getParentId());
			st.executeUpdate();

			rs = st.getGeneratedKeys();
			if (rs.next()) {
				long productid = rs.getLong(1);
				if (productid == 0) {
					throw new SQLException("Creating user failed, no rows affected.");
				}

				for (int i = 0; i < product.getSiteId().size(); i++) {
					st = null;
					qty += product.getSiteId().get(i).getQty();
					String query2 = "insert into product_size(productid,sizeid,createby,createdate,qty) values (?,?,?,?,?)";
					st = con.prepareStatement(query2);
					st.setInt(1, (int) productid);
					st.setInt(2, (int) product.getSiteId().get(i).getSizeId());
					st.setString(3, "admin");
					st.setDate(4, new Date(System.currentTimeMillis()));
					st.setInt(5, (int) product.getSiteId().get(i).getQty());
					st.executeUpdate();
				}

				st = null;

				String query1 = " insert into productetails (productid,shottitle,price,comment,imagelist,content,createby,createdate,parentid,categoryid,qty) values (?,?,?,?,?,?,?,?,?,?,?)";
				st = con.prepareStatement(query1);
				st.setInt(1, (int) productid);
				st.setString(2, product.getShotTitle());
				st.setLong(3, product.getPrice());
				st.setString(4, product.getComment());
				st.setString(5, product.getPart());
				st.setString(6, product.getDetail());
				st.setString(7, "admin");
				st.setDate(8, new Date(System.currentTimeMillis()));
				st.setInt(9, product.getParentId());
				st.setInt(10, product.getCategoryId());
				st.setInt(11, qty);

				st.executeUpdate();
			} else {

			}

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
		}

		return true;
	}

	@Override
	public List<ProductionTetails> getAllProductionTetails() {
		List<ProductionTetails> productionTetails = new ArrayList<>();
		con = connect.getConnection();
		String query = "SELECT b.productid, a.categoryname, b.nameproduct, c.price, e.sizeid, GROUP_CONCAT(d.sizename SEPARATOR ', ') size , sum(e.qty) total, b.createdate, c.imagelist \r\n"
				+ "				FROM product b \r\n"
				+ "                inner JOIN product_size e ON b.productid = e.productid \r\n"
				+ "                 inner join size d on e.sizeid = d.sizeid\r\n"
				+ "				 inner JOIN category a ON b.categoryid = a.categoryid \r\n" + "                 \r\n"
				+ "				inner  JOIN productetails c ON b.productid = c.productid  \r\n" + "				 \r\n"
				+ "                  group by e.productid;";

		try {
			rs = null;
			st = con.prepareStatement(query);

			rs = st.executeQuery();
			int count = 0;
			while (rs.next()) {

				count++;
				ProductionTetails productionTetail = new ProductionTetails();

				productionTetail.setStt(count);
				productionTetail.setProductId(rs.getInt(1));
				productionTetail.setCategoryName(rs.getString(2));
				productionTetail.setNameProduct(rs.getString(3));
				productionTetail.setPrice(rs.getString(4));
				productionTetail.setSizeName(rs.getString(6));
				productionTetail.setQty(rs.getInt(7));

				productionTetail.setCreatedate(rs.getString(8));
				String[] listimg = rs.getString(9).split(";");
				List<Image> images = new ArrayList<Image>();
				for (int i = 0; i < listimg.length; i++) {
					Image img = new Image();
					img.setStt(i);
					img.setImage(listimg[i]);
					images.add(img);
				}

				productionTetail.setImageList(images);

				productionTetails.add(productionTetail);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productionTetails;
	}

	@Override
	public ProductionTetails getProductById(Integer productId) {

		ProductionTetails productionTetail = new ProductionTetails();
		con = connect.getConnection();
		String query = " SELECT b.productid,b.nameproduct,d.parentid ,d.parentscategoryname ,a.categoryid ,a.categoryname, c.shottitle, c.price , c.imagelist ,  c.comment, c.content \r\n"
				+ "				 				FROM product b  \r\n"
				+ "				 				 inner JOIN category a ON b.categoryid = a.categoryid                      \r\n"
				+ "				 				inner  JOIN productetails c ON b.productid = c.productid  \r\n"
				+ "                                inner  JOIN parentscategory d ON b.parentid = d.parentid    \r\n"
				+ "				                  and b.productid = ?";

		try {
			rs = null;
			st = con.prepareStatement(query);
			st.setInt(1, Integer.valueOf(productId));
			rs = st.executeQuery();
			while (rs.next()) {

				productionTetail.setStt(1);
				productionTetail.setProductId(rs.getInt(1));
				productionTetail.setNameProduct(rs.getString(2));
				productionTetail.setParentCatetoryId(rs.getString(3));
				productionTetail.setParentCategoryName(rs.getString(4));
				productionTetail.setCategoryId(rs.getInt(5));
				productionTetail.setCategoryName(rs.getString(6));
				productionTetail.setShotTitle(rs.getString(7));
				productionTetail.setPrice(rs.getString(8));
				String[] listimg = rs.getString(9).split(";");
				List<Image> images = new ArrayList<Image>();
				for (int i = 0; i < listimg.length; i++) {
					Image img = new Image();
					img.setStt(i);
					img.setImage(listimg[i]);
					images.add(img);
				}

				productionTetail.setImageList(images);
				productionTetail.setComment(rs.getString(10));
				productionTetail.setDetail(rs.getString(11));
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productionTetail;
	}
 
}
