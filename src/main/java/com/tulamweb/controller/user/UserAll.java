package com.tulamweb.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tulamweb.common.StaticVariable;
@WebServlet("/homeUser")
public class UserAll extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8199560067216866777L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/view/home/homepage.jsp");
		String name = request.getParameter("name");
		String error = request.getParameter("error");
		
		System.out.println(StaticVariable.role);
		request.setAttribute("role", StaticVariable.role);
		request.setAttribute("name", name);
		request.setAttribute("error", error);
		rd.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	}
}
