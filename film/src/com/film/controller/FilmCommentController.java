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
import com.film.model.FilmComment;
import com.film.model.User;
import com.film.service.FilmCommentService;
import com.film.util.DateJsonValueProcessor;
import com.film.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;



/**
 *	影片评价controller层 
 */
@Controller
@RequestMapping("/filmComment")
public class FilmCommentController {
	@Resource
	private FilmCommentService filmCommentService;
	
	@InitBinder	//格式化数据
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false); 	
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true)); //true 允许输入空值，false不能为空
	}
	@RequestMapping("/add")	//发表影片评论
	public String add(String filmId,String userId,FilmComment filmComment,HttpServletResponse response) throws Exception{
		User user=new User();
		user.setUserId(Integer.parseInt(userId));
		Film film=new Film();
		film.setFilmId(Integer.parseInt(filmId));
		filmComment.setUser(user);
		filmComment.setFilm(film);
		filmComment.setPublishTime(new Date());
		filmCommentService.add(filmComment);
		return "redirect:/singlefilm.jsp";
	}
	
	@RequestMapping("/delete")	//删除影片评论
	public String delete(@RequestParam(value="filmCommentId")String filmCommentId,HttpServletResponse response) throws Exception{
		filmCommentService.delete(Integer.parseInt(filmCommentId));
		JSONObject result=new JSONObject();
	    result.put("success", true);
	    ResponseUtil.write(response, result);
		return null;
	}
	

	@RequestMapping("/filmList")	//查询影片评论集合
	public String list(@RequestParam(value="filmId",required=false)String filmId,HttpServletResponse response) throws Exception{
		List<FilmComment> filmCommenttList=filmCommentService.findByFilmId(Integer.parseInt(filmId));
		JsonConfig jsonConfig=new JsonConfig();
	    jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
	    JSONArray jsonArray = JSONArray.fromObject(filmCommenttList,jsonConfig);
		HashMap<String, Object> fcdata=new HashMap<String, Object>();
		fcdata.put("fclist",jsonArray);
		ResponseUtil.write(response, JSON.toJSONString(fcdata,SerializerFeature.WriteMapNullValue));
		return null;
	}
	
}
