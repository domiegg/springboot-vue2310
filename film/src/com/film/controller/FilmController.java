package com.film.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.film.model.Film;
import com.film.service.FilmService;
import com.film.util.DateJsonValueProcessor;
import com.film.util.FileUploadUtil;
import com.film.util.PageBean;
import com.film.util.ResponseUtil;
import com.film.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 *	影片controller层 
 */
@Controller
@RequestMapping("/film")
public class FilmController {
	@Resource
	private FilmService filmService;

	@InitBinder	//格式化数据
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false); 	
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true)); //true 允许输入空值，false不能为空
	}
	
	@RequestMapping("/save")		//修改影片信息
    public String save(Film film,HttpServletResponse response,HttpServletRequest request)throws Exception{
         
        String fileName = FileUploadUtil.fileUpload(film.getFile(), request);
        //定义前台访问路径
        ResourceBundle resource = ResourceBundle.getBundle("config");
        String path = resource.getString("imageUrl");
        //持久化图片操作
        film.setCover(path+fileName);
        
        int resultTotal=0;
        if(film.getFilmId()==null){
        	resultTotal=filmService.add(film);
        }else{
        	resultTotal=filmService.update(film);
        }
        JSONObject result=new JSONObject();
        if(resultTotal>0){
            result.put("success", true);
        }else{
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }
	
	@RequestMapping("/delete")		//删除影片
	public String delete(@RequestParam(value="ids")String ids,HttpServletResponse response)throws Exception{
	        String []idsStr=ids.split(","); //定义ID数组
	        for(int i=0;i<idsStr.length;i++){
	            filmService.delete(Integer.parseInt(idsStr[i]));
	        }
	        JSONObject result=new JSONObject();
	        result.put("success", true);
	        ResponseUtil.write(response, result);
	        return null;
	 }
	 	 
	@RequestMapping("/list")	//分页查询影片集合
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,Film film,HttpServletResponse response) throws Exception{
			PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("filmName", StringUtil.formatLike(film.getFilmName()));
			map.put("director", StringUtil.formatLike(film.getDirector()));
		    map.put("actor", StringUtil.formatLike(film.getActor()));
//		    map.put("cover", film.getCover());
		    map.put("type", StringUtil.formatLike(film.getType()));
//		    map.put("startTime", film.getStartTime());
//		    map.put("endTime", film.getEndTime());
//		    map.put("duration", film.getDuration());
//		    map.put("description", film.getDescription());
//		    map.put("registrationTime", film.getRegistrationTime());
		    map.put("start", pageBean.getStart());
		    map.put("size", pageBean.getPageSize());
		    List<Film> filmList=filmService.find(map);
		    Long total=filmService.getTotal(map);
		    JSONObject result=new JSONObject();
		    JsonConfig jsonConfig=new JsonConfig();
		    jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		    JSONArray jsonArray=JSONArray.fromObject(filmList,jsonConfig);
	        result.put("rows", jsonArray);
	        result.put("total", total);
	        ResponseUtil.write(response, result);
	        return null;
	}
		
	@RequestMapping("/findById")	//按id查询影片
	public String findById(@RequestParam(value="filmId")String filmId,HttpServletResponse response)throws Exception{
			Film film=filmService.findById(Integer.parseInt(filmId));
			int times =film.getTimes()+1;
			film.setTimes(times);
			filmService.update(film);
			JsonConfig jsonConfig=new JsonConfig();
			jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
			JSONArray jsonArray=JSONArray.fromObject(film,jsonConfig);
			HashMap<String, Object> fidata=new HashMap<String, Object>();
			fidata.put("filist",jsonArray);
			ResponseUtil.write(response, JSON.toJSONString(fidata,SerializerFeature.WriteMapNullValue));
			return null;
	}
	
	@RequestMapping("/indexList")	//查询全部影片集合
	public String indexFind(HttpServletResponse response) throws Exception{
		List<Film> filmList = filmService.indexFind();
		JsonConfig jsonConfig=new JsonConfig();
	    jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
	    JSONArray jsonArray=JSONArray.fromObject(filmList,jsonConfig);
		HashMap<String, Object> fdata=new HashMap<String, Object>();
		fdata.put("flist",jsonArray);
		ResponseUtil.write(response, JSON.toJSONString(fdata,SerializerFeature.WriteMapNullValue));
		return null;
	}
	
	@RequestMapping("/likeList")	//首页查询影片集合
	public String likeList(HttpServletResponse response) throws Exception{
		List<Film> filmList = filmService.likeFilmList();
		JsonConfig jsonConfig=new JsonConfig();
	    jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
	    JSONArray jsonArray=JSONArray.fromObject(filmList,jsonConfig);
		HashMap<String, Object> lfdata=new HashMap<String, Object>();
		lfdata.put("lflist",jsonArray);
		ResponseUtil.write(response, JSON.toJSONString(lfdata,SerializerFeature.WriteMapNullValue));
		return null;
	}
	
	@RequestMapping("/filmHome")	//首页查询影片集合
	public String cinemaHome(@RequestParam(value="filmId")String filmId,HttpServletRequest request) throws Exception{
		Film film = filmService.findById(Integer.parseInt(filmId));
		HttpSession session = request.getSession();
		session.setAttribute("currentFilm", film);
		return "redirect:/singlefilm.jsp";
	}
}
