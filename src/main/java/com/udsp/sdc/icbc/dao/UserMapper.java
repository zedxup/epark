package com.udsp.sdc.icbc.dao;

import org.apache.ibatis.annotations.Param;

import com.udsp.sdc.icbc.bean.UserInfo;


/**
 *用户信息接口 
 *
 */

public interface UserMapper {
	//查询用户是否存在
	UserInfo queryUserInfo(@Param("userName") String userName,
						 @Param("cardId") String cardId);
	//查询用户是否是第一次登陆
	int queryUserFirst(@Param("wxUserId") String wxUserId);
	
	//用户第一次登陆，需要把微信用户id存入相应用户
	int updateWXUserId(@Param("wxUserId") String wxUserId,
			           @Param("cardId") String cardId,
			           @Param("state") int state);
	
    UserInfo existUser(@Param("wxUserId") String wxUserId);
}
