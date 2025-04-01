package com.film.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.film.model.Film;
import com.film.model.FilmArticle;
import com.film.model.User;
import com.film.service.FilmArticleService;
import com.film.util.DateJsonValueProcessor;
import com.film.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 影评Controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/filmArticle")
public class FilmArticleController {
	@Resource
	private FilmArticleService filmArticleService;
	
	@InitBinder	//格式化数据
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false); 	
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true)); //true 允许输入空值，false不能为空
	}
	
	@RequestMapping("/add")	//发表影评
	public String add(String userId,String filmId,FilmArticle filmArticle,HttpServletResponse response) throws Exception{
		User user=new User();
		user.setUserId(Integer.parseInt(userId));
		Film film=new Film();
		film.setFilmId(Integer.parseInt(filmId));
		filmArticle.setUser(user);
		filmArticle.setFilm(film);
		filmArticle.setPublishTime(new Date());
		filmArticleService.add(filmArticle);
		return "redirect:/userhome.jsp";
	}
	
	@RequestMapping("/delete")	//删除影评
	public String delete(@RequestParam(value="articleId")String articleId,HttpServletResponse response) throws Exception{
		filmArticleService.delete(Integer.parseInt(articleId));
		JSONObject result=new JSONObject();
	    result.put("success", true);
	    ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/findById")	//按ID查询影评
	public String findById(@RequestParam(value="id")String id,HttpServletResponse response) throws Exception{
		FilmArticle filmArticle=filmArticleService.findById(Integer.parseInt(id));
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm"));
		JSONObject jsonObject=JSONObject.fromObject(filmArticle,jsonConfig);
	    ResponseUtil.write(response, jsonObject);
		return "redirect:/userhome.jsp";
	}
		
	@RequestMapping("/list")	//分页查询影评集合
	public String list(@RequestParam(value="userId",required=false)String userId,HttpServletResponse response) throws Exception{
		List<FilmArticle> filmArticleList=filmArticleService.findByUserId(Integer.parseInt(userId));
		HashMap<String, Object> data=new HashMap<String, Object>();
		data.put("list",filmArticleList);
		ResponseUtil.write(response, JSON.toJSONString(data,SerializerFeature.WriteMapNullValue));
		return null;
	}
	
	@RequestMapping("/filmList")	//分页查询影评集合
	public String filmList(@RequestParam(value="filmId",required=false)String filmId,HttpServletResponse response) throws Exception{
		List<FilmArticle> filmArticleList=filmArticleService.findByFilmId(Integer.parseInt(filmId));
		JsonConfig jsonConfig=new JsonConfig();
	    jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
	    JSONArray jsonArray = JSONArray.fromObject(filmArticleList,jsonConfig);
		HashMap<String, Object> fadata=new HashMap<String, Object>();
		fadata.put("falist",jsonArray);
		ResponseUtil.write(response, JSON.toJSONString(fadata,SerializerFeature.WriteMapNullValue));
		return null;
	}
	
	@RequestMapping("/searchList")	//关键字查询影评集合
	public String searchList(String userId,String search,HttpServletResponse response) throws Exception{
		User user = new User();
		user.setUserId(Integer.parseInt(userId));
		FilmArticle filmArticle = new FilmArticle();
		filmArticle.setUser(user);
		filmArticle.setContent(search);
		
		List<FilmArticle> filmArticleList=filmArticleService.findBySearch(filmArticle);
		
		JsonConfig jsonConfig=new JsonConfig();
	    jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
	    JSONArray jsonArray = JSONArray.fromObject(filmArticleList,jsonConfig);
		HashMap<String, Object> sadata=new HashMap<String, Object>();
		sadata.put("salist",jsonArray);
		ResponseUtil.write(response, JSON.toJSONString(sadata,SerializerFeature.WriteMapNullValue));
		return null;
	}
}
