package com.film.dao;

import java.util.List;
import java.util.Map;
import com.film.model.FilmNews;

/**
 * 资讯Dao接口
 */
public interface FilmNewsDao {
	/**
	 * 新增资讯
	 * @param filmNews
	 * @return
	 */
	public int add(FilmNews filmNews);
	
	/**
	 * 删除资讯
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * 修改资讯
	 * @param filmNews
	 * @return
	 */
	public int update(FilmNews filmNews);
	
	/**
	 * 查询资讯集合
	 * @param map
	 * @return
	 */
	public List<FilmNews> find(Map<String,Object> map);

	
	/**
	 * 根据影院查询资讯集合
	 * @param map
	 * @return
	 */
	public List<FilmNews> findByCinemaId(Integer cinemaId);
	
	/**
	 * 根据关键字查询资讯集合
	 * @param map
	 * @return
	 */
	public List<FilmNews> findBySearch(FilmNews filmNews);
	
	/**
	 * 查询资讯总数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
}