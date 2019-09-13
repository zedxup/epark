package com.udsp.sdc.icbc.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udsp.sdc.icbc.bean.Order;
import com.udsp.sdc.icbc.dao.OrderDao;


@Service
public class OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	/**
	 * 
	 *订单信息列表
	 * 
	 * @param cardid
	 *            身份证号
	 * @return Order 订单信息实例
	 *
	 */
	public List<Order> queryOrder(String cardId,Integer orderState) {
		 List<Order> order=orderDao.queryOrder(cardId,orderState);
		return order;
	}

	public int updateOrderCancel(String orderId) {
		return orderDao.updateOrderCancel(orderId);
	}
	
	

}
