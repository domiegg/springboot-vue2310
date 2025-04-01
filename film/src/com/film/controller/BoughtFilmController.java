package com.film.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.film.model.BoughtFilm;
import com.film.model.Cinema;
import com.film.model.Film;
import com.film.model.FilmArrangement;
import com.film.service.BoughtFilmService;
import com.film.service.FilmArrangementService;
import com.film.util.DateJsonValueProcessor;
import com.film.util.PageUtil;
import com.film.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 已购影片Controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/boughtFilm")
public class BoughtFilmController {
	@Resource
	private BoughtFilmService boughtFilmService;
	@Resource
	private FilmArrangementService filmArrangementService;

	@InitBinder	//格式化数据
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false); 	
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true)); //true 允许输入空值，false不能为空
	}
	
	@RequestMapping("/buyTicket")	//购票
	public String buyTicket(String userId,String filmId,String cinemaId,String arrangementId,HttpServletResponse response) throws Exception{
		Film film=new Film();
		film.setFilmId(Integer.parseInt(filmId));
		Cinema cinema=new Cinema();
		cinema.setCinemaId(Integer.parseInt(cinemaId));
		FilmArrangement	filmArrangement=new FilmArrangement();
		filmArrangement.setArrangementId(Integer.parseInt(arrangementId));
		BoughtFilm boughtFilm=new BoughtFilm();
		boughtFilm.setUserId(Integer.parseInt(userId));
		boughtFilm.setCinema(cinema);
		boughtFilm.setFilm(film);
		boughtFilm.setFilmArrangement(filmArrangement);
		boughtFilm.setPurchasingDate(new Date());
		boughtFilmService.add(boughtFilm);
		
		FilmArrangement arrangement=filmArrangementService.findById(Integer.parseInt(arrangementId));
		int sc=arrangement.getSeatCount();
		JSONObject result=new JSONObject();
		if(sc>0){
			arrangement.setSeatCount(sc-1);
			filmArrangementService.update(arrangement);
		    result.put("success", true);
	    }else{
	    	
	    	result.put("success", false);
	    }
	    ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/delete")	//购票
	public String delTicket(String boughtId,String arrangementId,HttpServletResponse response) throws Exception{
		FilmArrangement	filmArrangement=new FilmArrangement();
		filmArrangement.setArrangementId(Integer.parseInt(arrangementId));
		BoughtFilm boughtFilm=new BoughtFilm();
		boughtFilm.setBoughtId(Integer.parseInt(boughtId));
		boughtFilm.setFilmArrangement(filmArrangement);
		boughtFilm.setPurchasingDate(new Date());
		
		int del = boughtFilmService.delete(boughtFilm);
		
		FilmArrangement arrangement=filmArrangementService.findById(Integer.parseInt(arrangementId));
		int sc=arrangement.getSeatCount();
		JSONObject result=new JSONObject();
		if(del>0){
			arrangement.setSeatCount(sc+1);
			filmArrangementService.update(arrangement);
		    result.put("success", true);
	    }else{
	    	
	    	result.put("success", false);
	    }
	    ResponseUtil.write(response, result);
		return null;
	}

	
	

	@RequestMapping("/list")	//分页查询购票集合
	public String list(@RequestParam(value="pageNow",required=false)String pageNow,@RequestParam(value="userId",required=false)String userId, HttpServletResponse response) throws Exception{
		    int total=boughtFilmService.getTotal(Integer.parseInt(userId));
			PageUtil pageUtil=new PageUtil(total,Integer.parseInt(pageNow));
	        Map<String,Object> map=new HashMap<String,Object>();
	        map.put("userId", userId);
	        map.put("startPos", pageUtil.getStartPos());
	        map.put("pageSize", pageUtil.getPageSize());
		
	        List<BoughtFilm> boughtFilmList=boughtFilmService.findByUserId(map);
	        JsonConfig jsonConfig=new JsonConfig();
	        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm"));
	        JSONArray jsonArray=JSONArray.fromObject(boughtFilmList,jsonConfig);
	        HashMap<String, Object> data=new HashMap<String, Object>();
	        data.put("list",jsonArray);
	        data.put("pageUtil", pageUtil);
	       
	        ResponseUtil.write(response, JSON.toJSONString(data,SerializerFeature.WriteMapNullValue));
	        return null;
	}

}
