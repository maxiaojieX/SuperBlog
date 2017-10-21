package com.ma.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.ma.dao.NodeDao;
import com.ma.entity.Article;
import com.ma.entity.Node;
import com.ma.service.ArticleService;
import com.ma.utils.Page;

@WebServlet("/user/home")
public class HomeServlet extends BaseServlet{
	

	private static final long serialVersionUID = 1L;
	ArticleService articleService = new ArticleService();
	NodeDao nodeDao = new NodeDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//可能获取page
		String page = req.getParameter("page");
		//可能获取节点nodename
		String nodename = req.getParameter("nodename");
		if(StringUtils.isNotEmpty(nodename)) {
			nodename = new String(nodename.getBytes("ISO8859-1"),"UTF-8");
		}
		//可能获取lablename
		String lablename = req.getParameter("lablename");
		if(StringUtils.isNotEmpty(lablename)) {
			lablename = new String(lablename.getBytes("ISO8859-1"),"UTF-8");
		}
		//可能获取title关键字keys
		String keys = req.getParameter("keys");
		if(StringUtils.isNotEmpty(keys)) {
			keys = new String(keys.getBytes("ISO8859-1"),"UTF-8");
		}
		List<Node> nodelist = nodeDao.findAllNode();
		req.setAttribute("nodelist", nodelist);
		
		Page<Article> pa = articleService.findByLimit(page,nodename,lablename,keys);
		req.setAttribute("page", pa);
		
		//查询最高浏览次数排行榜
		List<Article> ranking = articleService.findMaxScan();
		req.setAttribute("ranking", ranking);
		
		req.setAttribute("urikeys", keys);
		req.setAttribute("urinodename", nodename);
		req.setAttribute("urilablename", lablename);
		
//		//楼层
//		int floor = 0;
//		req.setAttribute("floor", floor);
		//请求转发至home.jsp
		forward("user/home", req, resp);
		
		
	}
}
