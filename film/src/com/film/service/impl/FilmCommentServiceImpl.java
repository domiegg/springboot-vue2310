package com.film.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.film.dao.FilmCommentDao;
import com.film.model.FilmComment;
import com.film.service.FilmCommentService;
/**
 * 影院评论Service实现类
 */
@Service
public class FilmCommentServiceImpl implements FilmCommentService {
	@Resource
	private FilmCommentDao filmCommentDao;
	
	@Override	//添加影片评论
	public int add(FilmComment filmComment) {
		return filmCommentDao.add(filmComment);
	}

	@Override	//删除影片评论
	public int delete(Integer id) {
		return filmCommentDao.delete(id);
	}

	@Override	//查询影片评论集合
	public List<FilmComment> findByFilmId(Integer filmId){
		return filmCommentDao.findByFilmId(filmId);
	}

	@Override	//查询影片评论数量
	public Long getTotal(Map<String, Object> map) {
		return filmCommentDao.getTotal(map);
	}

}
