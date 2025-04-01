package com.film.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import com.film.model.FilmNews;
import com.film.service.FilmNewsService;
import com.film.util.DateJsonValueProcessor;
import com.film.util.FileUploadUtil;
import com.film.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 资讯Controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/filmNews")
public class FilmNewsController {
	@Resource
	private FilmNewsService filmNewsService;
	
	@InitBinder	//格式化数据
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false); 	
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true)); //true 允许输入空值，false不能为空
	}
	
	@RequestMapping("/add")	//添加资讯
	public String add(String cinemaId,FilmNews filmNews,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String fileName = FileUploadUtil.fileUpload(filmNews.getFile(), request);
//      HttpSession session = request.getSession();
//      session.setAttribute("imageSession", fileName);
        //定义前台访问路径
        ResourceBundle resource = ResourceBundle.getBundle("config");
        String path = resource.getString("imageUrl");
        //持久化图片操作
        filmNews.setPicture(path+fileName);
		
		Cinema cinema=new Cinema();
		cinema.setCinemaId(Integer.parseInt(cinemaId));
		filmNews.setCinema(cinema);
		filmNews.setPublishTime(new Date());
		filmNewsService.add(filmNews);
		return "redirect:/cinemahome.jsp";
	}
	
	@RequestMapping("/delete")	//删除资讯
	public String delete(@RequestParam(value="newsId")String newsId,HttpServletResponse response) throws Exception{
		filmNewsService.delete(Integer.parseInt(newsId));
		JSONObject result=new JSONObject();
	    result.put("success", true);
	    ResponseUtil.write(response, result);
	    return "redirect:/cinemahome.jsp";
	}
		
	@RequestMapping("/list")	//查询资讯集合
	public String list(@RequestParam(value="cinemaId",required=false)String cinemaId,HttpServletResponse response) throws Exception{
		List<FilmNews> filmNewsList=filmNewsService.findByCinemaId(Integer.parseInt(cinemaId));
		JsonConfig jsonConfig=new JsonConfig();
	    jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
	    JSONArray jsonArray = JSONArray.fromObject(filmNewsList,jsonConfig);
		HashMap<String, Object> ndata=new HashMap<String, Object>();
		ndata.put("nlist",jsonArray);
		ResponseUtil.write(response, JSON.toJSONString(ndata,SerializerFeature.WriteMapNullValue));
		return null;
	}
	
	@RequestMapping("/searchList")	//查询资讯集合
	public String searchList(String cinemaId, String search, HttpServletResponse response) throws Exception{
		Cinema cinema = new Cinema();
		cinema.setCinemaId(Integer.parseInt(cinemaId));
		FilmNews filmNews = new FilmNews();
		filmNews.setCinema(cinema);
		filmNews.setNewsTitle(search);
		filmNews.setNewsContent(search);
		List<FilmNews> filmNewsList=filmNewsService.findBySearch(filmNews);
		JsonConfig jsonConfig=new JsonConfig();
	    jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
	    JSONArray jsonArray = JSONArray.fromObject(filmNewsList,jsonConfig);
		HashMap<String, Object> sndata=new HashMap<String, Object>();
		sndata.put("snlist",jsonArray);
		ResponseUtil.write(response, JSON.toJSONString(sndata,SerializerFeature.WriteMapNullValue));
		return null;
	}
}
