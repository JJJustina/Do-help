package com.doHelp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.doHelp.model.User;
import com.doHelp.model.Remarks;
import com.doHelp.service.RemarksServices;
import com.doHelp.service.UserService;
@RestController
@EnableAutoConfiguration
public class RemarksController {
	@Autowired
	UserService userService;
	
	@Autowired
    RemarksServices remarksServices;
	
	
      //页面跳转
	@RequestMapping(value="/remarksPublish" , method=RequestMethod.GET)
	 public ModelAndView r(Remarks remarks){
		return new ModelAndView("remarksPublish");
    }
	  //感悟发布
	@RequestMapping(value="/doRemarks" , method = RequestMethod.POST)
	public Map<String,Object> doRemarks(String remarksInfo ,HttpSession session ) throws Exception{
		Map<String,Object> resultMap=new HashMap<String,Object>();
		Remarks remarks=new Remarks();
		User user=(User) session.getAttribute("userRedis");
		remarks.setRemarks_info(remarksInfo);
		remarks.setUser_id(user.getUser_id());
		
		remarksServices.save(remarks);
		resultMap.put("result","YES");
		return resultMap;
	}
		// 感悟点赞
	@RequestMapping(value="/doRemarksSnap",method= RequestMethod.POST)
	public Map<String,Object> doRemarksSnap(int  remarks_id,int snapcount,HttpSession session)throws Exception {
		Map<String,Object> resultMap=new HashMap<String,Object>();
		Remarks remarks=new Remarks();
		remarks.setRemarks_id(remarks_id);
		remarks.setSnapCount(snapcount+1);
		remarksServices.snapCount(remarks);
		return resultMap;
		
	}
	
}
