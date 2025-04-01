package com.film.model;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

/**
 *	影片类
 */
public class Film {
	private Integer filmId;					//影片ID(自增)
	private String filmName;				//片名
	private String director;				//导演
	private String actor;					//主演
	private String cover;					//封面
	private String type;						//类型(XXX)
	private Date startTime;			//上映时间
	private Date endTime;				//下映时间
	private String description;				//简介
	private int likeCount;					//关注量
	private Date registrationTime;		//添加时间(default 当前时间)
	private MultipartFile file;
	private int times;
	
	public Integer getFilmId() {
		return filmId;
	}
	public void setFilmId(Integer filmId) {
		this.filmId = filmId;
	}
	public String getFilmName() {
		return filmName;
	}
	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public Date getRegistrationTime() {
		return registrationTime;
	}
	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	
}
