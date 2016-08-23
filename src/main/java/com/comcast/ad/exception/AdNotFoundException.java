package com.comcast.ad.exception;

public class AdNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 101091L;
	
	private String partnerId;
	public AdNotFoundException(String partnerId) {
		this.partnerId = partnerId;
		
	}
	public String getPartnerId() {
		return partnerId;
	}
}
