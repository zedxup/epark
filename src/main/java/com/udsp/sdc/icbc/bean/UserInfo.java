package com.udsp.sdc.icbc.bean;

import java.io.Serializable;

/**
 *用户信息实体类
 *@author liyang
 *@date 2019-08-20
 */
public class UserInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4255827595893637942L;

	//用户Id
	private String userId;
	
	//用户身份证
	private String cardId;
	//用户姓名
	private String userName;
		
	//微信端的用户ID
	private String wxUserId;
	
	//用户星级
	private String userStar;
	
	//用户积分
	private String intgral;
	
	//车牌信息
	private String plate;
	
	//车辆信息
	private String car;
	
	//登陆状态
	private Integer state;
	
	private TokenInfo tokenInfo;

	
	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

	public String getUserStar() {
		return userStar;
	}

	public void setUserStar(String userStar) {
		this.userStar = userStar;
	}

	public String getIntgral() {
		return intgral;
	}

	public void setIntgral(String intgral) {
		this.intgral = intgral;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public TokenInfo getTokenInfo() {
		return tokenInfo;
	}

	public void setTokenInfo(TokenInfo tokenInfo) {
		this.tokenInfo = tokenInfo;
	}
	
	
	
	
}
