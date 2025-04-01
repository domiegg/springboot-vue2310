package com.film.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.film.dao.LikeFilmDao;
import com.film.model.LikeFilm;
import com.film.service.LikeFilmService;

@Service
public class LikeFilmServiceImpl implements LikeFilmService {
	@Resource
	private LikeFilmDao likeFilmDao;
	
	@Override	//添加喜爱影片
	public int add(LikeFilm likeFilm) {
		return likeFilmDao.add(likeFilm);
	}

	@Override	//删除喜爱影片
	public int delete(LikeFilm likeFilm) {
		return likeFilmDao.delete(likeFilm);
	}
	
	@Override	//查询个人喜爱影片
	public List<LikeFilm> findByUserId(Integer userId) {
		return likeFilmDao.findByUserId(userId);
	}

	@Override	//查询喜爱影片集合
	public Long getTotal(Integer userId) {
		return likeFilmDao.getTotal(userId);
	}

	@Override
	public int checkFind(LikeFilm likeFilm) {
		return likeFilmDao.checkFind(likeFilm);
	}

}
