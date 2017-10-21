package com.ma.entity;

import java.sql.Timestamp;
import java.util.List;

public class Manager_ArticleList {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String title;
	private String nodename;
	private List<Lable> lablelist;
	private Timestamp lastreplytime;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNodename() {
		return nodename;
	}
	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	public List<Lable> getLablelist() {
		return lablelist;
	}
	public void setLablelist(List<Lable> lablelist) {
		this.lablelist = lablelist;
	}
	public Timestamp getLastreplytime() {
		return lastreplytime;
	}
	public void setLastreplytime(Timestamp lastreplytime) {
		this.lastreplytime = lastreplytime;
	}
	private Timestamp createtime;
	
	
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	private String righttime;
	public String getRighttime() {
		return righttime;
	}
	public void setRighttime(String righttime) {
		this.righttime = righttime;
	}

}
