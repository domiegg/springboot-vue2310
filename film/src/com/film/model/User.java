package com.film.model;
import java.util.Date;

/**
 *	用户类
 */
public class User {
	private Integer userId;					//用户ID(自增)
	private String name;				//用户名称(自动生成)
	private String password;				//密码
	private int gender;						//性别(0 Female 1 Male)
	private Date birthday;					//生日
	private String address;					//地址
	private long phone;						//手机号
	private Date registrationTime;		//注册时间(default 当前时间)
	private int authority;					//默认0为普通用户，管理员为1
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public Date getRegistrationTime() {
		return registrationTime;
	}
	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}

}
