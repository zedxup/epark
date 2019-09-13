package com.udsp.sdc.icbc.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;

import com.udsp.sdc.icbc.bean.TokenInfo;
import com.udsp.sdc.icbc.comm.exception.ExceptionCode;
import com.udsp.sdc.icbc.comm.exception.FrameException;

/**
 * 
 * 用户访问鉴权类
 * 第一层认证token有效性
 * 第二层认证用户访问方法有效性
 * 
 * @author 张宏恺
 * @date 2018-03-16
 *
 */
@Service
public class TokenAuthService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LoginCacheService loginCacheService;
//	@Value("${devops.grant.all.privileges}")
//	private String grantAllPrivileges;
	/**
	 * 浏览器中 token的命名以及session中token命名
	 */
	public static final String HTTP_TOKEN_KEY = "token";

	/**
	 * 
	 * token拦截验证
	 * 
	 * @param request
	 *            http请求request
	 * @param response
	 *            http相应response
	 * @param handler
	 *            对应的拦截方法
	 * @return boolean 判定是否进行下一步
	 * @throws IOException
	 *             参数
	 */
	public Boolean intercept(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) {
		String method = request.getMethod();
		String uri = request.getRequestURI();
		String tokenId = getTokenId();
		logger.debug("访问path: " + uri + " method: " + method + " tokenId: " + tokenId);
		String[] ignorePaths = { 
				"/api/v1/tenant/login",
				"/api/v1/admin/login", 
				"/api/admin/req", 
				"/api/tenant/req",
				"/error", 
				"/api/v1/tenant/register", 
				"/api/v1/tenant/checkUserName",
				"/api/v1/admin/verificationCodeImage", 
				"/api/v1/verificationCodeImage", 
				"/api/v1/health",
				"/api/v1/funcres/funcs/sync",
				"/api/admin/v1/notify",
				"/api/v1/platform/email/sms/send/interface/service",
				"/api/v1/register/phonecode",
				"/api/v1/resetPassWord/phonecode",
				"/api/v1/tenant/checkPhoneCode",
				"/prometheus"
				};
		boolean isInIgnorePaths = false;
		for (String ignorePath : ignorePaths) {
			if (uri.startsWith(ignorePath)) {
				isInIgnorePaths = true;
			}
		}
		if (!isInIgnorePaths) {

			if (StringUtils.isEmpty(tokenId))
				throw new FrameException(ExceptionCode.TOKEN_INVALID);

			TokenInfo tokenInfo = loginCacheService.getTokenInfo(tokenId);

			if (tokenInfo == null)
				throw new FrameException(ExceptionCode.TOKEN_INVALID);

			if (!methodAllowedAuth(tokenInfo.getTokenId(), handler))
				throw new FrameException(ExceptionCode.PERMISSION_NOT_ALLOWED);

			if (isKickingOutUser(tokenInfo.getTokenId()))
				throw new FrameException(ExceptionCode.KICKING_OUT_USER);

			loginCacheService.renewTokenExpireTime(tokenInfo.getTokenId());
		}

		return true;
	}

	/**
	 * 
	 * 判定Token是否有效
	 */
	public boolean isTokenValid() {
		String tokenId = getTokenId();
		if (StringUtils.isEmpty(tokenId))
			return false;
		TokenInfo tokenInfo = loginCacheService.getTokenInfo(tokenId);
		if (tokenInfo == null)
			return false;
		return true;
	}

	/**
	 * 
	 * 判定为已经踢出token
	 * 
	 * @return boolean
	 */
	private boolean isKickingOutUser(String tokenId) {
		boolean result = false;
		result = loginCacheService.isTokenKickingOutUserTagExist(tokenId);
		return result;

	}

	/**
	 * 
	 * 判定所访问方法用户是否拥有权限(mehtod鉴权入口)
	 * 
	 * @param tokenId
	 *            tokenId
	 * @param handler
	 *            处理方法
	 * @return boolean 判定访问方法是否用户拥有权限
	 */
	private boolean methodAllowedAuth(String tokenId, HandlerMethod handler) {

		boolean result = false;

		String method = handler.getMethod().getDeclaringClass().getName() + "." + handler.getMethod().getName();
		String methodParams = getMethodParameter(handler);
		result = loginCacheService.getTokenAuthMethodParams(tokenId, method, methodParams);
//		// 是否打开所有的权限开关
//		if (Boolean.valueOf(grantAllPrivileges))
//			result = true;
		return result;
	}

	/**
	 * 
	 * 返回tokenId
	 * 获取顺序 session -> header -> url parameter
	 * 
	 * @return String 返回tokenId
	 */
	private String getTokenId() {
		String tokenId = "";
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
		if (request.getSession(false) != null)
			tokenId = (String) request.getSession().getAttribute(HTTP_TOKEN_KEY);
		if (StringUtils.isEmpty(tokenId))
			tokenId = request.getHeader(HTTP_TOKEN_KEY);
		if (StringUtils.isEmpty(tokenId))
			tokenId = request.getParameter(HTTP_TOKEN_KEY);
		return tokenId;
	}

	/**
	 * 
	 * 获取访问方法参数 并用括号包围
	 * 
	 * @param handlerMethod
	 *            访问方法实例
	 * @return String 获取方法全路径名称
	 */
	private String getMethodParameter(HandlerMethod handlerMethod) {

		StringBuilder paramsSb = new StringBuilder();
		MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
		for (MethodParameter methodParameter : methodParameters) {
			paramsSb.append(methodParameter.getParameterType().getName() + ",");
		}
		if (methodParameters.length > 0)
			paramsSb.deleteCharAt(paramsSb.length() - 1);

		return paramsSb.toString();
	}
	
	
 
}
