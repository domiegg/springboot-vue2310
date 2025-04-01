package com.film.service;

import java.util.List;
import java.util.Map;
import com.film.model.FilmArticle;


/**
 * 影评Service接口
 */
public interface FilmArticleService {
	/**
	 * 新增影评
	 * @param film
	 * @return
	 */
	public int add(FilmArticle filmArticle);
	
	/**
	 * 删除影评
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * 修改影评
	 * @param film
	 * @return
	 */
	public int update(FilmArticle filmArticle);
	
	/**
	 * 查询影评集合
	 * @param map
	 * @return
	 */
	public List<FilmArticle> find(Map<String,Object> map);

	/**
	 * 查询影评总数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 根据用户id查找实体
	 * @param id
	 * @return
	 */
	public List<FilmArticle> findByUserId(Integer userId);
	
	/**
	 * 根据影片id查找实体
	 * @param id
	 * @return
	 */
	public List<FilmArticle> findByFilmId(Integer filmId);
	
	/**
	 * 根据ID查找实体
	 * @param id
	 * @return
	 */
	public FilmArticle findById(Integer id);
	
	/**
	 * 根据关键字查找实体
	 * @param id
	 * @return
	 */
	public List<FilmArticle> findBySearch(FilmArticle filmArticle);
}
