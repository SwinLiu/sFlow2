package com.lyplay.sflow.service;

public interface CacheService {
	
	public String getString(String key);
	public void setString(String key, String value);
	public void deleteStr(String key);
	
	public Object getObject(Object key);
	public void setObject(Object key, Object value);
	public void deleteObject(Object key);
	
}
