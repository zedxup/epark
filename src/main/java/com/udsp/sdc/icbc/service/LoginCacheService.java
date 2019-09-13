package com.udsp.sdc.icbc.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.text.StrSubstitutor;
//import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.udsp.sdc.icbc.bean.TokenInfo;
import com.udsp.sdc.icbc.comm.utils.DateUtils;

/**
 * 
 * 缓存登录信息
 * 
 * @author 张宏恺
 * @date 2018年3月13日 下午3:43:56
 *
 */
@Service
public class LoginCacheService {


	@Autowired
	private RedisTemplate<String, String> redisTemplateStr;
	@Autowired
	private RedisTemplate<String, Object> redisTemplateObj;

	public static final String HTTP_ADMIN_TOKEN_KEY = "adminToken";

	public static final String HTTP_TENANT_TOKEN_KEY = "tenantToken";

	public static final String REDIS_TOKEN_KEY = "token";
	public static final String REDIS_TOKEN_FIELD_TOKENINFO = "token";
	public static final String REDIS_TOKEN_FIELD_USERINFO = "user";
	public static final String REDIS_TOKEN_FIELD_IP = "IP";
	public static final String REDIS_TOKEN_FIELD_LOCKIP = "LockIp";
	public static final String SMS_CODE = "code";
	public static final String RESET_PWD = "tokenId";
	public static final String SMS_CODE_TIME_SEND = "time";

	/**
	 * user:${userId}:[tokenId] 二期 管理员端用户下线使用
	 */
	public static final String REDIS_USER_TOKEN_TEMPLATE = "user:${userId}";
	/**
	 * token:${tokenId}:[tokenInfo] 用户登录鉴权使用tokenInfo 尽量存储少量信息加快信息传递速度 redis
	 * hash 进行存储
	 */
	public static final String REDIS_TOKEN_AUTH_TEMPLATE = "token:${tokenId}";
	
	/**
	 * SMSCode:${number}:[tokenInfo] 用户登录鉴权使用tokenInfo 尽量存储少量信息加快信息传递速度 redis
	 * hash 进行存储
	 */
	public static final String REDIS_TOKEN_PHONE_NUMBER = "SMSCode:${number}";
	
	/**
	 * userName:${userName}:[tokenInfo] 用户登录鉴权使用tokenInfo 尽量存储少量信息加快信息传递速度 redis
	 * hash 进行存储
	 */
	public static final String REDIS_TOKEN_USERNAME = "resetPwd:${userName}";

	/**
	 * ip:${ip}:[ip] ip记数
	 * hash 进行存储
	 */
	public static final String REDIS_TOKEN_IP = "IP:${ipAdress}";
	public static final String REDIS_TOKEN_LOCK_IP = "LockIp:${ipAdress}";

	/**
	 * 缓存用户访问controller层方法(等同于url访问控制)模板
	 * ${} 代表替换字段
	 * [] 代表redis存储内容
	 * auth:method:${methodPath}:${methodParams}
	 * 
	 */
	public static final String REDIS_TOKEN_AUTH_METHOD_TEMPLATE = "auth:method:${methodPath}(${methodParams})";
	public static final String REDIS_TOKEN_AUTH_METHOD_NO_PARAMETER = "null";

	/**
	 * 替换使用
	 */
	public static final String STRSUBSTITUTOR_TOKEN_ID = "tokenId";
	public static final String STRSUBSTITUTOR_USER_ID = "userId";
	public static final String STRSUBSTITUTOR_IPADRESS = "ipAdress";
	public static final String STRSUBSTITUTOR_PHONENUMBER = "number";
	public static final String STRSUBSTITUTOR_USERNAME = "userName";
	public static final String STRSUBSTITUTOR_MEHTOD_PATH = "methodPath";
	public static final String STRSUBSTITUTOR_MEHTOD_PARAMS = "methodParams";

	/**
	 * 踢出用户tag
	 */
	public static final String KICKING_OUT_USER = "kicking_out_user";

	/**
	 * 
	 * 获取token信息
	 * 
	 * @param tokenId
	 *            token id
	 * @return TokenInfo 返回tokenInfo实例
	 */
	public TokenInfo getTokenInfo(String tokenId) {
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put(STRSUBSTITUTOR_TOKEN_ID, tokenId);
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String key = sub.replace(REDIS_TOKEN_AUTH_TEMPLATE);
		return (TokenInfo) redisTemplateObj.opsForHash().get(key, REDIS_TOKEN_FIELD_TOKENINFO);
	}

	/**
	 * 
	 * 插入token信息并设置30分钟超时
	 * 
	 * @param tokenId
	 *            tokenId
	 * @param tokenInfo
	 *            token信息
	 */
	public void insertTokenInfo(String tokenId, TokenInfo tokenInfo) {
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put(STRSUBSTITUTOR_TOKEN_ID, tokenId);
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String key = sub.replace(REDIS_TOKEN_AUTH_TEMPLATE);
		redisTemplateObj.opsForHash().put(key, REDIS_TOKEN_FIELD_TOKENINFO, tokenInfo);
		redisTemplateStr.opsForHash().put(key, REDIS_TOKEN_FIELD_USERINFO, tokenInfo.getUserId());
		redisTemplateObj.expire(key, 30, TimeUnit.MINUTES);
	}

	/**
	 * 
	 * 插入token msg
	 * 
	 * @param tokenId
	 *            tokenId
	 * @param hashKey
	 *            hash key
	 * @param hashValue
	 *            hash value
	 */
	public void insertTokenMsg(String tokenId, String hashKey, String hashValue) {
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put(STRSUBSTITUTOR_TOKEN_ID, tokenId);
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String key = sub.replace(REDIS_TOKEN_AUTH_TEMPLATE);
		redisTemplateStr.opsForHash().put(key, hashKey, hashValue);
	}

	/**
	 * 
	 * 插入token踢出状态tag
	 * @param tokenId 参数
	 */
	public void insertTokenKicingOutUserTag(String tokenId) {
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put(STRSUBSTITUTOR_TOKEN_ID, tokenId);
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String key = sub.replace(REDIS_TOKEN_AUTH_TEMPLATE);
		redisTemplateStr.opsForHash().put(key, KICKING_OUT_USER, "");
		//踢出用户redis token 驻留时长改为30分钟
		redisTemplateStr.expire(key, 30, TimeUnit.MINUTES);

	}

	/**
	 * 
	 * 判断该token是否已经标记为踢出状态
	 * 
	 * @param tokenId
	 * @return 参数
	 */
	public boolean isTokenKickingOutUserTagExist(String tokenId) {
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put(STRSUBSTITUTOR_TOKEN_ID, tokenId);
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String key = sub.replace(REDIS_TOKEN_AUTH_TEMPLATE);

		return redisTemplateStr.opsForHash().hasKey(key, KICKING_OUT_USER);
	}

	/**
	 * 
	 * 缓存token权限
	 * 
	 * @param tokenId
	 *            tokenId
	 * @param methodPath
	 *            方法路径
	 * @param methodParams
	 *            方法参数
	 * @param methodId
	 *            方法Id
	 */
	public void insertTokenAuth(String tokenId, String methodPath, String methodParams, String methodId) {

		Map<String, String> keyMap = new HashMap<>();
		keyMap.put(STRSUBSTITUTOR_TOKEN_ID, tokenId);
		StrSubstitutor tokenSub = new StrSubstitutor(keyMap);
		String key = tokenSub.replace(REDIS_TOKEN_AUTH_TEMPLATE);

		Map<String, String> fieldMap = new HashMap<>();
		fieldMap.put(STRSUBSTITUTOR_MEHTOD_PATH, methodPath);
		fieldMap.put(STRSUBSTITUTOR_MEHTOD_PARAMS, methodParams);
		StrSubstitutor sub = new StrSubstitutor(fieldMap);
		String field = sub.replace(REDIS_TOKEN_AUTH_METHOD_TEMPLATE);
		redisTemplateStr.opsForHash().put(key, field, methodId);
	}

	
	/**
	 * 
	 * 判定token是否有某一权限 token存储为hash
	 * 
	 * @param tokenId
	 *            tokenId
	 * @param methodPath
	 *            方法路径
	 * @param methodParams
	 *            方法参数
	 * @return boolean 是否有访问权限
	 */
	public boolean getTokenAuthMethodParams(String tokenId, String methodPath, String methodParams) {
		Map<String, String> keyMap = new HashMap<>();
		keyMap.put(STRSUBSTITUTOR_TOKEN_ID, tokenId);
		StrSubstitutor tokenSub = new StrSubstitutor(keyMap);
		String key = tokenSub.replace(REDIS_TOKEN_AUTH_TEMPLATE);

		Map<String, String> fieldMap = new HashMap<>();
		fieldMap.put(STRSUBSTITUTOR_MEHTOD_PATH, methodPath);
		fieldMap.put(STRSUBSTITUTOR_MEHTOD_PARAMS, methodParams);
		StrSubstitutor sub = new StrSubstitutor(fieldMap);
		String field = sub.replace(REDIS_TOKEN_AUTH_METHOD_TEMPLATE);

		return redisTemplateStr.opsForHash().hasKey(key, field);
	}

	/**
	 * 
	 * 获取关联用户tokenId
	 * 
	 * @param userId
	 * @return tokenId
	 */
	public String getOperatorTokenRelevant(String userId) {
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put(STRSUBSTITUTOR_USER_ID, userId);
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String key = sub.replace(REDIS_USER_TOKEN_TEMPLATE);
		return redisTemplateStr.opsForValue().get(key);
	}

	/**
	 * 
	 * 添加操作员与token的对应关系缓存
	 * 
	 * @param userId
	 *            userId
	 * @param tokenId
	 *            tokenId
	 */
	public void insertOperatorTokenRelevant(String userId, String tokenId) {
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put(STRSUBSTITUTOR_USER_ID, userId);
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String key = sub.replace(REDIS_USER_TOKEN_TEMPLATE);
		redisTemplateStr.opsForValue().set(key, tokenId);
	}

	/**
	 * 
	 * 删除操作员与token对应缓存
	 * 
	 * @param userId
	 *            用户Id
	 */
	public void deleteOperatorTokenRelevant(String userId) {
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put(STRSUBSTITUTOR_USER_ID, userId);
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String key = sub.replace(REDIS_USER_TOKEN_TEMPLATE);
		redisTemplateObj.delete(key);
	}

	/**
	 * 
	 * 删除token:tokenId:*缓存
	 * 
	 * @param tokenId
	 *            tokenId
	 */
	public void deleteTokenAuthCache(String tokenId) {
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put(STRSUBSTITUTOR_TOKEN_ID, tokenId);
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String key = sub.replace(REDIS_TOKEN_AUTH_TEMPLATE);
		Set<String> keys = redisTemplateObj.keys(key + "*");
		redisTemplateObj.delete(keys);
	}

	/**
	 * 
	 * 续期tokenId 过期时间
	 * 
	 * @param tokenId
	 *            tokenId
	 */
	public void renewTokenExpireTime(String tokenId) {
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put(STRSUBSTITUTOR_TOKEN_ID, tokenId);
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String key = sub.replace(REDIS_TOKEN_AUTH_TEMPLATE);
		redisTemplateObj.expire(key, 30, TimeUnit.MINUTES);
	}

	
	
	public Map<String, String> getCodeByPhone(String phone) {
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put(STRSUBSTITUTOR_PHONENUMBER, phone);
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String key = sub.replace(REDIS_TOKEN_PHONE_NUMBER);
		Map<String, String> codeInfo = new HashMap<>();
		codeInfo.put("code", (String) redisTemplateStr.opsForHash().get(key, SMS_CODE));
		codeInfo.put("sendTime", (String)redisTemplateStr.opsForHash().get(key, SMS_CODE_TIME_SEND));
		return codeInfo;
	}
	
	
	
	public String getResetToken(String userName) {
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put(STRSUBSTITUTOR_USERNAME, userName);
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String key = sub.replace(REDIS_TOKEN_USERNAME);
		return redisTemplateStr.opsForValue().get(key);
	}
	
	
	public void deleteCodeByPhone(String phone) {
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put(STRSUBSTITUTOR_PHONENUMBER, phone);
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String key = sub.replace(REDIS_TOKEN_PHONE_NUMBER);
		redisTemplateStr.delete(key);
	}
}
