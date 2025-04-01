var nflag = true;
var aflag = true;
var cflag = true;
var flflag = true;

function searchNews(){
	$.ajax({
		url:"${pageContext.request.contextPath}/../filmNews/list.do?cinemaId="+cinemaId,  
		type:"POST",
		datatype:'json',
		success:function(ndata){
			if(nflag){
			  	//遍历返回的json数组
			     $.each(eval('('+ ndata +')').nlist, function(index,item){	
			    	var div=$('<div class="panel panel-default news"></div>');
			    	var div1=$('<div class="panel-heading"></div>');
			    	div1.append('<h3 class="panel-title">' + item.newsTitle + '</h3>');
			    	div1.append('<a href="javascript:deleteNews(' + item.newsId + ')" class="del">删除</a>');
			    	var div2=$('<div class="panel-body"></div>');
			    	var divi=$('<div class="newsimg"></div>');
			        divi.append('<img src="'+ item.picture +'"></img>');
			        div2.append(divi);
			    	div2.append(item.newsContent);
		           	div.append(div1);
		           	div.append(div2);
		            $('#news').append(div);
				}); 
			     nflag = false;
			}
	  	}
	});
}

function searchArrangement(page){
	$.ajax({
		url:"${pageContext.request.contextPath}/../filmArrangement/clist.do?pageNow="+page+"&cinemaId="+cinemaId,  
		type:"POST",
		datatype:'json',
		success:function(data){
//			if(aflag){
				$("#arrangement").html("");
				$("#page").html("");
			  	//遍历返回的json数组
			     $.each(eval('('+ data +')').list, function(index,item){	
		            var tr=$('<tr></tr>');
		            tr.append('<td>' + item.film.filmName + '</td>');
		            tr.append('<td>' + item.language + '</td>');
		            tr.append('<td>' + item.filmStartTime + '</td>');
		            tr.append('<td>' + item.filmEndTime + '</td>');
		            tr.append('<td>' + item.filmHall + '</td>');
		            tr.append('<td>' + item.seatCount + '</td>');
		            tr.append('<td>' + item.price + '</td>');
		            tr.append('<td><a href="javascript:deleteArrangement(' + item.arrangementId + ')" class="del">删除</a></td>');
		            var td= document.createElement('td');
		            td.setAttribute("text-align","center");
		            $("#arrangement").append(tr);
				}); 
			 	var total=eval('('+ data +')').pageUtil.totalPageCount;
				var ul=$('<ul class="pagination"></ul>');
				if(page==1){
			  		ul.append('<li><a style="opacity: 0.2" disable="true" >首页</a></li>');
		  			ul.append('<li><a style="opacity: 0.2" disable="true" >'+'&laquo;'+'</a></li>');
				}else{
		  			ul.append('<li><a href="javascript:searchArrangement('+1+')">首页</a></li>');
		  			ul.append('<li><a href="javascript:searchArrangement('+(page-1)+')">'+'&laquo;'+'</a></li>');
		  		}

		  		for(var c=1;c<=total;c++){
		  			ul.append('<li><a href="javascript:searchArrangement('+c+')">'+c+'</a></li>');
		  		}

		  		if(page==total){
			  		ul.append('<li><a style="opacity: 0.2" disable="true" >&raquo;</a></li>');
			  		ul.append('<li><a style="opacity: 0.2" disable="true" >末页</a></li>');	
		  		}else{
			  		ul.append('<li><a href="javascript:searchArrangement('+(page+1)+')">&raquo;</a></li>');
			  		ul.append('<li><a href="javascript:searchArrangement('+total+')">末页</a></li>');		  		
		  		}
		  		$("#page").append(ul);
//			     aflag = false;
			}
//	  	}
	});
}


function searchComment(){
	$.ajax({
		url:"${pageContext.request.contextPath}/../cinemaComment/list.do?cinemaId="+cinemaId,  
		type:"POST",
		datatype:'json',
		success:function(ctdata){
		  	//遍历返回的json数组
		  	if(cflag){
			     $.each(eval('('+ ctdata +')').ctlist, function(index,item){	
			    	var div=$('<div class="panel panel-default news"></div>');
			    	var div1=$('<div class="panel-heading"></div>');
			    	div1.append('<h3 class="panel-title"> [用户] ' + item.user.name + '</h3>');
			    	div1.append('<h4 class="time">'+ item.publishTime + '</h4>');
			    	var div2=$('<div class="panel-body"></div>');
			    	div2.append(item.content);
		           	div.append(div1);
		           	div.append(div2);
		           	$('#comment').append(div);
				}); 
			     cflag = false;
		  	}
	  	}
	});
}

function openAddArrangement(){
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

function getSearchNews(){
	var search = $("#search").val();
	$.ajax({
		url:"${pageContext.request.contextPath}/../filmNews/searchList.do?cinemaId="+cinemaId+"&search="+search,
		type:"POST",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
		datatype:'json',
		success: function (sndata) {	
			$('#news').html("");
			$.each(eval('('+ sndata +')').snlist, function(index,item){	
		    	var div=$('<div class="panel panel-default news"></div>');
		    	var div1=$('<div class="panel-heading"></div>');
		    	div1.append('<h3 class="panel-title">' + item.newsTitle + '</h3>');
		    	div1.append('<a href="javascript:deleteNews(' + item.newsId + ')" class="del">删除</a>');
		    	var div2=$('<div class="panel-body"></div>');
		    	var divi=$('<div class="newsimg"></div>');
		        divi.append('<img src="'+ item.picture +'"></img>');
		        div2.append(divi);
		    	div2.append(item.newsContent);
	           	div.append(div1);
	           	div.append(div2);
	            $('#news').append(div);
			}); 
		  }
	});
}