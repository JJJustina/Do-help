package com.doHelp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doHelp.dao.LiveRedisDAO;
import com.doHelp.model.LiveRedis;
@Service
public class LiveService {
	@Autowired
	LiveRedisDAO liveRedisDAO;

	public List<LiveRedis> getAll() {
		List<LiveRedis> list = liveRedisDAO.getAll();
		return list;
	}

	public void save(LiveRedis liveRedis) {
		liveRedisDAO.put(liveRedis.getKeyname(), liveRedis, -1);
	}
}
