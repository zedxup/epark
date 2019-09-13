package com.udsp.sdc.icbc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.udsp.sdc.icbc.bean.Order;

/**
 *订单信息接口 
 *
 */

public interface OrderDao {

	//查询用户的订单信息
	List<Order> queryOrder(@Param("cardId") String cardId,
			               @Param("orderState") Integer orderState
			);
	
	//取消订单
	int updateOrderCancel(@Param("orderId") String orderId);
}
