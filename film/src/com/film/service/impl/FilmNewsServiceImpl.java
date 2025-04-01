package com.film.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.film.dao.FilmNewsDao;
import com.film.model.FilmNews;
import com.film.service.FilmNewsService;

/**
 * 资讯Service实现类
 */
@Service
public class FilmNewsServiceImpl implements FilmNewsService {
	@Resource
	private FilmNewsDao filmNewsDao;
	
	@Override	//添加影片资讯
	public int add(FilmNews filmNews) {
		return filmNewsDao.add(filmNews);
	}

	@Override	//删除影片资讯
	public int delete(Integer id) {
		return filmNewsDao.delete(id);
	}

	@Override	//更新影片资讯
	public int update(FilmNews filmNews) {
		return filmNewsDao.update(filmNews);
	}

	@Override	//查询影片资讯集合
	public List<FilmNews> find(Map<String, Object> map) {
		return filmNewsDao.find(map);
	}

	@Override	//查询影片资讯数量
	public Long getTotal(Map<String, Object> map) {
		return filmNewsDao.getTotal(map);
	}

	@Override
	public List<FilmNews> findByCinemaId(Integer cinemaId) {
		return filmNewsDao.findByCinemaId(cinemaId);
	}

	@Override
	public List<FilmNews> findBySearch(FilmNews filmNews) {
		return filmNewsDao.findBySearch(filmNews);
	}

}
