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
import com.film.model.Cinema;
import com.film.model.CinemaComment;
import com.film.model.User;
import com.film.service.CinemaCommentService;
import com.film.util.DateJsonValueProcessor;
import com.film.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


/**
 *	影院评价controller层 
 */
@Controller
@RequestMapping("/cinemaComment")
public class CinemaCommentController {
	@Resource
	private CinemaCommentService cinemaCommentService;
	
	@InitBinder	//格式化数据
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false); 	
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true)); //true 允许输入空值，false不能为空
	}
	@RequestMapping("/add")	//发表影院评论
	public String add(String cinemaId,String userId,CinemaComment cinemaComment,HttpServletResponse response) throws Exception{
		User user=new User();
		user.setUserId(Integer.parseInt(userId));
		Cinema cinema=new Cinema();
		cinema.setCinemaId(Integer.parseInt(cinemaId));
		cinemaComment.setUser(user);
		cinemaComment.setCinema(cinema);
		cinemaComment.setPublishTime(new Date());
		cinemaCommentService.add(cinemaComment);
		return "redirect:/singlecinema.jsp";
	}
	
	@RequestMapping("/delete")	//删除影院评论
	public String delete(@RequestParam(value="commentId")String commentId,HttpServletResponse response) throws Exception{
		cinemaCommentService.delete(Integer.parseInt(commentId));
		JSONObject result=new JSONObject();
	    result.put("success", true);
	    ResponseUtil.write(response, result);
		return null;
	}
	

	@RequestMapping("/list")	//查询影院评论集合
	public String list(@RequestParam(value="cinemaId",required=false)String cinemaId,HttpServletResponse response) throws Exception{
		List<CinemaComment> cinemaCommenttList=cinemaCommentService.findByCinemaId(Integer.parseInt(cinemaId));
		JsonConfig jsonConfig=new JsonConfig();
	    jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
	    JSONArray jsonArray = JSONArray.fromObject(cinemaCommenttList,jsonConfig);
		HashMap<String, Object> ctdata=new HashMap<String, Object>();
		ctdata.put("ctlist",jsonArray);
		ResponseUtil.write(response, JSON.toJSONString(ctdata,SerializerFeature.WriteMapNullValue));
		return null;
	}
	
	
	
}
