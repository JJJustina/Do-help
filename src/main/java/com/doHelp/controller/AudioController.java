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
import com.doHelp.model.Audio;
import com.doHelp.model.User;
import com.doHelp.model.Video;
import com.doHelp.service.AudioShareServices;
import com.doHelp.service.UserServices;

@RestController
@EnableAutoConfiguration
public class AudioController {
   @Autowired
   AudioShareServices audioShareServices;
   @Autowired
   UserServices userServices;
 //页面跳转
 	@RequestMapping(value="/audioShare" , method=RequestMethod.GET)
 	 public ModelAndView a(Audio  audio){
 		return new ModelAndView("audioPublish");
     }
 	  //视频发布
 		@RequestMapping(value="/doAudioShare" , method = RequestMethod.POST)
 		public ModelAndView  doAudioShare(@ModelAttribute(value = "audio") Audio audio,HttpSession session ) throws Exception{
 			Date date =new Date();
 			SimpleDateFormat from =new SimpleDateFormat("yyyy-MM-dd");
 			audio.setAudio_publishTime(from.format(date));
 			User user=(User) session.getAttribute("userRedis");
 			audio.setUser_id(user.getUser_id());
 			audioShareServices.save(audio);
 			return new ModelAndView("audioShare");
 		}
 			// 音频点赞
 		@RequestMapping(value="/doAudioSnap",method= RequestMethod.POST)
 		public Map<String,Object> doAudioSnap(int  audio_id,int snapcount,HttpSession session)throws Exception {
 			Map<String,Object> resultMap=new HashMap<String,Object>();
			Date date =new Date();
			SimpleDateFormat from =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//任务一 ：将点赞的音频的点赞数量加1   二：将点赞的音频id 以及点赞者id保存至snapaudio表中 三：将音频发布者的信息状态改为T
			User users=(User)session.getAttribute("Redis");
			Audio audio=new Audio();
			audio.setAudio_id(audio_id);
			audio.setAudio_snapCount(snapcount+1); 
			audio.setUser_id(users.getUser_id());
			audioShareServices.snapCount(audio);//任务一
			audioShareServices.saveSnap(from.format(date), audio_id, users.getUser_id()); //任务二
			User user=new User();
			user.setUser_id(audio_id);
			user.setUser_messageStatus("T");  //当消息状态为T时  表明音频发布者有新消息未看,就是点赞信息！
			userServices.updateStatus(user);  //任务三
			return resultMap;
 		}
       //音频收藏
 		 @RequestMapping(value="/doVideoCollect", method=RequestMethod.POST)
 		   public Map<String,Object> doVideoCollect(int video_id ,HttpSession session) throws Exception{
 			   Map<String,Object> resultMap=new HashMap<String,Object>();
 			   Date date =new Date();
 			   SimpleDateFormat from =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 			   User user=(User)session.getAttribute("Redis");
 			   audioShareServices.saveCollectAudio(from.format(date), video_id, user.getUser_id());
 				
 			return resultMap;
 		   }
 		 //视频举报
 		   @RequestMapping(value="/doAudioReport" , method=RequestMethod.POST)
 		   public Map<String ,Object> doVideoReport(int reportType_id,int audio_id,int user_id,HttpSession session) throws Exception{
 			 Map<String,Object> resultMap=new HashMap<String,Object>();
 	//任务一：将举报信息插入至reportvideo 表中， 任务二： 根据举报类型以及video_id将 video中相应字段
 	//video_reportCount的分数增加，到达一定值就将video中的video_is_verify_code设置为T，等待管理员查看,若真的违反相关规定，则将视频给删除掉,并对违规用户进行安全分数调整。
 			   audioShareServices.saveReport(reportType_id, audio_id, user_id); //任务一
 			   Audio audio=new Audio();
 			   audio.setAudio_id(audio_id);
 			   audioShareServices.reportCount(audio);//任务二
 			   audioShareServices.update(audio);
 			   Audio audios=  audioShareServices.findAudioById(audio_id);
 			   if(audios.getAudio_reportCount()>=100)
 			   { audioShareServices.audioStatus("T", audio_id);}
 			   return resultMap;
 			   
 		   }
 			
 		
 		
   
}
