package com.udsp.sdc.icbc.comm.utils;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 * 
 * @author huangjl
 *
 */
@Component
public class RedisUtils {

	/**
	 * redisTemplate
	 */
	@Autowired(required = false)
	private RedisTemplate<Object, Object> redisTemplate;

	/**
	 * 默认过期时长，单位：秒
	 */
	public static final long DEFAULT_EXPIRE = 60 * 60 * 24L;

	/**
	 * 不设置过期时长
	 */
	public static final long NOT_EXPIRE = -1;

	/**
	 * Gson操作类
	 */
	private static final Gson GSON = new Gson();

	/**
	 * 设置json值
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @param expire
	 *            过期
	 */
	public void set(String key, Object value, long expire) {
		redisTemplate.opsForValue().set(key, toJson(value));
		if (expire != NOT_EXPIRE) {
			redisTemplate.expire(key, expire, TimeUnit.SECONDS);
		}
	}

	/**
	 * 设置json值
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public void set(String key, Object value) {
		set(key, value, DEFAULT_EXPIRE);
	}

	/**
	 * 读取json值
	 * 
	 * @param key
	 *            键
	 * @param clazz
	 *            类型
	 * @param expire
	 *            过期
	 * @return 查到的对象
	 */
	public <T> T get(String key, Class<T> clazz, long expire) {
		String value = (String) redisTemplate.opsForValue().get(key);
		if (expire != NOT_EXPIRE) {
			redisTemplate.expire(key, expire, TimeUnit.SECONDS);
		}
		return value == null ? null : fromJson(value, clazz);
	}

	/**
	 * 读取json值
	 * 
	 * @param key
	 *            键
	 * @param clazz
	 *            类型
	 * @return 查到的对象
	 */
	public <T> T get(String key, Class<T> clazz) {
		return get(key, clazz, NOT_EXPIRE);
	}

	/**
	 * 读取字符串值
	 * 
	 * @param key
	 *            键
	 * @param expire
	 *            过期
	 * @return 查到的字符串
	 */
	public String get(String key, long expire) {
		String value = (String) redisTemplate.opsForValue().get(key);
		if (expire != NOT_EXPIRE) {
			redisTemplate.expire(key, expire, TimeUnit.SECONDS);
		}
		return value;
	}

	/**
	 * 读取字符串值
	 * 
	 * @param key
	 *            键
	 * @return 查到的字符串
	 */
	public String get(String key) {
		return get(key, NOT_EXPIRE);
	}

	/**
	 * 删除键
	 * 
	 * @param key
	 *            键
	 */
	public void delete(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * Object转成JSON数据
	 * 
	 * @param object
	 *            待转json的对象
	 * @return 查到的字符串
	 */
	private String toJson(Object object) {
		if (object instanceof Number || object instanceof Boolean || object instanceof String) {
			return String.valueOf(object);
		}
		return GSON.toJson(object);
	}

	/**
	 * JSON数据，转成Object
	 * 
	 * @param json
	 *            json字符串
	 * @param clazz
	 *            类型
	 * @return json转换的对象
	 */
	private <T> T fromJson(String json, Class<T> clazz) {
		return GSON.fromJson(json, clazz);
	}
}
