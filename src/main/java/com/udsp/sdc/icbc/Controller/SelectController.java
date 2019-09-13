package com.udsp.sdc.icbc.Controller;

import javax.annotation.Resource; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.udsp.sdc.icbc.comm.GeneralResponse;
import com.udsp.sdc.icbc.service.SelectService;
import com.udsp.sdc.icbc.service.UserInfoService;

import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping("/api/v1")
public class SelectController {
		@Resource
		private UserInfoService userInfoService;
		@Resource
		private SelectService selectservice;
		
		@ApiOperation(value = "查询", notes = "查询")//功能解释
		@GetMapping(value = "/select/serviceStarts")
		public GeneralResponse selectuser(@RequestParam(value = "cardId", required = true) String cardId) {
			//String cardId1=userInfoService.getUserInfo().getCardId();
			return GeneralResponse.success(selectservice.selectUser(cardId));
		}
	
}
