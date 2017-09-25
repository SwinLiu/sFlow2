package com.lyplay.sflow.api.dto;

public class RsaKeyDto {
	private String rsaPublicKeyId;
	private String rsaPublicKey;
	
	public RsaKeyDto() {
		super();
	}

	public RsaKeyDto(String rsaPublicKeyId, String rsaPublicKey) {
		super();
		this.rsaPublicKeyId = rsaPublicKeyId;
		this.rsaPublicKey = rsaPublicKey;
	}

	public String getRsaPublicKeyId() {
		return rsaPublicKeyId;
	}

	public void setRsaPublicKeyId(String rsaPublicKeyId) {
		this.rsaPublicKeyId = rsaPublicKeyId;
	}

	public String getRsaPublicKey() {
		return rsaPublicKey;
	}

	public void setRsaPublicKey(String rsaPublicKey) {
		this.rsaPublicKey = rsaPublicKey;
	}
	
}
