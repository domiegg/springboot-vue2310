var oflag = true;
var lflag = true;
var aflag = true;
var flflag = true;

function searchOrder(page){
	$.ajax({
		url:"${pageContext.request.contextPath}/../boughtFilm/list.do?pageNow="+page+"&userId="+userId,  
		type:"POST",
		datatype:'json',
		success:function(data){
			$("#order").html("");
			$("#page").html("");
//			if(oflag){
				//遍历返回的json数组
			     $.each(eval('('+ data +')').list, function(index,item){	
		            var tr=$('<tr></tr>');
		            tr.append('<td>' + item.film.filmName + '</td>');
		            tr.append('<td>' + item.cinema.name + '</td>');
		            tr.append('<td>' + item.filmArrangement.price + '</td>');
		            tr.append('<td>' + item.purchasingDate + '</td>');
		            tr.append('<td><a href="javascript:deleteOrder(' + item.boughtId + ','+item.filmArrangement.arrangementId + ')" >退票</a></td>');
		            var td= document.createElement('td');
		            td.setAttribute("text-align","center");
		            $("#order").append(tr);
				}); 
			      	var total=eval('('+ data +')').pageUtil.totalPageCount;
					var ul=$('<ul class="pagination"></ul>');
					if(page==1){
				  		ul.append('<li><a style="opacity: 0.2" disable="true" >首页</a></li>');
			  			ul.append('<li><a style="opacity: 0.2" disable="true" >'+'&laquo;'+'</a></li>');
					}else{
			  			ul.append('<li><a href="javascript:searchOrder('+1+')">首页</a></li>');
			  			ul.append('<li><a href="javascript:searchOrder('+(page-1)+')">'+'&laquo;'+'</a></li>');
			  		}

			  		for(var i=1;i<=total;i++){
			  			ul.append('<li><a href="javascript:searchOrder('+i+')">'+i+'</a></li>');
			  		}

			  		if(page==total){
				  		ul.append('<li><a style="opacity: 0.2" disable="true" >&raquo;</a></li>');
				  		ul.append('<li><a style="opacity: 0.2" disable="true" >末页</a></li>');	
			  		}else{
				  		ul.append('<li><a href="javascript:searchOrder('+(page+1)+')">&raquo;</a></li>');
				  		ul.append('<li><a href="javascript:searchOrder('+total+')">末页</a></li>');		  		
			  		}
			  		$("#page").append(ul);
//			     oflag = false;
			}
//	  	}
	});
}


function searchLike(){
	$.ajax({
		url:"${pageContext.request.contextPath}/../likeFilm/list.do?userId="+userId,  
		type:"POST",
		datatype:'json',
		success:function(data){
			if(lflag){
				//遍历返回的json数组
			     $.each(eval('('+ data +')').list, function(index,item){	
			    	var div=$('<div class="panel panel-default attention"></div>');
			    	var div1=$('<div class="panel-body"></div>');
			    	div1.append('<img src="'+ item.film.cover +'" class="img-circle">');
					div1.append('<span>' + item.film.filmName + '</span>');
		           	var a=$('<a href="javascript:visitFilm(' + item.film.filmId + ')" class="visit"></a>');
		           	a.append('<div class="panel-footer visit">查看</div>');
		           	div.append(div1);
		           	div.append(a);
		            $('#like').append(div);
				}); 
			     lflag = false;
			}					  	
	  	}
	});
}

function searchArticle(){
	$.ajax({
		url:"${pageContext.request.contextPath}/../filmArticle/list.do?userId="+userId,  
		type:"POST",
		datatype:'json',
		success:function(data){
			if(aflag){
				//遍历返回的json数组
			     $.each(eval('('+ data +')').list, function(index,item){	
			    	var div=$('<div class="panel panel-default article"></div>');
			    	var div1=$('<div class="panel-heading"></div>');
			    	div1.append('<h3 class="panel-title" >' + item.title + '</h3>');
			    	div1.append('<a href="javascript:deleteArticle(' + item.articleId + ')" class="del">删除</a>');
			    	var div2=$('<div class="panel-body"></div>');
			    	div2.append(item.content);
		           	div.append(div1);
		           	div.append(div2);
		            $('#article').append(div);
				}); 
			     aflag = false;
			}
		  	
	  	}
	});
}

function openAddArticle(){
	$.ajax({
		url:"${pageContext.request.contextPath}/../film/indexList.do",
		type:"POST",
		datatype:'json',
		success: function (fdata) {			 
			 if(flflag){
				 var ddl = $("#filmNameCb");
				 ddl.append("<option value='-1'>--请选择--</option>");
				 $.each(eval('('+ fdata +')').flist, function(index,item){	
			         var opt = $("<option></option>").text(item.filmName).val(item.filmId);
			         ddl.append(opt);
				 }); 
				 flflag = false;
			 }  
		  }
	});
}

function getSearchArticle(){
	var search = $("#search").val();
	$.ajax({
		url:"${pageContext.request.contextPath}/../filmArticle/searchList.do?userId="+userId+"&search="+search,
		type:"POST",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
		datatype:'json',
		success: function (sadata) {	
			$('#article').html("");
			$.each(eval('('+ sadata +')').salist, function(index,item){	
		    	var div=$('<div class="panel panel-default article"></div>');
		    	var div1=$('<div class="panel-heading"></div>');
		    	div1.append('<h3 class="panel-title" >' + item.title + '</h3>');
		    	div1.append('<a href="javascript:deleteArticle(' + item.articleId + ')" class="del">删除</a>');
		    	var div2=$('<div class="panel-body"></div>');
		    	div2.append(item.content);
	           	div.append(div1);
	           	div.append(div2);
	            $('#article').append(div);
			}); 
		  }
	});
}

//跳转单个影片界面
function visitFilm(filmId){
	window.location.href="${pageContext.request.contextPath}/../film/filmHome.do?filmId="+filmId;
}