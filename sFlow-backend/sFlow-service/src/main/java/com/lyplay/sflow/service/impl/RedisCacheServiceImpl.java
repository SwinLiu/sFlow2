package com.lyplay.sflow.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.lyplay.sflow.service.CacheService;

@Service
public class RedisCacheServiceImpl implements CacheService {

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Resource(name = "stringRedisTemplate")
	ValueOperations<String, String> valOpsStr;

	@Autowired
	RedisTemplate<Object, Object> redisTemplate;
	
	@Resource(name = "redisTemplate")
	ValueOperations<Object, Object> valOpsObj;
	
	@Override
	public String getString(String key) {
		return valOpsStr.get(key);
	}

	@Override
	public void setString(String key, String value) {
		valOpsStr.set(key,value);
	}

	@Override
	public void deleteStr(String key) {
		stringRedisTemplate.delete(key);
	}

	@Override
	public Object getObject(Object key) {
		return valOpsObj.get(key);
	}

	@Override
	public void setObject(Object key, Object value) {
		valOpsObj.set(key, value);
	}

	@Override
	public void deleteObject(Object key) {
		redisTemplate.delete(key);
	}


}
