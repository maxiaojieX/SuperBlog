package com.ma.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ma.entity.Node;
import com.ma.utils.DbHelp;

public class NodeDao {

	public List<Node> findAllNode() {
		String sql = "select * from t_node";
		return (List<Node>) DbHelp.query(sql, new BeanListHandler<>(Node.class));
	}

	public Node findNodeByid(int nodeid) {
		String sql = "select * from t_node where nid = ?";
		return DbHelp.query(sql, new BeanHandler<>(Node.class), nodeid);
	}

	public int findCountNode() {
		String sql ="select COUNT(*) from t_node";
		return DbHelp.query(sql, new ScalarHandler<Long>()).intValue();
	}

	public void delByNid(String nodenid) {
		String sql = "delete from t_node where nid = ?";
		DbHelp.upDate(sql, nodenid);
	}

	public Node findNodeBynodename(String newnodename) {
		String sql = "select * from t_node where nodename = ?";
		return DbHelp.query(sql, new BeanHandler<>(Node.class), newnodename);
	}

	public void addNewNode(String newnodename) {
		String sql = "insert into t_node (nodename) values (?)";
		DbHelp.upDate(sql, newnodename);
	}

}
