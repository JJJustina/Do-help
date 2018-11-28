package com.doHelp.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doHelp.dao.DAOFactory;
import com.doHelp.model.User;
import com.doHelp.model.Video;
import com.doHelp.service.VideoShareServices;

@Service
public  class VideoShareServiceImp  implements VideoShareServices{
	@Autowired
	DAOFactory  DAOFactory;
	
	@SuppressWarnings("static-access")
	@Override
	public Video findVideoById(int  video_id) throws Exception {
		return DAOFactory.getVideoShareDAO().findVideoById(video_id);
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public void save(Video video) throws Exception {
		DAOFactory.getVideoShareDAO().save(video);
	}
	
	
	
	@SuppressWarnings("static-access")
	@Override 
	public void saveSnap(String time,int video_id,int user_id) throws Exception {
		DAOFactory.getVideoShareDAO().saveSnap(time, video_id, user_id);
	}
	
	
	@SuppressWarnings("static-access")
	@Override 
	public void saveCollectVideo(String time,int video_id,int user_id) throws Exception {
		DAOFactory.getVideoShareDAO().saveSnap(time, video_id, user_id);
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public void seeCount(Video video) throws Exception {
		DAOFactory.getVideoShareDAO().seeCount(video);
	}
	

    @SuppressWarnings("static-access")
    @Override
	public void snapCount(Video video) throws Exception{
		 DAOFactory.getVideoShareDAO().snapCount(video);
   }
	
    
	@SuppressWarnings("static-access")
	@Override
	public void update(Video video) throws Exception {
		 DAOFactory.getVideoShareDAO().update(video);
	}

	
	@SuppressWarnings("static-access")
	@Override
	public List<Video> find() throws Exception {
		 return DAOFactory.getVideoShareDAO().find();
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public List<Video> findCollectVideo(User u) throws Exception{
		 return DAOFactory.getVideoShareDAO().find();
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public void delete(Video video) throws Exception {
		DAOFactory.getVideoShareDAO().delete(video);
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public void reportCount(Video video) throws Exception {
		DAOFactory.getVideoShareDAO().reportCount(video);
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public void saveReport(int reportType_id,int video_id,int user_id) throws Exception{
		DAOFactory.getVideoShareDAO().saveReport(reportType_id,video_id,user_id);
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public void  videoStatus(String  video_is_verify_code ,int video_id )throws Exception{
		DAOFactory.getVideoShareDAO().videoStatus(video_is_verify_code,video_id);
	}
	
}
