package com.ma.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ma.service.ArticleService;

@WebServlet("/admin/articlelistdel")
public class ArticleListDel extends BaseServlet{

	private static final long serialVersionUID = 1L;
	ArticleService articleservice = new ArticleService();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取要删除的article的id
		String del = req.getParameter("del");
		//删除文章,需要判断del是否存在
		articleservice.delelteByArticleId(del);
		
		//重定向至/admin/ArticleList
		resp.sendRedirect("/admin/ArticleList");
		
	}

}
