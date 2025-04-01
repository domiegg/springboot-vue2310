//加载影院列表
$.ajax({
	url:"${pageContext.request.contextPath}/../cinema/lcinemaList.do?pageNow=1",
	type:"POST",
	datatype:'json',
	success:function(cdata){
		$("#cinemalist").html("");
		$("#page").html("");
			//遍历返回的json数组
		     $.each(eval('('+ cdata +')').clist, function(index,item){	
	            var tr=$('<tr></tr>');
	            tr.append('<td>' + item.cinemaId + '</td>');
	            tr.append('<td>' + item.name + '</td>');
	            tr.append('<td width=200px>' + item.description + '</td>');
	            tr.append('<td>' + item.location + '</td>');
	            tr.append('<td><a href="javascript:visitCinema(' + item.cinemaId + ')" class="visit">查看</a></td>');
	            var td= document.createElement('td');
	            $("#cinemalist").append(tr);
			}); 
		     var page=1;
		     var total=eval('('+ cdata +')').pageUtil.totalPageCount;
				var ul=$('<ul class="pagination"></ul>');

				if(page==1){
			  		ul.append('<li><a style="opacity: 0.2" disable="true" >首页</a></li>');
		  			ul.append('<li><a style="opacity: 0.2" disable="true" >'+'&laquo;'+'</a></li>');
				}else{
					ul.append('<li><a href="javascript:searchCinema('+1+')">首页</a></li>');
		  			ul.append('<li><a href="javascript:searchCinema('+(page-1)+')">'+'&laquo;'+'</a></li>');
				}

		  		for(var i=1;i<=total;i++){
		  			ul.append('<li><a href="javascript:searchCinema('+i+')">'+i+'</a></li>');
		  		}
		  		if(page==total){
			  		ul.append('<li><a style="opacity: 0.2" disable="true" >&raquo;</a></li>');
			  		ul.append('<li><a style="opacity: 0.2" disable="true" >末页</a></li>');	
		  		}else{
			  		ul.append('<li><a href="javascript:searchCinema('+(page+1)+')">&raquo;</a></li>');
			  		ul.append('<li><a href="javascript:searchCinema('+total+')">末页</a></li>');	
		  		}
				
		  		$("#page").append(ul);
		}
});
function searchCinema(page){
	$.ajax({
		url:"${pageContext.request.contextPath}/../cinema/lcinemaList.do?pageNow="+page,
		type:"POST",
		datatype:'json',
		success:function(cdata){
			$("#cinemalist").html("");
			$("#page").html("");
				//遍历返回的json数组
			     $.each(eval('('+ cdata +')').clist, function(index,item){	
		            var tr=$('<tr></tr>');
		            tr.append('<td>' + item.cinemaId + '</td>');
		            tr.append('<td>' + item.name + '</td>');
		            tr.append('<td width=200px>' + item.description + '</td>');
		            tr.append('<td>' + item.location + '</td>');
		            tr.append('<td><a href="javascript:visitCinema(' + item.cinemaId + ')" class="visit">查看</a></td>');
		            var td= document.createElement('td');
		            $("#cinemalist").append(tr);
				}); 
			     var total=eval('('+ cdata +')').pageUtil.totalPageCount;
					var ul=$('<ul class="pagination"></ul>');

					if(page==1){
				  		ul.append('<li><a style="opacity: 0.2" disable="true" >首页</a></li>');
			  			ul.append('<li><a style="opacity: 0.2" disable="true" >'+'&laquo;'+'</a></li>');
					}else{
						ul.append('<li><a href="javascript:searchCinema('+1+')">首页</a></li>');
			  			ul.append('<li><a href="javascript:searchCinema('+(page-1)+')">'+'&laquo;'+'</a></li>');
					}

			  		for(var i=1;i<=total;i++){
			  			ul.append('<li><a href="javascript:searchCinema('+i+')">'+i+'</a></li>');
			  		}
			  		if(page==total){
				  		ul.append('<li><a style="opacity: 0.2" disable="true" >&raquo;</a></li>');
				  		ul.append('<li><a style="opacity: 0.2" disable="true" >末页</a></li>');	
			  		}else{
				  		ul.append('<li><a href="javascript:searchCinema('+(page+1)+')">&raquo;</a></li>');
				  		ul.append('<li><a href="javascript:searchCinema('+total+')">末页</a></li>');	
			  		}

			  		$("#page").append(ul);

			}
	});
}
//加载影院模块
var opflag = true;
$.ajax({
	url:"${pageContext.request.contextPath}/../cinema/cinemaList.do?",
	type:"POST",
	datatype:'json',
	success:function(cdata){
			//遍历返回的json数组
		     $.each(eval('('+ cdata +')').clist, function(index,item){	
	            var divh = $('<div class="cpanel panel panel-info "></div>');
	            var divph = $('<div class="panel-heading"></div>');
	            var hpt = $('<h4 class="panel-title"></h4>');
	            var a = $('<a data-toggle="collapse" data-parent="#accordion" href="#cinema'+item.cinemaId+'">' + item.name +'</a>');
	            if(opflag){
	            	var divc = $('<div id="cinema' + item.cinemaId + '" class="panel-collapse collapse in"></div>');
	            	opflag = false;
	            }else{
	            	var divc = $('<div id="cinema' + item.cinemaId + '" class="panel-collapse collapse"></div>');
	            }
	            var divbd = $('<div class="panel-body" id="cbd'+ item.cinemaId +'"></div>');
	            var news = $('<script>openNews('+ item.cinemaId +');</script>');
	            var comments = $('<script>openComments('+ item.cinemaId +');</script>');
	            hpt.append(a);
	            divph.append(hpt);
	            divbd.append(news);
	            divbd.append(comments);
	            divc.append(divbd);
	            divh.append(divph);
	            divh.append(divc);
	            $("#accordion").append(divh);
			}); 
		}
});

//根据影院Id加载影院资讯
function openNews(cinemaId){
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
	            var str = "cbd" + cinemaId;
	            $("#"+str).append(divn);

			}); 
	  	}
	});
}

//根据影院Id加载影院评论
function openComments(cinemaId){
	$.ajax({
		url:"${pageContext.request.contextPath}/../cinemaComment/list.do?cinemaId="+cinemaId,  
		type:"POST",
		datatype:'json',
		success:function(ctdata){
			var str = "cbd" + cinemaId;
	         $("#"+str).append('<hr>');
		  	//遍历返回的json数组
		     $.each(eval('('+ ctdata +')').ctlist, function(index,item){	
		    	 var divct=$('<div class="panel panel-default comment"></div>');
		    	 var divh=$('<div class="panel-heading"></div>');
		    	 divh.append('<h3 class="panel-title"> [用户] ' + item.user.name + '</h3>');
		    	 divh.append('<h4 class="time">'+ item.publishTime + '</h4>');
		    	 var divbd=$('<div class="commentcontent">' + item.content +'</div>');
		    	 divct.append(divh);
		    	 divct.append(divbd);
		         $("#"+str).append(divct);
			}); 
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


//跳转单个影院界面
function visitCinema(cinemaId){
	window.location.href="${pageContext.request.contextPath}/../cinema/cinemaHome.do?cinemaId="+cinemaId;
}

//跳转单个影片界面
function visitFilm(filmId){
	window.location.href="${pageContext.request.contextPath}/../film/filmHome.do?filmId="+filmId;
}