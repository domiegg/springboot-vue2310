//登录
function login(){
	var radio = document.getElementsByName("userType");
	var phone = $("#phone").val();
	var password = $("#password").val();
	
	if(phone==null || phone==""){
		alert("请输入用户名");
		return ;
	}
	if(password==null||password==""){
		alert("请输入密码");
		return ;
	}
	var date = new Date();
    date.setTime(date.getTime() +   60 * 60 * 1000); //一小时

	if(radio[0].checked){
		$.post("${pageContext.request.contextPath}/../user/loginCheck.do?phone="+phone+"&password="+password,
				function(result){
					 if(result.state==0){
							 alert("该普通用户不存在");
					          $("#phone").val("");
					          $("#password").val("");
					          radio[0].checked=false;
						 }else if(result.state==1){
							  alert("密码错误");
			    			  $("#password").val("");
						 }else{
							 document.forms['loginform'].action = "${pageContext.request.contextPath}/../user/login.do";
							 document.forms['loginform'].submit();
							 document.cookie="isLogin='${currentLogin.name}'"+ ";expires=" + date.toGMTString();
							 document.cookie="loginType=user"+";expires=" + date.toGMTString();
							 changeSign();
						 }
					},"json");
	}
	else if(radio[1].checked){
		$.post("${pageContext.request.contextPath}/../cinema/loginCheck.do?phone="+phone+"&password="+password,
				function(result){
					 if(result.state==0){
							 alert("该影院用户不存在");
					          $("#phone").val("");
					          $("#password").val("");
					          radio[0].checked=false;
						 }else if(result.state==1){
							  alert("密码错误");
			    			  $("#password").val("");
						 }else{
							 document.forms['loginform'].action = "${pageContext.request.contextPath}/../cinema/login.do";
							 document.forms['loginform'].submit();
					    	 document.cookie="isLogin='${currentLogin.name}'"+ ";expires=" + date.toGMTString();
					    	 document.cookie="loginType=cinema"+ ";expires=" + date.toGMTString();
					    	 changeSign();
						 }
					},"json");
	}else{
		alert("请选择用户类型");
		return ;
	}
}


//注册
function register(){
	/*var fmtSuccess = checkFormat();
	if(fmtSuccess){
		document.forms['registerform'].submit();
	}*/
	var phone = $("#rphone").val();
	var name = $("#rname").val();
	var password = $("#rpassword").val();
	var password1 = $("#rpassword1").val(); 	
	var radio = document.getElementsByName("ruserType");

	var re = /^1[3|5|7|8]\d{9}$/;
	
	if(phone=="" || phone==null){
        alert("手机号不能为空！");
        return ;
    }
	else if(!re.test(phone)){
        alert("手机号格式错误！");
        return ;
    }
	else if(name=="" || name==null){
		alert("用户名不能为空！");
		return ;
	}
	else if(password=="" || password1=="" || password==null || password1==null){
        alert("密码不能为空！");
        return ;
    }
	else if(password!=password1){
		alert("密码输入不一致！");
		return ;
	}
	else if(!checkRadio()){
		alert("请选择用户类型！");
		return ;
	}
	else{
		if(radio[0].checked){
			$.post("${pageContext.request.contextPath}/../user/getUserByPhone.do?phone="+phone,
					  function(udata){
					      if(udata.success){
					    	  alert("该用户手机号已被注册！");
					      }else{
					    	  document.forms['registerform'].submit();
					          alert("注册成功");
					      }
					  },"json");
		}else{
			$.post("${pageContext.request.contextPath}/../cinema/getCinemaByPhone.do?phone="+phone,
					  function(udata){
					      if(udata.success){
					    	  alert("该影院手机号已被注册！");
					      }else{
					    	  document.forms['registerform'].submit();
					          alert("注册成功");
					      }
					  },"json");
		}
		 

	}
	
	var telchk = re.test(phone);
	console.log(telchk);
}

//检测用户类型
function checkRadio(){
	var radio = document.getElementsByName("ruserType");
	if(radio[0].checked){
		document.forms['registerform'].action = "${pageContext.request.contextPath}/../user/register.do";
		document.cookie="loginType=user";
		return true;
	}
	else if(radio[1].checked){
		document.forms['registerform'].action = "${pageContext.request.contextPath}/../cinema/register.do";	
		document.cookie="loginType=cinema";
		return true;
	}else{
		return false;
	}
}


//转到主页
function toHome(){
	/*window.location.href="userhome.jsp";*/
	var strCookie=document.cookie;
	var arrCookie=strCookie.split("; "); 
	var loginType=null;
	for(var i=0;i<arrCookie.length;i++){ 
		var arr=arrCookie[i].split("="); 
		if(arr[0]=="loginType"){ 
			loginType=arr[1]; 
			break; 
		} 
	} 
	if(loginType=="user")
	{
		window.location.href="userhome.jsp"
	}
	else if(loginType=="cinema"){
		window.location.href="cinemahome.jsp"
	}
	else{
		alert("请您先登录个人账户！");
	}
}

/*//转到主页
function toHome(){
	window.location.href="userhome.jsp";
	var strCookie=document.cookie;
	var arrCookie=strCookie.split("; "); 
	var loginType=null;
	for(var i=0;i<arrCookie.length;i++){ 
		var arr=arrCookie[i].split("="); 
		if(arr[0]=="loginType"){ 
			loginType=arr[1]; 
			break; 
		} 
	} 
	if(loginType=="user")
	{
		window.location.href="userhome.jsp"
	}
	else if(loginType=="cinema"){
		window.location.href="cinemahome.jsp"
	}
	else{
		alert("请您先登录个人账户！");
	}
}*/

//转到影片页面
function toCinema(){
	var strCookie=document.cookie;
	var arrCookie=strCookie.split("; "); 
	var loginType=null;
	for(var i=0;i<arrCookie.length;i++){ 
		var arr=arrCookie[i].split("="); 
		if(arr[0]=="loginType"){ 
			loginType=arr[1]; 
			break; 
		} 
	} 
	if(loginType=="user")
	{
		window.location.href="cinema.jsp"
	}
	else if(loginType=="cinema"){
		window.location.href="cinema_cinema.jsp"
	}
	else{
		alert("请您先登录个人账户！");
	}
}

//转到影片页面
function toFilm(){
	var strCookie=document.cookie;
	var arrCookie=strCookie.split("; "); 
	var loginType=null;
	for(var i=0;i<arrCookie.length;i++){ 
		var arr=arrCookie[i].split("="); 
		if(arr[0]=="loginType"){ 
			loginType=arr[1]; 
			break; 
		} 
	} 
	if(loginType=="user")
	{
		window.location.href="film.jsp"
	}
	else if(loginType=="cinema"){
		window.location.href="film_cinema.jsp"
	}
	else{
		alert("请您先登录个人账户！");
	}
}

//弹出登录框
function showBox(){
	var show=document.getElementById("login_box");
	var bg_filter=document.getElementById("bg_filter");
	show.style.display="block";
	bg_filter.style.display="block";
	
}

//关闭登录框
function deleteLogin(){
	canelRegErr();
	canelLoginErr();
	var del=document.getElementById("login_box");
	var bg_filter=document.getElementById("bg_filter");
	bg_filter.style.display="none";
	del.style.display="none";
	var del=document.getElementById("register_box");
	del.style.display="none";
}

//转到注册框
function toRegister(){
	canelLoginErr();
	var del=document.getElementById("login_box");
	del.style.display="none";
	var show=document.getElementById("register_box");
	show.style.display="block";
}

//返回登录框
function backLogin(){
	canelRegErr();
	var del=document.getElementById("register_box");
	del.style.display="none";
	var show=document.getElementById("login_box");
	show.style.display="block";
}

//登录、用户名转换
function changeSign(){
	var strCookie=document.cookie;
	var arrCookie=strCookie.split("; "); 
	var isLogin=null;
	for(var i=0;i<arrCookie.length;i++){ 
		var arr=arrCookie[i].split("="); 
		if(arr[0]=="isLogin"){ 
			isLogin=arr[1]; 
			break; 
		} 
	} 
	if(isLogin!=null)
	{
		deleteLogin();
		$(".signin").css("display","none");
		$(".userin").css("display","block");
	}
	else{
		$(".signin").css("display","block");
		$(".userin").css("display","none");
	}
}

//用户退出
function exit(){
	if(confirm("确认退出账号吗？")){
		$(".signin").css("display","block");
		$(".userin").css("display","none");
		delCookie();
		changeSign();
	}
	
}

//清除cookie
function delCookie(){
   var date = new Date();
   date.setTime(date.getTime() - 10000);
   document.cookie = "isLogin" + "=null; expires=" + date.toGMTString();
   document.cookie = "loginType" + "=null; expires=" + date.toGMTString();
}

//跳转单个影院界面
function visitCinema(cinemaId){
	var strCookie=document.cookie;
	var arrCookie=strCookie.split("; "); 
	var loginType=null;
	for(var i=0;i<arrCookie.length;i++){ 
		var arr=arrCookie[i].split("="); 
		if(arr[0]=="loginType"){ 
			loginType=arr[1]; 
			break; 
		} 
	} 
	if(loginType!=null)
	{
		window.location.href="${pageContext.request.contextPath}/../cinema/cinemaHome.do?cinemaId="+cinemaId;
	}
	else{
		alert("请您先登录个人账户！");
	}
	
}

//跳转单个影片界面
function visitFilm(filmId){
	var strCookie=document.cookie;
	var arrCookie=strCookie.split("; "); 
	var loginType=null;
	for(var i=0;i<arrCookie.length;i++){ 
		var arr=arrCookie[i].split("="); 
		if(arr[0]=="loginType"){ 
			loginType=arr[1]; 
			break; 
		} 
	} 
	if(loginType!=null)
	{
		window.location.href="${pageContext.request.contextPath}/../film/filmHome.do?filmId="+filmId;
	}
	else{
		alert("请您先登录个人账户！");
	}
}

function loginErr(mess){
	$(".loginerr").empty();
	$(".loginerr").append(mess);
	$(".login").css("height","420px");
	$(".loginerr").css("display","block");

}

function canelLoginErr(){
	$(".login").css("height","320px");
	$(".loginerr").css("display","none");
	$(".loginerr").empty();
}

function regErr(mess){
	$(".regerr").empty();
	$(".register").css("height","520px");
	$(".regerr").css("display","block");
	$(".regerr").append(mess);
}

function canelRegErr(){
	$(".register").css("height","520px");
	$(".regerr").css("display","none");
	$(".regerr").empty();
}

/*film*/
var _index5=0;
$("#four_flash .but_right img").click(function(){
	_index5++;
	var len=$(".flashBg ul.mobile li").length;
	if(_index5+5>len){
		$("#four_flash .flashBg ul.mobile").stop().append($("ul.mobile").html());
	}
	$("#four_flash .flashBg ul.mobile").stop().animate({left:-_index5*326},1000);
	});

	
$("#four_flash .but_left img").click(function(){
	if(_index5==0){
		$("ul.mobile").prepend($("ul.mobile").html());
		$("ul.mobile").css("left","-1380px");
		_index5=6
	}
	_index5--;
	$("#four_flash .flashBg ul.mobile").stop().animate({left:-_index5*326},1000);
	});  