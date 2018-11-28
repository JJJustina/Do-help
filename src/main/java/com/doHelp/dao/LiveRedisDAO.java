package com.doHelp.dao;

import org.springframework.stereotype.Repository;

import com.doHelp.model.LiveRedis;

@Repository
public class LiveRedisDAO extends IRedisDAO<LiveRedis> {

	private static final String REDIS_KEY="com.doHelp.model.LiveRedis";

	@SuppressWarnings("static-access")
	@Override
	protected String getRedisKey() {
		return this.REDIS_KEY;
	}
	
	
}
