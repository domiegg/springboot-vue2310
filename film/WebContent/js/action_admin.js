var url;
$('#c_dlg').dialog({  
onClose:function(){  
  alert('dlg closed');  
  resetValue();
},  
onOpen:function(){  
  alert('dlg Open'); 
  resetValue();
},  
});


//搜索
function searchUser(){
    $("#u_dg").datagrid('load',{
       "name":$("#u_name").val(),
       "address":$("#u_address").val() 
    });
}
function searchCinema(){
    $("#c_dg").datagrid('load',{
	   "name":$("#c_name").val(),
	   "location":$("#c_location").val() 
	});
}
function searchFilm(){
    $("#f_dg").datagrid('load',{
	   "filmName":$("#f_filmName").val(),
	   "director":$("#f_director").val(),
	   "actor":$("#f_actor").val(),
	   "type":$("#f_type").val() 
	});
}

//打开添加
function openUserAddDialog(){
	document.getElementById("u_password").readOnly=false;
    $("#u_dlg").dialog("open").dialog("setTitle","添加用户信息");
    $("#u_registrationTime").val(getCurrentDateTime());
    $("#authority").val("0");
    url="${pageContext.request.contextPath}/../user/save.do";
}
function openCinemaAddDialog(){
	document.getElementById("c_password").readOnly=false;
    $("#c_dlg").dialog("open").dialog("setTitle","添加影院信息");
    $("#c_registrationTime").val(getCurrentDateTime());
    url="${pageContext.request.contextPath}/../cinema/save.do";
}
function openFilmAddDialog(){
    $("#f_dlg").dialog("open").dialog("setTitle","添加影片信息");
    $("#f_registrationTime").val(getCurrentDateTime());
    url="${pageContext.request.contextPath}/../film/save.do";
}

//打开修改
function openUserModifyDialog(){
	 document.getElementById("u_password").readOnly=true;
    var selectedRows=$("#u_dg").datagrid("getSelections");
    if(selectedRows.length!=1){
        $.messager.alert("系统提示","请选择一条要编辑的数据！");
        return;
    }
    var row=selectedRows[0];
    $("#u_dlg").dialog("open").dialog("setTitle","编辑用户信息");
    $("#u_fm").form("load",row);
    url="${pageContext.request.contextPath}/../user/save.do?userId="+row.userId;
}
function openCinemaModifyDialog(){
	document.getElementById("c_password").readOnly=true;
    var selectedRows=$("#c_dg").datagrid("getSelections");
    if(selectedRows.length!=1){
        $.messager.alert("系统提示","请选择一条要编辑的数据！");
        return;
    }
    var row=selectedRows[0];
    $("#c_dlg").dialog("open").dialog("setTitle","编辑影院信息");
    $("#c_fm").form("load",row);
    url="${pageContext.request.contextPath}/../cinema/save.do?cinemaId="+row.cinemaId;
}
function openFilmModifyDialog(){
    var selectedRows=$("#f_dg").datagrid("getSelections");
    if(selectedRows.length!=1){
        $.messager.alert("系统提示","请选择一条要编辑的数据！");
        return;
    }
    var row=selectedRows[0];
    $("#f_dlg").dialog("open").dialog("setTitle","编辑影片信息");
    $("#f_fm").form("load",row);
    url="${pageContext.request.contextPath}/../film/save.do?filmId="+row.filmId;
}

//保存
function saveUser(){
    $("#u_fm").form("submit",{
       url:url,
       onSubmit:function(){
           return $(this).form("validate");
       },
       success:function(result){
           var result=eval('('+result+')');
           if(result.success){
               $.messager.alert("系统提示","保存成功！");
               resetValue();
               $("#u_dlg").dialog("close");
               $("#u_dg").datagrid("reload");
           }else{
               $.messager.alert("系统提示","保存失败！");
               return;
           }
       }
    });
}
function saveCinema(){
    $("#c_fm").form("submit",{
       url:url,
       onSubmit:function(){
           return $(this).form("validate");
       },
       success:function(result){
           var result=eval('('+result+')');
           if(result.success){
               $.messager.alert("系统提示","保存成功！");
               resetValue();
               $("#c_dlg").dialog("close");
               $("#c_dg").datagrid("reload");
           }else{
               $.messager.alert("系统提示","保存失败！");
               return;
           }
       }
    });
}
function saveFilm(){
    $("#f_fm").form("submit",{
       url:url,
       onSubmit:function(){
           return $(this).form("validate");
       },
       success:function(result){
           var result=eval('('+result+')');
           if(result.success){
               $.messager.alert("系统提示","保存成功！");
               resetValue();
               $("#f_dlg").dialog("close");
               $("#f_dg").datagrid("reload");
           }else{
               $.messager.alert("系统提示","保存失败！");
               return;
           }
       }
    });
}

//删除
function deleteUser(){
    var selectedRows=$("#u_dg").datagrid("getSelections");
    if(selectedRows.length==0){
        $.messager.alert("系统提示","请选择要删除的数据！");
        return;
    }
    var strIds=[];
    for(var i=0;i<selectedRows.length;i++){
        strIds.push(selectedRows[i].userId);
    }
    var ids=strIds.join(",");
    $.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
       if(r){
           $.post("${pageContext.request.contextPath}/../user/delete.do",{ids:ids},function(result){
               if(result.success){
                    $.messager.alert("系统提示","数据已成功删除！");
                    $("#u_dg").datagrid("reload");
               }else{
                   $.messager.alert("系统提示","数据删除失败，请联系系统管理员！");
               }
           },"json");
       } 
    });
} 
function deleteCinema(){
    var selectedRows=$("#c_dg").datagrid("getSelections");
    if(selectedRows.length==0){
        $.messager.alert("系统提示","请选择要删除的数据！");
        return;
    }
    var strIds=[];
    for(var i=0;i<selectedRows.length;i++){
        strIds.push(selectedRows[i].cinemaId);
    }
    var ids=strIds.join(",");
    $.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
       if(r){
           $.post("${pageContext.request.contextPath}/../cinema/delete.do",{ids:ids},function(result){
               if(result.success){
                    $.messager.alert("系统提示","数据已成功删除！");
                    $("#c_dg").datagrid("reload");
               }else{
                   $.messager.alert("系统提示","数据删除失败，请联系系统管理员！");
               }
           },"json");
       } 
    });
} 
function deleteFilm(){
    var selectedRows=$("#f_dg").datagrid("getSelections");
    if(selectedRows.length==0){
        $.messager.alert("系统提示","请选择要删除的数据！");
        return;
    }
    var strIds=[];
    for(var i=0;i<selectedRows.length;i++){
        strIds.push(selectedRows[i].filmId);
    }
    var ids=strIds.join(",");
    $.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
       if(r){
           $.post("${pageContext.request.contextPath}/../film/delete.do",{ids:ids},function(result){
               if(result.success){
                    $.messager.alert("系统提示","数据已成功删除！");
                    $("#f_dg").datagrid("reload");
               }else{
                   $.messager.alert("系统提示","数据删除失败，请联系系统管理员！");
               }
           },"json");
       } 
    });
} 

//关闭
function closeUserDialog(){
    $("#u_dlg").dialog("close");
    resetValue();
}
function closeCinemaDialog(){
    $("#c_dlg").dialog("close");
    resetValue();
}
function closeFilmDialog(){
    $("#f_dlg").dialog("close");
    resetValue();
}


//置空
function resetValue(){
	$("#u_phone").val("");
    $("#c_phone").val("");
    $("#u_password").val("");
    $("#c_password").val("");
    $("#u_name").val("");
    $("#c_name").val("");
    $("#u_registrationTime").val("");
    $("#c_registrationTime").val("");
    $("#f_registrationTime").val("");
    $("#authority").val("");
    $("#c_description").val("");
    $("#f_description").val("");
    $("#location").val("");
    $("#filmName").val("");
    $("#director").val("");
    $("#actor").val("");
    $("#cover").val("");
    $("#type").val("");
    $("#startTime").datebox('setValue', '');	;
    $("#endTime").datebox('setValue', '');;
    $("#duration").val("");
    $("#likeCount").val("");
//    $("#type").combobox("setValue","");
}

//退出
function logout(){
	$.messager.confirm("系统提示","您确定要退出系统吗？",function(r){
	if(r){
		window.location.href='${pageContext.request.contextPath}/../user/logout.do';
		delCookie();
	} 	
});
}

//清除cookie
function delCookie(){
   var date = new Date();
   date.setTime(date.getTime() - 10000);
   document.cookie = "isLogin" + "=null; expires=" + date.toGMTString();
   document.cookie = "loginType" + "=null; expires=" + date.toGMTString();
}