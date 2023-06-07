package com.tulamweb.common;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class FilterRequest implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String url = req.getRequestURI();
		String[] urls = url.split("/");
		if (urls.length > 2) {
			String path = urls[2];
			if (path.equals("admin")) {
				Cookie[] cookies = req.getCookies();
				if (cookies.length > 1) {
					for (int i = 0; i < cookies.length; i++) {
						String c = cookies[i].getName().toString();
						if (c.equals("token")) {
							String session = (String) req.getSession().getAttribute(c);
							String check = checkAndDecodeCookie(cookies[i].getValue(), session, "ADMIN");

							if (check.equals("ok") || check.equals("ADMIN") ) {
								chain.doFilter(request, response);
							} else if ("Ban khong co quyen truy cap".equals(check)) {
								res.sendRedirect("/com.tulamweb/login?error=Ban khong co quyen truy cap");
							} else {
								res.sendRedirect("/com.tulamweb/login?error=Ban can dang nhap truoc");
							}
						}

					}

				}
			} else if (path.equals("homeUser")) {
				Cookie[] cookies = req.getCookies();
				if (cookies.length > 1) {
					for (int i = 0; i < cookies.length; i++) {
						String c = cookies[i].getName().toString();
						if (c.equals("token")) {
							String session = (String) req.getSession().getAttribute(c);
							String check = checkAndDecodeCookie(cookies[i].getValue(), session, "user");
								
							if (check.equals("ok")) {
								chain.doFilter(request, response);
							}else if ("ADMIN".equals(check)) {
								
								chain.doFilter(request, response);
							} 
							else if ("Ban khong co quyen truy cap".equals(check)) {
								res.sendRedirect("/com.tulamweb/login?error=Ban khong co quyen truy cap");
							} else {
								res.sendRedirect("/com.tulamweb/login?error=Ban can dang nhap truoc");
							}
						}
					}
				}
			} else {
				Cookie ck = new Cookie("token", "");
				Cookie ck1 = new Cookie("user", "");
				ck.setMaxAge(0);
				ck1.setMaxAge(0);
				res.addCookie(ck);
				res.addCookie(ck1);
				// res.sendRedirect("/com.tulamweb/logout");
				chain.doFilter(request, response);
			}

		} else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {

	}

	private String checkAndDecodeCookie(String cookie, String session, String role) {

		if (cookie.equals(session)) {

			byte[] decodedBytes = Base64.getDecoder().decode(session);
			String decodedString = new String(decodedBytes);
			if (decodedString.split(";")[2].equals("ADMIN")) {
				return "ADMIN";
			}
			if (decodedString.split(";")[2].equals(role) || role.equals("user")) {
				return "ok";
			} else {
				return "Ban khong co quyen truy cap";
			}

		} else {
			return "Hay dang nhap truoc";

		}
	}

}
