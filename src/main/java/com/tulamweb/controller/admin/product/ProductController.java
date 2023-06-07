package com.tulamweb.controller.admin.product;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.tag.common.core.ForEachSupport;
import org.apache.taglibs.standard.tag.common.xml.IfTag;
import org.json.JSONObject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mysql.cj.util.StringUtils;
import com.tulamweb.common.FileCommon;
import com.tulamweb.model.Category;
import com.tulamweb.model.ParentsCategory;
import com.tulamweb.model.ProductionTetails;
import com.tulamweb.model.Size;
import com.tulamweb.model.SizeQty;
import com.tulamweb.request.ProductRequest;
import com.tulamweb.service.CategoryService;
import com.tulamweb.service.ProductService;
import com.tulamweb.service.SizeService;
import com.tulamweb.service.impl.CategoryServiceImlp;
import com.tulamweb.service.impl.ProductServiceImpl;
import com.tulamweb.service.impl.SizeServiceImpl;

@WebServlet(urlPatterns = { "/admin/product", "/admin/api", "/admin/addproduct" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
		maxFileSize = 1024 * 1024 * 50, // 50 MB
		maxRequestSize = 1024 * 1024 * 200) // 100 MB
public class ProductController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CategoryService categoryService = new CategoryServiceImlp();

	SizeService sizeService = new SizeServiceImpl();

	ProductService productService = new ProductServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getRequestURI();
		String[] urls = url.split("/");

		if ("product".equals(urls[3])) {

			List<ProductionTetails> productionTetails = productService.getAllProductionTetails();
			request.setAttribute("productionTetails", productionTetails);

			List<ParentsCategory> parentscategory = categoryService.getParentsCategory();
			request.setAttribute("parentsCates", parentscategory);

			RequestDispatcher rd = request.getRequestDispatcher("/view/admin/product/productList.jsp");
			rd.forward(request, response);
		} else if ("api".equals(urls[3])) {
			if (!StringUtils.isNullOrEmpty(request.getParameter("parenrid"))) {

				String id = request.getParameter("parenrid");

				List<Category> categorys = categoryService.getCategoryById(id);
				List<Size> sizes = sizeService.getSizeById(id);

				Gson gson = new Gson();

				request.setAttribute("categorys", categorys);
				request.setAttribute("sizes", sizes);

				String categorysjson = gson.toJson(categorys);

				String sizesjson = gson.toJson(sizes);

				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				JSONObject json = new JSONObject();
				json.put("categorys", categorysjson);
				json.put("sizes", sizesjson);
				out.print(json);
				out.flush();
			} if (!StringUtils.isNullOrEmpty(request.getParameter("productid"))) {

				String id = request.getParameter("productid");

				ProductionTetails prd = productService.getProductById(id);
				List<SizeQty> sizes = sizeService.getSizeByProductId(id);
				
				Gson gson = new Gson();

				request.setAttribute("product", prd);
				request.setAttribute("sizes", sizes);

				String categorysjson = gson.toJson(prd);

				String sizesjson = gson.toJson(sizes);

				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				JSONObject json = new JSONObject();
				json.put("product", categorysjson);
				json.put("sizes", sizesjson);
				out.print(json);
				out.flush();
			}
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String url = request.getRequestURI();
		String[] urls = url.split("/");

		if ("addproduct".equals(urls[3])) {
			ProductRequest product = new ProductRequest();

			String parentId = request.getParameter("parentcategoryId");
			String categoryId = request.getParameter("categoryId");
			String productName = request.getParameter("productName");
			String shotTitle = request.getParameter("shotTitle");
			String price = request.getParameter("price");
			String comment = request.getParameter("comment");
			String detail = request.getParameter("detail");

			String sizeList = request.getParameter("sizelist");

			JSONObject jsonObject = new JSONObject(sizeList);
			ObjectMapper objectMapper = new ObjectMapper();
			List<SizeQty> sizeQties = new ArrayList<SizeQty>();
			
			for(Entry<String, Object> map : jsonObject.toMap().entrySet()) {
				SizeQty myNestedList = new SizeQty();
				myNestedList = objectMapper.convertValue(map.getValue(), SizeQty.class);
				sizeQties.add(myNestedList);
			}

			String savePath = getServletContext().getRealPath("/") + "upload";
			
			System.out.println(savePath);

			String filePart = FileCommon.upLoadFile(request, savePath);

			product.setSiteId(sizeQties);
			product.setParentId(Integer.parseInt(parentId));
			product.setCategoryId(Integer.parseInt(categoryId));
			product.setProductName(productName);
			product.setShotTitle(shotTitle);
			product.setPrice(Long.parseLong(price));
			product.setComment(comment);
			product.setDetail(detail);
			product.setPart(filePart);

			boolean check = productService.insertProduct(product);
			if (check) {
				response.sendRedirect("/com.tulamweb/admin/product");
			} else {
				response.sendRedirect("/com.tulamweb/admin/product");

			}
		}
	}

}
