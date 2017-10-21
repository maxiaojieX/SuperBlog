package com.ma.entity;

import java.sql.Timestamp;

public class T_ReplyAndArticle {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String title;
	private String content;
	private String userip;
	private Timestamp createtime;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
