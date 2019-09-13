package com.udsp.sdc.icbc.Controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udsp.sdc.icbc.bean.CapUser;
import com.udsp.sdc.icbc.bean.UserInfo;
import com.udsp.sdc.icbc.comm.GeneralResponse;
import com.udsp.sdc.icbc.service.LoginService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@Resource
	private LoginService loginService;
	@ApiOperation(value = "用户登录", notes = "用户登录")
	@PostMapping(value = "/user/login")
	public GeneralResponse userLogin(@Valid @RequestBody CapUser capUser) {
		UserInfo userInfo = loginService.login(capUser);
		if(null==userInfo){
			return GeneralResponse.success();
		}
		return GeneralResponse.success(userInfo);
	}
	
	
	@ApiOperation(value = "用户检验是否登录过", notes = "用户检验是否登录过")
	@PostMapping(value = "/user/exist")
	public GeneralResponse userLogin(@RequestParam(value = "cardId", required = true)  String cardId) {
		UserInfo userInfo = loginService.existUser(cardId);
		if(null==userInfo){
			return GeneralResponse.success();
		}
		return GeneralResponse.success(userInfo);
	}
	
	
}
