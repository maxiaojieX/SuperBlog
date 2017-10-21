package com.ma.utils;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ma.datesource.DataSource;


public class DbHelp {
	
	static Logger logger = LoggerFactory.getLogger(DbHelp.class);
	
	static QueryRunner runner = new QueryRunner(DataSource.getDataSource());
	
	public static void upDate(String sql,Object...obj){
		
		try {
			runner.update(sql, obj);
			logger.info("执行sql语句："+sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//新增
	public static int insert(String sql,Object...obj){
		int articleid = 0;
			try {
				articleid = runner.insert(sql, new ScalarHandler<Long>(), obj).intValue();
				logger.info("执行sql语句："+sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return articleid;
	}
	
	public static <T> T query(String sql,ResultSetHandler<T> rsh, Object...obj){
		T t = null;
		try {
			t = runner.query(sql, rsh, obj);
			logger.info("执行sql语句："+sql);
		} catch (SQLException e) {
		}
		return t;
	}
	

}
