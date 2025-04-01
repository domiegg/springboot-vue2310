package com.film.service;

import java.util.List;
import java.util.Map;

import com.film.model.Cinema;

/**
 * 影院Service接口
 */
public interface CinemaService {
	/**
	 * 影院登录
	 * @param cinema
	 * @return
	 */
	
	public Cinema login(Cinema cinema);		
	/**
	 * 影院注册
	 * @param cinema
	 * @return
	 */
	public int register(Cinema cinema);

	/**
	 * 管理员删除影院
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * 修改影院信息
	 * @param cinema
	 * @return
	 */
	public int update(Cinema cinema);
	
	/**
	 * 管理员查询影院集合
	 * @param map
	 * @return
	 */
	public List<Cinema> find(Map<String,Object> map);
	
	/**
	 * 首页查询影院集合
	 * @param map
	 * @return
	 */
	public List<Cinema> indexFind();
	
	/**
	 * 随机查询影院集合
	 * @param map
	 * @return
	 */
	public List<Cinema> randFind();
	
	/**
	 * 管理员查询影院总数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	/**
	 * 查询影院总数
	 * @return
	 */
	public int totalGet();

	/**
	 * 根据id查找实体
	 * @param id
	 * @return
	 */
	public Cinema findById(Integer id);
	/**
	 * 根据手机号查找实体
	 * @param phone
	 * @return
	 */
	public Cinema findByPhone(long phone);
	
	/**
	 * 影院列表分页
	 * @param map
	 * @return
	 */
	public List<Cinema> cinemaFind(Map<String,Object> map);
}
