package com.doHelp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doHelp.dao.DAOFactory;
import com.doHelp.model.Audio;
import com.doHelp.model.User;
import com.doHelp.service.AudioShareServices;

@Service
public class AudioShareServiceImp  implements  AudioShareServices {
	@Autowired
	DAOFactory  DAOFactory;
	
	@SuppressWarnings("static-access")
	@Override
	public void save(Audio audio) throws Exception {
		DAOFactory.getAudioShareDAO().save(audio);
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public  Audio findAudioById(int audio_id)throws Exception {
		return DAOFactory.getAudioShareDAO().findAudioById(audio_id);
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public void saveSnap(String time,int audio_id,int user_id) throws Exception {
		DAOFactory.getAudioShareDAO().saveSnap( time, audio_id, user_id);
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public void saveCollectAudio(String time,int audio_id,int user_id) throws Exception {
		DAOFactory.getAudioShareDAO().saveSnap( time, audio_id, user_id);
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public void seeCount(Audio audio) throws Exception {
		DAOFactory.getAudioShareDAO().seeCount(audio);
	}

	
    @SuppressWarnings("static-access")
    @Override
	public void snapCount(Audio audio) throws Exception{
		 DAOFactory.getAudioShareDAO().snapCount(audio);
   }
	
    
	@SuppressWarnings("static-access")
	@Override
	public void update(Audio audio) throws Exception {
		 DAOFactory.getAudioShareDAO().update(audio);
	}

	
	@SuppressWarnings("static-access")
	@Override
	public List<Audio> find() throws Exception {
		 return DAOFactory.getAudioShareDAO().find();
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public List<Audio> findCollectAudio(User user) throws Exception{
		 return DAOFactory.getAudioShareDAO().find();
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public void delete(Audio audio) throws Exception {
		DAOFactory.getAudioShareDAO().delete(audio);
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public void reportCount(Audio audio) throws Exception {
		DAOFactory.getAudioShareDAO().reportCount(audio);
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public void saveReport(int reportType_id,int audio_id,int user_id) throws Exception {
		DAOFactory.getAudioShareDAO().saveReport(reportType_id,audio_id,user_id);
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public void  audioStatus(String  audio_is_verify_code ,int audio_id )throws Exception{
		DAOFactory.getAudioShareDAO().audioStatus(audio_is_verify_code,audio_id);
	}
}
