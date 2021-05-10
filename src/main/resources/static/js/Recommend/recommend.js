$(document).ready(function(){

	$("#reco_btn").click( function () { // 챗봇-음성 대화
	
		    var title = $("#book_title").val();
		    alert(title+ " 책과 유사한 책을 추천할게요");
		    
		    $.post("../../../recommend.jes",
					  {			   
					    title:title,
					  },
					  function(data, status){
						  let json_msg = data;
						  let js_msg = JSON.parse(json_msg);
						  let my_array = js_msg.data;
						  let sumMsg='';
						  for (i=0; i<my_array.length; i++){
							  sumMsg += "<ul class='prod_li'> <li> <div class='prodSet'> <p class='prod_img'> <span class='prod_imgSet'> <a href='#'> <img src='" + my_array[i].book[0].imgurl 
							  	+ "border='0'> </a>	</span> </p> </div> <div class='prod_info'> <div class='prod_name'> <a>"
								+ my_array[i].book[0].title + "</a><br><br> </div> <div class='prod_pubGrp'> <span class='prod_auth'>" 
								+ my_array[i].book[0].author + "</a> 저 	</span> <em class='divi'>｜</em> <span class='prod_pub'>"
								+ my_array[i].book[0].publisher + "</span> <em class='divi'>｜</em> </div> <div class='prod_price'> <em class='price'>" 
								+ my_array[i].book[0].price + "</em> 원 </div> </div></li></ul>";
								
							}
							$('#prod_list').html(sumMsg);
					
					  }//end function
				);//end post() 
	
		});	


	$(".recommend").click( function () { // 챗봇-음성 대화
	
		var title = $("#book_title").val();
	    
	    $.post("../../../search.jes",
				  {			   
				    title:title,
				  },
				  function(data, status){
					  let json_msg = data;				  
					  let js_msg = JSON.parse(json_msg);
					  let sumMsg='';

					  sumMsg += "<ul class='prod_li'> <li> <div class='prodSet'> <p class='prod_img'> <span class='prod_imgSet'> <a href='#'> <img src='" + js_msg[0].imgurl 
					  	+ "border='0'> </a>	</span> </p> </div> <div class='prod_info'> <div class='prod_name'> <a>"
						+ js_msg[0].title + "</a><br><br> </div> <div class='prod_pubGrp'> <span class='prod_auth'>" 
						+ js_msg[0].author + "</a> 저 	</span> <em class='divi'>｜</em> <span class='prod_pub'>"
						+ js_msg[0].publisher + "</span> <em class='divi'>｜</em> </div> <div class='prod_price'> <em class='price'>" 
						+ js_msg[0].price + "</em> 원 </div> </div></li></ul>";
						

					$('#prod').html(sumMsg);
				
				  }//end function
			);//end post() 	    	
	});	
});