package com.lyplay.sflow.api.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author lyplay
 */
public class UserParam {
	
	@NotBlank(message="{login.userName}")
    private String userName;
	
	@NotBlank(message="{login.password}")
    private String password;
    
	@NotBlank(message="{login.captchaCodeId}")
    private String captchaCodeId;
	
	@NotBlank(message="{login.captchaCode}")
    private String captchaCode;
    
//	@NotBlank(message="{login.rsaKeyId}")
//    private String rsaKeyId;

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

//	public String getRsaKeyId() {
//		return rsaKeyId;
//	}
//
//	public void setRsaKeyId(String rsaKeyId) {
//		this.rsaKeyId = rsaKeyId;
//	}
    
    
}
