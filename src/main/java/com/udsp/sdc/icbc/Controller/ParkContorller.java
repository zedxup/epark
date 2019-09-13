package com.udsp.sdc.icbc.Controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udsp.sdc.icbc.bean.Park;
import com.udsp.sdc.icbc.comm.GeneralResponse;
import com.udsp.sdc.icbc.service.ParkService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
public class ParkContorller {
	
	@Resource
	private ParkService parkService;
	
	@ApiOperation(value = "获取用户车牌信息", notes = "获取用户车牌信息")
	@PostMapping(value = "/user/Park")
	public GeneralResponse userLogin(@RequestParam(value = "cardId", required = true)  String cardId) {
		List<Park> park =parkService.queryPark(cardId) ;
		if(null==park){
			return GeneralResponse.success();
		}
		return GeneralResponse.success(park);
	}
	

}
