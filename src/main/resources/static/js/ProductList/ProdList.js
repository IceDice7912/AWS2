var page_num = 1;
function next_click(){	
	page_num++;
}
function prev_click(){	
	page_num--;
}
function setCookie(cname, cvalue, exdays) {
	  var d = new Date();
	  d.setTime(d.getTime() + (exdays*24*60*60*1000));
	  var expires = "expires="+ d.toUTCString();
	  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
	}
function setCookie_buy(value, exp){	
	var date = new Date();
	date.setTime(date.getTime() + exp*24*60*60*1000);
	document.cookie = "select_product_isbn_buy" + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
};
function setCookie_cart(value, exp){	
	var date = new Date();
	date.setTime(date.getTime() + exp*24*60*60*1000);
	document.cookie = "select_product_isbn_cart" + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
};
function getCookie(cname) {
	  var name = cname + "=";
	  var decodedCookie = decodeURIComponent(document.cookie);
	  var ca = decodedCookie.split(';');
	  for(var i = 0; i <ca.length; i++) {
	    var c = ca[i];
	    while (c.charAt(0) == ' ') {
	      c = c.substring(1);
	    }
	    if (c.indexOf(name) == 0) {
	      return c.substring(name.length, c.length);
	    }
	  }
	  return null;
}
function addCookie(id) {
	var loginch = getCookie('M_id');
	
	if(loginch == null){
		
('우선 로그인부터 해주세요.');
		$.removeCookie("select_product_isbn_cart", { path: '/' });// 장바구니 쿠키 삭제
		window.close();
	}
	
	

	  var items = getCookie('select_product_isbn_cart'); // 이미 저장된 값을 쿠키에서 가져오기
	  var maxItemNum = 10; // 최대 저장 가능한 아이템개수
	  var expire = 1; // 쿠키값을 저장할 기간
	  if (items) {
	    var itemArray = items.split(',');
	    if (itemArray.indexOf(id) != -1) {
	    }
	    else {
	      // 새로운 값 저장 및 최대 개수 유지하기
	      itemArray.unshift(id);
	      if (itemArray.length > maxItemNum ) itemArray.length = 5;
	      items = itemArray.join(',');
	      setCookie('select_product_isbn_cart', items, expire);
	    }
	  }
	  else {
	    // 신규 id값 저장하기
	    setCookie('select_product_isbn_cart', id, expire);
	  }
}



$(document).ready(function(){
	$.post("../../../productListAll.jes", {
		page_num : page_num
	},
		function(data, status){
			let json_msg = data;
			let js_msg = JSON.parse(json_msg);
			let my_array = js_msg.data;
			let sumMsg='';
			var i;
			for (i=0; i<my_array.length; i++){
				sumMsg += "<ul class='prod_li'> <li> <div class='prodSet'> <p class='prod_img'> <span class='prod_imgSet'> <a href='#'> <img src='" + my_array[i].book.imgurl 
				+ "border='0'> </a>	</span> </p> </div> <div class='prod_info'> <div class='prod_name'> <a>"
				+ my_array[i].book.title + "</a><br><br> </div> <div class='prod_pubGrp'> <span class='prod_auth'>" 
				+ my_array[i].book.author + "</a> 저 	</span> <em class='divi'>｜</em> <span class='prod_pub'>"
				+ my_array[i].book.publisher + "</span> <em class='divi'>｜</em> </div> <div class='prod_price'> <em class='price'>" 
				+ my_array[i].book.price + "</em> 원 </div> </div> <div class='prod_btn'> <a class='btn1' href='#' onclick=addCookie("+my_array[i].book.isbn+")+window.open('../../html/OrderPage/orderForm.html')>카트에 넣기</a><a class='btn2' href='#' onclick=setCookie_buy("
				+ my_array[i].book.isbn+","+1+")+window.open('../../html/ProductDetail/ProductDetail.html')>바로 구매하기</a></div></li></ul>";		
			}
			$('#prod_list').html(sumMsg);
				
		});	
	
	
	$("#nextBtn").click(function(){		
		if(page_num > 24){
			alert("목록이 끝났습니다.");
		}
		else{
			$('#paging').text(page_num);
			$.post("../../../productListAll.jes", {
				page_num : page_num
			},
				function(data, status){
					let json_msg = data;
					let js_msg = JSON.parse(json_msg);
					let my_array = js_msg.data;
					let sumMsg='';
					var i;
					for (i=0; i<my_array.length; i++){
						sumMsg += "<ul class='prod_li'> <li> <div class='prodSet'> <p class='prod_img'> <span class='prod_imgSet'> <a href='#'> <img src='" + my_array[i].book.imgurl 
						+ "border='0'> </a>	</span> </p> </div> <div class='prod_info'> <div class='prod_name'> <a>"
						+ my_array[i].book.title + "</a><br><br> </div> <div class='prod_pubGrp'> <span class='prod_auth'>" 
						+ my_array[i].book.author + "</a> 저 	</span> <em class='divi'>｜</em> <span class='prod_pub'>"
						+ my_array[i].book.publisher + "</span> <em class='divi'>｜</em> </div> <div class='prod_price'> <em class='price'>" 
						+ my_array[i].book.price + "</em> 원 </div> </div> <div class='prod_btn'> <a class='btn1' href='#' onclick=addCookie("+my_array[i].book.isbn+")+window.open('../../html/OrderPage/orderForm.html')>카트에 넣기</a><a class='btn2' href='#' onclick=setCookie_buy("
						+ my_array[i].book.isbn+","+1+")+window.open('../../html/ProductDetail/ProductDetail.html')>바로 구매하기</a></div></li></ul>";		
					}
					$('#prod_list').html(sumMsg);
						
				});	
		}
				
	});
	
	
	
	
	$("#previousBtn").click(function(){		
		if(page_num == 0){
			alert("첫 페이지입니다");
		}
		else{
			$('#paging').text(page_num);
			$.post("../../../productListAll.jes", {
				page_num : page_num
			},
				function(data, status){
					let json_msg = data;
					let js_msg = JSON.parse(json_msg);
					let my_array = js_msg.data;
					let sumMsg='';
					var i;
					for (i=0; i<my_array.length; i++){
						sumMsg += "<ul class='prod_li'> <li> <div class='prodSet'> <p class='prod_img'> <span class='prod_imgSet'> <a href='#'> <img src='" + my_array[i].book.imgurl 
						+ "border='0'> </a>	</span> </p> </div> <div class='prod_info'> <div class='prod_name'> <a>"
						+ my_array[i].book.title + "</a><br><br> </div> <div class='prod_pubGrp'> <span class='prod_auth'>" 
						+ my_array[i].book.author + "</a> 저 	</span> <em class='divi'>｜</em> <span class='prod_pub'>"
						+ my_array[i].book.publisher + "</span> <em class='divi'>｜</em> </div> <div class='prod_price'> <em class='price'>" 
						+ my_array[i].book.price + "</em> 원 </div> </div> <div class='prod_btn'> <a class='btn1' href='#' onclick=addCookie("+my_array[i].book.isbn+")+window.open('../../html/OrderPage/orderForm.html')>카트에 넣기</a><a class='btn2' href='#' onclick=setCookie_buy("
						+ my_array[i].book.isbn+","+1+")+window.open('../../html/ProductDetail/ProductDetail.html')>바로 구매하기</a></div></li></ul>";		
					}
					$('#prod_list').html(sumMsg);
						
				});		
		}
			
	});


});




