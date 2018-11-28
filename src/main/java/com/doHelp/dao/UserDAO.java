package com.doHelp.dao;

import com.doHelp.model.User;

public interface UserDAO {
	    public User findByPhone(String user_phone) throws Exception ;
	    public User findByid(int user_id) throws Exception;
		public void save(User user) throws Exception;
		public void update(User user) throws Exception ;
		public void updateStatus(User user) throws Exception;
		public void updateTime(User user) throws Exception ;
		public void updatePassWord(User user) throws Exception ;
		public String findPwByPhone(String user_phone) throws Exception;
		public String findPwByEmail(String user_email) throws Exception;
}
