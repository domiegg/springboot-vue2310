package com.film.dao;

import java.util.List;
import java.util.Map;

import com.film.model.FilmArrangement;

/**
 * 电影排片Dao接口
 */
public interface FilmArrangementDao {
	/**
	 * 新增电影排片
	 * @param filmArrangement
	 * @return
	 */
	public int add(FilmArrangement filmArrangement);
	
	/**
	 * 删除电影排片
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * 修改电影排片
	 * @param filmArrangement
	 * @return
	 */
	public int update(FilmArrangement filmArrangement);
	
	/**
	 * 查询电影排片集合
	 * @param map
	 * @return
	 */
	public List<FilmArrangement> find(Map<String,Object> map);
	
	/**
	 * 根据影院id查询电影排片集合
	 * @param map
	 * @return
	 */
	public List<FilmArrangement> findByCinemaId(Integer cinemaId);
	
	/**
	 * 根据影院用户查询电影排片集合
	 * @param map
	 * @return
	 */
	public List<FilmArrangement> findByCinema(Map<String, Object> map);
	
	/**
	 * 根据影片id查询电影排片集合
	 * @param map
	 * @return
	 */
	public List<FilmArrangement> findByFilmId(Integer filmId);
	
	/**
	 * 根据影院排序查询电影排片集合
	 * @param map
	 * @return
	 */
	public List<FilmArrangement> findByCinemaOrder(Map<String, Object> map);
	
	/**
	 * 根据影片排序查询电影排片集合
	 * @param map
	 * @return
	 */
	public List<FilmArrangement> findByFilmOrder(Map<String, Object> map);

	/**
	 * 查询电影排片总数
	 * @return
	 */
	public int getTotal();
	
	/**
	 * 按影院id查询电影排片总数
	 * @param cinemaId
	 * @return
	 */
	public int getTotalById(Integer cinemaId);
	/**
	 * 根据id查找实体
	 * @param id
	 * @return
	 */
	public FilmArrangement findById(Integer id);
}
