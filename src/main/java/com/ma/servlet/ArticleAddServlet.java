package com.ma.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;
import com.ma.dao.ArticleDao;
import com.ma.entity.Article;
import com.ma.entity.Node;
import com.ma.service.ArticleService;

@WebServlet("/admin/articleadd")
public class ArticleAddServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	ArticleDao articleDao = new ArticleDao(); 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArticleService articleService = new ArticleService();
		ArrayList<Node> nodelist = (ArrayList<Node>) articleService.findAllNode();
		req.setAttribute("nodelist", nodelist);
		forward("admin/article_add", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String nodeid = req.getParameter("node");
		String modifyid = req.getParameter("modifyid");
		String lablenames = req.getParameter("labelnames");
		
		ArticleService articleService = new ArticleService();
		Article article = new Article();
		article.setTitle(title);
		article.setContent(content);
		article.setNodeid(Integer.parseInt(nodeid));
		
		
		Document doc =  Jsoup.parseBodyFragment(content);
		String simplecontent = doc.select("p").first().toString();
		article.setSimplecontent(simplecontent);
		
		Element e = doc.select("img").first();
		if(e != null) {
			e.attr("width", "128px");
			e.attr("height","128px");
			article.setSimplepic(e.toString());
		}
		int articleid;
		if(StringUtils.isNotEmpty(modifyid)) {
			article.setId(Integer.parseInt(modifyid));
			articleDao.update(article);
			articleid = Integer.parseInt(modifyid);
		}else {
			 articleid = articleService.insert(article);
		}
		
		String[] lables = lablenames.split(",");
		articleService.insertIntoArticle_Lable(articleid,lables);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("Application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		Map<String,Object> maps = new HashMap<>();
		maps.put("state", "success");
		maps.put("data", articleid);
		Gson gson = new Gson();
		out.print(gson.toJson(maps));
	}
	
}
