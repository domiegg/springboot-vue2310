package com.film.model;
import java.text.DecimalFormat;
import java.util.Date;

/**
 *	电影排片类
 */
public class FilmArrangement {
	private Integer arrangementId;			//ID(自增)
	private Film film;						//电影ID
	private Cinema cinema;					//影院ID
	private Date filmStartTime; 			//影片开始时间
	private Date filmEndTime;				//影片结束时间
	private String language;				//语言版本(XXX)
	private String filmHall;				//放映厅(XXX)
	private Integer seatCount;				//座位数
	private float price;					//价格(￥)
	
	public Integer getArrangementId() {
		return arrangementId;
	}
	public void setArrangementId(Integer arrangementId) {
		this.arrangementId = arrangementId;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public Cinema getCinema() {
		return cinema;
	}
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	public Date getFilmStartTime() {
		return filmStartTime;
	}
	public void setFilmStartTime(Date filmStartTime) {
		this.filmStartTime = filmStartTime;
	}
	public Date getFilmEndTime() {
		return filmEndTime;
	}
	public void setFilmEndTime(Date filmEndTime) {
		this.filmEndTime = filmEndTime;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getFilmHall() {
		return filmHall;
	}
	public void setFilmHall(String filmHall) {
		this.filmHall = filmHall;
	}
	public Integer getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
	}
	public String getPrice() {
		  DecimalFormat   fnum  =   new  DecimalFormat("##0.00");  
		  String   p=fnum.format(price);  
		return p;
	}
	public void setPrice(float price) {
		this.price = price;
	}

}
