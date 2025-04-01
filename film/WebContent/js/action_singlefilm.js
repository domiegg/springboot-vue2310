var likeStr = null;
var userId = null;
if(loginType=="user"){
	getUserId(phone);
}

//获取用户userId
function getUserId(phone){
	$.ajax({
		url:"${pageContext.request.contextPath}/../user/getUserByPhone.do?phone="+phone,  
		async:false,
		type:"POST",
		datatype:'json',
		success:function(udata){
			var user = udata.split(":");
			var temp = user[11].split("}");	
			userId = temp[0];
	  	}
	});
}

//判断是否关注影片
$.post("${pageContext.request.contextPath}/../likeFilm/checkFind.do?filmId="+filmId+"&userId="+userId,
  function(result){
      if(result.success){
          likeStr = "取消关注";
      }else{
    	  likeStr = "关注影片";
      }
  },"json");

//根据影片id加载排片表
$.ajax({
	url:"${pageContext.request.contextPath}/../filmArrangement/findByFilmId.do?filmId="+filmId,
	type:"POST",
	datatype:'json',
	success:function(data){
			 if(loginType=="user"){
				 $("#trcz").append('<th width=50px;>操作</th>');
			 }
			//遍历返回的json数组
		     $.each(eval('('+ data +')').list, function(index,item){	
	            var tr=$('<tr></tr>');
	            tr.append('<td>' + item.cinema.name + '</td>');
	            tr.append('<td>' + item.filmStartTime + '</td>');
	            tr.append('<td>' + item.filmEndTime + '</td>');
	            tr.append('<td>' + item.language + '</td>');
	            tr.append('<td>' + item.filmHall + '</td>');
	            tr.append('<td>' + item.seatCount + '</td>');
	            tr.append('<td>' + item.price + '</td>');
	            if(loginType=="user"){
	            	tr.append('<td><a class="visit" href="javascript:buyTicket(' + item.film.filmId + ',' + item.cinema.cinemaId + ',' + item.arrangementId + ')" >购买</a></td>');
	            }
	            	var td= document.createElement('td');
	            $("#aList").append(tr);
			}); 
		}
});

//根据影片Id加载影片信息
$.ajax({
	url:"${pageContext.request.contextPath}/../film/findById.do?filmId="+filmId,  
	type:"POST",
	datatype:'json',
	success:function(fidata){
	  	//遍历返回的json数组
	     $.each(eval('('+ fidata +')').filist, function(index,item){	
	    	 var divn=$('<div class="panel panel-default news"></div>');
	    	 var divt=$('<div class="newstitle"></div>');
	    	 divt.append('<span>'+item.filmName+'</span>');	   
	    	 if(loginType=="user"){
	    		 divt.append('<input type="button" onclick="javascript:like();" class="abtn btn btn-primary btn-sm" id="like" value="'+ likeStr +'"/>');
	    	 }
	    	 divt.append('<h5>关注量:' +item.likeCount+'</h5>');
	         divn.append(divt);
	         var divi=$('<div class="newsimg"></div>');
	         divi.append('<img src="'+ item.cover +'"></img>');
	         divn.append(divi);
	         var table = $('<table class="table table-hover flist" fit="true"></table>');
	         var tr1 = $('<tr></tr>');
	         var tr2 = $('<tr></tr>');
	         var tr3 = $('<tr></tr>');
	         tr1.append('<th>影片名称</th><td>'+item.filmName+'</td><th>类型</th><td>'+item.type+'</td>');
	         tr2.append('<th>导演</th><td>'+item.director+'</td><th>主要演员</th><td>'+item.actor+'</td>');
	         tr3.append('<th>上映时间</th><td>'+item.startTime+'</td><th>下映时间</th><td>'+item.endTime+'</td>');
	         table.append(tr1);
	         table.append(tr2);
	         table.append(tr3);
	         divn.append(table);
	         divn.append('<div class="description">'+ item.description+'</div>');
	         $("#info").append(divn);

		}); 
  	}
});

//根据影片id加载用户影评
$.ajax({
	url:"${pageContext.request.contextPath}/../filmArticle/filmList.do?filmId="+filmId,  
	type:"POST",
	datatype:'json',
	success:function(fadata){
		//遍历返回的json数组
	     $.each(eval('('+ fadata +')').falist, function(index,item){	
	    	 var divct=$('<div class="panel panel-default article"></div>');
	    	 var divh=$('<div class="panel-heading"></div>');
	    	 divh.append('<h3 class="panel-title">  ' + item.title + '</h3>');
	    	 divh.append('<h4 class="time"> [作者] '+ item.user.name + '</h4>');
	    	 var divbd=$('<div class="articlecontent">' + item.content +'</div>');
	    	 divct.append(divh);
	    	 divct.append(divbd);
	         $("#articles").append(divct);
		}); 
 	}
});


//根据影片id加载影片评论
$.ajax({
	url:"${pageContext.request.contextPath}/../filmComment/filmList.do?filmId="+filmId,  
	type:"POST",
	datatype:'json',
	success:function(fcdata){
		if(loginType=="user"){
			var form = $('<form id="addCommentform" method="post" action=""></form>');
			form.append('<span>添加评论</span>');
			form.append('<textarea class="form-control" id="content" name="content" rows="15"></textarea>');
			form.append('<button type="button" onclick="javascript:addComment()" class="ac-btn btn btn-primary btn-sm" >提交</button>');
			form.append('<button type="button" onclick="javascript:setNull()" class="ac-btn btn btn-default btn-sm" >取消</button>');
			$("#addcomment").append(form);
		}
	  	//遍历返回的json数组
	     $.each(eval('('+ fcdata +')').fclist, function(index,item){	
	    	 var divct=$('<div class="panel panel-default article"></div>');
	    	 var divh=$('<div class="panel-heading"></div>');
	    	 divh.append('<h3 class="panel-title"> [用户] ' + item.user.name + '</h3>');
	    	 divh.append('<h4 class="time">'+ item.publishTime + '</h4>');
	    	 var divbd=$('<div class="commentcontent">' + item.content +'</div>');
	    	 divct.append(divh);
	    	 divct.append(divbd);
	         $("#comments").append(divct);
		}); 
  	}
});


//清空评价
function setNull(){
	 $("#content").val("");
}

//添加影片评论
function addComment(){
	var content = $("#content").val();
	
	if(content=="" || content==null){
		alert("评论不能为空！");
		return ;
	}
	else{
		document.forms['addCommentform'].action = "${pageContext.request.contextPath}/../filmComment/add.do?userId="+userId+"&filmId="+filmId;
		document.forms['addCommentform'].submit();
	}
	alert("成功发表影片评论！");
	setNull();
}

//购买影票
function buyTicket(filmId,cinemaId,arrangementId){
	if(confirm("确认购票吗？")){
	 $.post("${pageContext.request.contextPath}/../boughtFilm/buyTicket.do?userId="+userId+"&filmId="+filmId+"&cinemaId="+cinemaId+"&arrangementId="+arrangementId,
			  function(result){
			      if(result.success){
			          alert("购票成功！");
			          window.location.reload();
			      }else{
			          alert("购票失败，已售罄！");
			      }
			  },"json");
	}
}

//关注、取关
function like(){
	if(likeStr=="关注影片"){
		$.post("${pageContext.request.contextPath}/../likeFilm/add.do?userId="+userId+"&filmId="+filmId,
				function(result){
			      if(result.success){
			          alert("关注成功！");
			          likeStr = "取消关注";
			          window.location.reload();
			      }else{
			          alert("关注失败！");
			      }
			  },"json");		
	}
	else{
		if(confirm("确认取消关注吗？")){
			$.post("${pageContext.request.contextPath}/../likeFilm/delete.do?userId="+userId+"&filmId="+filmId,
					function(result){
				      if(result.success){
				          alert("取消关注成功！");
				          likeStr = "关注影片";
				          window.location.reload();
				      }else{
				          alert("取消关注失败！");
				      }
				  },"json");	
		}
	}
	
}