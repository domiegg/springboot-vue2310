package com.film.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.film.model.Film;
import com.film.model.LikeFilm;
import com.film.model.User;
import com.film.service.FilmService;
import com.film.service.LikeFilmService;
import com.film.util.ResponseUtil;

import net.sf.json.JSONObject;

/**
 * 喜爱影片Controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/likeFilm")
public class LikeFilmController {
	@Resource
	private LikeFilmService likeFilmService;
	@Resource
	private FilmService filmservice;
	
	@RequestMapping("/add")	//添加喜爱影片
	public String add(String userId,String filmId,HttpServletResponse response) throws Exception{
		LikeFilm likeFilm=new LikeFilm();
		User user = new User();
		user.setUserId(Integer.parseInt(userId));
		likeFilm.setUser(user);
		Film film=new Film();
		film.setFilmId(Integer.parseInt(filmId));
		likeFilm.setFilm(film);
		likeFilmService.add(likeFilm);

		Film film1=filmservice.findById(Integer.parseInt(filmId));
		int like=film1.getLikeCount();
		film1.setLikeCount(like+1);
		filmservice.update(film1);
		JSONObject result=new JSONObject();
	    result.put("success", true);
	    ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/delete")	//删除喜爱影片
	public String delete(String userId,String filmId,HttpServletResponse response) throws Exception{
		LikeFilm likeFilm=new LikeFilm();
		User user = new User();
		user.setUserId(Integer.parseInt(userId));
		likeFilm.setUser(user);
		Film film=new Film();
		film.setFilmId(Integer.parseInt(filmId));
		likeFilm.setFilm(film);
		likeFilmService.delete(likeFilm);
		
		Film film1=filmservice.findById(Integer.parseInt(filmId));
		int like=film1.getLikeCount();
		film1.setLikeCount(like-1);
		filmservice.update(film1);
		JSONObject result=new JSONObject();
	    result.put("success", true);
	    ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/list")	//分页查询喜爱影片集合
	public String list(@RequestParam(value="userId",required=false)String userId, HttpServletResponse response) throws Exception{
		List<LikeFilm> likeFilmList=likeFilmService.findByUserId(Integer.parseInt(userId));
		HashMap<String, Object> data=new HashMap<String, Object>();
		data.put("list",likeFilmList);
		ResponseUtil.write(response, JSON.toJSONString(data,SerializerFeature.WriteMapNullValue));
		return null;
	}
	
	@RequestMapping("/checkFind")	//查找喜爱影片
	public String findById(String filmId,String userId, HttpServletResponse response) throws Exception{
		Film film = new Film();
		film.setFilmId(Integer.parseInt(filmId));
		User user = new User();
		user.setUserId(Integer.parseInt(userId));
		LikeFilm likeFilm = new LikeFilm();
		likeFilm.setFilm(film);
		likeFilm.setUser(user);
		int res = likeFilmService.checkFind(likeFilm);
		JSONObject result=new JSONObject();
		if(res>0){
		    result.put("success", true);
	    }else{
	    	
	    	result.put("success", false);
	    }
	    ResponseUtil.write(response, result);
		return null;
	}
}
