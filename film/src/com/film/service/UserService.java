package com.film.service;

import java.util.List;
import java.util.Map;

import com.film.model.User;

/**
 * 用户Service接口
 */
public interface UserService {
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public User login(User user);	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	public int register(User user);

	/**
	 * 管理员删除用户
	 * @param id
	 * @return
	 */
	public int delete(Integer id);

	/**
	 * 修改用户个人信息
	 * @param user
	 * @return
	 */
	public int update(User user);
	
	/**
	 * 管理员查询用户集合
	 * @param map
	 * @return
	 */
	public List<User> find(Map<String,Object> map);

	/**
	 * 管理员查询用户总数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);

	/**
	 * 根据ID查找实体
	 * @param id
	 * @return
	 */
	public User findById(Integer id);
	
	/**
	 * 根据手机号查找实体
	 * @param phone
	 * @return
	 */
	public User findByPhone(long phone);
}
