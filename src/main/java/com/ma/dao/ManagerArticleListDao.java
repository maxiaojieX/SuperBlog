package com.ma.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ma.entity.Manager_ArticleList;
import com.ma.utils.DbHelp;

public class ManagerArticleListDao {

	/**
	 * 
	 * @param sql
	 * @return 返回ManagerArticleList总数据条数
	 */
	public int findCountArticleList(String sql) {
		return DbHelp.query(sql, new ScalarHandler<Long>()).intValue();
	}

	public List<Manager_ArticleList> findArticleList(String sql2) {
		return DbHelp.query(sql2, new BeanListHandler<>(Manager_ArticleList.class));
	}
	

}
