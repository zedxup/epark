package com.udsp.sdc.icbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udsp.sdc.icbc.bean.UserInfo;
import com.udsp.sdc.icbc.dao.SelectMapper;

/**
 * 查询星级、车牌服务
 * @author sunqi
 * @data 2019-8-22
 * */

@Service
public class SelectService {

@Autowired
private SelectMapper selectMapper;

public UserInfo selectUser(String cardId) {
	return selectMapper.queryUserInfo(cardId);
	}		
}

