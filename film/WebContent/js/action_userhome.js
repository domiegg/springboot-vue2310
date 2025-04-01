var color1;
var color2;
function addOnclick(){
	$(".del").click(function(){del();});
	$(".choose1").click(function(){choose1();});
	$(".choose2").click(function(){choose2();});
	$(".choose3").click(function(){choose3();});
	$(".choose4").click(function(){choose4();});
	$(".choose5").click(function(){choose5();});
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
	$(".choose1 a,.choose1 a:focus,.choose1 a:hover").css("color",color1);
	$(".choose2 a,.choose2 a:focus,.choose2 a:hover").css("color",color2);
	$(".choose3 a,.choose3 a:focus,.choose3 a:hover").css("color",color2);
	$(".choose4 a,.choose4 a:focus,.choose4 a:hover").css("color",color2);
	$(".choose5 a,.choose5 a:focus,.choose5 a:hover").css("color",color2);
}

function choose2(){
	$(".contain1").css("display","none");
	$(".contain2").css("display","block");
	$(".contain3").css("display","none");
	$(".contain4").css("display","none");
	$(".contain5").css("display","none");
	$(".choose1 a,.choose1 a:focus,.choose1 a:hover").css("color",color2);
	$(".choose2 a,.choose2 a:focus,.choose2 a:hover").css("color",color1);
	$(".choose3 a,.choose3 a:focus,.choose3 a:hover").css("color",color2);
	$(".choose4 a,.choose4 a:focus,.choose4 a:hover").css("color",color2);
	$(".choose5 a,.choose5 a:focus,.choose5 a:hover").css("color",color2);
}

function choose3(){
	$(".contain1").css("display","none");
	$(".contain2").css("display","none");
	$(".contain3").css("display","block");
	$(".contain4").css("display","none");
	$(".contain5").css("display","none");
	$(".choose1 a,.choose1 a:focus,.choose1 a:hover").css("color",color2);
	$(".choose2 a,.choose2 a:focus,.choose2 a:hover").css("color",color2);
	$(".choose3 a,.choose3 a:focus,.choose3 a:hover").css("color",color1);
	$(".choose4 a,.choose4 a:focus,.choose4 a:hover").css("color",color2);
	$(".choose5 a,.choose5 a:focus,.choose5 a:hover").css("color",color2);
	var len=$(".img-circle").css("width");
	$(".img-circle").css("height",len);
}

function choose4(){
	$(".contain1").css("display","none");
	$(".contain2").css("display","none");
	$(".contain3").css("display","none");
	$(".contain4").css("display","block");
	$(".contain5").css("display","none");
	$(".choose1 a,.choose1 a:focus,.choose1 a:hover").css("color",color2);
	$(".choose2 a,.choose2 a:focus,.choose2 a:hover").css("color",color2);
	$(".choose3 a,.choose3 a:focus,.choose3 a:hover").css("color",color2);
	$(".choose4 a,.choose4 a:focus,.choose4 a:hover").css("color",color1);
	$(".choose5 a,.choose5 a:focus,.choose5 a:hover").css("color",color2);
}

function choose5(){
	$(".contain1").css("display","none");
	$(".contain2").css("display","none");
	$(".contain3").css("display","none");
	$(".contain4").css("display","none");
	$(".contain5").css("display","block");
	$(".choose1 a,.choose1 a:focus,.choose1 a:hover").css("color",color2);
	$(".choose2 a,.choose2 a:focus,.choose2 a:hover").css("color",color2);
	$(".choose3 a,.choose3 a:focus,.choose3 a:hover").css("color",color2);
	$(".choose4 a,.choose4 a:focus,.choose4 a:hover").css("color",color2);
	$(".choose5 a,.choose5 a:focus,.choose5 a:hover").css("color",color1);
}

//修改信息
function update(){
	var newname = $("#uname").val();
	var newphone = $("#uphone").val();
	
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
		document.forms['updateform'].action = "${pageContext.request.contextPath}/../user/update.do?userId="+userId;
		document.forms['updateform'].submit();
	}
	alert("修改成功，下次登录生效！");
	
}
//修改密码
function modifyPwd(){
	
	var oldpassword = $("#uoldpassword").val();
	var newpassword = $("#unewpassword").val();
	var newpassword1 = $("#unewpassword1").val();
	
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
		alert("用户原密码输入错误！");
		return false;
	}
	else{
		document.forms['modifyPwdform'].action = "${pageContext.request.contextPath}/../user/modifyPwd.do?userId="+userId;
		document.forms['modifyPwdform'].submit();
		alert("修改成功，下次登录生效！");
		delCookie();
		return true;
	}
	
//	window.location.href='${pageContext.request.contextPath}/../user/logout.do';
}

function setNull(){
	 $("#title").val("");
	 $("#content").val("");
	 $("#filmNameCb").val("-1");
}

function addArticle(){
	var title = $("#title").val();
	var content = $("#content").val();
	var filmNameCb = $("#filmNameCb").val();
	
	if(title=="" || title==null){
		alert("标题不能为空！");
		return ;
	}
	else if(filmNameCb=="-1"){
       alert("请选择电影！");
       return false;
   }
	else if(content=="" || content==null){
       alert("内容不能为空！");
       return false;
   }
	else{
		document.forms['addArticleform'].action = "${pageContext.request.contextPath}/../filmArticle/add.do?userId="+userId+"&filmId="+filmNameCb;
		document.forms['addArticleform'].submit();
	}
	alert("成功发表影评！");
	setNull();
}

function deleteArticle(articleId){
	/* var articleId= $("#article").val(); */
	if(confirm("确认删除这条影评吗？")){
  $.post("${pageContext.request.contextPath}/../filmArticle/delete.do?articleId="+articleId,
  function(result){
      if(result.success){
          alert("已成功删除此影评！");
          window.location.reload();
      }else{
          alert("删除失败");
      }
  },"json");
	}
}


function deleteOrder(boughtId,arrangementId){
	if(confirm("确认退票吗？")){
	$.post("${pageContext.request.contextPath}/../boughtFilm/delete.do?boughtId="+boughtId+"&arrangementId="+arrangementId,
	function(result){
		if(result.success){
			alert("已成功退票！");
			window.location.reload();
		}else{
			alert("退票失败");
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