package com.tulamweb.controller.login;

import java.io.IOException;

import java.util.Objects;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tulamweb.common.SendEmailUtil;
import com.tulamweb.common.StaticVariable;
import com.tulamweb.model.User;
import com.tulamweb.service.UserService;
import com.tulamweb.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/recover", "/otpconfirm", "/confirmuser", "/newpass"})
public class RecoverPass extends HttpServlet {

	/**
	 * 
	 */
	
	private UserService userService = new UserServiceImpl();
	private static final long serialVersionUID = 1L;
	
	String userMail = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = request.getRequestURI();
		String[] urls = url.split("/");
		
		if ("recover".equals(urls[2])) {
			RequestDispatcher rd = request.getRequestDispatcher("/view/recover.jsp");
			
			rd.forward(request, response);
		} else if ("confirmuser".equals(urls[2])) {
			RequestDispatcher rd = request.getRequestDispatcher("/view/confirmuser.jsp");
			
			String email = request.getParameter("email");
			User user;
			try {
				
				user = userService.findUserByEmail(email);
				if(Objects.nonNull(user)) {
					request.setAttribute("user", user);
					
				} else {
					request.setAttribute("error", "Khong tim thay tai khoan");
				}
				rd.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getRequestURI();
		String[] urls = url.split("/");
		
		if ("recover".equals(urls[2])) {
			userMail = request.getParameter("email");

			String subject = "Reset Pass From Vớ store !";

			Random r = new Random();
			int otp = r.nextInt((999999 - 100000) + 1) + 100000;
			HttpSession session = request.getSession();
			session.setAttribute("otp", String.valueOf(otp));
			
			session.setMaxInactiveInterval(60);
			String content = "Hello \r\n" + "đây là mã otp của bạn : " + otp + "\r\n" + "Thanks you!";

			try {
				SendEmailUtil.sendEmail(StaticVariable.HOST, StaticVariable.POST, StaticVariable.MAIL_SEVER,
						StaticVariable.PASSWORD, userMail, subject, content);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/view/otpconfirm.jsp");

			rd.forward(request, response);
		} else if ("otpconfirm".equals(urls[2])) {
			
			String optNum1 = request.getParameter("otp1");
			String optNum2 = request.getParameter("otp2");
			String optNum3 = request.getParameter("otp3");
			String optNum4 = request.getParameter("otp4");
			String optNum5 = request.getParameter("otp5");
			String optNum6 = request.getParameter("otp6");
			
			String fullOtp = optNum1 + optNum2 + optNum3 + optNum4 + optNum5 + optNum6;
			
			
			String sessionOtp = (String) request.getSession().getAttribute("otp");
			
			if (fullOtp.equals(sessionOtp)) {
				RequestDispatcher rd = request.getRequestDispatcher("/view/changenewpass.jsp");
				request.setAttribute("mail", userMail);
				rd.forward(request, response);
			} else {
				request.setAttribute("otpstatus", "Mã xác thực không đúng hoặc đã hết hạn");
				
				RequestDispatcher rd = request.getRequestDispatcher("/view/otpconfirm.jsp");

				rd.forward(request, response);
			}
		} else if ("newpass".equals(urls[2])) {
			
			String newPass = request.getParameter("userPass");
			String newPass1 = request.getParameter("userPass1");
			
			if(newPass.equals(newPass1)) {
				
				try {
					userService.changePassByEmail(newPass, userMail);
					
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					
				}
				
				response.sendRedirect("/com.tulamweb/login");
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/view/changenewpass.jsp");
				request.setAttribute("error", "Mật khẩu không khớp");
				rd.forward(request, response);
				return;
			}
			
		}
	}

}