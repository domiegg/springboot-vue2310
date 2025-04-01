package com.film.dao;

import java.util.List;
import java.util.Map;
import com.film.model.Film;

/**
 * 影片Dao接口
 */
public interface FilmDao {
	/**
	 * 新增影片
	 * @param film
	 * @return
	 */
	public int add(Film film);
	
	/**
	 * 删除影片
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * 修改影片
	 * @param film
	 * @return
	 */
	public int update(Film film);
	
	/**
	 * 查询影片集合
	 * @param map
	 * @return
	 */
	public List<Film> find(Map<String,Object> map);

	/**
	 * 查询首页影片集合
	 * @param map
	 * @return
	 */
	public List<Film> indexFind();
	
	/**
	 * 推荐影片集合
	 * @param map
	 * @return
	 */
	public List<Film> likeFilmList();
	
	/**
	 * 查询影片总数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 根据ID查找实体
	 * @param id
	 * @return
	 */
	public Film findById(Integer id);
	
	public int updateTimes(Integer id);

}
