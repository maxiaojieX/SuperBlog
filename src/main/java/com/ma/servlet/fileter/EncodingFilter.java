package com.ma.servlet.fileter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


import com.ma.utils.Config;

public class EncodingFilter extends Fileter{

	String encoding = "UTF-8";
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		encoding = Config.get("encoding");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding(encoding);
		chain.doFilter(req, resp);
	}

}
