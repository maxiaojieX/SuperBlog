package com.ma.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ma.entity.Article;
import com.ma.utils.DbHelp;

public class ArticleDao {
	
	public int insertArticle(Article article) {
		
		String sql = "insert into t_article (title,content,nodeid,scannum,replynum,simplecontent,simplepic) values (?,?,?,?,?,?,?)";
		return DbHelp.insert(sql, article.getTitle(),article.getContent(),article.getNodeid(),article.getScannum(),article.getReplynum(),article.getSimplecontent(),article.getSimplepic());
	}

	public Article findById(int articleid) {
		String sql = "select * from t_article where id = ?";
		return DbHelp.query(sql, new BeanHandler<>(Article.class),articleid);
	}

	public void update(Article article) {
		String sql = "update t_article set title = ?,content = ?,nodeid = ?,scannum = ?,replynum = ? where id = ?";
		DbHelp.upDate(sql, article.getTitle(),article.getContent(),article.getNodeid(),article.getScannum(),article.getReplynum(),article.getId());
	}

	public int findLimit(String count) {
		return DbHelp.query(count, new ScalarHandler<Long>()).intValue();
	}

	public List<Article> findArticleLimit(String sql) {
		return DbHelp.query(sql, new BeanListHandler<>(Article.class));
	}

	public List<Article> findMaxScan() {
		String sql = "SELECT * FROM t_article ORDER BY scannum DESC LIMIT 0,5";
		return DbHelp.query(sql, new BeanListHandler<>(Article.class));
	}

	public void delByArticleId(String del) {
		String sql = "delete from t_article where id = ?";
		DbHelp.upDate(sql, del);
	}

	public List<Article> findByNodeid(String nodenid) {
		String sql = "select * from t_article where nodeid = ?";
		return DbHelp.query(sql, new BeanListHandler<>(Article.class), nodenid);
	}

}
