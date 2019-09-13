package com.udsp.sdc.icbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udsp.sdc.icbc.bean.Order;
import com.udsp.sdc.icbc.bean.Park;
import com.udsp.sdc.icbc.dao.ParkMapper;

@Service
public class ParkService {
	
	@Autowired
	private  ParkMapper park;
	
	/**ss
	 * 
	 *订单信息列表
	 * 
	 * @param cardid
	 *            身份证号
	 * @return Order 订单信息实例
	 *
	 */
	public List<Park> queryPark(String cardId) {
		 List<Park> Park=park.queryPark(cardId);
		return Park;
	}

	
	

}



