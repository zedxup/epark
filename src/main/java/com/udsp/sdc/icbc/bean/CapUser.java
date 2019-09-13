package com.udsp.sdc.icbc.bean;

import java.io.Serializable;

public class CapUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7895166736353673959L;
	
	
	//用户身份证
	private String cardId;
	//用户姓名
	private String userName;
	
	//微信端的用户ID
	private String wxUserId;
	
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getWxUserId() {
		return wxUserId;
	}
	public void setWxUserId(String wxUserId) {
		this.wxUserId = wxUserId;
	}
	
	
	
}
