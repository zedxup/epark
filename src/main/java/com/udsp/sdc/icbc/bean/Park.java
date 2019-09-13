package com.udsp.sdc.icbc.bean;

import java.io.Serializable;

public class Park implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		//停车ID
		private String parkId;
		//用户车牌
		private String plate;
		//用户身份证
		private String cardId;
		//用户车型
		private String car;
		//登陆状态
		private Integer state;
		
		private TokenInfo tokenInfo;
		
		public String getParkId() {
			return parkId;
		}

		public void setParkId(String parkId) {
			this.parkId = parkId;
		}
		public String getPlate() {
			return plate;
		}

		public void setPlate(String plate) {
			this.plate = plate;
		}

		public String getCardId() {
			return cardId;
		}

		public void setCardId(String cardId) {
			this.cardId = cardId;
		}
		
		public String getCar() {
			return car;
		}

		public void setCar(String car) {
			this.car = car;
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
