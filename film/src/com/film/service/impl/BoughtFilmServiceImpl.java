package com.film.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.film.dao.BoughtFilmDao;
import com.film.model.BoughtFilm;
import com.film.service.BoughtFilmService;

/**
 * 购票Service实现类
 */
@Service
public class BoughtFilmServiceImpl implements BoughtFilmService {
	@Resource
	private BoughtFilmDao boughtFilmDao;

	@Override	//购票
	public int add(BoughtFilm boughtFilm) {
		return boughtFilmDao.add(boughtFilm);
	}
	
	@Override	//退票
	public int delete(BoughtFilm boughtFilm) {
		return boughtFilmDao.delete(boughtFilm);
	}

	@Override	//查询个人购票集合
	public List<BoughtFilm> findByUserId(Map<String, Object> map) {
		return boughtFilmDao.findByUserId(map);
	}

	@Override	//查询个人购票数量
	public int getTotal(Integer userId) {
		return boughtFilmDao.getTotal(userId);
	}

	@Override
	public int find(Integer filmArrangementId) {
		return boughtFilmDao.find(filmArrangementId);
	}
}
