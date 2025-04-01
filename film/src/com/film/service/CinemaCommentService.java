package com.film.service;

import java.util.List;
import java.util.Map;

import com.film.model.CinemaComment;

/**
 * 影院评论Service接口
 */
public interface CinemaCommentService {
	/**
	 * 新增影院评论
	 * @param cinemaComment
	 * @return
	 */
	public int add(CinemaComment cinemaComment);
	
	/**
	 * 删除影院评论
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * 查询影院评论集合
	 * @param map
	 * @return
	 */
	public List<CinemaComment> find(Map<String,Object> map);

	/**
	 * 查询影院评价集合
	 * @param userId
	 * @return
	 */
	public List<CinemaComment> findByCinemaId(Integer cinemaId);
	
	/**
	 * 查询影院评论总数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
}
