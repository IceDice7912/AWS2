$(document).ready(function(){

	$("#orderBtn").click(function(){

		let cookieidcheck=$.cookie("M_id");
		if(cookieidcheck==null){
			alert('로그인 이후 이용해주세요.');
			window.close();
		}

		var totalprice= document.getElementById("total_price").innerText;
		var id= document.getElementById("M_id").innerText;
		var name= document.getElementById("M_name").innerText;
		var count= document.getElementById("count").innerText;
		var title= "";
		
		for(var i=0; i<count; i++){
			title = title + document.getElementById("title"+i+"").innerText + ", ";
		}

		alert(title + "  " + totalprice + "  " + id + "  " + name);
		
		let confirm_data=confirm("다음과 같이 주문하시겠습니까?\n");		
		if(confirm_data){
			$.post("../../order",
				{
				  totalprice: totalprice,
				  id: id,
				  name: name,
				  title: title
				},  
				  function(data, status){	
					$.removeCookie("select_product_isbn_cart", { path: '/' });// 장바구니 쿠키 삭제
				  	window.close();			   
				  }
			);//end post() 			
		}		
	});


	$("#anotherBtn").click(function(){		
		window.close();
	});

	$("#cancelBtn").click(function(){
		alert("장바구니를 모두 비웁니다");		
		$.removeCookie("select_product_isbn_cart", { path: '/' });// 장바구니 쿠키 삭제
		window.close();
	});


$("#loginBtn").click(function(){//로그인 처리	
		
		var id=$("#id").val();
		var pw=$("#pw").val();
		
		$.post("../login.jes",
			  {			   
			    id:id,
			    pw:pw
			  },
			  function(data, status){	
				  var obj=JSON.parse(data);
				  var name=obj.name;
			  		$.cookie("M_name",obj.name,{expires: 1, path: '/' }); // cookie expires in 10 days 
			  		$.cookie("M_id",id,{expires: 1, path: '/' }); // cookie expires in 10 days 	
				  	if(obj.name){
				  		data =name+"님 환영합니다\t<input type='button' value='로그아웃' id='logoutBtn' class='log'><input type='button' value='구매성향보기' id='styleBtn' >"
				  		$.cookie("logined",data,{expires: 1, path: '/' }); // cookie expires in 10 days 
				  		window.opener.document.getElementById("loginArea").innerHTML= data;
				  		alert(obj.name+"님 로그인 되셨습니다.");
				  		window.close();
					}else{
						alert(obj.msg);
						location.reload();	
					}							   
			  }//end function
		);//end post() 
	});//end 로그인 처리





$(document).on("click", "#logoutBtn", function(event) { //로그아웃 처리
	
	alert("로그아웃합니다.");
	$.post("logout.jes",
		  {			   
		   
		  },
		  function(data, status){		  	
		  	
		  	$.removeCookie("logined");	
		  	$.removeCookie("M_name");	
		  	$.removeCookie("M_id");	
			location.reload();						   
		  }
	);//end post() 
});//end 로그아웃 처리
	
	

$(document).on("click", "#styleBtn", function(event) { //구매성향 처리
	window.open('../html/myStyle.html', '_blank', 'toolbar=yes,scrollbars=yes,resizable=yes,top=50,left=500,width=800,height=350');
	
});//end style 처리



	$("#memberInsertBtn").click(function(){//회원 가입 처리
		
		var name=$("#name").val();
		var id=$("#id").val();
		var pw=$("#pw").val();
		var gender=$('input[name="gender"]:checked').val();
		var favorite=$("#favorite").val();		

		if( (name.length==0 || name== "") || (id.length == 0 || id== "") ||  (pw.length == 0 || pw== "")  ) {
			alert("모든 항목은 필수로 입력하셔야하 합니다.");
		} else {	
					
		$.post("../memberInsert.jes",
			  {
			    name:name,
			    id:id,
			    pw:pw,
			    gender:gender,
			    favorite:favorite
			  },
			  function(data, status){
			    alert( data);
			    window.close();
			  });
		
	}
	});
	
	
	$("#chatBtn").click( function () { // 챗봇-텍스트 대화
	    var chat=$("#chat").val();
	    if ((chat.length == 0 || chat == "")) {
	        alert("뭐라도 입력하시오.");
	    } else {
	        $.post("../../chat.jes", {
	            chat: chat
	        }, function (data, status) {
			    $('p').append(data + "<br>");  
	        });

	    }
	});
	
	$("#saySubmitBtn").click( function () { // 챗봇-음성 대화
	    var chat;
	    	
	    	$.post("../../stt.jes", {
	    		chat: chat
	    	}, function(data) {
	    		chat = data;
	            $('say').empty();
			    $('say').append(data);    	
	            	$.post("../../chat.jes", {
	            		chat: chat
	            	}, function(data) {
	    			    $('p').append(data + "<br>");
	            	},
	            	);
	    		});
	    	
	});	
	
	
	
	$("#faceSubmitBtn").click( function () { // 페이스 테스트
			alert("찍은 사진을 서버에 전송해서 정보를 읽어옵니다.");
			var star;
			var personinfo;
			var ages1;
			var agecut = /[^0-9]/gi;
			var ages2;
			var agei;
			var gender1;
			var gendercut = /[^a-z]/gi;
			var gender2;
			
			$.post("../../face-celebrity.jes", 
					{

					}, function(data, status){
			            star = data;
			            $('star').empty();
					    $('star').append(star);
					});
			$.post("../../face-face.jes",
					{

					}, function(data, status){
			            
			            personinfo = data;
			            
			            ages1 = (personinfo).replaceAll("~", "");			            
			            ages2 = (ages1).replaceAll(agecut, "");			            
			            agei = Number(ages2);			            
			            agei = parseInt(agei / 100);
			            $('age').empty();
					    $('age').append(agei);  
					    
					    gender1 = (personinfo).replaceAll("~", "");
			            gender2 = (gender1).replaceAll(gendercut, "");
			            if(gender2=="male")
			          	  gender2 = "남성";
			            if(gender2=="female")
			          	  gender2 = "여성";
			            if(gender2=="child")
			          	  gender2 = "어린이";
			            $('gender').empty();
					    $('gender').append(gender2);	            
					});
		});
	
});

