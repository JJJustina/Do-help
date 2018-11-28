package com.doHelp.dao;

import org.springframework.stereotype.Repository;

import com.doHelp.model.User;
import com.doHelp.model.UserRedis;

@Repository
//@Repository存储层Bean 它用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean
public class UserRedisDAO extends IRedisDAO<User> {

	private static final String REDIS_KEY = "com.doHelp.model.User";

	@SuppressWarnings("static-access")
	@Override
	protected String getRedisKey() {
		return this.REDIS_KEY;
	}
}
