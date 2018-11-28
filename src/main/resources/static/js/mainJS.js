/**
 * 
 */

$(function(){
	 $('.contact-box').each(function() { 
         animationHover(this, 'pulse'); 
     }); 
     $("img").hover(function(){
          var user_accountCount= $("#user_accountCount").val();
          alert(user_accountCount);
          if(user_accountCount>=100)
                               $("figcaption").css("background-color","#17dbd4");
                              else
                                  if(user_accountCount>=80 && user_accountCount<100)
                                      $("figcaption").css("background-color","#183bd2");
                                  else
                                      if(user_accountCount>=60 && user_accountCount<80)
                                          $("figcaption").css("background-color","#eef317");
                                      else
                                          if(user_accountCount<60 && user_accountCount>=0)
                                              $("figcaption").css("background-color","red");
                                          else
                                                  $("figcaption").css("background-color","#373b39");
       });
          
	
	})
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}