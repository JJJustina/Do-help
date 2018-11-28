package com.doHelp.dao;

import org.springframework.stereotype.Repository;

import com.doHelp.model.Managers;

@Repository
//@Repository存储层Bean 它用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean
public class ManagerRedisDAO  extends IRedisDAO<Managers>{
	private static final String REDIS_KEY = "com.doHelp.model.Manager";

	@SuppressWarnings("static-access")
	@Override
	protected String getRedisKey() {
		return this.REDIS_KEY;
	}
}
