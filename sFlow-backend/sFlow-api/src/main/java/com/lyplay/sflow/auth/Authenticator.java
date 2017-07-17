package com.lyplay.sflow.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyplay.sflow.common.UserSession;
import com.lyplay.sflow.exception.AuthenticateException;

/**
 * 
 * @author lyplay
 *
 */

public interface Authenticator {

	// 认证成功返回UserSession，认证失败抛出异常，无认证信息返回null:
	UserSession authenticate(HttpServletRequest request, HttpServletResponse response) throws AuthenticateException;
    
}
