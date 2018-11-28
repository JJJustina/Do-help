package com.doHelp.dao;

import java.util.List;

import com.doHelp.model.Audio;
import com.doHelp.model.User;

public interface AudioShareDAO {

	public void save(Audio audio) throws Exception;
	public void saveSnap(String time,int audio_id,int user_id) throws Exception; 
	public void saveCollectAudio(String time,int audio_id,int user_id) throws Exception;
	public void update(Audio audio) throws Exception ;
	public void snapCount(Audio  audio)throws Exception;
	public void  seeCount(Audio audio)throws Exception;
	public void  reportCount(Audio audio)throws Exception;
	public void saveReport(int reportType_id,int audio_id,int user_id) throws Exception;
	public void  audioStatus(String  audio_is_verify_code ,int audio_id )throws Exception;
	public List<Audio> find() throws Exception;
	public List<Audio> findCollectAudio(User user) throws Exception;
	public   Audio findAudioById(int audio_id)throws Exception;
	public void delete(Audio audio) throws Exception;
	
	
}
