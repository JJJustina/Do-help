package com.doHelp.dao;

import org.springframework.stereotype.Repository;

import com.doHelp.dao.UserDAO;
import com.doHelp.dao.impl.ArticleShareDAOImp;
import com.doHelp.dao.impl.AudioShareDAOImp;
import com.doHelp.dao.impl.RemarksDAOImp;
import com.doHelp.dao.impl.UserDAOImp;
import com.doHelp.dao.impl.VideoShareDAOImp;
@Repository
public class DAOFactory {
	public static UserDAO getUserDAO(){
		return new UserDAOImp();
	}
	
	public static RemarksDAOImp getRemarksDAO(){
		return new RemarksDAOImp();
	}
	public static  VideoShareDAOImp getVideoShareDAO() {
		return new VideoShareDAOImp();
	}
	public static AudioShareDAOImp  getAudioShareDAO() {
		 return new AudioShareDAOImp();
	}
	public static ArticleShareDAOImp  getArticleShareDAO() {
		 return new ArticleShareDAOImp();
	}
	}
