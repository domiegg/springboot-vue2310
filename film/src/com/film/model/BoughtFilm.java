package com.film.model;
import java.util.Date;

/**
 *	购票类
 */
public class BoughtFilm {
	private Integer boughtId;					//购票ID(自增)
	private Integer userId;						//用户ID
	private Cinema cinema;						//获取影院名称
	private Film film;							//获取电影名称
	private FilmArrangement filmArrangement;	//获取影票价格(￥)
	private Date purchasingDate;				//购票时间（default 当前时间）
	
	public Integer getBoughtId() {
		return boughtId;
	}
	public void setBoughtId(Integer boughtId) {
		this.boughtId = boughtId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Cinema getCinema() {
		return cinema;
	}
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public FilmArrangement getFilmArrangement() {
		return filmArrangement;
	}
	public void setFilmArrangement(FilmArrangement filmArrangement) {
		this.filmArrangement = filmArrangement;
	}
	public Date getPurchasingDate() {
		return purchasingDate;
	}
	public void setPurchasingDate(Date purchasingDate) {
		this.purchasingDate = purchasingDate;
	}

}
