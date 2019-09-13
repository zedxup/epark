package com.udsp.sdc.icbc.Controller;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.udsp.sdc.icbc.service.DeletePlateService;
import com.udsp.sdc.icbc.service.UserInfoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
public class DeletePlateController {
	
    @Resource
    private DeletePlateService deleteplateservice;//方法名
	
//	@Resource
//	private UserInfoService userInfoService;

	@ApiOperation(value = "删除车牌", notes = "删除车牌") //功能解释
	@PostMapping(value = "/user/delete") ///整体功能/具体行为的名词命名
	public int deleteplate(@RequestParam(value = "plate", required = true)  String plate) {
//		String cardId1=userInfoService.getUserInfo().getCardId();
		return deleteplateservice.deleteplate(plate);
	}
}
