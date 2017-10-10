package com.lyplay.sflow.api.exception;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyplay.sflow.api.util.TranslatorHelper;
import com.lyplay.sflow.common.dto.RestResult;

@ControllerAdvice
public class ExceptionTranslator {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RestResult commonException(Exception e, HttpServletResponse response) {
    	logger.error(e.getMessage(), e);
    	RestResult result = null;
    	try {
    		result = RestResult.fail();
    		if (e instanceof AuthenticateException) {
    			response.sendError(HttpStatus.UNAUTHORIZED.value());
    			result.setMessage(TranslatorHelper.get("system.error.authenticate"));
            } else {
            	response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
            	result.setMessage(TranslatorHelper.get("system.error.internal"));
            }
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
    	return result;
    }

}
