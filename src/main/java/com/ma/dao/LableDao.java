package com.ma.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ma.entity.Lable;
import com.ma.utils.DbHelp;

public class LableDao {
	
	public Lable findByLableName(String lablename) {
		String sql = "select * from t_lable where lablename = ?";
		return DbHelp.query(sql, new BeanHandler<>(Lable.class), lablename);
	}
	public int insert(String lablename) {
		String sql = "insert into t_lable (lablename) values (?)";
		return DbHelp.insert(sql, lablename);
	}
	/**
	 * 根据articleid内联查询出所有lablelist
	 * @param id
	 * @return
	 */
	public List<Lable> findByInner(int articleid) {
		String sql = "SELECT * FROM t_lable INNER JOIN t_article_lable ON t_lable.lid = t_article_lable.lid INNER JOIN t_article ON t_article_lable.aid = t_article.id WHERE t_article.id = ?";
		return DbHelp.query(sql, new BeanListHandler<>(Lable.class),articleid);
	}

}
