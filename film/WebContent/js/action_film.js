//加载影院模块
var fpflag = true;
$.ajax({
	url:"${pageContext.request.contextPath}/../film/indexList.do",
	type:"POST",
	datatype:'json',
	success:function(fdata){
			//遍历返回的json数组
		     $.each(eval('('+ fdata +')').flist, function(index,item){	
	            var divh = $('<div class="fpanel panel panel-info "></div>');
	            var divph = $('<div class="panel-heading"></div>');
	            var hpt = $('<h4 class="panel-title"></h4>');
	            var a = $('<a data-toggle="collapse" data-parent="#accordion" href="#film'+item.filmId+'">' + item.filmName +'<a href="javascript:visitFilm(' + item.filmId + ')" class="cvisit">查看</a></a>');
	            if(fpflag){
	            	var divc = $('<div id="film' + item.filmId + '" class="panel-collapse collapse in fpc"></div>');
	            	fpflag = false;
	            }else{
	            	var divc = $('<div id="film' + item.filmId + '" class="panel-collapse collapse fpc"></div>');
	            }
	            var divbd = $('<div class="panel-body" id="cbd'+ item.filmId +'"></div>');
	            var info = $('<script>openInfo('+ item.filmId +');</script>');
	            var comments = $('<script>openComments('+ item.filmId +');</script>');
	            hpt.append(a);
	            divph.append(hpt);
	            divbd.append(info);
	            divbd.append(comments);
	            divc.append(divbd);
	            divh.append(divph);
	            divh.append(divc);
	            $("#accordion").append(divh);
			}); 
		}
});

//根据影院Id加载影片信息
function openInfo(filmId){
	$.ajax({
		url:"${pageContext.request.contextPath}/../film/findById.do?filmId="+filmId,  
		type:"POST",
		datatype:'json',
		success:function(fidata){
		  	//遍历返回的json数组
		     $.each(eval('('+ fidata +')').filist, function(index,item){	
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
	            var str = "cbd" + filmId;
	            $("#"+str).append(table);

			}); 
	  	}
	});
}

//根据影院Id加载影片评论
function openComments(filmId){
	$.ajax({
		url:"${pageContext.request.contextPath}/../filmComment/filmList.do?filmId="+filmId,  
		type:"POST",
		datatype:'json',
		success:function(fcdata){
			var str = "cbd" + filmId;
	         $("#"+str).append('<hr>');
		  	//遍历返回的json数组
		     $.each(eval('('+ fcdata +')').fclist, function(index,item){	
		    	 var divct=$('<div class="panel panel-default article"></div>');
		    	 var divh=$('<div class="panel-heading"></div>');
		    	 divh.append('<h3 class="panel-title"> [用户] ' + item.user.name + '</h3>');
		    	 divh.append('<h4 class="time">'+ item.publishTime + '</h4>');
		    	 var divbd=$('<div class="articlecontent">' + item.content +'</div>');
		    	 divct.append(divh);
		    	 divct.append(divbd);
		         $("#"+str).append(divct);
			}); 
	  	}
	});
}

//加载按影院排序排片表
$.ajax({
	url:"${pageContext.request.contextPath}/../filmArrangement/findByCinemaOrder.do?pageNow=1",
	type:"POST",
	datatype:'json',
	success:function(codata){
			$("#corderList").html("");
			$("#cpage").html("");
			//遍历返回的json数组
		     $.each(eval('('+ codata +')').colist, function(index,item){	
	            var tr=$('<tr></tr>');
	            tr.append('<td>' + item.cinema.name + '</td>');
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
	            $("#corderList").append(tr);
			}); 
		     var page=1;
		     var total=eval('('+ codata +')').pageUtil.totalPageCount;
				var ul=$('<ul class="pagination"></ul>');
		  		
				if(page==1){
			  		ul.append('<li><a style="opacity: 0.2" disable="true" >首页</a></li>');
		  			ul.append('<li><a style="opacity: 0.2" disable="true" >'+'&laquo;'+'</a></li>');
				}else{
		  			ul.append('<li><a href="javascript:searchCOrder('+1+')">首页</a></li>');
		  			ul.append('<li><a href="javascript:searchCOrder('+(page-1)+')">'+'&laquo;'+'</a></li>');
		  		}

		  		for(var i=1;i<=total;i++){
		  			ul.append('<li><a href="javascript:searchCOrder('+i+')">'+i+'</a></li>');
		  		}

		  		if(page==total){
			  		ul.append('<li><a style="opacity: 0.2" disable="true" >&raquo;</a></li>');
			  		ul.append('<li><a style="opacity: 0.2" disable="true" >末页</a></li>');	
		  		}else{
			  		ul.append('<li><a href="javascript:searchCOrder('+(page+1)+')">&raquo;</a></li>');
			  		ul.append('<li><a href="javascript:searchCOrder('+total+')">末页</a></li>');		  		
		  		}
		  		$("#cpage").append(ul);
		}
});

function searchCOrder(page){
	$.ajax({
		url:"${pageContext.request.contextPath}/../filmArrangement/findByCinemaOrder.do?pageNow="+page,
		type:"POST",
		datatype:'json',
		success:function(codata){
				$("#corderList").html("");
				$("#cpage").html("");
				//遍历返回的json数组
			     $.each(eval('('+ codata +')').colist, function(index,item){	
		            var tr=$('<tr></tr>');
		            tr.append('<td>' + item.cinema.name + '</td>');
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
		            $("#corderList").append(tr);
				}); 
			     
			     var total=eval('('+ codata +')').pageUtil.totalPageCount;
					var ul=$('<ul class="pagination"></ul>');
					if(page==1){
				  		ul.append('<li><a style="opacity: 0.2" disable="true" >首页</a></li>');
			  			ul.append('<li><a style="opacity: 0.2" disable="true" >'+'&laquo;'+'</a></li>');
					}else{
			  			ul.append('<li><a href="javascript:searchCOrder('+1+')">首页</a></li>');
			  			ul.append('<li><a href="javascript:searchCOrder('+(page-1)+')">'+'&laquo;'+'</a></li>');
			  		}

			  		for(var i=1;i<=total;i++){
			  			ul.append('<li><a href="javascript:searchCOrder('+i+')">'+i+'</a></li>');
			  		}

			  		if(page==total){
				  		ul.append('<li><a style="opacity: 0.2" disable="true" >&raquo;</a></li>');
				  		ul.append('<li><a style="opacity: 0.2" disable="true" >末页</a></li>');	
			  		}else{
				  		ul.append('<li><a href="javascript:searchCOrder('+(page+1)+')">&raquo;</a></li>');
				  		ul.append('<li><a href="javascript:searchCOrder('+total+')">末页</a></li>');		  		
			  		}
			  		$("#cpage").append(ul);
			}
	});
}
//加载按影片排序排片表
$.ajax({
	url:"${pageContext.request.contextPath}/../filmArrangement/findByFilmOrder.do?pageNow=1",
	type:"POST",
	datatype:'json',
	success:function(fodata){
			$("#forderList").html("");
			$("#fpage").html("");
			//遍历返回的json数组
		     $.each(eval('('+ fodata +')').folist, function(index,item){	
	            var tr=$('<tr></tr>');
	            tr.append('<td>' + item.film.filmName + '</td>');
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
	            $("#forderList").append(tr);
			}); 
		     var page=1;
		     var total=eval('('+ fodata +')').pageUtil.totalPageCount;
				var ul=$('<ul class="pagination"></ul>');
				if(page==1){
			  		ul.append('<li><a style="opacity: 0.2" disable="true" >首页</a></li>');
		  			ul.append('<li><a style="opacity: 0.2" disable="true" >'+'&laquo;'+'</a></li>');
				}else{
		  			ul.append('<li><a href="javascript:searchFOrder('+1+')">首页</a></li>');
		  			ul.append('<li><a href="javascript:searchFOrder('+(page-1)+')">'+'&laquo;'+'</a></li>');
		  		}

		  		for(var i=1;i<=total;i++){
		  			ul.append('<li><a href="javascript:searchFOrder('+i+')">'+i+'</a></li>');
		  		}

		  		if(page==total){
			  		ul.append('<li><a style="opacity: 0.2" disable="true" >&raquo;</a></li>');
			  		ul.append('<li><a style="opacity: 0.2" disable="true" >末页</a></li>');	
		  		}else{
			  		ul.append('<li><a href="javascript:searchFOrder('+(page+1)+')">&raquo;</a></li>');
			  		ul.append('<li><a href="javascript:searchFOrder('+total+')">末页</a></li>');		  		
		  		}
		  		$("#fpage").append(ul);
		}
});

function searchFOrder(page){
	$.ajax({
		url:"${pageContext.request.contextPath}/../filmArrangement/findByFilmOrder.do?pageNow="+page,
		type:"POST",
		datatype:'json',
		success:function(fodata){
				$("#forderList").html("");
				$("#fpage").html("");
				//遍历返回的json数组
			     $.each(eval('('+ fodata +')').folist, function(index,item){	
		            var tr=$('<tr></tr>');
		            tr.append('<td>' + item.film.filmName + '</td>');
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
		            $("#forderList").append(tr);
				}); 
			     
			     var total=eval('('+ fodata +')').pageUtil.totalPageCount;
					var ul=$('<ul class="pagination"></ul>');
					if(page==1){
				  		ul.append('<li><a style="opacity: 0.2" disable="true" >首页</a></li>');
			  			ul.append('<li><a style="opacity: 0.2" disable="true" >'+'&laquo;'+'</a></li>');
					}else{
			  			ul.append('<li><a href="javascript:searchFOrder('+1+')">首页</a></li>');
			  			ul.append('<li><a href="javascript:searchFOrder('+(page-1)+')">'+'&laquo;'+'</a></li>');
			  		}

			  		for(var i=1;i<=total;i++){
			  			ul.append('<li><a href="javascript:searchFOrder('+i+')">'+i+'</a></li>');
			  		}

			  		if(page==total){
				  		ul.append('<li><a style="opacity: 0.2" disable="true" >&raquo;</a></li>');
				  		ul.append('<li><a style="opacity: 0.2" disable="true" >末页</a></li>');	
			  		}else{
				  		ul.append('<li><a href="javascript:searchFOrder('+(page+1)+')">&raquo;</a></li>');
				  		ul.append('<li><a href="javascript:searchFOrder('+total+')">末页</a></li>');		  		
			  		}
			  		$("#fpage").append(ul);
			}
	});
}
//加载推荐影片列表
$.ajax({
	url:"${pageContext.request.contextPath}/../film/likeList.do?",
	type:"POST",
	datatype:'json',
	success:function(lfdata){
			//遍历返回的json数组
		     $.each(eval('('+ lfdata +')').lflist, function(index,item){	
	            var tr=$('<tr></tr>');
	            tr.append('<td>' + item.filmName + '</td>');
	            tr.append('<td>' + item.director + '</td>');
	            tr.append('<td>' + item.actor + '</td>');
	            tr.append('<td><a href="javascript:visitFilm(' + item.filmId + ')" class="visit">查看</a></td>');
	            var td= document.createElement('td');
	            $("#flist").append(tr);
			}); 
		}
});

//加载影院列表
$.ajax({
	url:"${pageContext.request.contextPath}/../cinema/randList.do?",
	type:"POST",
	datatype:'json',
	success:function(rcdata){
			//遍历返回的json数组
		     $.each(eval('('+ rcdata +')').rclist, function(index,item){	
	            var tr=$('<tr></tr>');
	            tr.append('<td>' + item.name + '</td>');
	            tr.append('<td>' + item.location + '</td>');
	            tr.append('<td><a href="javascript:visitCinema(' + item.cinemaId + ')" class="visit">查看</a></td>');
	            var td= document.createElement('td');
	            $("#clist").append(tr);
			}); 
		}
});


function getSearchArrangement(){
	var search = $("#search").val();
	
	
}

//跳转单个影院界面
function visitCinema(cinemaId){
	window.location.href="${pageContext.request.contextPath}/../cinema/cinemaHome.do?cinemaId="+cinemaId;
}

//跳转单个影片界面
function visitFilm(filmId){
	window.location.href="${pageContext.request.contextPath}/../film/filmHome.do?filmId="+filmId;
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