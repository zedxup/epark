package com.udsp.sdc.icbc.service;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.udsp.sdc.icbc.bean.CapUser;
import com.udsp.sdc.icbc.bean.TokenInfo;
import com.udsp.sdc.icbc.bean.UserInfo;
import com.udsp.sdc.icbc.dao.UserMapper;


/**
 * 
 * 用户登录服务
 * 
 * @author 李炀
 * @date 2019-08-21
 *
 *
 */
@Service
public class LoginService {
	public static final String USER_INFO = "userInfo";	
	@Resource
	private LoginCacheService loginCacheService;
	
	@Autowired
	private UserMapper userMapper;
	/**
	 * 
	 * 用户登录 并缓存用户可以访问的方法
	 * 
	 * @param account
	 *            账号
	 * @param cardid
	 *            身份证号
	 * @return UserInfo 用户信息实例
	 * @throws CloneNotSupportedException
	 */
	public UserInfo login(CapUser capUser) {
		TokenInfo tokenInfo = null;
		UserInfo userInfo = new UserInfo();
		if(userMapper.queryUserFirst(capUser.getWxUserId())!=0&&
		   null!=userMapper.queryUserInfo(capUser.getUserName(), capUser.getCardId())){
			tokenInfo = assembleTokenInfo(capUser.getCardId());
			userInfo = assembleUserInfo(capUser, tokenInfo);
			setSession(tokenInfo.getTokenId(), userInfo);
			cacheInfo(tokenInfo.getTokenId(), tokenInfo);
		}else if(userMapper.queryUserFirst(capUser.getWxUserId())==0&&
			null!=userMapper.queryUserInfo(capUser.getUserName(), capUser.getCardId())){
			userMapper.updateWXUserId(capUser.getWxUserId(), capUser.getCardId(),1);
			userInfo.setState(1);
			tokenInfo = assembleTokenInfo(capUser.getCardId());
			userInfo = assembleUserInfo(capUser, tokenInfo);
			setSession(tokenInfo.getTokenId(), userInfo);
			cacheInfo(tokenInfo.getTokenId(), tokenInfo);
		}else{
			userInfo=null;
		}
		return userInfo;
	}

		/**
	 * 
	 * 拼装TokenInfo
	 * 
	 * @param userId
	 *            用户id
	 * @return TokenInfo 返回TokenInfo实体
	 */
	private TokenInfo assembleTokenInfo(String userId) {
		TokenInfo tokenInfo = new TokenInfo();
		String tokenId = UUID.randomUUID().toString().replaceAll("-", "");
		tokenInfo.setTokenId(tokenId);
		tokenInfo.setUserId(userId);
		return tokenInfo;
	}

	/**
	 * 
	 * 拼装UserInfo
	 * 
	 * @param capUser
	 *            用户实体
	 * @param tokenInfo
	 *            token实体
	 * @param ip
	 *            访问ip
	 * @return UserInfo 返回实体
	 */
	private UserInfo assembleUserInfo(CapUser capUser, TokenInfo tokenInfo) {
		UserInfo userInfo = new UserInfo();
		userInfo.setCardId(capUser.getCardId());
		userInfo.setWxUserId(capUser.getWxUserId());
		userInfo.setUserName(capUser.getUserName());
		userInfo.setTokenInfo(tokenInfo);	
		return userInfo;
	}

	/**
	 * 
	 * 设置用户session
	 * 
	 * @param tokenId
	 *            tokenId
	 * @param userInfo
	 *            用户信息实例
	 */
	private void setSession(String tokenId, UserInfo userInfo) {
		RequestAttributes requestAttributes = RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes)
				.getRequest();
		HttpSession session = request.getSession(true);
		session.setAttribute(USER_INFO, userInfo);
		session.setAttribute(TokenAuthService.HTTP_TOKEN_KEY, tokenId);

	}

	/**
	 * 
	 * 缓存用户信息以及用户可访问方法到redis 三部分信息 1. 用户与token的关联关系信息 2. token基础信息 3. token的权限信息
	 * 
	 * @param tokenId
	 *            tokenId
	 * @param tokenInfo
	 *            token信息
	 * @param OperatorAuthFuncResList
	 *            用户可访问方法
	 */
	private void cacheInfo(String tokenId, TokenInfo tokenInfo) {
		loginCacheService.insertOperatorTokenRelevant(tokenInfo.getUserId(),
				tokenId);
		loginCacheService.insertTokenInfo(tokenId, tokenInfo);
	}

	

	/**
	 * 
	 * 用户退出登录
	 * 
	 * @param userId
	 *            用户Id
	 * @param tokenId
	 *            tokenId
	 */
	public void logout(String userId, String tokenId) {
		loginCacheService.deleteOperatorTokenRelevant(userId);
		loginCacheService.deleteTokenAuthCache(tokenId);
	}
	
	public UserInfo existUser(String wxUserId) {
		UserInfo userInfo = new UserInfo();
		userInfo=userMapper.existUser(wxUserId);
		return userInfo;
	}
	

	
}
