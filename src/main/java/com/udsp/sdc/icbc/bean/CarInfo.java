package com.udsp.sdc.icbc.bean;

import java.io.Serializable;

public class CarInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8384462152251544893L;
	
	//park_id
	private String parkId;
	
	//车牌号
	private String plate;
	
	//车辆信息
	private String car;
	
	//身份证号
	private String cardId;
	
	//车牌状态
	private String state;
	
	
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

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

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
