package com.film.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.film.model.Cinema;
import com.film.service.CinemaService;
import com.film.util.DateJsonValueProcessor;
import com.film.util.FileUploadUtil;
import com.film.util.PageBean;
import com.film.util.PageUtil;
import com.film.util.ResponseUtil;
import com.film.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 影院管理controller层 
 */
@Controller
@RequestMapping("/cinema")
public class CinemaController {
	@Resource
	private CinemaService cinemaService;
	
	@InitBinder	//格式化数据
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false); 	
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true)); //true 允许输入空值，false不能为空
	}

	@RequestMapping("/login")	//影院登录
	public String login(Cinema cinema,HttpServletRequest request) throws Exception{
			String pwd=cinema.getPassword();
			Cinema resultCinema= cinemaService.login(cinema);
			HttpSession session = request.getSession();
			resultCinema.setPassword(pwd);
			session.setAttribute("currentLogin", resultCinema);
			return "redirect:/index.jsp";
	}
	
	@RequestMapping("/loginCheck")		//登录检验
    public String loginCheck(@RequestParam(value="phone")String phone,@RequestParam(value="password")String password,HttpServletResponse response)throws Exception{
        Cinema cinema = cinemaService.findByPhone(Long.parseLong(phone));
        Cinema c=new Cinema();
        c.setPhone(Long.parseLong(phone));
        c.setPassword(password);
        JSONObject result = new JSONObject();
		if(cinema==null){
			result.put("state",0);
		}else{
			Cinema resultUser = cinemaService.login(c);
			if(resultUser==null){
				result.put("state", 1);

			}else{
				result.put("state", 2);
			}
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/register")	//影院注册
	public String register(Cinema cinema,HttpServletRequest request) throws Exception{
    	cinema.setRegistrationTime(new Date());
    	cinemaService.register(cinema);
    	return "redirect:/index.jsp";
	}

	@RequestMapping(" /logout")	//影院登出
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("/delete")		//删除影院
	public String delete(@RequestParam(value="ids")String ids,HttpServletResponse response)throws Exception{
	        String []idsStr=ids.split(","); //定义ID数组
	        for(int i=0;i<idsStr.length;i++){
	            cinemaService.delete(Integer.parseInt(idsStr[i]));
	        }
	        JSONObject result=new JSONObject();
	        result.put("success", true);
	        ResponseUtil.write(response, result);
	        return null;
	    }
	
	@RequestMapping("/update")		//修改影院信息
    public String update(Cinema cinema,HttpServletResponse response,HttpServletRequest request)throws Exception{
		String fileName = FileUploadUtil.fileUpload(cinema.getPic(), request);
        //定义前台访问路径
        ResourceBundle resource = ResourceBundle.getBundle("config");
        String path = resource.getString("imageUrl");
        //持久化图片操作
        cinema.setCover(path+fileName);
		cinemaService.update(cinema);
        return "redirect:/cinemahome.jsp";
 }
	
	@RequestMapping(" /modifyPwd")	//修改影院密码
	public String modifyPwd(Integer cinemaId,String newpassword,HttpServletResponse response) throws Exception{
		Cinema cinema=new Cinema();
		cinema.setCinemaId(cinemaId);
		cinema.setPassword(DigestUtils.md5Hex(newpassword));
		cinemaService.update(cinema);
		return "redirect:logout.do";
	}
	
	@RequestMapping("/save")		//管理员修改影院信息
	public String save(Cinema cinema,HttpServletResponse response,HttpServletRequest request)throws Exception{
		
		String fileName = FileUploadUtil.fileUpload(cinema.getPic(), request);
        //定义前台访问路径
        ResourceBundle resource = ResourceBundle.getBundle("config");
        String path = resource.getString("imageUrl");
        //持久化图片操作
        cinema.setCover(path+fileName);
        
		int resultTotal=0;
        if(cinema.getCinemaId()==null){
        	resultTotal=cinemaService.register(cinema);
        }else{
        	resultTotal=cinemaService.update(cinema);
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
 
	@RequestMapping("/list")	//影院集合
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,Cinema cinema,HttpServletResponse response) throws Exception{
			PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
			Map<String,Object> map=new HashMap<String,Object>();
//			map.put("phone", cinema.getPhone());
			map.put("name", StringUtil.formatLike(cinema.getName()));
//		    map.put("description", cinema.getDescription());
		    map.put("location", StringUtil.formatLike(cinema.getLocation()));
//		    map.put("registrationTime", cinema.getRegistrationTime());
		    map.put("start", pageBean.getStart());
		    map.put("size", pageBean.getPageSize());
		    List<Cinema> cinemaList=cinemaService.find(map);
		    Long total=cinemaService.getTotal(map);
		    JSONObject result=new JSONObject();
		    JsonConfig jsonConfig=new JsonConfig();
		    jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm"));
		    JSONArray jsonArray=JSONArray.fromObject(cinemaList,jsonConfig);
	        result.put("rows", jsonArray);
	        result.put("total", total);
	        ResponseUtil.write(response, result);
	        return null;
	}
		
	@RequestMapping("/findById")	//按id查询影院
	public String findById(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
			Cinema cinema=cinemaService.findById(Integer.parseInt(id));
			JsonConfig jsonConfig=new JsonConfig();
			jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm"));
			JSONObject jsonObject=JSONObject.fromObject(cinema,jsonConfig);
		    ResponseUtil.write(response, jsonObject);
			return null;
	}
	
	@RequestMapping("/indexList")	//首页查询影片集合
	public String indexFind(HttpServletResponse response) throws Exception{
		List<Cinema> cinemaList = cinemaService.indexFind();
		HashMap<String, Object> cdata=new HashMap<String, Object>();
		cdata.put("clist", cinemaList);
		ResponseUtil.write(response, JSON.toJSONString(cdata,SerializerFeature.WriteMapNullValue));
		return null;
	}
	
	@RequestMapping("/cinemaList")	//首页查询影片集合
	public String cinemaList(HttpServletResponse response) throws Exception{
		List<Cinema> cinemaList = cinemaService.indexFind();
		HashMap<String, Object> cdata=new HashMap<String, Object>();
		cdata.put("clist", cinemaList);
		ResponseUtil.write(response, JSON.toJSONString(cdata,SerializerFeature.WriteMapNullValue));
		return null;
	}
	
	
	@RequestMapping("/randList")	//首页查询影片集合
	public String randFind(HttpServletResponse response) throws Exception{
		List<Cinema> cinemaList = cinemaService.randFind();
		HashMap<String, Object> rcdata=new HashMap<String, Object>();
		rcdata.put("rclist", cinemaList);
		ResponseUtil.write(response, JSON.toJSONString(rcdata,SerializerFeature.WriteMapNullValue));
		return null;
	}
	
	@RequestMapping("/lcinemaList")	//首页查询影片集合
	public String cinemaFind(@RequestParam(value="pageNow",required=false)String pageNow,HttpServletResponse response) throws Exception{
		int total=cinemaService.totalGet();
		PageUtil pageUtil=new PageUtil(total,Integer.parseInt(pageNow));
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("startPos", pageUtil.getStartPos());
        map.put("pageSize", pageUtil.getPageSize());
		
		List<Cinema> cinemaList = cinemaService.cinemaFind(map);
		HashMap<String, Object> cdata=new HashMap<String, Object>();
		cdata.put("clist", cinemaList);
		cdata.put("pageUtil", pageUtil);
		ResponseUtil.write(response, JSON.toJSONString(cdata,SerializerFeature.WriteMapNullValue));
		return null;
	}
	
	@RequestMapping("/cinemaHome")	//首页查询影片集合
	public String cinemaHome(@RequestParam(value="cinemaId")String cinemaId,HttpServletRequest request) throws Exception{
		Cinema cinema=cinemaService.findById(Integer.parseInt(cinemaId));
		HttpSession session = request.getSession();
		session.setAttribute("currentCinema", cinema);
		return "redirect:/singlecinema.jsp";
	}
	
	@RequestMapping("/getCinemaByPhone")		//通过手机号获取影院
    public String getCinemaByPhone(@RequestParam(value="phone")String phone,HttpServletResponse response)throws Exception{
        Cinema cinema = cinemaService.findByPhone(Long.parseLong(phone));
        HashMap<String, Object> udata=new HashMap<String, Object>();
		udata.put("cienma", cinema);
		if(cinema!=null){
			udata.put("success",true);
		}else{
			udata.put("success",false);
		}
		ResponseUtil.write(response, JSON.toJSONString(udata,SerializerFeature.WriteMapNullValue));
		return null;
	}

}
