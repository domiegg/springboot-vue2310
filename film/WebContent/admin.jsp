<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width" />
<title>管理员</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/action_admin.js"></script>
<script type="text/javascript">

</script> 

<title>管理员界面</title>
</head>
<body style="margin: 1px">
	<div class="easyui-layout" style="width:100%;height:970px;">
		<div data-options="region:'center',title:'管理员界面',iconCls:'icon-fwgl'">
			<div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
				<div title="用户管理" style="padding:5px">
				  <table id="u_dg" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
					   url="${pageContext.request.contextPath}/user/list.do" fit="true" toolbar="#u_tb">
					   	<thead>
					    	<tr>
						        <th field="cb" checkbox="true" align="center"></th>
						        <th field="userId" width="75" align="center">编号</th>
						        <th field="phone" width="75" align="center">手机号</th>
						        <th field="name" width="75" align="center">用户名</th>
				<!-- 		        <th field="password" width="75" align="center">密码</th> -->
						        <th field="registrationTime" width="75" align="center">注册时间</th> 
						        <th field="authority" width="75" align="center">权限</th>
						    </tr>
						 </thead>
					 </table>
				 	 <div id="u_tb">
					    <div>
					        <a href="javascript:openUserAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
					        <a href="javascript:openUserModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
					        <!-- <a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a> -->
					        <a href="javascript:logout()" class="easyui-linkbutton" iconCls="icon-exit" style="float:right;right:10px;">退出</a>
					    </div>
					 <div>
					     	&nbsp;用户名：&nbsp;<input type="text" id="u_name" size="20" onkeydown="if(event.keyCode==13) searchUser()"/>
					        &nbsp;地址：&nbsp;<input type="text" id="u_address" size="20" onkeydown="if(event.keyCode==13) searchUser()"/>
					        <a href="javascript:searchUser()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
				     </div>
					 </div>
					 <div id="u_dlg" class="easyui-dialog" style="width:620px;height:200px;padding: 10px 20px" closable="false" closed="true" buttons="#u_dlg-buttons">
				   	 <form id="u_fm" method="post">
				     	<table cellspacing="10px">
					        <tr>
					            <td>手机号：</td>
					            <td><input type="text" id="u_phone" name="phone" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
					            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					            <td>密码</td>
					            <td><input type="text" id="u_password" name="password" class="easyui-validatebox"  required="true"/>&nbsp;<font color="red">*</font></td>
					        </tr>
					        <tr>
					            <td>用户名：</td>
					            <td><input type="text" id="u_name" name="name" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font></td>
					            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					            <td>注册时间：</td>
					            <td><input type="text" id="u_registrationTime" name="registrationTime" class="easyui-validatebox" readonly="readonly"/>&nbsp;<font color="red">*</font></td>
					        </tr>
					        <tr>
					            <td>权限：</td>
					            <td><input type="text" id="authority" name="authority" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font></td>
					            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					        </tr>
				    	</table>
				   	 </form>
				 	 </div>
					 <div id="u_dlg-buttons">
					    <a href="javascript:saveUser()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
					    <a href="javascript:closeUserDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
					 </div> 
				</div>
				
				
				
				<div title="影院管理" style="padding:5px">
					 <table id="c_dg" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
					   url="${pageContext.request.contextPath}/cinema/list.do" fit="true" toolbar="#c_tb">
					   	<thead>
					    	<tr>
						        <th field="cb" checkbox="true" align="center"></th>
						        <th field="cinemaId" width="75" align="center">编号</th>
						        <th field="phone" width="75" align="center">手机号</th>
						        <th field="name" width="75" align="center">影院名</th>
				<!-- 		        <th field="password" width="75" align="center">密码</th> -->
						        <th field="description" width="75" align="center">详情</th>
						        <th field="location" width="75" align="center">地址</th>
						        <th field="registrationTime" width="75" align="center">注册时间</th>
						    </tr>
						 </thead>
					 </table>
				 	 <div id="c_tb">
					    <div>
					        <a href="javascript:openCinemaAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
					        <a href="javascript:openCinemaModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
					        <!-- <a href="javascript:deleteCinema()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a> -->
					    	<a href="javascript:logout()" class="easyui-linkbutton" iconCls="icon-exit" style="float:right;right:10px;">退出</a>
					    </div>
						<div>
						    &nbsp;影院名：&nbsp;<input type="text" id="c_name" size="20" onkeydown="if(event.keyCode==13) searchCinema()"/>
						    &nbsp;地址：&nbsp;<input type="text" id="c_location" size="20" onkeydown="if(event.keyCode==13) searchCinema()"/>
						    <a href="javascript:searchCinema()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
					    </div>
					 </div>
					 <div id="c_dlg" class="easyui-dialog" style="width:650px;height:230px;padding: 10px 20px" closable="false" closed="true" buttons="#c_dlg-buttons">
				   	 <form id="c_fm" method="post" enctype="multipart/form-data">
				     <table cellspacing="8px">
				        <tr>
				            <td>手机号：</td>
				            <td><input type="text" id="c_phone" name="phone" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
				            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				            <td>密码</td>
				            <td><input type="text" id="c_password" name="password" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
				        </tr>
				        <tr>
				            <td>用户名：</td>
				            <td><input type="text" id="c_name" name="name" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
				            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				            <td>影院简介：</td>
				            <td><input type="text" id="c_description" name="description" class="easyui-validatebox" /></td>
				        </tr>
				        <tr>
				            <td>影院地址：</td>
				            <td><input type="text" id="location" name="location" class="easyui-validatebox" /></td>
				            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				            <td>封面：</td>
				            <td><input type="file" id="pic" name="pic" style="width:173px" accept="image/png,image/jpeg,image/gif">&nbsp;<font color="red">*</font></td>
				        </tr>
				        <tr>
				        	<td>注册时间：</td>
				            <td><input type="text" id="c_registrationTime" name="registrationTime" class="easyui-validatebox" readonly="readonly"/>&nbsp;<font color="red">*</font></td>
				            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				        </tr>
				     </table>
				   	 </form>
				 	 </div>
					 <div id="c_dlg-buttons">
					    <a href="javascript:saveCinema()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
					    <a href="javascript:closeCinemaDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
					 </div>
				</div> 
				
				
				<div title="影片管理" style="padding:5px">	 
					 <table id="f_dg" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"
					   url="${pageContext.request.contextPath}/film/list.do" fit="true" toolbar="#f_tb">
					   	<thead>
					    	<tr>
						        <th field="cb" checkbox="true" align="center"></th>
						        <th field="filmId" width="75" align="center">编号</th>
						        <th field="filmName" width="75" align="center">影片名</th>
						        <th field="director" width="75" align="center">导演</th>
						        <th field="actor" width="75" align="center">主演</th>
						        <th field="cover" width="75" align="center">封面</th>
						        <th field="type" width="75" align="center">类型</th>
						        <th field="startTime" width="75" align="center">上映时间</th>
						        <th field="endTime" width="75" align="center">下映时间</th>
						        <th field="description" width="75" align="center">简介</th>
						        <th field="likeCount" width="75" align="center">关注量</th>
						        <th field="registrationTime" width="75" align="center">添加时间</th>
						        <th field="times" width="75" align="times">热度</th>
						    </tr>
						 </thead>
					 </table>
				 	 <div id="f_tb">
					    <div>
					        <a href="javascript:openFilmAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
					        <a href="javascript:openFilmModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
					        <!-- <a href="javascript:deleteFilm()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a> -->
					    	<a href="javascript:logout()" class="easyui-linkbutton" iconCls="icon-exit" style="float:right;right:10px;">退出</a>
					    </div>
					 <div>
					  	    &nbsp;影片名：&nbsp;<input type="text" id="f_fileName" size="20" onkeydown="if(event.keyCode==13) searchFilm()"/>
					     	&nbsp;导演：&nbsp;<input type="text" id="f_director" size="20" onkeydown="if(event.keyCode==13) searchFilm()"/>
					     	&nbsp;主演：&nbsp;<input type="text" id="f_actor" size="20" onkeydown="if(event.keyCode==13) searchFilm()"/>
					     	&nbsp;类型：&nbsp;<input type="text" id="f_type" size="20" onkeydown="if(event.keyCode==13) searchFilm()"/>
					    	<a href="javascript:searchFilm()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
				     </div>
					 </div>
					 <div id="f_dlg" class="easyui-dialog" style="width:650px;height:260px;padding: 10px 20px" closable="false" closed="true" buttons="#f_dlg-buttons">
				   	 <form id="f_fm" method="post" enctype="multipart/form-data">
				     <table cellspacing="8px">
				        <tr>
				        	<td>封面：</td>
				            <td><input type="file" id="file" name="file" style="width:173px" accept="image/png,image/jpeg,image/gif">&nbsp;<font color="red">*</font></td>
				            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				            <td>影片名：</td>
				            <td><input type="text" id="filmName" name="filmName" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
				        </tr>
				        <tr>
				        	<td>导演：</td>
				            <td><input type="text" id="director" name="director" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font></td>
				            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				            <td>主演：</td>
				            <td><input type="text" id="actor" name="actor" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font></td>
				        </tr>
				        <tr>
				            <td>简介：</td>
				            <td><input type="text" id="f_description" name="description" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font></td>
				            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				            <td>类型:</td>
				            <td><input type="text" id="type" name="type" class="easyui-validatebox" required="true" />&nbsp;<font color="red">*</font></td>
				            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<!-- 		                <select class="easyui-combobox" id="type" name="type" style="width: 154px" editable="false" panelHeight="auto">
				                    <option value="">请选择角色...</option>
				                    <option value="喜剧">喜剧</option>
				                    <option value="动作">动作</option>
				                </select> -->
				        </tr>
				        <tr>
				            <td>上映时间：</td>
				            <td><input type="text" id="startTime" name="startTime" class="easyui-datebox" required="true" />&nbsp;<font color="red">*</font></td>
				            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				            <td>下映时间：</td>
				            <td><input type="text" id="endTime" name="endTime" class="easyui-datebox" required="true" />&nbsp;<font color="red">*</font></td>
				        </tr>
				        <tr>
				        	<td>关注量：</td>
				            <td><input type="text" id="likeCount" name="likeCount" class="easyui-validatebox" required="true" readonly="readonly"/>&nbsp;<font color="red">*</font></td>
				            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				            <td>热度：</td>
				            <td><input type="text" id="times" name="times" class="easyui-validatebox" required="true" readonly="readonly"/>&nbsp;<font color="red">*</font></td>
				        </tr>
				        <tr>
				            <td>添加时间：</td>
				            <td><input type="text" id="f_registrationTime" name="registrationTime" class="easyui-validatebox" readonly="readonly"/>&nbsp;<font color="red">*</font></td>
				        </tr>
				    </table>
				   	</form>
				 	</div>
					<div id="f_dlg-buttons">
					    <a href="javascript:saveFilm()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
					    <a href="javascript:closeFilmDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
					</div>
				</div>
			</div>
		</div>	
		<div region="south" style="height: 30px;padding: 5px" align="center">
			版本所有 15NIIT - 电影管理系统
		</div>
	</div>
</body>
</html>