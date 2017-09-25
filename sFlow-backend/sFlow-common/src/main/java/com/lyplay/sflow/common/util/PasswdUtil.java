package com.lyplay.sflow.common.util;

import org.apache.commons.lang3.StringUtils;

public class PasswdUtil {
	
	
	public static String getPasswd(String rsaPrivateKey, String passwd) throws Exception{
		return getPasswd(rsaPrivateKey, null, passwd);
	}

	public static String getPasswd(String rsaPrivateKey, String loginAccount, String passwd) throws Exception{
		
		String password = StringUtils.trim(passwd);  //取值  
		password= password.replaceAll(" ","+");//将空格转为+号  
		String data = RSAUtil.decryptByPrivateKey(password, rsaPrivateKey);
		String[] passwdGroup = data.split(Constant.SPLIT_STR);
		String realPasswd = StringUtils.EMPTY;
		if(StringUtils.isNotEmpty(loginAccount)){
			if(StringUtils.equals(passwdGroup[0], loginAccount)){
				realPasswd = passwdGroup[1];
			}
		} else {
			realPasswd = passwdGroup[0];
		}
		
		return realPasswd;
	}
	
}
