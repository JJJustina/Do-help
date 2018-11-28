package com.doHelp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.doHelp.model.Article;
import com.doHelp.model.User;
import com.doHelp.model.Video;
import com.doHelp.service.ArticleShareServices;
import com.doHelp.service.UserServices;

@RestController
@EnableAutoConfiguration
public class ArticleController {
      @Autowired
      ArticleShareServices articleShareServices;
      @Autowired
      UserServices userServices;
     
    //页面跳转
  	@RequestMapping(value="/articleShare" , method=RequestMethod.GET)
  	 public ModelAndView a(Article  article){
  		return new ModelAndView("articlePublish");
      }
  	  //视频发布
  		@RequestMapping(value="/doArticleShare" , method = RequestMethod.POST)
  		public ModelAndView  doArticleShare(@ModelAttribute(value = "article") Article article,HttpSession session ) throws Exception{
  			Date date =new Date();
  			SimpleDateFormat from =new SimpleDateFormat("yyyy-MM-dd");
  			article.setArticle_publishTime(from.format(date));
  			User user=(User) session.getAttribute("userRedis");
  			article.setUser_id(user.getUser_id());
  			articleShareServices.save(article);
  			return new ModelAndView("articleShare");
  		}
  			// 文字点赞
  		@RequestMapping(value="/doArticleSnap",method= RequestMethod.POST)
  		public Map<String,Object> doArticleSnap(int  article_id,int snapcount,HttpSession session)throws Exception {
  			Map<String,Object> resultMap=new HashMap<String,Object>();
			Date date =new Date();
			SimpleDateFormat from =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//任务一 ：将点赞的文字的点赞数量加1   二：将点赞的文字id 以及点赞者id保存至snaparticle表中 三：将文字发布者的信息状态改为T
			User users=(User)session.getAttribute("Redis");
			Article article=new Article();
			article.setArticle_id(article_id);
			article.setArticle_snapCount(snapcount+1); 
			article.setUser_id(users.getUser_id());
			articleShareServices.snapCount(article);//任务一
			articleShareServices.saveSnap(from.format(date), article_id, users.getUser_id()); //任务二
			User user=new User();
			user.setUser_id(article_id);
			user.setUser_messageStatus("T");  //当消息状态为T时  表明文字发布者有新消息未看,就是点赞信息！
			userServices.updateStatus(user);  //任务三
			return resultMap;
  		}
  		  //文字收藏
  		 @RequestMapping(value="/doVideoCollect", method=RequestMethod.POST)
  	   public Map<String,Object> doVideoCollect(int video_id ,HttpSession session) throws Exception{
  		   Map<String,Object> resultMap=new HashMap<String,Object>();
  		   Date date =new Date();
  		   SimpleDateFormat from =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  		   User user=(User)session.getAttribute("Redis");
  		   articleShareServices.saveCollectArticle(from.format(date), video_id, user.getUser_id());
  			
  		return resultMap;
  	   }
  		 //文字举报
  	   @RequestMapping(value="/doArticleReport" , method=RequestMethod.POST)
  	   public Map<String ,Object> doVideoReport(int reportType_id,int article_id,int user_id,HttpSession session) throws Exception{
  		 Map<String,Object> resultMap=new HashMap<String,Object>();
  //任务一：将举报信息插入至reportvideo 表中， 任务二： 根据举报类型以及video_id将 video中相应字段
  //video_reportCount的分数增加，到达一定值就将video中的video_is_verify_code设置为T，等待管理员查看,若真的违反相关规定，则将视频给删除掉,并对违规用户进行安全分数调整。
  		   articleShareServices.saveReport(reportType_id, article_id, user_id); //任务一
  		   Article article=new Article();
  		   article.setArticle_id(article_id);
  		   articleShareServices.reportCount(article);//任务二
  		   articleShareServices.update(article);
  		   Article articles =  articleShareServices.findArticleById(article_id);
  		   if(articles.getArticle_reportCount()>=100)
  		   { articleShareServices.articleStatus("T", article_id);}
  		   
  		   return resultMap;
  		   
  	   }
  		
      
	
	
}
