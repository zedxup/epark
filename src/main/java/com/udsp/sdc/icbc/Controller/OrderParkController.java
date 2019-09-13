package com.udsp.sdc.icbc.Controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udsp.sdc.icbc.bean.CapUser;
import com.udsp.sdc.icbc.bean.CarInfo;
import com.udsp.sdc.icbc.bean.ParkInfo;
import com.udsp.sdc.icbc.comm.GeneralResponse;
import com.udsp.sdc.icbc.service.OrderParkService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
public class OrderParkController {
	
	@Resource
	private OrderParkService orderParkService;
	@ApiOperation(value = "停车预约", notes = "停车预约")
	@PostMapping(value = "/parking/orderinfo")
	public GeneralResponse orderPark(@Valid @RequestBody ParkInfo parkInfo){
		return GeneralResponse.success("停车场预约成功！",orderParkService.orderParking(parkInfo));
	}
	
	@ApiOperation(value = "更新停车信息", notes = "更新停车信息")
	@PostMapping(value = "/parking/updateorderinfo")
	public GeneralResponse updateOrderPark(@Valid @RequestBody ParkInfo parkInfo){
		return GeneralResponse.success("停车信息更新成功！",orderParkService.updateOrderParking(parkInfo));
	}
	@ApiOperation(value = "新增车牌信息", notes = "新增车牌信息")
	@PostMapping(value = "/carinfo/addplate")
	public GeneralResponse addCarPlate(@Valid @RequestBody CarInfo carInfo){
		return GeneralResponse.success("新增车牌信息",
				orderParkService.insertCarInfo(carInfo));
	}
}
