/**
 * 
 */
$(function(){
	$("#remarksSubmit").click(function(){
		
	 var snapCount;
	 var info=$("#info");
	 alert("wrqqwrqw"+info);
		if(remarks="")
			{
			info.text("您还有信息没有填写。").css('color','red');
			}
		else
			{
			$.ajax(   //  调用Jquery中ajax方法 \
					type:"POST",
					url: "/doRemarks",
					data:{remarksInfo:$("#remarks").val()},
					dataType:"json",
					success:function(data){
						if(data.result.equals("YES"))
						info.text("提交成功 √").css('color','green');}
						else{
						info.text("提交失败！").css('color','red');}
					
			}
	})
	
}