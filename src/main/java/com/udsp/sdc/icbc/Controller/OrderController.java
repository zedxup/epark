package com.udsp.sdc.icbc.Controller;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udsp.sdc.icbc.bean.Order;
import com.udsp.sdc.icbc.comm.GeneralResponse;
import com.udsp.sdc.icbc.service.OrderService;
import com.udsp.sdc.icbc.service.UserInfoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

	@Resource
	private OrderService orderService;
	
	@Resource
	private UserInfoService userInfoService;
	
	@ApiOperation(value = "订单列表", notes = "订单列表")
	@PostMapping(value = "/order/query")
	public GeneralResponse orderQuery(@RequestParam(value = "cardId", required = true)  String cardId,
			                          @RequestParam(value = "orderState", required = true)  Integer orderState) {
		//String cardId=userInfoService.getUserInfo().getCardId();
		//System.out.print(cardId);
//		if(null==cardId){
//		return 	GeneralResponse.success("该用户暂未登录");
//		}
		List<Order> order = orderService.queryOrder(cardId,orderState);
		if(null==order){
			return GeneralResponse.success("该用户暂无订单");
		}
		return GeneralResponse.success(order);
		
	}


	@ApiOperation(value = "订单取消", notes = "订单取消")
	@PostMapping(value = "/order/cancle")
	public int orderCancle(@RequestParam(value = "orderId", required = true)  String orderId) {	
		return orderService.updateOrderCancel(orderId);
		
//		String orderId = "0041d32922ae43d194dc9b267c823727";
//		int order = orderService.updateOrderCancel(orderId);
//		if(null==order){
//			return GeneralResponse.success("该用户暂无订单");
//		}
//		return GeneralResponse.success(order);
	}
	
	
	
}
