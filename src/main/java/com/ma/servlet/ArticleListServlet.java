package com.ma.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ma.entity.Manager_ArticleList;
import com.ma.service.ArticleService;
import com.ma.utils.Page;

@WebServlet("/admin/ArticleList")
public class ArticleListServlet extends BaseServlet{

	ArticleService articleservice = new ArticleService();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//有可能获取到currentPage
		String currentpage = req.getParameter("currentpage");
		//专用方法一次性查询
		Page<Manager_ArticleList> p = articleservice.findArticleList(currentpage);
		req.setAttribute("page", p);
		
		//请求转发至ArticleList.jsp页面
		forward("admin/articlelist", req, resp);
	}

}
