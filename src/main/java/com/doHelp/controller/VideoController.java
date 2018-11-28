package com.doHelp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.doHelp.model.User;
import com.doHelp.model.Video;
import com.doHelp.service.UserService;
import com.doHelp.service.UserServices;
import com.doHelp.service.VideoShareServices;
@RestController
@EnableAutoConfiguration
public class VideoController {
      @Autowired 
      VideoShareServices  videoShareServices;
  	  @Autowired
      UserService userService;
  	  @Autowired
  	  UserServices userServices;
  	
      //页面跳转
	@RequestMapping(value="/videoShare" , method=RequestMethod.GET)
	 public ModelAndView v(Video  video){
		return new ModelAndView("videoPublish");
    }
	  //视频发布
		@RequestMapping(value="/doVideoShare" , method = RequestMethod.POST)
		public ModelAndView  doVideoShare(@ModelAttribute(value = "video") Video video,HttpSession session ) throws Exception{
			Date date =new Date();
			SimpleDateFormat from =new SimpleDateFormat("yyyy-MM-dd");
			video.setVideo_publishTime(from.format(date));
			User user=(User) session.getAttribute("userRedis");
			video.setUser_id(user.getUser_id());
			videoShareServices.save(video);
			return new ModelAndView("videoShare");
		}
			// 视频点赞
		@RequestMapping(value="/doVideoSnap",method= RequestMethod.POST)
		public Map<String,Object> doVideoSnap(int  video_id,int user_id,int snapcount,HttpSession session)throws Exception {
			Map<String,Object> resultMap=new HashMap<String,Object>();
			Date date =new Date();
			SimpleDateFormat from =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//任务一 ：将点赞的视频的点赞数量加1   二：将点赞的视频id 以及点赞者id保存至snapvideo表中 三：将视频发布者的信息状态改为T
			User users=(User)session.getAttribute("Redis");
			Video video=new Video();
			video.setVideo_id(video_id);
			video.setVideo_snapCount(snapcount+1); 
			video.setUser_id(users.getUser_id());
			videoShareServices.snapCount(video);//任务一
			videoShareServices.saveSnap(from.format(date), video_id, users.getUser_id()); //任务二
			User user=new User();
			user.setUser_id(user_id);
			user.setUser_messageStatus("T");  //当消息状态为T时  表明视频发布者有新消息未看,就是点赞信息！
			userServices.updateStatus(user);  //任务三
			return resultMap;
		}
		   //视频收藏
	   @RequestMapping(value="/doVideoCollect", method=RequestMethod.POST)
	   public Map<String,Object> doVideoCollect(int video_id ,HttpSession session) throws Exception{
		   Map<String,Object> resultMap=new HashMap<String,Object>();
		   Date date =new Date();
		   SimpleDateFormat from =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   User user=(User)session.getAttribute("Redis");
		   videoShareServices.saveCollectVideo(from.format(date), video_id, user.getUser_id());
			
		return resultMap;
	   }
		 //视频举报
	   @RequestMapping(value="/doVideoReport" , method=RequestMethod.POST)
	   public Map<String ,Object> doVideoReport(int reportType_id,int video_id,int user_id,HttpSession session) throws Exception{
		 Map<String,Object> resultMap=new HashMap<String,Object>();
//任务一：将举报信息插入至reportvideo 表中， 任务二： 根据举报类型以及video_id将 video中相应字段
//video_reportCount的分数增加，到达一定值就将video中的video_is_verify_code设置为T，等待管理员查看,若真的违反相关规定，则将视频给删除掉,并对违规用户进行安全分数调整。
		   videoShareServices.saveReport(reportType_id, video_id, user_id); //任务一
		   Video video=new Video();
		   video.setVideo_id(video_id);
		   videoShareServices.reportCount(video);//任务二
		   videoShareServices.update(video);
		   Video videos=  videoShareServices.findVideoById(video_id);
		   if(videos.getVideo_reportCount()>=100)
		   { videoShareServices.videoStatus("T", video_id);}
		   
		   return resultMap;
		   
	   }
		
		
		
		
		
		
}
