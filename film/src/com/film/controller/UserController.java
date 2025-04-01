package com.film.controller;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.film.model.User;
import com.film.service.UserService;
import com.film.util.DateJsonValueProcessor;
import com.film.util.PageBean;
import com.film.util.ResponseUtil;
import com.film.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


/**
 * 个人管理controller层 
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	
	@InitBinder	//格式化数据
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false); 	
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true)); //true 允许输入空值，false不能为空
	}

	@RequestMapping("/login")		//用户登录
	public String login(User user,HttpServletRequest request) throws Exception{
			String pwd=user.getPassword();
			User resultUser = userService.login(user);
			HttpSession session = request.getSession();
			resultUser.setPassword(pwd);
			session.setAttribute("currentLogin", resultUser);
			if(resultUser.getAuthority()==0){
				return "redirect:/index.jsp";
			}else{
				return "redirect:/admin.jsp";
			}
	}
	
	@RequestMapping("/loginCheck")		//登录检验
    public String loginCheck(@RequestParam(value="phone")String phone,@RequestParam(value="password")String password,HttpServletResponse response)throws Exception{
        User user = userService.findByPhone(Long.parseLong(phone));
        User u=new User();
        u.setPhone(Long.parseLong(phone));
        u.setPassword(password);
        JSONObject result = new JSONObject();
		if(user==null){
			result.put("state",0);
		}else{
			User resultUser = userService.login(u);
			if(resultUser==null){
				result.put("state", 1);

			}else{
				result.put("state", 2);
			}
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	

	@RequestMapping("/register")	//用户注册
	public String register(User user,HttpServletRequest request) throws Exception{
    	user.setRegistrationTime(new Date());
    	userService.register(user);
    	return "redirect:/index.jsp";
	}


	
	@RequestMapping("/logout")		//用户登出
	public String logout(HttpSession session){
		session.removeAttribute("currentLogin");
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("/delete")		//删除用户
	public String delete(@RequestParam(value="ids")String ids,HttpServletResponse response)throws Exception{
	        String []idsStr=ids.split(","); //定义ID数组
	        JSONObject result=new JSONObject();
	        for(int i=0;i<idsStr.length;i++){
	        	int res=userService.delete(Integer.parseInt(idsStr[i]));
	        	 if(res==1){
	        		 result.put("success", true);
	        	}
	        }
	       
	       
	        ResponseUtil.write(response, result);
	        return null;
	}
	
	@RequestMapping("/update")		//修改用户个人信息
    public String update(User user,HttpServletResponse response)throws Exception{
        userService.update(user);
        return "redirect:/userhome.jsp";
	}
	
	@RequestMapping(" /modifyPwd")	//修改用户密码
	public String  modifyPwd(Integer userId,String newpassword,HttpServletResponse response) throws Exception{
		User user=new User();
		user.setUserId(userId);
		user.setPassword(DigestUtils.md5Hex(newpassword));
		userService.update(user);
		return "redirect:logout.do";
	}
	
	@RequestMapping("/save")		//管理员修改用户个人信息
	public String save(User user,HttpServletResponse response)throws Exception{
	        int resultTotal=0;
	        if(user.getUserId()==null){
	        	resultTotal=userService.register(user);
	        }else{
	        	resultTotal=userService.update(user);
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
	 
	@RequestMapping("/list")	//用户集合
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,User user,HttpServletResponse response) throws Exception{
			PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
			Map<String,Object> map=new HashMap<String,Object>();
//			map.put("phone", user.getPhone());
			map.put("name", StringUtil.formatLike(user.getName()));
//		    map.put("gender", user.getGender());
//		    map.put("birthday", user.getBirthday());
		    map.put("address", StringUtil.formatLike(user.getAddress()));
//		    map.put("registrationTime", user.getRegistrationTime());
		    map.put("start", pageBean.getStart());
		    map.put("size", pageBean.getPageSize());
		    List<User> userList=userService.find(map);
		    Long total=userService.getTotal(map);
		    JSONObject result=new JSONObject();
		    JsonConfig jsonConfig=new JsonConfig();
		    jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm"));
		    JSONArray jsonArray=JSONArray.fromObject(userList,jsonConfig);
	        result.put("rows", jsonArray);
	        result.put("total", total);
	        ResponseUtil.write(response, result);
	        return null;
	}
	 
	@RequestMapping("/getUserByPhone")		//通过手机号获取用户
    public String getUserByPhone(@RequestParam(value="phone")String phone,HttpServletResponse response)throws Exception{
        User user = userService.findByPhone(Long.parseLong(phone));
        HashMap<String, Object> udata=new HashMap<String, Object>();
		udata.put("user", user);
		if(user!=null){
			udata.put("success",true);
		}else{
			udata.put("success",false);
		}
		ResponseUtil.write(response, JSON.toJSONString(udata,SerializerFeature.WriteMapNullValue));
		return null;
	}

}





