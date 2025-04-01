package com.film.model;

import java.util.Date;

/**
 *	电影评论类
 */
public class FilmComment {
	private Integer fimCommentId;			//评论ID(自增)
	private User user;					//评论者ID
	private Film film;						//获取电影信息
	private String content;					//内容
	private Date publishTime;				//发表时间(default当前时间)
	
	public Integer getFimCommentId() {
		return fimCommentId;
	}
	public void setFimCommentId(Integer fimCommentId) {
		this.fimCommentId = fimCommentId;
	}
	
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
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
