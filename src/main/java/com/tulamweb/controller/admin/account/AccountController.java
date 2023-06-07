package com.tulamweb.controller.admin.account;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tulamweb.request.AccountRequest;
import com.tulamweb.request.RoleRequest;
import com.tulamweb.service.AccountSevice;
import com.tulamweb.service.impl.AccountSeviceImpl;


@WebServlet(urlPatterns = { "/admin/account", "/admin/updateaccount", "/admin/deleteaccount"})
public class AccountController extends HttpServlet {

	
		 	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AccountSevice accountSevice = new AccountSeviceImpl();
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			String url = request.getRequestURI();
			String[] urls = url.split("/");

			if ("account".equals(urls[3])) {
				
				List<AccountRequest> accountRequests = accountSevice.getAccount();
				request.setAttribute("accountRequests", accountRequests);
				
				List<RoleRequest> roleRequests = accountSevice.getRole();
				request.setAttribute("roleRequests", roleRequests);
				RequestDispatcher rd = request.getRequestDispatcher("/view/admin/account/accountList.jsp");
				rd.forward(request, response);
			} else {
			}

		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			
			request.setCharacterEncoding("UTF-8");

			String url = request.getRequestURI();
			String[] urls = url.split("/");

			if ("updateaccount".equals(urls[3])) {
				
				String roleid =request.getParameter("roleId");
				String userid =request.getParameter("userId");
				
				boolean check = accountSevice.updateAccount(userid, roleid);
				
				if(check) {
					response.sendRedirect("/com.tulamweb/admin/account");
				}else {
					response.sendRedirect("/com.tulamweb/admin/account");
					
				}
			 	
			}else if (("deleteaccount".equals(urls[3]))) {
				
				String userid = request.getParameter("userId");
				
				boolean check = accountSevice.deleteAccount(userid);
				
				response.sendRedirect("/com.tulamweb/admin/account");
				
			}
		}

	}

