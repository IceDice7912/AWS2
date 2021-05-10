$(function(){
	 var $searchBtn = $('.container nav .gnb_search'), 
	 	 $searchForm = $('.container form');
	 
	 $searchBtn.click(function(){
	 		 $searchForm.toggleClass('active');
	 		 
	 });
 });//document ready fuction
 
 
//$(function() {
//	  $('.clickMore').on('click', function() {
//	    $('.showMore').not($(this).next('.showMore')).slideUp('fast');
//	    $(this).next('.showMore').slideToggle('fast');
//	  });
//	}); 