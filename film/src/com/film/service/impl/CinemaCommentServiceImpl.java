package com.film.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.film.dao.CinemaCommentDao;
import com.film.model.CinemaComment;
import com.film.service.CinemaCommentService;

/**
 * 影院评论Service实现类
 */
@Service
public class CinemaCommentServiceImpl implements CinemaCommentService {
	@Resource
	private CinemaCommentDao cinemaCommentDao;
	
	@Override	//添加影院评论
	public int add(CinemaComment cinemaComment) {
		return cinemaCommentDao.add(cinemaComment);
	}
	
	@Override	//删除影院评论
	public int delete(Integer id) {
		return cinemaCommentDao.delete(id);
	}
	
	@Override	//查询影院评论集合
	public List<CinemaComment> find(Map<String, Object> map) {
		return cinemaCommentDao.find(map);
	}

	@Override	//查询影院评论数量
	public Long getTotal(Map<String, Object> map) {
		return cinemaCommentDao.getTotal(map);
	}

	@Override
	public List<CinemaComment> findByCinemaId(Integer cinemaId) {
		return cinemaCommentDao.findByCinemaId(cinemaId);
	}
}
