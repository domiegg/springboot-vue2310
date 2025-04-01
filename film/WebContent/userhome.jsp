<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    	<meta name="viewport" content="width=device-width" />
  
    	<title>我的主页</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme_xue.css" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userhome.css" />
	    
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/action_index.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/action_userhome.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>	
			
		
		<script type="text/javascript">
			var password = '${currentLogin.password}';
			var userId = '${currentLogin.userId}';
			
			
			
/* 			var phone='{currentLogin.phone}';
			var name='{currentLogin.name}'; */
			

			 
			 
		</script>
		
	</head>
	<body>
		<div class="bgp">
			<div class="header">
				<div class="nav">
					<div class="dropdown">
						<div class="user-dropdown dropdown-toggle" id="userin" data-toggle="dropdown">
							<span class="loginname">${currentLogin.name}</span>
						</div>
						<ul class="user-exit dropdown-menu" role="menu" aria-labelledby="userin">
							<li role="presentation">
								<a role="menuitem" class="menua" href="${pageContext.request.contextPath}/userhome.jsp">我的主页</a>
							</li>
							<li role="presentation">
								<a role="menuitem" class="menua" href="${pageContext.request.contextPath}/index.jsp" onclick="exit()">退出</a>
							</li>
						</ul>
					</div>
					
					<a href="${pageContext.request.contextPath}/userhome.jsp">个人主页</a> &nbsp;&nbsp;
					<a href="javascript:void(0)" onclick="toFilm()">影片</a> &nbsp;&nbsp;
					<a href="javascript:void(0)" onclick="toCinema()">影院</a> &nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/index.jsp">首页</a> &nbsp;&nbsp;
				</div>
			</div>
			<div class="body">
				<div class="left">
					<div class="userin">
						<span class="welcome">欢迎您</span><br>
						<span class="username">${currentLogin.name}</span>
					</div>
					<div class="user-action">
						<span class="choose1" ><a>账号设置</a></span>
						<span class="choose2" ><a href="javascript:searchArticle()">我的影评</a></span>
						<span class="choose3" ><a href="javascript:openAddArticle()">发表影评</a></span>
						<span class="choose4" ><a href="javascript:searchLike()">我的关注</a></span>
						<span class="choose5" ><a href="javascript:searchOrder(1)">已购影片</a></span>	
					</div>
				</div>
				<div class="contain1">
					<div class="search">
						<span>账号设置</span>
					</div>
					<div class="info-set">
				 		<form id="updateform" action="" method="post" >
					  		<div>
					  			<span>用户名：<br/></span>
					  			<input type="text" value="${currentLogin.name}" name="name" id="uname" class="form-control" />
					  		</div>
							<div>
								<span>手机号：<br/></span>
								<input type="text" value="${currentLogin.phone}" name="phone" id="uphone" class="form-control" />
							</div>
							<button type="button" onclick="update()" class="btn btn-primary">更新信息</button> 
						</form>
						<form id="modifyPwdform" action="" method="post" >
							<div>
								<span>旧密码：<br/></span>
								<input type="password" name="oldpassword" id="uoldpassword" class="form-control" />
							</div>
							<div>
								<span>新密码：<br/></span>
								<input type="password" name="newpassword" id="unewpassword" class="form-control"  />
							</div>
							<div>
								<span>确认密码：<br/></span>
								<input type="password" name="newpassword1" id="unewpassword1" class="form-control" />
							</div>
							 <button type="button" onClick="javascript:modifyPwd()" class="btn btn-primary">修改密码</button> 
						</form>
					</div> 
				</div>
				<div class="contain2">
					<div class="search">
						<span>我的影评</span>
						<form class="navbar-form navbar-left search-form" role="search">
							<div class="form-group">
								<input type="text" id="search" name="search" class="form-control" placeholder="输入搜索内容" />
							</div>
							<button type="button" class="btn btn-default" onclick="getSearchArticle();">搜索</button>
						</form>
					</div>
					<div class="article" id="article">				
						
					</div>
				</div>
				<div class="contain3">
					<div class="search">
						<span>发表影评</span>
					</div>
					<div class="addarticles panel panel-default">
						<div class="addarticles panel-body">
							<form id="addArticleform" method="post" action="">
								<table class="table">
									<tr>
			                            <td style="width：30px;text-align:center;vertical-align:middle;">
			                               <span>标题</span>
			                            </td>
			                            <td colspan="3" style="height:60px">
			                            	<input type="text" id="title" name="title" class="form-control" />
										</td>
			                        </tr>
			                        <tr>
			                            <td style="width：30px;text-align:center;vertical-align:middle;">
			                               <span>影片名称</span>
			                            </td>
										<td colspan="3" style="height:60px">		
										<select id="filmNameCb" name="filmNameCb" class="form-control">
										</select>
										</td>
			                        </tr>
			                        <tr>
			                            <td style="width：30px;text-align:center;">
			                               <span>内容</span>
			                            </td>
			                            <td colspan="3">
			                            	<textarea class="form-control" id="content" name="content" rows="35"></textarea>
										</td>
			                        </tr>
			                        <tr>
			                            
										<td colspan="4" align="center">
			                               	<button type="button" onclick="javascript:setNull()" class="btn btn-default btn-lg" >重置</button> 
											<button type="button" onclick="javascript:addArticle()" class="btn btn-primary btn-lg" >保存</button> 
										</td>
			                        </tr>

								</table>
							</form>
						</div>
					</div>
				</div>
				<div class="contain4">
					<div class="search">
						<span>我的关注</span>
					</div>
					<div class="fimls" id="like">
							
					</div>
				</div>
				<div class="contain5">
					<div class="search">
						<span>已购影片</span>
					</div>
					<table class="table table-hover orders" fit="true">
  						<thead align="center">
	  						<tr>
	  							<th>影片名</th>
	  							<th>影院名</th>
	  							<th>票价</th>
	  							<th>购买时间</th>
	  							<th>操作</th>
	  						</tr>
  						</thead>
  						<tbody id="order">
  						</tbody>
					</table>
					<div id="page" class="page" style="text-align:center">
					</div>
				</div>	
				<script>
					addOnclick();
				</script>
			</div>
			<div class="footer">
				Copyright &copy; 2018  NIIT影片管理系统  All Right Reserved
			</div>
		</div>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/userhome.js" ></script>	
	</body>
</html>
