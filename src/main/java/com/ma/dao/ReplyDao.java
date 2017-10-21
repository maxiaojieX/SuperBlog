package com.ma.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ma.entity.Reply;
import com.ma.utils.DbHelp;

public class ReplyDao {

	public List<Reply> findAll(int articleid) {
		String sql = "select * from t_reply where articleid = ?";
		return DbHelp.query(sql, new BeanListHandler<>(Reply.class),articleid);
	}

	public void insert(Reply reply) {
		String sql = "insert into t_reply (pid,content,userip,articleid) values (?,?,?,?)";
		DbHelp.upDate(sql, reply.getPid(),reply.getContent(),reply.getUserip(),reply.getArticleid());
	}

	public int findCountAllReply() {
		String sql = "select COUNT(*) from t_reply";
		return DbHelp.query(sql, new ScalarHandler<Long>()).intValue();
	}

	public List<Reply> findAllReply(int currentpage) {
		String sql = "select * from t_reply";
		sql += " limit "+ ((currentpage-1)*3)+","+3;
		return DbHelp.query(sql, new BeanListHandler<>(Reply.class));
	}

	public void deleteById(String replyid) {
		String sql = "delete from t_reply where id = ?";
		DbHelp.upDate(sql, replyid);
	}
	

}
