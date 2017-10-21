package com.ma.entity;

import java.sql.Timestamp;

public class Reply {
	private int id;
	private int pid;
	private String content;
	private String userip;
	private int articleid;
	private Timestamp createtime;
	
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserip() {
		return userip;
	}
	public void setUserip(String userip) {
		this.userip = userip;
	}
	public int getArticleid() {
		return articleid;
	}
	public void setArticleid(int articleid) {
		this.articleid = articleid;
	}
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
