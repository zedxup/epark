package com.udsp.sdc.icbc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.udsp.sdc.icbc.bean.Order;
import com.udsp.sdc.icbc.bean.Park;

public interface ParkMapper {

	//查询用户的订单信息
	List<Park> queryPark(@Param("cardId") String cardId);

}
