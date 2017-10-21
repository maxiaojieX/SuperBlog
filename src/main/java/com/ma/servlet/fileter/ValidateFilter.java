package com.ma.servlet.fileter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValidateFilter extends Fileter{

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)arg0;
		HttpServletResponse resp = (HttpServletResponse)arg1;
		String uri = req.getRequestURI();
		HttpSession session = req.getSession();
		if(uri.startsWith("/admin")) {
			//如果访问/admin需要判断Session有没有登录
			if(session.getAttribute("user") != null) {
				//如果不等于null，表示已经登录过了
				arg2.doFilter(req, resp);
			}else {
				//等于null，打回登录页面吧
				resp.sendRedirect("/login?callback="+uri);
			}
		}else {
			arg2.doFilter(req, resp);
		}
	}

}
