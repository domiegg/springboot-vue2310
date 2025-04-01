<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    	<meta name="viewport" content="width=device-width" />
  
    	<title>我的主页</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme_xue.css" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/film.css" />
	    
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/action_index.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>	
		<script type="text/javascript">
			var userId='${currentLogin.userId}';
			var loginType = "user";
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
					<a href="${pageContext.request.contextPath}/index.jsp">首页</a> &nbsp;&nbsp;
				</div>
			</div>
			<script>
				  changeSign();  
			</script>
			<div class="body">
				<div class="f_section1">
					<div class="search">
						<span>影片信息</span>
					</div>
					<div class="panel-group fpanelist" id="accordion">
					
					</div>
				</div>
				<div class="f_section2">
					<div class="search">
						<span>排片信息</span>
					</div>
					<ul id="myTab" class="nav nav-tabs myTab">
						<li class="active">
							<a href="#corder" data-toggle="tab">
								 按影院排序
							</a>
						</li>
						<li>
							<a href="#forder" data-toggle="tab">
								按影片排序
							</a>
						</li>
					</ul>
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane fade in active" id="corder">
							<table class="table table-hover alist" fit="true">
		  						<thead align="center">
			  						<tr>
		  	  							<th>影院名称</th>
			  							<th>电影名称</th>
			  							<th>开始时间</th>
			  							<th>结束时间</th>
			  							<th>语言类型</th>
			  							<th>放映厅</th>
			  							<th>剩余座位</th>
			  							<th>价格(元)</th>
			  							<th>操作</th>
			  						</tr>
		  						</thead>
		  						<tbody id="corderList" class="abody">
		  						</tbody>
							</table>
							<div id="cpage" class="page" style="text-align:center">
							</div>
						</div>
						
						<div class="tab-pane fade" id="forder">
							<table class="table table-hover alist" fit="true">
		  						<thead align="center">
			  						<tr>
		  	  							<th>电影名称</th>
			  							<th>影院名称</th>
			  							<th>开始时间</th>
			  							<th>结束时间</th>
			  							<th>语言类型</th>
			  							<th>放映厅</th>
			  							<th>剩余座位</th>
			  							<th>价格(元)</th>
			  							<th>操作</th>
			  						</tr>
		  						</thead>
		  						<tbody id="forderList" class="abody">
		  						</tbody>
							</table>
							<div id="fpage" class="page" style="text-align:center">
							</div>
						</div>
					</div>
					
				</div>
				<div class="f_section3">
					<div class="filmlist">
						<div class="search">
							<span>推荐影片</span>
						</div>
						<table class="table table-hover clist" fit="true">
	  						<thead align="center">
		  						<tr>
		  							<th width=75px;>影片名称</th>
		  							<th>导演</th>
		  							<th>主要演员</th>
		  							<th width=50px;>操作</th>
		  						</tr>
	  						</thead>
	  						<tbody id="flist" class="cbody">
	  						</tbody>
						</table>
					</div>
					<div class="cinemalist">
						<div class="search">
							<span>推荐影院</span>
						</div>
						<table class="table table-hover clist" fit="true">
	  						<thead align="center">
		  						<tr>
		  							<th>影院名称</th>
		  							<th>影院地址</th>
		  							<th>影院操作</th>
		  						</tr>
	  						</thead>
	  						<tbody id="clist" class="cbody">
	  						</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="footer">
				Copyright &copy; 2018  NIIT影片管理系统  All Right Reserved
			</div>
		</div>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/action_film.js" ></script>
	</body>
</html>
