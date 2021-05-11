$(document).ready(function(){
	$.post("../myStyle.jes",
		  {			   
		   
		  },
		  function(data, status){			  	
		  
		  	data=JSON.parse(data);
		
		  	let tData='';
		  	let category='';
		  	data.forEach(function(item,index){
		  		
		  		tData+=item.count+",";
		  		category+=item.category+"|";
		  	});
		  	tData=tData.slice(0,-1);
		  	category=category.slice(0,-1);
		  	console.log(tData);
		  	console.log(category);
		  	$('#here').attr('src','https://chart.googleapis.com/chart?cht=p3&chs=750x200&chd=t:'+tData+'&chl='+category+'&chdl='+category);			

		  }
	);//end post() 
			
});
