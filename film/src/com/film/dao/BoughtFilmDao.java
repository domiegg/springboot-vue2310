package com.film.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;

import com.film.model.BoughtFilm;

/**
 * 购票Dao接口
 */
@Alias("BoughtFilm")
public interface BoughtFilmDao {
	/**
	 * 购票
	 * @param boughtFilm
	 * @return
	 */
	public int add(BoughtFilm boughtFilm);
	
	/**
	 * 退票
	 * @param id
	 * @return
	 */
	public int delete(BoughtFilm boughtFilm);
	
	/**
	 * 查询购票集合
	 * @param userId
	 * @return
	 */
	public List<BoughtFilm> findByUserId(Map<String, Object> map);

	/**
	 * 查询购票总数
	 * @param userId
	 * @return
	 */
	public int getTotal(Integer userId);
	
	/**
	 * 查找是否有购票
	 * @param id
	 * @return
	 */
	public int find(Integer filmArrangementId);
}
