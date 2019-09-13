package com.udsp.sdc.icbc.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.udsp.sdc.icbc.bean.UserInfo;


/**
 * 
 * 获取用户信息服务
 * 
 * @author 李炀
 * @date 2019-08-22
 *
 *
 */
@Service
public class UserInfoService {

	/**
	 * 
	 * 获取用户信息
	 * 
	 * @return userInfo 用户信息实例
	 */
	public UserInfo getUserInfo() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
		return (UserInfo) request.getSession().getAttribute(LoginService.USER_INFO);
	}
}
