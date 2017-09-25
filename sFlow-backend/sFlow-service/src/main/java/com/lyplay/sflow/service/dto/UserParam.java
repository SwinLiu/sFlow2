package com.lyplay.sflow.service.dto;

/**
 * @author lyplay
 */
public class UserParam {
	
    private String userName;
    private String password;
    
    private String captchaCodeId;
    private String captchaCode;
    
    private String rsaKeyId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getCaptchaCodeId() {
		return captchaCodeId;
	}

	public void setCaptchaCodeId(String captchaCodeId) {
		this.captchaCodeId = captchaCodeId;
	}

	public String getCaptchaCode() {
		return captchaCode;
	}

	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}

	public String getRsaKeyId() {
		return rsaKeyId;
	}

	public void setRsaKeyId(String rsaKeyId) {
		this.rsaKeyId = rsaKeyId;
	}
    
    
}
