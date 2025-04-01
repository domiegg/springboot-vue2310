<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 transitional//EN" "http://www.w3.org/tr/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    	<meta name="viewport" content="width=device-width" />
  
    	<title>影片管理系统</title>
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme_xue.css" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/carousel.css"/>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_m2.css" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_m3.css" />
	
	    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/wySilder.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/action_index.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>
		<script type="text/javascript">
			//加载推荐影片列表	
			$.ajax({
				url:"${pageContext.request.contextPath}/film/likeList.do",  
				type:"POST",
				datatype:'json',
				success:function(lfdata){
				  	//遍历返回的json数组
				     $.each(eval('('+ lfdata +')').lflist, function(index,item){	
				    	var fa=$('<a class="grid__item" href="javascript:visitFilm(' + item.filmId + ')" style="text-decoration: none;"></a>');
				    	fa.append('<h2 class="gridtitle">' + item.filmName + '</h2>');
				    	fa.append('<div class="loader">' + item.director + '</div>');
				    	fa.append('<span class="category">'+ item.type + '</span>');
				    	var fdiv=$('<div class="meta meta--preview"></div>');
				    	fdiv.append('<img class="meta__avatar" src="'+ item.cover +'" />');
				    	fdiv.append('<span class="meta__date">' + item.startTime  + '</span>');
				    	fdiv.append('<span class="meta__reading-time">' + item.endTime + '</span>');
				    	fa.append(fdiv);
				    	$('#filmlist').append(fa);
					}); 
			  	}
			}); 
			
			//加载影院列表
			$.ajax({
				url:"${pageContext.request.contextPath}/cinema/indexList.do",  
				type:"POST",
				datatype:'json',
				success:function(cdata){
				  	//遍历返回的json数组
				     $.each(eval('('+ cdata +')').clist, function(index,item){	
				    	var cli=$('<li></li>');
				    	cli.append('<img src="'+ item.cover +'" />');
				    	cli.append('<dd>' + item.name + '</dd>');
				    	cli.append('<p>' + item.description + '</p>');
				    	cli.append('<a href="javascript:visitCinema(' + item.cinemaId + ')"></a>');
				    	$('#cinemalist').append(cli);
					}); 
			  	}
			}); 

			
		</script>
	</head>
	<body>
		<div class="bgp">
			<div class="header">
				<div class="event" id="login_box" style="display: none;">
					<div class="login">
						<div class="title1">
							<span class="t_txt">登录</span>
							<span class="del" onClick="deleteLogin()">X</span>
						</div>
						<form id="loginform" name="loginform" class="form-horizontal" action="" method="post">
							<input type="text" class="form-control" id="phone" name="phone"  placeholder="手机号" required autofocus />
							<input type="password" class="form-control" name="password"  id="password"  placeholder="密码" required />
							
							<label><input type="radio" name="userType" class="radio-btn" value="user"    />普通用户</label>
							<label><input type="radio" name="userType" class="radio-btn" value="cinema"  />影院用户</label><br>
							
							<button type="button" class="btn btn-primary" onclick="toRegister()">注册</button>
							<button type="button" onclick="login()" class="btn btn-primary" >登录</button>
							<input value="" id="test" style="display:none;">
						</form>	
					</div>
				</div>
				<div class="event" id="register_box" style="display: none;">
					<div class="register">
						<div class="title1">
							<span class="back" onClick="backLogin()"><<</span>
							<span class="r_txt">注册</span>
						</div>
						
						<form id="registerform" name="registerform" class="form-horizontal" action="" method="post"  role="form">
							<span>手机号</span><input type="text" class="form-control" name="phone" id="rphone" required />
							<span>用户名</span><input type="text" class="form-control" name="name" id="rname" required autofocus />
							<span>密码<input type="password" class="form-control" name="password"  id="rpassword" required />
							<span>确认密码<input type="password" class="form-control" name="password1"  id="rpassword1" required />
							<label><input type="radio" name="ruserType" class="radio-btn" value="user"    />普通用户</label>
							<label><input type="radio" name="ruserType" class="radio-btn" value="cinema"  />影院用户</label><br>
							<button type="button" onclick="register()" class="btn btn-primary" >提交</button>
						</form>	
					</div> 
				</div>
				<div class="bg_color" onClick="deleteLogin()" id="bg_filter" style="display: none;"></div>
				<div class="nav">
					<button type="button" class="btn_login btn btn-default signin " onClick="showBox()">登 录 &nbsp;</button>
					
					<div class="userin dropdown">
						<div class="user-dropdown dropdown-toggle" id="userin" data-toggle="dropdown">
							<span class="loginname">${currentLogin.name}</span>
						</div>
						<ul class="user-exit dropdown-menu" role="menu" aria-labelledby="userin">
							<li role="presentation">
								<a role="menuitem" class="menua" href="javascript:void(o)" onclick="toHome()">我的主页</a>
							</li>
							<li role="presentation">
								<a role="menuitem" class="menua" href="${pageContext.request.contextPath}/index.jsp" onclick="exit()">退出</a>
							</li>
						</ul>
					</div>
					
					<a href="javascript:void(0)" onclick="toHome()">个人主页</a> &nbsp;&nbsp;
					<a href="javascript:void(0)" onclick="toFilm()">影片</a> &nbsp;&nbsp;
					<a href="javascript:void(0)" onclick="toCinema()">影院</a> &nbsp;&nbsp;
					<a href="index.jsp">首页</a> &nbsp;&nbsp;
				</div>
			</div>
			<script>
				  //changeSign();  
			</script>
			<div class="body">
				<div class="section1">
				    <div class="js-silder">
				        <div class="silder-scroll">
						    <div class="silder-main" style="height:408px">
							    <div class="silder-main-img">
								    <a href="#"><img src="${pageContext.request.contextPath}/img/lb1.jpg" alt=""></a>
							    </div>
				                <div class="silder-main-img">
								    <a href="#"><img src="${pageContext.request.contextPath}/img/lb2.jpg" alt=""></a>
							    </div>
							    <div class="silder-main-img">
								    <a href="#"><img src="${pageContext.request.contextPath}/img/lb3.jpg" alt=""></a>
							    </div>
							    <div class="silder-main-img">
								    <a href="#"><img src="${pageContext.request.contextPath}/img/lb4.jpg" alt=""></a>
							    </div>
							    <div class="silder-main-img">
								    <a href="#"><img src="${pageContext.request.contextPath}/img/lb5.jpg" alt=""></a>
							    </div>
						    </div>
					    </div>
				    </div>
				</div>
				<div class="section2">
					<div class="students">	
						<div id="four_flash">
							<div class="flashBg">
								<ul class="mobile" id="cinemalist">
									<%-- <li>
										<img src="${pageContext.request.contextPath}/img/yy1.png" />
										<dd>影院名称</dd>
										<p>影院简介</p>
										<a href=""></a>
									</li> --%>
								</ul>
							</div>
							<div class="but_left"><img src="${pageContext.request.contextPath}/img/qianxleft.png" /></div>
							<div class="but_right"><img src="${pageContext.request.contextPath}/img/qianxr.png" /></div>
						</div>
					</div>					
				</div>
				
				<div class="section3">
					<div class="film_area">
						<section class="grid" id="filmlist">
							<%-- <a class="grid__item" href="#" style="text-decoration: none;">
								<h2 class="gridtitle">电影名称</h2>
								<div class="loader">
									这里是电影简介
								</div>
								<span class="category">电影分类</span>
								<div class="meta meta--preview">
									<img class="meta__avatar" src="${pageContext.request.contextPath}/img/dy1.jpg" alt="author01" /> 
									<span class="meta__date">上映日期</span>
									<span class="meta__reading-time">下映日期</span>
								</div>
							</a> --%>
						</section>				
					</div>
				</div>
			</div>
			<div class="footer">
				Copyright &copy; 2018  NIIT影片管理系统  All Right Reserved
			</div>
		</div>
	</body>
	
	<script>
	    $(function () {
	        $(".js-silder").silder({
	            auto: true,
	            speed: 20,
	            sideCtrl: true,
	            bottomCtrl: true,
	            defaultView: 0,
	            interval: 3000,
	            activeClass: "active",
	        });
	    });
	</script>
	<script type="text/javascript">
		//学员
		var _index5=0;
		$("#four_flash .but_right img").click(function(){
			_index5++;
			var len=$(".flashBg ul.mobile li").length;
			if(_index5+7>len){
				$("#four_flash .flashBg ul.mobile").stop().append($("ul.mobile").html());
			}
			$("#four_flash .flashBg ul.mobile").stop().animate({left:-_index5*326},1000);
			});
						
		$("#four_flash .but_left img").click(function(){
			if(_index5==0){
				$("ul.mobile").prepend($("ul.mobile").html());
				$("ul.mobile").css("left","-1380px");
				_index5=8
			}
			_index5--;
			$("#four_flash .flashBg ul.mobile").stop().animate({left:-_index5*326},1000);
			});
	</script>	
</html>