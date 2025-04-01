package com.film.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.film.dao.BoughtFilmDao;
import com.film.dao.FilmArrangementDao;
import com.film.model.FilmArrangement;
import com.film.service.FilmArrangementService;
/**
 * 电影排片Service实现类
 */
@Service
public class FilmArrangementServiceImpl implements FilmArrangementService {
	@Resource
	private FilmArrangementDao filmArrangementDao;
	@Resource
	private BoughtFilmDao boughtFilmDao;
	@Override	//添加排片
	public int add(FilmArrangement filmArrangement) {
		return filmArrangementDao.add(filmArrangement);
	}

	@Override	//删除排片
	public int delete(Integer id) {
		int res=boughtFilmDao.find(id);
		if(res==0){
			return filmArrangementDao.delete(id);
		}else{
			return 0;
		}

	}

	@Override	//更新排片
	public int update(FilmArrangement filmArrangement) {
		return filmArrangementDao.update(filmArrangement);
	}
	
	@Override	//查询排片集合
	public List<FilmArrangement> find(Map<String, Object> map) {
		return filmArrangementDao.find(map);
	}

	@Override	//根据id查看排片
	public FilmArrangement findById(Integer id) {
		return filmArrangementDao.findById(id);
	}

	@Override
	public List<FilmArrangement> findByCinemaId(Integer cinemaId) {
		return filmArrangementDao.findByCinemaId(cinemaId);
	}

	@Override
	public List<FilmArrangement> findByCinemaOrder(Map<String, Object> map) {
		return filmArrangementDao.findByCinemaOrder(map);
	}

	@Override
	public List<FilmArrangement> findByFilmOrder(Map<String, Object> map) {
		return filmArrangementDao.findByFilmOrder(map);
	}

	@Override
	public List<FilmArrangement> findByFilmId(Integer filmId) {
		return filmArrangementDao.findByFilmId(filmId);
	}

	@Override
	public List<FilmArrangement> findByCinema(Map<String, Object> map) {
		return filmArrangementDao.findByCinema(map);
	}

	@Override
	public int getTotal() {
		return filmArrangementDao.getTotal();
	}

	@Override
	public int getTotalById(Integer cinemaId) {
		return filmArrangementDao.getTotalById(cinemaId);
	}
}
