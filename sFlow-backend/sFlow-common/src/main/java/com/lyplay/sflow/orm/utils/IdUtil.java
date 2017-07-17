package com.lyplay.sflow.orm.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class IdUtil {
	
	public static String getAutoGeneratedId(Object po) throws SecurityException, NoSuchMethodException{
		String autoGeneratedId = "";

		Field[] allFields = po.getClass().getDeclaredFields();
		
		for(Field f:allFields){
			
			if("serialVersionUID".equals(f.getName())){
				continue;
			}
			
			Id idAnno = f.getAnnotation(Id.class);
			if(idAnno == null){
				continue;
			}
			GeneratedValue generatedValueAnno = f.getAnnotation(GeneratedValue.class);
			if(generatedValueAnno == null){
				continue;
			}
			
			if(GenerationType.IDENTITY == generatedValueAnno.strategy() || GenerationType.TABLE == generatedValueAnno.strategy()){
				autoGeneratedId = f.getName();
				break;
			}
		}
		return autoGeneratedId;
	}
	
	
	public static void setAutoIncreamentIdValue(Object po,String autoGeneratedId,Object idValue) throws Exception, NoSuchMethodException{
		String setterName = "set" + ColumnNameUtil.capitalize(autoGeneratedId);
		Method setter = po.getClass().getDeclaredMethod(setterName, idValue.getClass());
		setter.invoke(po, idValue);
	}
	
}
