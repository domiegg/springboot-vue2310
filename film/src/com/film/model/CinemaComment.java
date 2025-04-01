package com.film.model;

import java.util.Date;

/**
 *	影院评论类
 */
public class CinemaComment {
	private Integer commentId;				//影院评论ID(自增)
	private Cinema cinema;				//影院ID
	private User user;				//评论者ID
	private String content;				//内容
	private Date publishTime;		//发表时间(default当前时间)
	
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Cinema getCinema() {
		return cinema;
	}
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

}
