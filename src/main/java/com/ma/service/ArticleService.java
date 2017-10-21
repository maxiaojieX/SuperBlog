package com.ma.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ma.dao.ArticleAndLableDao;
import com.ma.dao.ArticleDao;
import com.ma.dao.LableDao;
import com.ma.dao.ManagerArticleListDao;
import com.ma.dao.NodeDao;
import com.ma.dao.NotifyDao;
import com.ma.dao.ReplyDao;
import com.ma.entity.Article;
import com.ma.entity.Lable;
import com.ma.entity.Manager_ArticleList;
import com.ma.entity.Node;
import com.ma.entity.Notify;
import com.ma.entity.Reply;
import com.ma.utils.Page;

public class ArticleService {
	ArticleDao articleDao = new ArticleDao();
	ArticleAndLableDao articleandlableDao = new ArticleAndLableDao();
	ReplyDao replyDao = new ReplyDao();
	NodeDao nodeDao = new NodeDao();
	LableDao lableDao = new LableDao();
	NotifyDao notifyDao = new NotifyDao();
	ManagerArticleListDao managerArticleList = new ManagerArticleListDao();
	
	public List<Node> findAllNode() {
		List<Node> list = nodeDao.findAllNode();
		return list;
	}

	public int insert(Article article) {
		return articleDao.insertArticle(article);
	}

	public void insertIntoArticle_Lable(int articleid, String[] lables) {
		LableDao lableDao = new LableDao();
		for(String lable : lables) {
			Lable la = lableDao.findByLableName(lable);
			if( la != null) {
				articleandlableDao.insertIntoArticleAndLable(articleid, la.getId());
			}else {
				int lableid = lableDao.insert(lable);
				articleandlableDao.insertIntoArticleAndLable(articleid, lableid);
			}
		}
	}

	public List<Reply> findReply(int articleid) {
		List<Reply> replylist = replyDao.findAll(articleid);
		for(Reply a : replylist ) {
			String time = a.getCreatetime().toString();
			String t = time.substring(0, time.lastIndexOf("."));
			a.setRighttime(t);
		}
		return replylist;
	}

	public Article findById(String articleid) {
		Article article = null;
		if(StringUtils.isNumeric(articleid)) {
			article = articleDao.findById(Integer.parseInt(articleid));
			article.setScannum(article.getScannum()+1);
			articleDao.update(article);
		}else {
			//抛出异常
		}
		return article;
	}

	public Node findNodeById(int nodeid) {
		return nodeDao.findNodeByid(nodeid);
	}

	public void insertReply(Reply reply) {
		replyDao.insert(reply);
		//回复入库成功之后需要再往t_notify中插入一条提醒数据
		//根据id查询文章title
		Article article = articleDao.findById(reply.getArticleid());
		String title =article.getTitle();
		String content = "你的《"+title+"》有一条新回复，<a href=\"/user/detail?id="+reply.getArticleid()+"\">点击查看</a>";
		Notify notify = new Notify();
		notify.setContent(content);
		notifyDao.add(notify);
	}

	public Page<Article> findByLimit(String page, String nodename, String lablename, String keys) {
		
		//查询总条数
		String count = "select COUNT(*) from t_article";
		
		if(StringUtils.isNotEmpty(nodename)) {
			count += " INNER JOIN t_node on t_article.nodeid = t_node.nid where t_node.nodename like '"+nodename+"'";
		}else if(StringUtils.isNotEmpty(lablename)) {
			count += " INNER JOIN t_article_lable on t_article_lable.aid = t_article.id INNER JOIN t_lable on t_article_lable.lid = t_lable.lid WHERE t_lable.lablename  like '"+lablename+"'";
		}else if(StringUtils.isNotEmpty(keys)) {
			String k = "'%"+keys+"%'";
			count += " WHERE title LIKE "+ k;
		}
		ArticleDao articleDao = new ArticleDao();
		//获取到数据总条数
		int totel = articleDao.findLimit(count);
		//判断page是否有值
		//如果有传给Page
		//如果没有，默认给1
		int currentpage = 1;
		if(StringUtils.isNotEmpty(page)) {
			currentpage = Integer.parseInt(page);
		}
		
		Page<Article> p = new Page<>(totel,currentpage);
		//**************************************
		//nodename lablename keys 三个是互斥的
		//判断page nodename lablename keys 有没有值
		String sql = "select * from t_article";
		
		if(StringUtils.isNotEmpty(nodename)) {
			sql += " INNER JOIN t_node on t_article.nodeid = t_node.nid where t_node.nodename like '"+nodename+"'";
		}else if(StringUtils.isNotEmpty(lablename)) {
			sql += " INNER JOIN t_article_lable on t_article_lable.aid = t_article.id INNER JOIN t_lable on t_article_lable.lid = t_lable.lid WHERE t_lable.lablename  like '"+lablename+"'";
		}else if(StringUtils.isNotEmpty(keys)) {
			String k = "'%"+keys+"%'";
			sql += " WHERE title LIKE "+ k;
		}
		sql += " limit "+ ((currentpage-1)*3)+","+3;
		//查询
		List<Article> articlelist = articleDao.findArticleLimit(sql);
		
		for(Article a : articlelist) {
			//循环迭代每个article，根据articleid内联查询出对应的lablelist
			List<Lable> lablelist = lableDao.findByInner(a.getId());
			//暴力封装进article对象
			a.setLablelist(lablelist);
			
			//日期bug
			String time = a.getLastreplytime().toString();
			String t = time.substring(0, time.lastIndexOf("."));
			a.setRighttime(t);
		}
		p.setItem((ArrayList<Article>) articlelist);
		return p;
	}

	public List<Article> findMaxScan() {
		return articleDao.findMaxScan();
	}

	/**
	 * articleList专用方法,查询文章标题，node，lable，time
	 * @param currentpage当前页，可能不存在
	 * @return 
	 */
	public Page<Manager_ArticleList> findArticleList(String currentpage) {
		//判断currentpage有没有值，如果没有设为 1
		int currentp;
		if(StringUtils.isNumeric(currentpage)) {
			currentp = Integer.parseInt(currentpage);
		}else {
			currentp = 1;
		}
		
		//查询articlelist总数据条数
		String sql = "SELECT COUNT(*) FROM t_article INNER JOIN t_node on t_article.nodeid = t_node.nid ";
		int totolpage = managerArticleList.findCountArticleList(sql);
		//查询并封装Manager_ArticleList
		String sql2 = "SELECT * FROM t_article INNER JOIN t_node on t_article.nodeid = t_node.nid ";
		sql2 += " limit "+ ((currentp-1)*3)+","+3;
		List<Manager_ArticleList> mal = managerArticleList.findArticleList(sql2);
		//迭代每个Manager_ArticleList，把lablelist封装进去
		for(Manager_ArticleList m : mal) {
			List<Lable> lablelist = lableDao.findByInner(m.getId());
			m.setLablelist(lablelist);
			
			String time = m.getLastreplytime().toString();
			String t = time.substring(0, time.lastIndexOf("."));
			m.setRighttime(t);
			
		}
		Page<Manager_ArticleList> p2 = new Page<>(totolpage,currentp);
		p2.setItem((ArrayList<Manager_ArticleList>) mal);
		return p2;
	}

	public void delelteByArticleId(String del) {
		//判断del是否存在
		if(StringUtils.isNotEmpty(del)) {
			//判断是否为数字
			if(StringUtils.isNumeric(del)) {
				//如果是，调用DAO删除操作
				articleDao.delByArticleId(del);
			}
		}else {
			//不存在，抛出异常
			
		}
	}


}
