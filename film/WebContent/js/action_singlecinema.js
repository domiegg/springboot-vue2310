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


//根据影院id加载排片表
$.ajax({
	url:"${pageContext.request.contextPath}/../filmArrangement/list.do?cinemaId="+cinemaId, 
	type:"POST",
	datatype:'json',
	success:function(data){	
			 if(loginType=="user"){
	    		 $("#trcz").append('<th width=50px;>操作</th>');
	    	 }
			 //遍历返回的json数组
		     $.each(eval('('+ data +')').list, function(index,item){
		    	
	            var tr=$('<tr></tr>');
	            tr.append('<td>' + item.film.filmName + '</td>');
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

//根据影院Id加载影院资讯

$.ajax({
	url:"${pageContext.request.contextPath}/../filmNews/list.do?cinemaId="+cinemaId,  
	type:"POST",
	datatype:'json',
	success:function(ndata){
	  	//遍历返回的json数组
	     $.each(eval('('+ ndata +')').nlist, function(index,item){	
	    	 var divn=$('<div class="panel panel-default news"></div>');
	    	 var divt=$('<div class="newstitle"></div>');
	    	 divt.append('<span>'+item.newsTitle+'</span>');
	    	 divt.append('<h5>' +item.publishTime+'</h5>');
	         divn.append(divt);
	         var divi=$('<div class="newsimg"></div>');
	         divi.append('<img src="'+ item.picture +'"></img>');
	         divn.append(divi);
	         divn.append('<div class="newscontent">'+ item.newsContent+'</div>');
	         $("#news").append(divn);
		}); 
  	}
});


//根据影院Id加载影院评论

$.ajax({
	url:"${pageContext.request.contextPath}/../cinemaComment/list.do?cinemaId="+cinemaId,  
	type:"POST",
	datatype:'json',
	success:function(ctdata){
		if(loginType=="user"){
			var form = $('<form id="addCommentform" method="post" action=""></form>');
			form.append('<span>添加评价</span>');
			form.append('<textarea class="form-control" id="content" name="content" rows="15"></textarea>');
			form.append('<button type="button" onclick="javascript:addComment()" class="ac-btn btn btn-primary btn-sm" >提交</button>');
			form.append('<button type="button" onclick="javascript:setNull()" class="ac-btn btn btn-default btn-sm" >取消</button>');
			$("#addcomment").append(form);
		}
		
	  	//遍历返回的json数组
	     $.each(eval('('+ ctdata +')').ctlist, function(index,item){	
	    	 var divct=$('<div class="panel panel-default comment"></div>');
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

//添加评价
function addComment(){
	var content = $("#content").val();
	if(content=="" || content==null){
		alert("评价不能为空！");
		return ;
	}
	else{
		document.forms['addCommentform'].action = "${pageContext.request.contextPath}/../cinemaComment/add.do?cinemaId="+cinemaId+"&userId="+userId;
		document.forms['addCommentform'].submit();
	}
	alert("成功发表影院评价！");
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

