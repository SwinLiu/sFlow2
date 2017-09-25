package com.lyplay.sflow.api.dto;

public class CaptchaDto {
	private String captchaId;
	private String captchaSrc;
	
	public CaptchaDto() {
		super();
	}

	public CaptchaDto(String captchaId, String captchaSrc) {
		super();
		this.captchaId = captchaId;
		this.captchaSrc = captchaSrc;
	}
	
	public String getCaptchaId() {
		return captchaId;
	}
	public void setCaptchaId(String captchaId) {
		this.captchaId = captchaId;
	}
	public String getCaptchaSrc() {
		return captchaSrc;
	}
	public void setCaptchaSrc(String captchaSrc) {
		this.captchaSrc = captchaSrc;
	}
	
	
}
