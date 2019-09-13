package com.udsp.sdc.icbc.bean;

import java.io.Serializable;

public class TokenInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7589690771378702211L;

	private String tokenId;
	
	private String userId;

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
