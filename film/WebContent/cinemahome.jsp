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
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cinemahome.css" />
	    
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/action_index.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/action_cinemahome.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>	
		<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script> 	
		
		<script type="text/javascript">
		var password = '${currentLogin.password}';
		var cinemaId = '${currentLogin.cinemaId}';
	
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
								<a role="menuitem" class="menua" href="${pageContext.request.contextPath}/cinemahome.jsp">我的主页</a>
							</li>
							<li role="presentation">
								<a role="menuitem" class="menua" href="${pageContext.request.contextPath}/index.jsp" onclick="exit()">退出</a>
							</li>
						</ul>
					</div>
					
					<a href="${pageContext.request.contextPath}/cinemahome.jsp">个人主页</a> &nbsp;&nbsp;
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
						<span class="choose1" ><a >账号设置</a></span>
						<span class="choose2" ><a href="javascript:searchNews()">影院资讯</a></span>
						<span class="choose3" ><a >添加资讯</a></span>
						<span class="choose4" ><a href="javascript:searchArrangement(1)">影院排片</a></span>
						<span class="choose5" ><a href="javascript:openAddArrangement()">添加排片</a></span>
						<span class="choose6" ><a href="javascript:searchComment()">影院评论</a></span>	
					</div>
				</div>
				<div class="contain1">
					<div class="search">
						<span>账号设置</span>
					</div>
					<div class="info-set">
				 		<form id="updateform" action="" method="post" enctype="multipart/form-data">
					  		<div>
					  			<span>用户名：<br/></span>
					  			<input type="text" value="${currentLogin.name}" name="name" id="cname" class="form-control" />
					  		</div>
							<div>
								<span>手机号：<br/></span>
								<input type="text" value="${currentLogin.phone}" name="phone" id="cphone" class="form-control" />
							</div>
							<div>
								<span>影院简介：<br/></span>
								<input type="text" value="${currentLogin.description}" name="description" id="description" class="form-control" />
							</div>
							<div>
								<span>影院地址：<br/></span>
								<input type="text" value="${currentLogin.location}" name="location" id="location" class="form-control" />
							</div>
							<div>
								<span>影院封面：<br/></span>
								<input type="file" id="pic" name="pic" class="pic" accept="image/png,image/jpeg,image/gif">
							</div>
							<button type="button" onclick="update()" class="btn btn-primary">更新信息</button> 
						</form>
						<form id="modifyPwdform" action="" method="post" >
							<div>
								<span>旧密码：<br/></span>
								<input type="password" name="oldpassword" id="coldpassword" class="form-control" />
							</div>
							<div>
								<span>新密码：<br/></span>
								<input type="password" name="newpassword" id="cnewpassword" class="form-control"  />
							</div>
							<div>
								<span>确认密码：<br/></span>
								<input type="password" name="newpassword1" id="cnewpassword1" class="form-control" />
							</div>
							 <button type="button" onClick="javascript:modifyPwd()" class="btn btn-primary">修改密码</button> 
						</form>
					</div> 
				</div>
				<div class="contain2">
					<div class="search">
						<span>影院资讯</span>
						<form class="navbar-form navbar-left search-form" role="search">
							<div class="form-group">
								<input type="text" id="search" name="search" class="form-control" placeholder="输入搜索内容" />
							</div>
							<button type="button" class="btn btn-default" onclick="getSearchNews();">搜索</button>
						</form>
					</div>
					<div class="news" id="news">
					</div>
				</div>
				<div class="contain3">
					<div class="search">
						<span>添加资讯</span>
					</div>
					<div class="addnews panel panel-default">
						<div class="addnews panel-body">
							<form id="addNewsform" method="post" action="" enctype="multipart/form-data">
								<table class="table">
									<tr>
			                            <td style="width：30px;text-align:center;vertical-align:middle;">
			                               <span>标题</span>
			                            </td>
			                            <td colspan="3" style="height:60px">
			                            	<input type="text" id="newsTitle" name="newsTitle" class="form-control"/>
										</td>
			                        </tr>
			                        <tr>
			                            <td style="width：30px;text-align:center;vertical-align:middle;">
			                               <span>图片</span>
			                            </td>
										<td colspan="3" style="height:60px">
										<input type="file" id="file" name="file" class="filcpic" accept="image/png,image/jpeg,image/gif">			
										<!-- <input type="text" id="picture" name="picture" class="form-control"/> -->
										</td>
			                        </tr>
			                        <tr>
			                            <td style="width：30px;text-align:center;">
			                               <span>内容</span>
			                            </td>
			                            <td colspan="3">
			                            	<textarea class="form-control" id="newsContent" name="newsContent" rows="35"></textarea>
										</td>
			                        </tr>
			                        <tr>		                            
										<td colspan="4" align="center">
			                               	<button type="button" onClick="javascript:setNull()" class="btn btn-default btn-lg" >重置</button>
											<button type="button" onClick="javascript:addNews()" class="btn btn-primary btn-lg" >保存</button> 
										</td>
			                        </tr>
								</table>
							</form>
						</div>
					</div>
				</div>
				<div class="contain4">
					<div class="search">
						<span>影院排片</span>
					</div>
					<table class="table table-hover orders" fit="true">
  						<thead align="center">
	  						<tr>
	  							<th>影片名</th>
	  							<th>语言</th>
	  							<th>开始时间</th>
	  							<th>结束时间</th>
	  							<th>影厅</th>
	  							<th>剩余座位数</th>
	  							<th>票价(元)</th>
	  							<th>操作</th>
	  						</tr>
  						</thead>
  						<tbody id="arrangement">
  						</tbody>
					</table>
					<div id="page" class="page" style="text-align:center">
					</div>
				</div>
				<div class="contain5">
					<div class="search">
						<span>添加排片</span>
					</div>
					<div class="addarrangement panel panel-default">
						<div class="addarrangement panel-body">
							<form id="addArrangementform" method="post" action="">
								<table class="table">							
									<tr>
					                	<td style="width：50px;text-align:center;vertical-align:middle;">
					                        <span>影片名称</span>
					                    </td>
										<td colspan="3" style="height:60px">		
											<select id="filmNameCb" name="filmNameCb" class="form-control">
											</select>
										</td>
					                </tr>
					                <tr>
				                        <td style="width：50px;text-align:center;vertical-align:middle;">
				                            <span>开始时间</span>
				                        </td>
				                        <td colspan="3" style="height:60px">
				                          <input class="form-control" type="text" id="filmStartTime" readonly="readonly"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
										</td>
				                	</tr>
				                	<tr>
				                        <td style="width：50px;text-align:center;vertical-align:middle;">
				                            <span>结束时间</span>
				                        </td>
				                        <td colspan="3" style="height:60px">
				                          <input class="form-control" id="filmEndTime" type="text" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
										</td>
				                	</tr>
				                	<tr>
				                        <td style="width：50px;text-align:center;vertical-align:middle;">
				                            <span>语言</span>
				                        </td>
				                        <td colspan="3" style="height:60px">
				                         	<select  id="language" name="language" editable="false"  class="form-control">
							            		<option value="">--请选择语言-</option>
							            		<option value="汉语">汉语</option>
							            		<option value="英语">英语</option>
							            		<option value="日语">日语</option>
							            		<option value="韩语">韩语</option>
							            		<option value="其他">其他</option>
							            	</select>
				                        </td>
				                	</tr>
				                	<tr>
				                        <td style="width：50px;text-align:center;vertical-align:middle;">
				                            <span>放映厅</span>
				                        </td>
				                        <td colspan="3" style="height:60px">
				                           	<input type="text" id="filmHall" name="filmHall" class="form-control" />
										</td>
				                	</tr>
				                	<tr>
				                        <td style="width：50px;text-align:center;vertical-align:middle;">
				                            <span>座位数</span>
				                        </td>
				                        <td colspan="3" style="height:60px">
				                           	<input type="number" id="seatCount" name="seatCount"  min="0" class="form-control" />
										</td>
				                	</tr>	
				                	<tr>
				                        <td style="width：50px;text-align:center;vertical-align:middle;">
				                            <span>价格</span>
				                        </td>
				                        <td colspan="3" style="height:60px">
				                           	<input type="number" id="price" name="price" min="0"  class="form-control" />
										</td>
				                	</tr>		
				                	<tr>		                            
										<td colspan="4" align="center">
				                            <button type="button" onClick="javascript:setNull()" class="btn btn-default btn-lg" >重置</button>
											<button type="button" onClick="javascript:addArrangement()" class="btn btn-primary btn-lg" >保存</button> 
										</td>
				                    </tr>										
								</table>
							</form>	
						</div>
					</div>
				</div>

				<div class="contain6">
					<div class="search">
						<span>影院评论</span>
					</div>
					<div class="comment" id="comment">
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
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/cinemahome.js" ></script>
	</body>
</html>
