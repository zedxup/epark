package com.udsp.sdc.icbc.bean;

import java.io.Serializable;
import java.util.Date;

public class ParkInfo implements Serializable{
	/**
	 * 停车订单信息
	 * @autor liyang
	 */
	private static final long serialVersionUID = -5271055067339103913L;

	//停车订单号
	private String orderId;
	
	//停车场名称
	private String parkName;
	
	//车牌号
	private String plateNumber;
	
	//用户身份证号
	private String cardId;
	
	//启程点
	private String startPoint;
	
	//终点，停车场地址
	private String arrive;
	
	//停车预约时间
	private String ostartTime;
	
	//停车预约时间
	private String oendTime;
	
	//开始停车时间
	private String startTime;
	
	//停车结束时间
	private String endTime;
	
	//支付金额
	private Double payMoney;
	
	//订单状态
	private Integer orderState;

	
	
	public String getOendTime() {
		return oendTime;
	}

	public void setOendTime(String oendTime) {
		this.oendTime = oendTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
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

	public String getOstartTime() {
		return ostartTime;
	}

	public void setOstartTime(String ostartTime) {
		this.ostartTime = ostartTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	
	
}
