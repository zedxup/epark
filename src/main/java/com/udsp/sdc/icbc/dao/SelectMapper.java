package com.udsp.sdc.icbc.dao;

import org.apache.ibatis.annotations.Param;

import com.udsp.sdc.icbc.bean.UserInfo;

public interface SelectMapper  {
	UserInfo queryUserInfo( @Param("cardId") String cardId);
}
