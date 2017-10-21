package com.ma.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.ma.dao.ArticleDao;
import com.ma.dao.NodeDao;
import com.ma.entity.Article;
import com.ma.entity.ArticleVo;
import com.ma.entity.Node;
import com.ma.entity.Reply;
import com.ma.service.ArticleService;

@WebServlet("/user/detail")
public class DetailServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	NodeDao nodeDao = new NodeDao();
	ArticleService articleService = new ArticleService();
	ArticleVo articleVo = new ArticleVo();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String articleid = req.getParameter("id");
		
		//��ȡ���нڵ㣬�����ڵ�����չʾ
		List<Node> nodelist = nodeDao.findAllNode();
		//��ȡ�������� 
		Article article = articleService.findById(articleid);
		//��ȡ�����б�list
		List<Reply> replylist = articleService.findReply(Integer.parseInt(articleid));
		//��ȡ���¶�Ӧ��node
		Node node = articleService.findNodeById(article.getNodeid());
		//articleVo.setNodelist(nodelist);ǰ�˻�ȡ����
		articleVo.setArticle(article);
		//articleVo.setReplylist(replylist);ǰ�˻�ȡ����
		articleVo.setNode(node);
		
		//
		resp.setCharacterEncoding("UTF-8");
		req.setAttribute("nodelist", nodelist);
		req.setAttribute("replylist", replylist);
		req.setAttribute("articlevo", articleVo);
		forward("user/detail",req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//��ȡ��ǰ��������id
		String articleid = req.getParameter("id");
		//��ȡ�ظ�����
		String replyContent = req.getParameter("editor");
		//��ȡuserip
		String userip = req.getRemoteAddr();
		
		String pid = req.getParameter("pid");
		
		
		Reply reply = new Reply();
		reply.setArticleid(Integer.parseInt(articleid));
		reply.setContent(replyContent);
		reply.setUserip(userip);
		if(StringUtils.isNotEmpty(pid)) {
			reply.setPid(Integer.parseInt(pid));
		}
		//�������ظ���
		articleService.insertReply(reply);
		//���»ظ�����
		Article article = articleService.findById(articleid);
		article.setReplynum(article.getReplynum()+1);
		ArticleDao articleDao = new ArticleDao();
		articleDao.update(article);
		//�ض�����/user/detail?id=
		resp.sendRedirect("/user/detail?id=" + articleid);
	}
}
