package com.lyplay.sflow.api.controller;

import static com.lyplay.sflow.common.dto.RestResult.success;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lyplay.sflow.common.dto.RestResult;

/**
 * 
 * @author lyplay
 *
 */
@RestController
@EnableAutoConfiguration
public class MailController {

	@RequestMapping(value = "/api/sendmail", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public RestResult save(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "passwd", required = true) String passwd){
		return success();
	}
	
	
	@RequestMapping(value = "/api/{seckillId}/detail", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public RestResult save(@PathVariable("seckillId") Long seckillId,
			@CookieValue(value = "killPhone", required = false) Long phone) {

		return null;
	}
	
	
	
	
}
