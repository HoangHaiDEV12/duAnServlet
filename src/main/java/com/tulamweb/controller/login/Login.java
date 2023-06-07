package com.tulamweb.controller.login;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tulamweb.common.StaticVariable;
import com.tulamweb.model.UserRole;
import com.tulamweb.service.UserService;
import com.tulamweb.service.impl.UserServiceImpl;

@WebServlet("/login")
public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1750055382903405396L;

	private UserService userService = new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/view/login.jsp");
		String error = request.getParameter("error");
		request.setAttribute("error", error);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String username = java.net.URLDecoder.decode(request.getParameter("userName"), "UTF-8");
		String pass = request.getParameter("userPass");

		UserRole userRole = null;

		try {
			userRole = userService.getUserByUseName(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (userRole == null) {
			response.sendRedirect("/com.tulamweb/login?error=Tai khoan khong ton tai");
			return;

		} else {

			if (pass.equals(userRole.getPass())) {
				String token = userRole.getUserName() + ";" + userRole.getPass() + ";" + userRole.getRole() + ";" + userRole.getFullName();
				
				String newToken = Base64.getEncoder().encodeToString(token.getBytes());
				
				Cookie ck = new Cookie("token", newToken);
				response.addCookie(ck);
				
				HttpSession session = request.getSession(false);
				session.setAttribute("token", newToken);
				
				if (userRole.getRole().equals("ADMIN")) {

					//response.sendRedirect("/com.tulamweb/admin?name=" + username);
					RequestDispatcher rd = request.getRequestDispatcher("/view/loginoption.jsp");
					request.setAttribute("name", userRole.getFullName());
					
					StaticVariable.role = userRole.getRole();
					
					rd.forward(request, response);
					return;
				} else {
					StaticVariable.role = "";
					response.sendRedirect("/com.tulamweb/homeUser?name=" + username);
					return;
				}
			} else {

				response.sendRedirect("/com.tulamweb/login?error=Mat khau sai");
				return ; 
			}
		}

//		boolean check = false;
//			if (username.equals(listUser.get(i).getUserName())) {
//				check = false;
//				if (pass.equals(listUser.get(i).getPass())) {
//
//					role = listUser.get(i).getRole();
//					Cookie ck = new Cookie("token", role);
//					Cookie ck1 = new Cookie("user", username);
//					response.addCookie(ck1);
//					response.addCookie(ck);
//
//					HttpSession session = request.getSession(false);
//					session.setAttribute(username, role);
//
//					if (role.equals("admin")) {
//
//						response.sendRedirect("/com.tulamweb/admin?name=" + username);
//						return;
//					} else {
//
//						response.sendRedirect("/com.tulamweb/homeUser?name=" + username);
//						return;
//					}
//
//				} else {
//					response.sendRedirect("/com.tulamweb/login?error=Mat khau sai");
//					return;
//				}
//
//			} else {
//				check = true;
//			}
//		}
//		if (check) {
//			response.sendRedirect("/com.tulamweb/login?error=Tai khoan khong ton tai");
//			return;
//		}

	}
}
