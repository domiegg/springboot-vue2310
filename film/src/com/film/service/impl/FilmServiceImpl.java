package com.film.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.film.dao.FilmDao;
import com.film.model.Film;
import com.film.service.FilmService;

/**
 * 影片Service实现类
 */
@Service
public class FilmServiceImpl implements FilmService {
	@Resource
	private FilmDao filmDao;
	
	@Override	//添加影片
	public int add(Film film) {
		return filmDao.add(film);
	}

	@Override	//删除影片
	public int delete(Integer id) {
		return filmDao.delete(id);
	}
	
	@Override	//跟新影片
	public int update(Film film) {
		return filmDao.update(film);
	}
	
	@Override	//查询影片集合
	public List<Film> find(Map<String, Object> map) {
		return filmDao.find(map);
	}

	@Override	//查询影片数量
	public Long getTotal(Map<String, Object> map) {
		return filmDao.getTotal(map);
	}

	@Override	//按id查询影片
	public Film findById(Integer id) {
		return filmDao.findById(id);
	}

	@Override
	public List<Film> indexFind() {
		return filmDao.indexFind();
	}

	@Override
	public List<Film> likeFilmList() {
		return filmDao.likeFilmList();
	}

}
