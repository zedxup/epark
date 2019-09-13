package com.udsp.sdc.icbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udsp.sdc.icbc.dao.DeleteplateMapper;


/**
 * 删除车牌服务
 * @author sunqi
 * @data 2019-8-22
 * */

@Service
public class DeletePlateService {
	@Autowired
	private DeleteplateMapper deleteplateMapper;
	
	public int deleteplate(String plate) {
		return deleteplateMapper.updateUserPlate(plate);
		}

	}

