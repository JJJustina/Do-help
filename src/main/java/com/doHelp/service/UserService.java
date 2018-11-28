package com.doHelp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doHelp.dao.UserRedisDAO;
import com.doHelp.model.User;

@Service
public class UserService {

	@Autowired
	UserRedisDAO userDAO;

	public List<User> getAll() {
		List<User> list = userDAO.getAll();
		return list;
	}

	/**
	 * 添加
	 * @param userRedis
	 */
	public void save(User user) {
		userDAO.put(user.getUser_phone(), user, -1);
	}

	/**
	 * 判断账号是否为空，密码是否正确
	 * @param user
	 * @return
	 */
	public void delete(User user) {
		userDAO.remove(user.getUser_phone());
		
	}
	public boolean isUser(User user) {
		boolean isUser = true;
		User realUser = userDAO.get(user.getUser_phone());
		if (realUser == null) {
			isUser = false;
		} else {
			if (!realUser.getUser_password().equals(user.getUser_password())) {
				isUser = false;
			}
		}
		return isUser;
	}
}
