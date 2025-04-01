package com.film.model;

/**
 *	喜爱影片类
 */
public class LikeFilm {
	private Integer likeId;				//喜爱ID(自增)
	private User user;				//用户ID
	private Film film;				   //获取电影信息

	public Integer getLikeId() {
		return likeId;
	}
	public void setLikeId(Integer likeId) {
		this.likeId = likeId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}

}
