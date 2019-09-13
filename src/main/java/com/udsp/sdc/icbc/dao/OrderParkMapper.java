package com.udsp.sdc.icbc.dao;

import org.apache.ibatis.annotations.Param;

import com.udsp.sdc.icbc.bean.CarInfo;
import com.udsp.sdc.icbc.bean.ParkInfo;
import com.udsp.sdc.icbc.bean.UserInfo;

public interface OrderParkMapper {
	
	//插入车辆预约信息
	int insertParking(ParkInfo parkInfo);
	
	//更新车辆停车信息
	int updateParking(ParkInfo parkInfo);
	
	//新增车辆信息
	int insertCarInfo(CarInfo carInfo);
}
