package com.doHelp.util;

import java.security.Security;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.net.ssl.internal.ssl.Provider;

@SuppressWarnings("restriction")
public class Sendmail {
	// 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
    // PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）, 
    //     对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
	String from = "401919049@qq.com";
	String user = "401919049"; 
	String password = "sxwfigowadnycbdj";
public Sendmail(){
		
	}
	public void sendMail(String to,String text,String title){//  text为正文 也就是传递过来的验证码， title为标题
		Properties props = new Properties();// 用于连接邮件服务器的参数配置（发送邮件时才需要用到）
		props.setProperty("mail,smtp.host", "smtp.qq.com");//设置邮箱服务器主机名  ，调用 Hashtable 的方法 put 。他通过调用基类的put方法来设置 键 - 值对。
		props.put("mail,smtp.host",  "smtp.qq.com"); 
		props.put("mail.smtp.auth", "true");  //设置使用验证服务器
		
		
		//安全认证   qq邮箱不需要 
//		Security.addProvider(new Provider());
//		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		props.setProperty("mail.smtp.port", "465");
//		props.setProperty("mail.smtp.socketFactory.port", "465");
//		
//		message.setFrom(new InternetAddress(from));
//		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to,false));
//		message.setSubject(title);
//		message.setText(text);
//		Transport.send(message);
				
		
		Session session = Session.getDefaultInstance(props);//创建话对象，用于和邮件服务器交互
		session.setDebug(true);  // 设置为debug模式, 可以查看详细的发送 log
		MimeMessage message = new MimeMessage(session);  //创建邮件信息
		try{
			
			message.setFrom(new InternetAddress(from)); //设置发件人
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));//设置发送方式以及收件人邮箱地址
			message.setSubject(title); //设置标题
//	 	 Multipart multpart = new MimeMultipart();//为抽象类，必须实例化  设置邮件内容
//    		BodyPart contentPart = new MimeBodyPart();//也为抽象类，需要实例化   添加附件
//			contentPart.setContent(text,"text/html;charset=utf-8");//设置邮件具体内容，传输html页面，以便使用文本编辑器
//			multpart.addBodyPart(contentPart); //将附加加入到邮件内容中
//			message.setContent(multpart);    //将邮件内容添加到邮件中
//			message.saveChanges();
			message.setText(text);
  		   Transport transport = session.getTransport("smtp");
			transport.connect("smtp.qq.com",user,password);
			transport.sendMessage(message, message.getAllRecipients());//发送邮件
			transport.close();
		}catch(MessagingException e){
			e.printStackTrace();
		}
	}
}
