package com.film.service;

import java.util.List;
import java.util.Map;
import com.film.model.FilmComment;

/**
 * 影片评论Service接口
 */
public interface FilmCommentService {
	/**
	 * 查询影片评论集合
	 * @param map
	 * @return
	 */
	public List<FilmComment> findByFilmId(Integer filmId);

	/**
	 * 查询影片评论总数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 新增影片评论
	 * @param FilmComment
	 * @return
	 */
	public int add(FilmComment filmComment);
	
	/**
	 * 删除影片评论
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
}
