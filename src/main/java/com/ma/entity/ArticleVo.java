package com.ma.entity;

import java.util.List;

public class ArticleVo {

	private List<Node> nodelist;
	private Article article;
	private List<Reply> replylist;
	private Node node;
	public List<Node> getNodelist() {
		return nodelist;
	}
	public void setNodelist(List<Node> nodelist) {
		this.nodelist = nodelist;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public List<Reply> getReplylist() {
		return replylist;
	}
	public void setReplylist(List<Reply> replylist) {
		this.replylist = replylist;
	}
	public Node getNode() {
		return node;
	}
	public void setNode(Node node) {
		this.node = node;
	}
	
}
