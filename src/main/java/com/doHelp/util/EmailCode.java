package com.doHelp.util;

import java.util.UUID;

public class EmailCode {
	public static String createEmailCode(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	public static void mian(String[] args){
		System.out.println(EmailCode.createEmailCode());
		System.out.println(EmailCode.createEmailCode());
		System.out.println(EmailCode.createEmailCode());
		
	}
	public static String getUid(String code){  //返回用户的注册邮箱
		if(code == null){
			return null;
		}
		int index = code.lastIndexOf("-");
		if(index==-1){
			return null;
		}
		return code.substring(index+1);
	}
	
	//获取验证码
	public static String getUUID(String code){
		if(code == null){
			return null;
		}
		int index = code.lastIndexOf("-");
		if(index ==-1){
			return null;
		}
		System.out.print("验证码为："+code.substring(0,index));
		return code.substring(0,index);  //返回一个新的字符串，该字符串由0开始，直到 index-1的地方
	}
}
