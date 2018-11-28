package com.doHelp.service;

import java.util.List;

import com.doHelp.model.User;
import com.doHelp.model.Video;

public interface VideoShareServices {
	public void save(Video video) throws Exception;
	public Video findVideoById(int  video_id) throws Exception;
	public void saveSnap(String time,int video_id,int user_id) throws Exception;
	public void saveCollectVideo(String time,int video_id,int user_id) throws Exception;
	public void update(Video  video) throws Exception ;
	public void  snapCount(Video video)throws Exception;
	public void  seeCount(Video video)throws Exception;
	public void  reportCount(Video video)throws Exception;
	public void saveReport(int reportType_id,int video_id,int user_id) throws Exception;
	public void  videoStatus(String  video_is_verify_code ,int video_id )throws Exception;
	public List<Video> find() throws Exception;
	public List<Video> findCollectVideo(User u) throws Exception;
	public void delete(Video  video) throws Exception;

}
