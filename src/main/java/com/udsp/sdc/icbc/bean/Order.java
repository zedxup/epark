package com.udsp.sdc.icbc.bean;

import java.io.Serializable;

public class Order implements Serializable {

	/**
	 * 订单信息实体类
	 */
	private static final long serialVersionUID = 7940264962782592359L;
	 
	//订单号
	private String orderId;
	
	//车牌号
	private String plateNumber;
	
	//身份证号
	private String cardId;
	 
	//启程点
	private String startPoint;
	
	//到达点
	private String arrive;
	
	//预约开始时间
	private String oStartTime;
	
	//预约结束时间
	private String oEndTime;
	
	//停车开始时间
	private String tStartTime;
	
	//停车结束时间
	private String tEndTime;
	
	//支付状态
	private String payState;
	
	//支付金额
	private String payMoney;
	
	//订单状态
	private String orderState;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}

	public String getArrive() {
		return arrive;
	}

	public void setArrive(String arrive) {
		this.arrive = arrive;
	}

	public String getoStartTime() {
		return oStartTime;
	}

	public void setoStartTime(String oStartTime) {
		this.oStartTime = oStartTime;
	}

	public String getoEndTime() {
		return oEndTime;
	}

	public void setoEndTime(String oEndTime) {
		this.oEndTime = oEndTime;
	}

	public String gettStartTime() {
		return tStartTime;
	}

	public void settStartTime(String tStartTime) {
		this.tStartTime = tStartTime;
	}

	public String gettEndTime() {
		return tEndTime;
	}

	public void settEndTime(String tEndTime) {
		this.tEndTime = tEndTime;
	}

	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	public String getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
