package com.udsp.sdc.icbc.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udsp.sdc.icbc.bean.CarInfo;
import com.udsp.sdc.icbc.bean.ParkInfo;
import com.udsp.sdc.icbc.bean.UserInfo;
import com.udsp.sdc.icbc.dao.OrderParkMapper;

@Service
public class OrderParkService {
	
	@Resource
	private UserInfoService userInfoService;
	
	@Autowired
	private OrderParkMapper orderParkMapper;
	/**
	 *车辆预约 
	 */
	public int orderParking(ParkInfo parkInfo){
		String orderId = UUID.randomUUID().toString().replaceAll("-", "");
		parkInfo.setOrderId(orderId);
//		System.out.println("******"+userInfoService.getUserInfo().getCardId());
//		parkInfo.setCardId(userInfoService.getUserInfo().getCardId());
		return orderParkMapper.insertParking(parkInfo);
	}
	
	/**
	 *更新车辆信息 
	 */
	public int updateOrderParking(ParkInfo parkInfo){
		return orderParkMapper.updateParking(parkInfo);
	}
	/**
	 *新增车辆信息 
	 */
	public int insertCarInfo(CarInfo carInfo){
		String parkId = UUID.randomUUID().toString().replaceAll("-", "");
		carInfo.setParkId(parkId);
		carInfo.setState("1");
		//carInfo.setCardId(userInfoService.getUserInfo().getCardId());
		return orderParkMapper.insertCarInfo(carInfo);
	}
}
