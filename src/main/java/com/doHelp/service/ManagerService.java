package com.doHelp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doHelp.dao.ManagerRedisDAO;
import com.doHelp.model.Managers;

@Service
public class ManagerService {

	
	@Autowired 
	ManagerRedisDAO  managerDAO;

	public List<Managers> getAll() {
		List<Managers> list = managerDAO.getAll();
		return list;
	}

	/**
	 * 添加
	 * @param userRedis
	 */
	public void save(Managers managers) {
		managerDAO.put(managers.getManager_phone(), managers, -1);
	}

	/**
	 * 判断账号是否为空，密码是否正确
	 * @param user
	 * @return
	 */
	public boolean isManager(Managers managers) {
		boolean isUser = true;
		Managers realUser = managerDAO.get(managers.getManager_phone());
		if (realUser == null) {
			isUser = false;
		} else {
			if (!realUser.getManager_password().equals(managers.getManager_password())) {
				isUser = false;
			}
		}
		return isUser;
	}
}
