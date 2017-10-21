package com.ma.dao;

import com.ma.utils.DbHelp;

public class ArticleAndLableDao {
	
	public void insertIntoArticleAndLable(int articleid,int lableid) {
		String sql = "insert into t_article_lable (aid,lid) values (?,?)";
		DbHelp.upDate(sql, articleid,lableid);
	}

}
