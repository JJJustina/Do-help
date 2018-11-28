/**
 * 
 */
$(function(){
	
	var PH=false ,PC=false,  P=false;
	// PH 为phone PC为phoneCode   P为passWord  
	// 如果填写的内容全部正确，则可以提交，否则不可提交!
	$("#btnClientRegister").click(function(){
		if(PH && PC && P ){
			return true;
		}
		else
           {
			$("#submitInfo").text("您还有信息没有填写。").css('color','red');
			return false;
			}
	});
	// PH  手机号码验证
	$("#textphone").blur(function(){
		var info = $("#phoneInfo");
		var  phone=$(this).val();
		var  re = /^1\d{10}$/;   
		PH=false;
		if(phone==""){info.text("手机号码不能为空。").css('color','red');}
		else
			if(re.test(phone))
				{
				$.post(   //  调用Jquery中ajax方法 
						$("#PageContext").val()+'/register?method=1',
						 {'phone':phone},
						function(data){   //data为返回数据    function
							if(data.flag){  
								info.text("恭喜，您填写的手机号可用 √").css('color','green');
								PH=true;
							}else{
								info.text("该手机号已存在。").css('color','red');
							}
						},
						'json'
				);
				}
			else
				{
				info.text("请填写有效的手机号码。").css('color','red');
				}
	})
//昵称  N
$("#txtNickName").blur(function(){
	N = false;
	var val = $(this).val();
	var info = $("#nameInfo");
	info.html("");
	if(val == ""){
		info.text("昵称不能为空").css('color','red');
	}else if(!val.match(/^[a-z0-9\u4e00-\u9efa5]{4,20}$/)){
		info.text("昵称格式不正确").css('color','red');	
	}else {
		info.html("恭喜，该昵称可用 √").css('color','green');
		N = true;
	}
});
//密码验证 P
$("#txtPassword").blur(function(){
	P = false;
	var val = $(this).val();
	var info = $("#passwordInfo");
	info.html("");
	if(val == ""){
		info.text("密码不能为空").css('color','red');
	}else if(!val.match(/^[a-z0-Z0-9]{6,20}$/)){
		info.text("密码格式不正确").css('color','red');
		
	}
	else {
		info.html("√").css('color','green');
		P = true;
	}
});
//再次密码验证  RP
$("#txtRepeatPass").blur(function(){
	R = false;
	var val = $(this).val();
	var info = $("#repasswordInfo");
	info.html("");
	if(val == ""){
		info.text("验证密码不能为空").css('color','red');
	}else if(val!=$("#txtPassword").val()){
		info.text("2次密码不正确").css('color','red');
	}else {
		RP = true;
	info.text("√").css('color','green');;
	}
});
////重置时将所有的css.color换成red
//$("reset").click(function(){
//$("#phoneInfo").text.css('color','red');
//$("#passwordInfo").text.css('color','red');
//$("#repasswordInfo").text.css('color','red');
//$("#birthdayInfo").text.css('color','red');
//$("#numberInfo").text.css('color','red');
//})


//  验证是否选填生日信息  B
//  获取不到date值  只能采用year 和   month两个值进行判断
$("#date").blur(function(){
	var birthdayInfo=$("#birthdayInfo");
	var  year=$("#year").val();
	var  month=$("#month").val();
    if(year!=0 && month!=0){
		birthdayInfo.text("√").css('color','green');;
		B=true;
		}
	else
		birthdayInfo.text("您的生日信息还未填写完成.").css('color','red');
})
	

// 验证码  Code
$("#txtVerifyCode").blur(function(){// blur为鼠标点外部事件
V=false;
var val = $(this).val(); //val保存的为输入框输入的值
//判断输入的值是否符合条件
var info = $("#numberInfo");
info.html("");
if(val ==""){
	info.text("验证码不能为空").css('color','red');
}else {
	//info.html("<img src='./images/my/ajax_loader.gif' />");
	// $.post为JQuery中ajax的post方法
	$.post(
			$("#PageContext").val()+'/validateCheckCode.do',  //访问validateCheckCode.do该链接，对应的为ValidateCheckCodeServlet 			
			{'checkCode':val}, 
			function(data){
				if(data.flag){
					info.text("√").css('color','green');
					Code = true; 
				}else{
					info.text("验证码错误").css('color','red');
				}
			},
			'json'
	);
}
});
$("#txtPhoneCode").blur(function(){
	  var  txtPhoneCode=$(this).val();
	  var  info=$("#phoneCodeInfo");
	  if(txtPhoneCode){
		  $.post(
					$("#PageContext").val()+'/phoneCheckCode', 
					{'txtPhoneCode':txtPhoneCode}, 
					function(data){
						if(data.flag){
							info.text("√").css('color','green');
							 PC= true; 
						}else{
							info.text("验证码错误.").css('color','red');
						}
					},
					'json'
			);	  
	  }
	  else
     info.text("请点击并获取验证码进行验证。").css('color','red');
	})

// PC  手机验证码验证
$("#btnSendCode").click(function(){
	var   phoneCodeInfo=$("#phoneCodeInfo");
	var phone =$("#textphone").val();
	if(PH)
		{	
    $.post(
    		$("#PageContext").val()+'/sendPhone', 
    		{mobile:jQuery.trim(phone)}, 
    		function(msg){
    			alert(msg);	
		   if(!msg.equals("提交成功")){
			   phoneCodeInfo.text("验证码已经发送，请注意查看。").css('color','green');
		  }else{
			phoneCodeInfo.text("验证码发送失败，请重新发送！").css('color','red');
			location.reload();
		}
    }
    		
    );}
	else
		phoneCodeInfo.text("请先填写您的手机号码。").css('color','red');
});
//手机验证码   PC
//var InterValObj; //timer变量，控制时间
//var count = 120; //间隔函数，1秒执行
//var curCount;//当前剩余秒数
//var code = ""; //验证码
//var codeLength = 6;//验证码长度
// 
//function sendMessage() {   // jbPhone:手机号  jbPhoneTip：手机提示信息  btnSendCode：手机验证码提示信息
//	curCount = count;
//	var jbPhone = $("#textphone").val();
//	var jbPhoneTip = $("#phoneInfo").text();
//	if (jbPhone != "") {
//		if(jbPhoneTip == "√ 该手机号码可以注册，输入正确" || jbPhoneTip == "√ 短信验证码已发到您的手机,请查收"){
//			// 产生验证码
//			for ( var i = 0; i < codeLength; i++) {
//				code += parseInt(Math.random() * 9).toString();
//			}
//			// 设置button效果，开始计时
//			$("#btnSendCode").attr("disabled", "true");
//			$("#btnSendCode").val("请在" + curCount + "秒内输入验证码");
//			InterValObj = window.setInterval(SetRemainTime, 1000); // 启动计时器，1秒执行一次
//			// 向后台发送处理数据
//			$.ajax({
//				type: "POST", // 用POST方式传输
//				dataType: "text", // 数据格式:JSON
//				url: "UserAction_sms.action", // 目标地址
//				data: "jbPhone=" + jbPhone +"&code=" + code,
//				error: function (XMLHttpRequest, textStatus, errorThrown) { 
//					
//				},
//				success: function (data){ 
//					data = parseInt(data, 10);
//					if(data == 1){
//						$("#phoneInfo").html("<font color='#339933'>√ 短信验证码已发到您的手机,请查收</font>");
//					}else if(data == 0){
//						$("#phoneInfo").html("<font color='red'>× 短信验证码发送失败，请重新发送</font>");
//					}else if(data == 2){
//						$("#phoneInfo").html("<font color='red'>× 该手机号码今天发送验证码过多</font>");
//					}
//				}
//			});
//		}
//	}else{
//		$("#phoneInfo").html("<font color='red'>× 手机号码不能为空</font>");
//	}
//}
// 
////timer处理函数
//function SetRemainTime() {
//	if (curCount == 0) {                
//		window.clearInterval(InterValObj);// 停止计时器
//		$("#btnSendCode").removeAttr("disabled");// 启用按钮
//		$("#btnSendCode").val("重新发送验证码");
//		code = ""; // 清除验证码。如果不清除，过时间后，输入收到的验证码依然有效
//	}else {
//		curCount--;
//		$("#btnSendCode").val("请在" + curCount + "秒内输入验证码");
//	}
//}
// 
//$(document).ready(function() {
//	$("#txtPhoneCode").blur(function() {
//		var SmsCheckCodeVal = $("#txtPhoneCode").val();
//		// 向后台发送处理数据0
//		$.ajax({
//			url : "UserAction_checkCode.action", 
//			data : {SmsCheckCode : SmsCheckCodeVal}, 
//			type : "POST", 
//			dataType : "text", 
//			success : function(data) {
//				data = parseInt(data, 10);
//				if (data == 1) {
//					$("#phoneInfo").html("<font color='#339933'>√ 短信验证码正确，请继续</font>");
//				} else {
//					$("#phoneInfo").html("<font color='red'>× 短信验证码有误，请核实后重新填写</font>");
//				}
//			}
//		});
//	});
//});

});

//刷新验证码
$(function(){
$("#checkCode").click(function(){
	$("#imgVcode").attr("src",$("#PageContext").val()+"/checkCode.action?dt="+new Date().getTime()); // 携带当前时间以便url是没有被缓存过的，以确保重新访问到后台的servlet，再重新生成。
});

});
