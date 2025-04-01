package com.film.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

/**
 *	资讯类
 */
public class FilmNews {
	private Integer newsId;				//资讯ID(1001开始自动增长)
	private Cinema cinema;				//影院ID
	private String newsTitle;			//资讯标题
	private String newsContent;			//资讯内容
	private Date publishTime;			//发表时间
	private MultipartFile file;		
	private String picture;				//资讯图片
	
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public Cinema getCinema() {
		return cinema;
	}
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
	
}
