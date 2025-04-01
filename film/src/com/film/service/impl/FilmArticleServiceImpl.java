package com.film.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.film.dao.FilmArticleDao;
import com.film.model.FilmArticle;
import com.film.service.FilmArticleService;

/**
 * 影评Service实现类
 */
@Service
public class FilmArticleServiceImpl implements FilmArticleService {
	@Resource
	private FilmArticleDao	filmArticleDao;
	
	@Override	//添加影评
	public int add(FilmArticle filmArticle) {
		return filmArticleDao.add(filmArticle);
	}

	@Override	//删除影评
	public int delete(Integer id) {
		return filmArticleDao.delete(id);
	}

	@Override	//更新影评
	public int update(FilmArticle filmArticle) {
		return filmArticleDao.update(filmArticle);
	}
	
	@Override	//查询影评集合
	public List<FilmArticle> find(Map<String, Object> map) {
		return filmArticleDao.find(map);
	}

	@Override	//查询影评数量
	public Long getTotal(Map<String, Object> map) {
		return filmArticleDao.getTotal(map);
	}

	@Override	//查询个人影评
	public List<FilmArticle> findByUserId(Integer userId){
		return filmArticleDao.findByUserId(userId);
	}

	@Override	//根据id查询影评
	public FilmArticle findById(Integer id) {
		return filmArticleDao.findById(id);
	}

	@Override
	public List<FilmArticle> findByFilmId(Integer filmId) {
		return filmArticleDao.findByFilmId(filmId);
	}

	@Override
	public List<FilmArticle> findBySearch(FilmArticle filmArticle) {
		return filmArticleDao.findBySearch(filmArticle);
	}

}
