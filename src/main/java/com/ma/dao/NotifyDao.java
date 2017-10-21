package com.ma.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ma.entity.Notify;
import com.ma.utils.DbHelp;

public class NotifyDao {

	public int findCountAll() {
		String sql = "select count(*) from t_notify";
		return DbHelp.query(sql, new ScalarHandler<Long>()).intValue();
	}

	public List<Notify> findAll(int p) {
		String sql = "select * from t_notify order by state asc, createtime desc limit ?,3";
		return DbHelp.query(sql, new BeanListHandler<>(Notify.class), p);
	}

	public void add(Notify notify) {
		String sql = "insert into t_notify (content) values (?)";
		DbHelp.upDate(sql, notify.getContent());
	}

	public int findNotRead() {
		String sql = "select count(*) from t_notify where state = 0";
		return DbHelp.query(sql, new ScalarHandler<Long>()).intValue();
	}

	public void update(int parseInt) {
		int READ = 1;
		Timestamp time = new Timestamp(System.currentTimeMillis());
		String sql = "update t_notify set state = ?,readtime = ? where id = ?";
		DbHelp.upDate(sql, READ,time,parseInt);
	}

}
