package com.lyplay.sflow.api.exception;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lyplay.sflow.api.util.TranslatorHelper;

@ControllerAdvice
public class ExceptionTranslator {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = AuthenticateException.class)
    public void loginException(HttpServletResponse response) {
    	try {
			response.sendError(HttpStatus.UNAUTHORIZED.value());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
    }

    /**
     * 获取错误信息
     * 
     * @param exception
     * @return
     */

    private String getException(Exception exception) {
        if (exception instanceof AuthenticateException) {
            return TranslatorHelper.get("system.error.authenticate");
        } else {
            return TranslatorHelper.get("system.error.internal");
        }

    }

}
