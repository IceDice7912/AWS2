var index;
var title;
var author;
var price;
var isbn;
var category;
var imgurl;
var detail;
var bookarry=[];

var setCookie = function ci(value, exp){	
	var date = new Date();
	date.setTime(date.getTime() + exp*24*60*60*1000);
	document.cookie = "isbn" + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
};


$(document).ready(function(){
	let topicBookList="<ul>";
	
	$.post("topicBook",
			  null,
			  function(data, status){	
		
			  	data=JSON.parse(data);
			  	data.forEach(function(item,index){		  		
			  		topicBookList +="<li><a href='#'  onclick=setCookie("+item.isbn+","+1	+")+window.open('html/test/CookieTest3.html')><img src='"+item.imgurl+"' ><em>"+item.isbn+"</em></a></li>";
			  	});
			  	topicBookList +="</ul>";
			  	$("#topicBook").html(topicBookList);
			  }
			  	
		);//end post()
});