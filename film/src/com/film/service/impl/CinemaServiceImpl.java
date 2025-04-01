package com.film.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.film.dao.CinemaDao;
import com.film.model.Cinema;
import com.film.service.CinemaService;

/**
 * 影院Service实现类
 */
@Service
public class CinemaServiceImpl implements CinemaService {
	
	@Resource
	private CinemaDao cinemaDao;
	
	@Override		//影院登录
	public Cinema login(Cinema cinema) {
		cinema.setPassword(DigestUtils.md5Hex(cinema.getPassword()));
		return cinemaDao.login(cinema);
	}

	@Override		//影院注册
	public int register(Cinema cinema) {
		cinema.setPassword(DigestUtils.md5Hex(cinema.getPassword()));
		return cinemaDao.register(cinema);
	}
	
	@Override		//删除影院
	public int delete(Integer id) {
		return cinemaDao.delete(id);
	}
	
	@Override		//修改影院信息
	public int update(Cinema cinema) {
		return cinemaDao.update(cinema);
	}

	@Override		//查询影院集合
	public List<Cinema> find(Map<String, Object> map) {
		return cinemaDao.find(map);
	}

	@Override		//影院总数
	public Long getTotal(Map<String, Object> map) {
		return cinemaDao.getTotal(map);
	}

	@Override		//通过id获取影院
	public Cinema findById(Integer id) {
		return cinemaDao.findById(id);
	}

	@Override
	public List<Cinema> indexFind() {
		return cinemaDao.indexFind();
	}

	@Override
	public Cinema findByPhone(long phone) {
		return cinemaDao.findByPhone(phone);
	}

	@Override
	public List<Cinema> randFind() {
		return cinemaDao.randFind();
	}

	@Override
	public int totalGet() {
		return cinemaDao.totalGet();
	}

	@Override
	public List<Cinema> cinemaFind(Map<String, Object> map) {
		return cinemaDao.cinemaFind(map);
	}
}
