package com.tulamweb.controller.admin.size;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tulamweb.model.ParentsCategory;
import com.tulamweb.model.Size;
import com.tulamweb.service.CategoryService;
import com.tulamweb.service.SizeService;
import com.tulamweb.service.impl.CategoryServiceImlp;
import com.tulamweb.service.impl.SizeServiceImpl;

@WebServlet(urlPatterns = { "/admin/size", "/admin/addsize", "/admin/updatesize", "/admin/deletesize"})
public class SizeController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CategoryService categoryService = new CategoryServiceImlp() {
		
	};
	SizeService sizeService = new SizeServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		String url = request.getRequestURI();
		String[] urls = url.split("/");

		if ("size".equals(urls[3])) {

			
			List<Size> sizeLits = sizeService.getSize();
			request.setAttribute("sizelit", sizeLits);
			
			List<ParentsCategory> parentscategory = categoryService.getParentsCategory();
			request.setAttribute("parentsCates", parentscategory);
			
			RequestDispatcher rd = request.getRequestDispatcher("/view/admin/size/sizeList.jsp");
			rd.forward(request, response);

		}else if ("addsize".equals(urls[3])) {
		
			RequestDispatcher rd = request.getRequestDispatcher("/view/admin/size/sizeList.jsp");
			rd.forward(request, response);}

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String url = request.getRequestURI();
		String[] urls = url.split("/");

		if ("addsize".equals(urls[3])) {
			String nameSize = java.net.URLDecoder.decode(request.getParameter("sizeName"), "UTF-8");
			String id = request.getParameter("parentcategoryId");
			boolean check = sizeService.insertSize(nameSize, id);
			
			if (check) {

				response.sendRedirect("/com.tulamweb/admin/size");
			} else {
				response.sendRedirect("/com.tulamweb/admin/size");
				request.setAttribute("error", "thêm thất bại");
			}
		}else if ("updatesize".equals(urls[3])) {
			String name = java.net.URLDecoder.decode(request.getParameter("sizeName"), "UTF-8");
			String id = request.getParameter("sizeId");
			
			boolean check = sizeService.updateSizeName(name, id);
			if(check) {
			response.sendRedirect("/com.tulamweb/admin/size");
			}else {
				response.sendRedirect("/com.tulamweb/admin/size");
				request.setAttribute("error", "sửa thất bại");
			}
		
		
		}else if ("deletesize".equals(urls[3])) {
	
		
		String id = request.getParameter("sizeId");
		
		boolean check = sizeService.deleteSizeName(id);
		if(check) {
		response.sendRedirect("/com.tulamweb/admin/size");
		}else {
			response.sendRedirect("/com.tulamweb/admin/size");
			request.setAttribute("error", "xóa thất bại");
			
		}
		}
		
}
}

