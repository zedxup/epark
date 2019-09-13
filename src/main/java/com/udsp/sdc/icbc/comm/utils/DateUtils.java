package com.udsp.sdc.icbc.comm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期处理
 * 
 */
public final class DateUtils {

	/** 时间格式(yyyy-MM-dd) */
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 静态类不可构造
	 */
	private DateUtils() {
	}
	
	/**
	 * 转日期(yyyy-MM-dd)
	 * 
	 * @param date
	 *            时间
	 * @return 格式化的时间字符串
	 */
	public static String formatToDate(Date date) {
		return format(date, DATE_PATTERN);
	}

	/**
	 * 转日期时间(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param date
	 *            时间
	 * @return 格式化的时间字符串
	 */
	public static String formatToTime(Date date) {
		return format(date, DATE_TIME_PATTERN);
	}

	/**
	 * 转换
	 * 
	 * @param date
	 *            时间
	 * @param pattern
	 *            格式串
	 * @return 格式化的时间字符串
	 */
	public static String format(Date date, String pattern) {
		if (date != null) {
			final SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
			return sdf.format(date);
		}
		return null;
	}

	/**
	 * 获得当前日期
	 * 
	 * @return 格式化的时间字符串
	 */
	public static String getDate() {
		return formatToDate(new Date());
	}

	/**
	 * 获得当前时间
	 * 
	 * @return 格式化的时间字符串
	 */
	public static String getTime() {
		return formatToTime(new Date());
	}
}
