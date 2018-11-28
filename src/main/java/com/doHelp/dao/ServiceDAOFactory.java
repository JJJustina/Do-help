package com.doHelp.dao;

import com.doHelp.service.ArticleShareServices;
import com.doHelp.service.AudioShareServices;
import com.doHelp.service.RemarksServices;
import com.doHelp.service.UserServices;
import com.doHelp.service.VideoShareServices;
import com.doHelp.service.impl.*;

public class ServiceDAOFactory {

	public static UserServices getUserDAO(){
		return new UserServiceImp();
	}
	public static RemarksServices getRemarksDAO(){
		return new RemarksServiceImp();
	}
	public static VideoShareServices getVideoShareDAO() {
		return new VideoShareServiceImp();
	}
	public static AudioShareServices getAudioShareDAO() {
		return new AudioShareServiceImp();
	}
	public static ArticleShareServices getArticleShareDAO() {
		return new ArticleShareServiceImp();
	}
}
