package com.tulamweb.controller.login;

import java.io.IOException;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.util.StringUtils;
import com.tulamweb.common.StaticVariable;
import com.tulamweb.model.User;
import com.tulamweb.service.UserService;
import com.tulamweb.service.impl.UserServiceImpl;

@WebServlet("/regist")
public class Regist extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String error = "";

	UserService userService = new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/view/regist.jsp");
		if (!StringUtils.isNullOrEmpty(error)) {
			request.setAttribute("error", error);
		}

		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String fullname =java.net.URLDecoder.decode(request.getParameter("fullname"), "UTF-8");
		String username = request.getParameter("username");
		String mail = request.getParameter("mail");
		
		if (!validate(mail)) {
			error = "Email không đúng định dạng";
			doGet(request, response);
			return;
		}
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");

		if (password.equals(repassword)) {
			User user = new User(fullname, username, password, mail, repassword);
			try {
				userService.insertUser(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("/com.tulamweb/login");

			error = "";
		} else {
			error = "Nhập lại mật khẩu không đúng";
			doGet(request, response);
			return;
		}
	}

	public boolean validate(String emailStr) {
	    Pattern pattern = Pattern.compile("^[a-z0-9](\\.?[a-z0-9]){5,}@g(oogle)?mail\\.com$", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(emailStr);
	    boolean matchFound = matcher.find();
		return matchFound;
	}
}
