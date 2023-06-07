package com.tulamweb.controller.admin.category;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tulamweb.model.Category;
import com.tulamweb.model.ParentsCategory;
import com.tulamweb.service.CategoryService;
import com.tulamweb.service.impl.CategoryServiceImlp;

@WebServlet(urlPatterns = { "/admin/category", "/admin/addcategory", "/admin/updateCategory", "/admin/deletecategory",
		"/admin/subcategory","/admin/updateCategoryDetail","/admin/deleteCategoryDetail" })
public class CategoryController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CategoryService categoryService = new CategoryServiceImlp();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getRequestURI();
		String[] urls = url.split("/");

		if ("addcategory".equals(urls[3])) {

			RequestDispatcher rd = request.getRequestDispatcher("/view/admin/categoryList.jsp");
			rd.forward(request, response);
		} else if ("subcategory".equals(urls[3])) {
			
			List<ParentsCategory> parentscategory = categoryService.getParentsCategory();
			request.setAttribute("parentsCates", parentscategory);
			
			List<Category> categories = categoryService.getAllCategory();
			
			request.setAttribute("categories", categories);
			
			RequestDispatcher rd = request.getRequestDispatcher("/view/admin/subCategory.jsp");
			rd.forward(request, response);
		} else {
			
			List<ParentsCategory> parentscategory = categoryService.getParentsCategory();
			request.setAttribute("parentsCates", parentscategory);
			
			RequestDispatcher rd = request.getRequestDispatcher("/view/admin/categoryList.jsp");
			rd.forward(request, response);

		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String url = request.getRequestURI();
		String[] urls = url.split("/");

		if ("addcategory".equals(urls[3])) {
			String categoryName = java.net.URLDecoder.decode(request.getParameter("category"), "UTF-8");

			boolean check = categoryService.insertParentsCategory(categoryName);

			if (check) {

				response.sendRedirect("/com.tulamweb/admin/category");
			} else {
				response.sendRedirect("/com.tulamweb/admin/category");
				request.setAttribute("error", "them that bai");
			}
		} else if ("updateCategory".equals(urls[3])) {

			String name = java.net.URLDecoder.decode(request.getParameter("categoryName"), "UTF-8");
			String id = request.getParameter("categoryId");

			categoryService.updateParentsCategoryName(id, name);

			response.sendRedirect("/com.tulamweb/admin/category");

		} else if ("deletecategory".equals(urls[3])) {

			String id1 = request.getParameter("categoryId");

			categoryService.deleteParentsCategory(id1);

			response.sendRedirect("/com.tulamweb/admin/category");
		} else if ("subcategory".equals(urls[3])) {

			String id = java.net.URLDecoder.decode(request.getParameter("parentcategoryId"), "UTF-8");
			String name = request.getParameter("categoryName");
			
			boolean check = categoryService.insertSubCategory(id, name);
			
			response.sendRedirect("/com.tulamweb/admin/subcategory");
		} else if ("updateCategoryDetail".equals(urls[3])) {
			
			String id = java.net.URLDecoder.decode(request.getParameter("categoryId"), "UTF-8");
			String name = request.getParameter("categoryName");
			 
			boolean check = categoryService.updateCategoryName(id, name);
			
			response.sendRedirect("/com.tulamweb/admin/subcategory");
		}else if ("deleteCategoryDetail".equals(urls[3])) {

			String id = request.getParameter("categoryId");
			
			boolean check = categoryService.deleteCategoryName(id);
			
			response.sendRedirect("/com.tulamweb/admin/subcategory");
	}
	}
}
