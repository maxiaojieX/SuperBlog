package com.ma.entity;

import java.sql.Timestamp;
import java.util.List;

public class Article {
	private int id;
	private String title;
	private String content;
	private int nodeid;
	private int scannum;
	private int replynum;
	private Timestamp lastreplytime;
	private String createtime;
	private String simplecontent;
	private String simplepic;
	
	
	public String getSimplecontent() {
		return simplecontent;
	}
	public void setSimplecontent(String simplecontent) {
		this.simplecontent = simplecontent;
	}
	public String getSimplepic() {
		return simplepic;
	}
	public void setSimplepic(String simplepic) {
		this.simplepic = simplepic;
	}
	private List<Lable> lablelist;
	public List<Lable> getLablelist() {
		return lablelist;
	}
	public void setLablelist(List<Lable> lablelist) {
		this.lablelist = lablelist;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public int getNodeid() {
		return nodeid;
	}
	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}
	public int getScannum() {
		return scannum;
	}
	public void setScannum(int scannum) {
		this.scannum = scannum;
	}
	public int getReplynum() {
		return replynum;
	}
	public void setReplynum(int replynum) {
		this.replynum = replynum;
	}
	public Timestamp getLastreplytime() {
		return lastreplytime;
	}
	public void setLastreplytime(Timestamp lastreplytime) {
		this.lastreplytime = lastreplytime;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	

	//只做时间bug处理，并不建议
	private String righttime;
	public String getRighttime() {
		return righttime;
	}
	public void setRighttime(String righttime) {
		this.righttime = righttime;
	}

}
