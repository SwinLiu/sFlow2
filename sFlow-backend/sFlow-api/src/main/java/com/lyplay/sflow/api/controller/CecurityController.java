package com.lyplay.sflow.api.controller;

import static com.lyplay.sflow.common.dto.RestResult.success;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lyplay.sflow.api.auth.AuthPassport;
import com.lyplay.sflow.api.dto.CaptchaDto;
import com.lyplay.sflow.api.dto.RsaKeyDto;
import com.lyplay.sflow.common.dto.RestResult;
import com.lyplay.sflow.common.util.CaptchaImageCode;
import com.lyplay.sflow.common.util.RSAUtil;
import com.lyplay.sflow.service.CacheService;

/**
 * 
 * Cecurity Rest API Functions
 * 
 * @author lyplay.com
 *
 */

@RestController
@EnableAutoConfiguration
public class CecurityController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CacheService cacheService;

	@AuthPassport(validate = false)
	@RequestMapping(value = "/api/captcha/{size}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public RestResult getCaptchaPic(@PathVariable("size") String size)
			throws IOException {

		int[] params = { CaptchaImageCode.DEFAULT_WIDTH,
				CaptchaImageCode.DEFAULT_HEIGHT,
				CaptchaImageCode.DEFAULT_CODECOUNT,
				CaptchaImageCode.DEFAULT_LINECOUNT };

		if (StringUtils.isNotEmpty(size)) {
			String[] sizeGrps = size.split("x");
			if (ArrayUtils.isNotEmpty(sizeGrps)) {
				for (int i = 0; i < sizeGrps.length; i++) {
					try {
						params[i] = Integer.valueOf(sizeGrps[i]);
					} catch (NumberFormatException e) {
						logger.error(" captcha size format NumberFormatException. size : {} .", size);
						logger.error(e.getMessage(), e);
					}
				}
			}
		}

		CaptchaImageCode captchaImageCode = new CaptchaImageCode(params[0], params[1],
				params[2], params[3]);
		String captchaUuid = UUID.randomUUID().toString();
		cacheService.setString(captchaUuid, captchaImageCode.getCode());
		return success(new CaptchaDto(captchaUuid, captchaImageCode.getBase64Str()));

	}
	
	@AuthPassport(validate = false)
	@RequestMapping(value = "/api/secret", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public RestResult getEncryptKey() throws Exception {
		Map<String, String> map = RSAUtil.getKeyPair();
		String rsaPublicKey = map.get(RSAUtil.PUBLIC_KEY);
		String rsaprivateKey = map.get(RSAUtil.PRIVATE_KEY);
		String rsaPublicKeyUuid = UUID.randomUUID().toString();
		cacheService.setString(RSAUtil.PUBLIC_KEY + "_" + rsaPublicKeyUuid, rsaPublicKey);
		cacheService.setString(RSAUtil.PRIVATE_KEY + "_" + rsaPublicKeyUuid, rsaprivateKey);
		return success(new RsaKeyDto(rsaPublicKeyUuid, rsaPublicKey));
	}
	
	
}
