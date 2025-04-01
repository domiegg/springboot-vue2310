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
 *	排片controller层 
 */
@Controller
@RequestMapping("/filmArrangement")
public class FilmArrangementController {
	@Resource
	private FilmArrangementService filmArrangementService;
	@Resource 
	private BoughtFilmService boughtFilmService;
	@InitBinder	//格式化数据
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false); 	
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true)); //true 允许输入空值，false不能为空
	}
	
	@RequestMapping("/add")	//发表排片
	public String add(String filmId,String cinemaId,Date filmStartTime,Date filmEndTime,FilmArrangement filmArrangement,HttpServletResponse response) throws Exception{
		Film film=new Film();
		film.setFilmId(Integer.parseInt(filmId));
		Cinema cinema=new Cinema();
		cinema.setCinemaId(Integer.parseInt(cinemaId));
		filmArrangement.setCinema(cinema);
		filmArrangement.setFilm(film);
		filmArrangement.setFilmStartTime(filmStartTime);
		filmArrangement.setFilmEndTime(filmEndTime);
		filmArrangementService.add(filmArrangement);
		return "redirect:/cinemahome.jsp";
	}


	
// 	@RequestMapping("/update")		//修改排片信息
//    public String update(FilmArrangement filmArrangement,HttpServletResponse response)throws Exception{
//        int resultTotal=0; 
//        	resultTotal=filmArrangementService.update(filmArrangement);
//        JSONObject result=new JSONObject();
//        if(resultTotal>0){
//            result.put("success", true);
//        }else{
//            result.put("success", false);
//        }
//        ResponseUtil.write(response, result);
//        return null;
//    }
 
 	@RequestMapping("/delete")	//删除排片
	public String delete(@RequestParam(value="arrangementId")String arrangementId,HttpServletResponse response) throws Exception{

 		
 		int res=filmArrangementService.delete(Integer.parseInt(arrangementId));
 		if(res==1){
 			JSONObject result=new JSONObject();
 			result.put("success", true);
 		    ResponseUtil.write(response, result);
 		    return null;
	    }else{
 			JSONObject result=new JSONObject();
 			result.put("success", false);
		    ResponseUtil.write(response, result);
		    return null;
	    }
		

	}
 	
 	@RequestMapping("/list")	//查询排片集合
	public String list(@RequestParam(value="cinemaId",required=false)String cinemaId,HttpServletResponse response) throws Exception{
		List<FilmArrangement> filmArrangementList=filmArrangementService.findByCinemaId(Integer.parseInt(cinemaId));
		JsonConfig jsonConfig=new JsonConfig();
	    jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm"));
	    JSONArray jsonArray = JSONArray.fromObject(filmArrangementList,jsonConfig);
		HashMap<String, Object> data=new HashMap<String, Object>();
		data.put("list",jsonArray);
		ResponseUtil.write(response, JSON.toJSONString(data,SerializerFeature.WriteMapNullValue));
		return null;
	}
 	
	@RequestMapping("/clist")	//查询排片集合
	public String clist(@RequestParam(value="pageNow",required=false)String pageNow,@RequestParam(value="cinemaId",required=false)String cinemaId,HttpServletResponse response) throws Exception{
 		    int total=filmArrangementService.getTotalById(Integer.parseInt(cinemaId));
			PageUtil pageUtil=new PageUtil(total,Integer.parseInt(pageNow));
	        Map<String,Object> map=new HashMap<String,Object>();
	        map.put("cinemaId", cinemaId);
	        map.put("startPos", pageUtil.getStartPos());
	        map.put("pageSize", pageUtil.getPageSize());
		
 		
 		List<FilmArrangement> filmArrangementList=filmArrangementService.findByCinema(map);
		JsonConfig jsonConfig=new JsonConfig();
	    jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm"));
	    JSONArray jsonArray = JSONArray.fromObject(filmArrangementList,jsonConfig);
		HashMap<String, Object> data=new HashMap<String, Object>();
		data.put("list",jsonArray);
		data.put("pageUtil", pageUtil);
		ResponseUtil.write(response, JSON.toJSONString(data,SerializerFeature.WriteMapNullValue));
		return null;
	}

	
	@RequestMapping("/findById")	//按id查询排片
	public String findById(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
		FilmArrangement filmArrangement=filmArrangementService.findById(Integer.parseInt(id));
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm"));
		JSONObject jsonObject=JSONObject.fromObject(filmArrangement,jsonConfig);
	    ResponseUtil.write(response, jsonObject);
		return null;
	}
	
	@RequestMapping("/findByCinemaOrder")	//按影院排序
	public String findByCinemaOrder(@RequestParam(value="pageNow",required=false)String pageNow,HttpServletResponse response)throws Exception{
		  int total=filmArrangementService.getTotal();
			PageUtil pageUtil=new PageUtil(total,Integer.parseInt(pageNow));
	        Map<String,Object> map=new HashMap<String,Object>();
	        map.put("startPos", pageUtil.getStartPos());
	        map.put("pageSize", pageUtil.getPageSize());
		
		List<FilmArrangement> filmArrangementList=filmArrangementService.findByCinemaOrder(map);
		JsonConfig jsonConfig=new JsonConfig();
	    jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm"));
	    JSONArray jsonArray = JSONArray.fromObject(filmArrangementList,jsonConfig);
		HashMap<String, Object> codata=new HashMap<String, Object>();
		codata.put("colist",jsonArray);
		codata.put("pageUtil", pageUtil);
		ResponseUtil.write(response, JSON.toJSONString(codata,SerializerFeature.WriteMapNullValue));
		return null;
	}
	
	@RequestMapping("/findByFilmOrder")	//按影片排序
	public String findByFilmOrder(@RequestParam(value="pageNow",required=false)String pageNow,HttpServletResponse response)throws Exception{
		 int total=filmArrangementService.getTotal();
			PageUtil pageUtil=new PageUtil(total,Integer.parseInt(pageNow));
	        Map<String,Object> map=new HashMap<String,Object>();
	        map.put("startPos", pageUtil.getStartPos());
	        map.put("pageSize", pageUtil.getPageSize());
	        
		List<FilmArrangement> filmArrangementList=filmArrangementService.findByFilmOrder(map);
		JsonConfig jsonConfig=new JsonConfig();
	    jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm"));
	    JSONArray jsonArray = JSONArray.fromObject(filmArrangementList,jsonConfig);
		HashMap<String, Object> fodata=new HashMap<String, Object>();
		fodata.put("folist",jsonArray);
		fodata.put("pageUtil", pageUtil);
		ResponseUtil.write(response, JSON.toJSONString(fodata,SerializerFeature.WriteMapNullValue));
		return null;
	}
	
	@RequestMapping("/findByFilmId")	//按影片id查询排片集合
	public String findByFilmId(@RequestParam(value="filmId",required=false)String filmId,HttpServletResponse response) throws Exception{
		List<FilmArrangement> filmArrangementList=filmArrangementService.findByFilmId(Integer.parseInt(filmId));
		JsonConfig jsonConfig=new JsonConfig();
	    jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm"));
	    JSONArray jsonArray = JSONArray.fromObject(filmArrangementList,jsonConfig);
		HashMap<String, Object> data=new HashMap<String, Object>();
		data.put("list",jsonArray);
		ResponseUtil.write(response, JSON.toJSONString(data,SerializerFeature.WriteMapNullValue));
		return null;
	}
}
