package com.film.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import com.film.dao.UserDao;
import com.film.model.User;
import com.film.service.UserService;

/**
 * 用户Service实现类
 */
@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	@Override		//用户登录
	public User login(User user) {
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		return userDao.login(user);
	}

	@Override		//用户注册
	public int register(User user) {
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		return userDao.register(user);
	}
	
	@Override		//删除用户
	public int delete(Integer id) {
		return userDao.delete(id);
	}

	@Override		//修改用户个人信息
	public int update(User user) {
		return userDao.update(user);
	}

	@Override		//查找用户集合
	public List<User> find(Map<String, Object> map) {
		return userDao.find(map);
	}

	@Override		//用户总数
	public Long getTotal(Map<String, Object> map) {
		return userDao.getTotal(map);
	}
	
	@Override		//根据id查询用户
	public User findById(Integer id) {
		return userDao.findById(id);
	}

	@Override
	public User findByPhone(long phone) {
		return userDao.findByPhone(phone);
	}
}