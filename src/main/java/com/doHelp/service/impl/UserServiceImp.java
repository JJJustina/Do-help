package com.doHelp.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doHelp.dao.DAOFactory;
import com.doHelp.model.User;
import com.doHelp.service.UserServices;
@Service
public class UserServiceImp implements UserServices{

	@Autowired
	DAOFactory  DAOFactory;
	
	@SuppressWarnings("static-access")
	@Override
	public void save(User user) throws Exception {
		DAOFactory.getUserDAO().save(user);
	}

	@SuppressWarnings("static-access")
	@Override
	public String findPwByEmail(String user_email) throws Exception {
		return DAOFactory.getUserDAO().findPwByEmail(user_email);
	}

    @SuppressWarnings("static-access")
    @Override
	public String findPwByPhone(String user_phone) throws Exception{
		return DAOFactory.getUserDAO().findPwByPhone(user_phone);
   }
	
	@SuppressWarnings("static-access")
	@Override
	public User findByid(int user_id) throws Exception {
		return DAOFactory.getUserDAO().findByid(user_id);
	}

	@SuppressWarnings("static-access")
	@Override
	public void update(User u) throws Exception {
		DAOFactory.getUserDAO().update(u);
	}
	@SuppressWarnings("static-access")
	@Override
	public void updateStatus(User u) throws Exception{
		DAOFactory.getUserDAO().updateStatus(u);
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void updateTime(User u) throws Exception {
		DAOFactory.getUserDAO().updateTime(u);
	}
	@SuppressWarnings("static-access")
	@Override
	public void updatePassWord(User u) throws Exception {
		DAOFactory.getUserDAO().updatePassWord(u);
	}
    @SuppressWarnings("static-access")
	@Override
    public User findByPhone(String user_phone) throws Exception {
    	return DAOFactory.getUserDAO().findByPhone(user_phone);
    }
}
