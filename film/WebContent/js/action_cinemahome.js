var color1;
var color2;
function addOnclick(){
	$(".del").click(function(){del();});
	$(".choose1").click(function(){choose1();});
	$(".choose2").click(function(){choose2();});
	$(".choose3").click(function(){choose3();});
	$(".choose4").click(function(){choose4();});
	$(".choose5").click(function(){choose5();});
	$(".choose6").click(function(){choose6();});
	color1= "#0275d8";
	color2= "#8C8C8C";
	$('#uploadBasicInfoHead').on('click',function(){  
     $('#basicInfoHead').click();  
    }); 
	//$(".contain2 form").submit(addLabel());
}

function choose1(){
	$(".contain1").css("display","block");
	$(".contain2").css("display","none");
	$(".contain3").css("display","none");
	$(".contain4").css("display","none");
	$(".contain5").css("display","none");
	$(".contain6").css("display","none");
	$(".choose1 a,.choose1 a:focus,.choose1 a:hover").css("color",color1);
	$(".choose2 a,.choose2 a:focus,.choose2 a:hover").css("color",color2);
	$(".choose3 a,.choose3 a:focus,.choose3 a:hover").css("color",color2);
	$(".choose4 a,.choose4 a:focus,.choose4 a:hover").css("color",color2);
	$(".choose5 a,.choose5 a:focus,.choose5 a:hover").css("color",color2);
	$(".choose6 a,.choose6 a:focus,.choose6 a:hover").css("color",color2);
}

function choose2(){
	$(".contain1").css("display","none");
	$(".contain2").css("display","block");
	$(".contain3").css("display","none");
	$(".contain4").css("display","none");
	$(".contain5").css("display","none");
	$(".contain6").css("display","none");
	$(".choose1 a,.choose1 a:focus,.choose1 a:hover").css("color",color2);
	$(".choose2 a,.choose2 a:focus,.choose2 a:hover").css("color",color1);
	$(".choose3 a,.choose3 a:focus,.choose3 a:hover").css("color",color2);
	$(".choose4 a,.choose4 a:focus,.choose4 a:hover").css("color",color2);
	$(".choose5 a,.choose5 a:focus,.choose5 a:hover").css("color",color2);
	$(".choose6 a,.choose6 a:focus,.choose6 a:hover").css("color",color2);
}

function choose3(){
	$(".contain1").css("display","none");
	$(".contain2").css("display","none");
	$(".contain3").css("display","block");
	$(".contain4").css("display","none");
	$(".contain5").css("display","none");
	$(".contain6").css("display","none");
	$(".choose1 a,.choose1 a:focus,.choose1 a:hover").css("color",color2);
	$(".choose2 a,.choose2 a:focus,.choose2 a:hover").css("color",color2);
	$(".choose3 a,.choose3 a:focus,.choose3 a:hover").css("color",color1);
	$(".choose4 a,.choose4 a:focus,.choose4 a:hover").css("color",color2);
	$(".choose5 a,.choose5 a:focus,.choose5 a:hover").css("color",color2);
	$(".choose6 a,.choose6 a:focus,.choose6 a:hover").css("color",color2);
	var len=$(".img-circle").css("width");
	$(".img-circle").css("height",len);
}

function choose4(){
	$(".contain1").css("display","none");
	$(".contain2").css("display","none");
	$(".contain3").css("display","none");
	$(".contain4").css("display","block");
	$(".contain5").css("display","none");
	$(".contain6").css("display","none");
	$(".choose1 a,.choose1 a:focus,.choose1 a:hover").css("color",color2);
	$(".choose2 a,.choose2 a:focus,.choose2 a:hover").css("color",color2);
	$(".choose3 a,.choose3 a:focus,.choose3 a:hover").css("color",color2);
	$(".choose4 a,.choose4 a:focus,.choose4 a:hover").css("color",color1);
	$(".choose5 a,.choose5 a:focus,.choose5 a:hover").css("color",color2);
	$(".choose6 a,.choose6 a:focus,.choose6 a:hover").css("color",color2);
}

function choose5(){
	$(".contain1").css("display","none");
	$(".contain2").css("display","none");
	$(".contain3").css("display","none");
	$(".contain4").css("display","none");
	$(".contain5").css("display","block");
	$(".contain6").css("display","none");
	$(".choose1 a,.choose1 a:focus,.choose1 a:hover").css("color",color2);
	$(".choose2 a,.choose2 a:focus,.choose2 a:hover").css("color",color2);
	$(".choose3 a,.choose3 a:focus,.choose3 a:hover").css("color",color2);
	$(".choose4 a,.choose4 a:focus,.choose4 a:hover").css("color",color2);
	$(".choose5 a,.choose5 a:focus,.choose5 a:hover").css("color",color1);
	$(".choose6 a,.choose6 a:focus,.choose6 a:hover").css("color",color2);
}

function choose6(){
	$(".contain1").css("display","none");
	$(".contain2").css("display","none");
	$(".contain3").css("display","none");
	$(".contain4").css("display","none");
	$(".contain5").css("display","none");
	$(".contain6").css("display","block");
	$(".choose1 a,.choose1 a:focus,.choose1 a:hover").css("color",color2);
	$(".choose2 a,.choose2 a:focus,.choose2 a:hover").css("color",color2);
	$(".choose3 a,.choose3 a:focus,.choose3 a:hover").css("color",color2);
	$(".choose4 a,.choose4 a:focus,.choose4 a:hover").css("color",color2);
	$(".choose5 a,.choose5 a:focus,.choose5 a:hover").css("color",color2);
	$(".choose6 a,.choose6 a:focus,.choose6 a:hover").css("color",color1);
}

//修改信息
function update(){
	var newname = $("#cname").val();
	var newphone = $("#cphone").val();
	
	var re = /^1[3|5|7|8]\d{9}$/;
	
	if(newname=="" || newname==null){
		alert("用户名不能为空！");
		return ;
	}
	else if(newphone=="" || newphone==null){
        alert("手机号不能为空！");
        return false;
    }
	else if(!re.test(newphone)){
        alert("手机号格式错误！");
        return ;
    }else{
		document.forms['updateform'].action = "${pageContext.request.contextPath}/../cinema/update.do?cinemaId="+cinemaId;
		document.forms['updateform'].submit();
	}
	alert("修改成功，下次登录生效！");
	
}
//修改密码
function modifyPwd(){
	
	var oldpassword = $("#coldpassword").val();
	var newpassword = $("#cnewpassword").val();
	var newpassword1 = $("#cnewpassword1").val();
	
	console.log(password);
	console.log(oldpassword);
	console.log(newpassword);
	console.log(newpassword1);
	
	if(oldpassword=="" || newpassword=="" || newpassword1=="" 
		|| oldpassword==null || newpassword==null || newpassword1==null ){
        alert("密码不能为空！");
        return false;
    }
	else if(newpassword != newpassword1){
		alert("密码输入不一致！");
		return false;
	}else if(oldpassword != password){
		alert("影院原密码输入错误！");
		return false;
	}
	else{
		document.forms['modifyPwdform'].action = "${pageContext.request.contextPath}/../cinema/modifyPwd.do?cinemaId="+cinemaId;
		document.forms['modifyPwdform'].submit();
		alert("修改成功，下次登录生效！");
		delCookie();
		return true;
	}
}

function setNull(){
	 $("#newsTitle").val("");
	 $("#file").val("");
	 $("#newsContent").val("");
	 $("#filmNameCb").val("");
	 $("#filmStartTime").val("");
	 $("#filmEndTime").val("");
	 $("#language").val("");
	 $("#filmHall").val("");
	 $("#seatCount").val("");
	 $("#price").val("");
	 
}
//排片			
function addArrangement(){
	var filmNameCb = $("#filmNameCb").val();
	var filmStartTime =document.getElementById("filmStartTime").value;
	var filmEndTime = $("#filmEndTime").val();
	var language = $("#language").val();
	var filmHall = $("#filmHall").val();
	var seatCount = $("#seatCount").val();
	var price = $("#price").val();
	
    if(filmNameCb=="-1"){
        alert("请选择电影！");
        return false;
    }
    if(filmStartTime==""||filmStartTime==null){
        alert("请选择开始时间！");
        return false;
    }
    if(filmEndTime==""||filmEndTime==null){
        alert("请选择结束时间！");
        return false;
    }
    var startNum = parseInt(filmStartTime.replace(/-/g,''),10); 
	var endNum = parseInt(filmEndTime.replace(/-/g,''),10); 
	if(startNum>endNum){ 
		alert("结束日期不能早于起始日期！"); 
		return false; 
	} 
    if(language==""){
        alert("请选择语言！");
        return false;
    }else if(filmHall=="" ||filmHall==null){
        alert("放映厅不能为空！");
        return false;
    }else if(seatCount=="" ||seatCount==null){
        alert("座位数不能为空！");
        return false;
    }else if(price=="" ||price==null){
        alert("价格不能为空！");
        return false;
    }else{
		 document.forms['addArrangementform'].action = "${pageContext.request.contextPath}/../filmArrangement/add.do?cinemaId="+cinemaId+"&filmId="+filmNameCb+"&filmStartTime="+filmStartTime+"&filmEndTime="+filmEndTime; 
		/* document.forms['addArrangementform'].action = "${pageContext.request.contextPath}/filmArrangement/add.do"; */
		document.forms['addArrangementform'].submit();
	}
	alert("成功发表排片！");
	setNull();
}

function addNews(){
	var newsTitle = $("#newsTitle").val();
	var newsContent = $("#newsContent").val();
	var file = $("#file").val();
	if(newsTitle=="" || newsTitle==null){
		alert("标题不能为空！");
		return ;
	}
	else if(file=="" || file==null){
		alert("图片不能为空！");
	       return false;
	}
	else if(newsContent=="" || newsContent==null){
       alert("内容不能为空！");
       return false;
   }
	else{
		document.forms['addNewsform'].action = "${pageContext.request.contextPath}/../filmNews/add.do?cinemaId="+cinemaId;
		document.forms['addNewsform'].submit();
	}
	alert("成功发表资讯！");
	setNull();
}

function deleteNews(newsId){
	if(confirm("确认删除这条资讯吗？")){
  $.post("${pageContext.request.contextPath}/../filmNews/delete.do?newsId="+newsId,
  function(result){
      if(result.success){
          alert("已成功删除此资讯!");
          window.location.reload();
      }else{
          alert("删除失败");
      }
  },"json");
  return;
  }
}

function deleteArrangement(arrangementId){
	if(confirm("确认删除这条排片吗？")){
	  $.post("${pageContext.request.contextPath}/../filmArrangement/delete.do?arrangementId="+arrangementId,
	  function(result){
	      if(result.success){ 
	          alert("已成功删除此排片！");
	          window.location.reload();
	      }else{
	          alert("删除失败!已有用户购票，不能删除此排片.");
//	          window.location.reload();
	      }
	  },"json");
	}
}

//清除cookie
function delCookie(){
   var date = new Date();
   date.setTime(date.getTime() - 10000);
   document.cookie = "isLogin" + "=null; expires=" + date.toGMTString();
}