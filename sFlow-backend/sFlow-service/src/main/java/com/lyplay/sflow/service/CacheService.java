package com.lyplay.sflow.service;

import java.util.concurrent.TimeUnit;

public interface CacheService {
	
	public String getString(String key);
	public void setString(String key, String value);
	public void setString(String key, String value, long timeout, TimeUnit unit);
	public void deleteStr(String key);
	
	public Object getObject(Object key);
	public void setObject(Object key, Object value);
	public void setObject(Object key, Object value, long timeout, TimeUnit unit);
	public void deleteObject(Object key);
	
}
