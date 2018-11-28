package com.doHelp.service;

import com.doHelp.model.User;

public interface UserServices {

    public User findByPhone(String user_phone) throws Exception ;
    public User findByid(int user_id) throws Exception;
	public void save(User user) throws Exception;
	public void update(User u) throws Exception ;
	public void updateStatus(User u) throws Exception;
	public void updateTime(User u) throws Exception ;
	public void updatePassWord(User u) throws Exception ;
	public String findPwByPhone(String user_phone) throws Exception;
	public String findPwByEmail(String user_email) throws Exception;
	
	
}
