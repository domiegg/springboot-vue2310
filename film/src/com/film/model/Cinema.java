package com.film.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

/**
 *	影院类
 */
public class Cinema {
	private Integer cinemaId;					//影院ID(1001开始自动增长)
	private String password;				//密码
	private String name;				//影院名称
	private long phone;					//影院电话
	private String description;				//影院简介
	private String location;				//影院地址
	private String cover;					//影院封面
	private Date registrationTime;		//注册时间(default 当前时间)
	private MultipartFile pic;
	
	public Integer getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(Integer cinemaId) {
		this.cinemaId = cinemaId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public Date getRegistrationTime() {
		return registrationTime;
	}
	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}
	public MultipartFile getPic() {
		return pic;
	}
	public void setPic(MultipartFile pic) {
		this.pic = pic;
	}
	
}
