package com.hitler.common.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/*
 * 日期时间格式转换
 * @author onsoul by JT 2015-6-24 下午4:32:23
 * */
public class DateUtil {

	/**
	 * 将传入的时间格式化成 yyyy:MM:dd HH:mm:ss 格式
	 */
	public static String timeStr(Date _Date) {
		if (_Date == null)
			return "";
		SimpleDateFormat _SimpleDateFormat = new SimpleDateFormat(
				"yyyy:MM:dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
		return _SimpleDateFormat.format(_Date);
	}

	/**
	 * 将传入时间格式化成 yyyy-MM-dd 格式字符串
	 */
	public static String dateStr(Date _Date) {
		if (_Date == null)
			return "";
		SimpleDateFormat _SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",
				Locale.SIMPLIFIED_CHINESE);
		return _SimpleDateFormat.format(_Date);
	}

	/**
	 * 将传入 yyyy-MM-dd字符串 转换成Date对象
	 * 
	 * @param _Date
	 * @return
	 */
	public static Date formatDate(String _Date) throws ParseException {
		if (_Date == null)
			return new Date();
		SimpleDateFormat _SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",
				Locale.SIMPLIFIED_CHINESE);
		return _SimpleDateFormat.parse(_Date);
	}

	/**
	 * 将传入的时间字符串转换成时间格式
	 */
	public static Date formatDate2(String _Date) throws ParseException {
		SimpleDateFormat _SimpleDateFormat = new SimpleDateFormat(
				"yyyy:MM:dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
		return _SimpleDateFormat.parse(_Date);
	}

	/**
	 * 将传入时间格式化成 yyyy/MM/dd HH:mm:ss 格式字符串
	 * 
	 * @param _Date
	 * @return
	 */
	public static String formatStr(Date _Date) {
		if (_Date == null)
			return null;
		SimpleDateFormat _SimpleDateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
		return _SimpleDateFormat.format(_Date);
	}

	public static String getYesTeday() {
		Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
		calendar.add(Calendar.DATE, -1); // 得到前一天
		String yestedayDate = new SimpleDateFormat("yyyy-MM-dd")
				.format(calendar.getTime());
		return yestedayDate;
	}

	public static String getToday() {
		Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
		String today = new SimpleDateFormat("yyyy-MM-dd").format(calendar
				.getTime());
		return today;
	}

	public static String getAddDay(Date date, int i) {
		Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
		calendar.setTime(date);
		calendar.add(Calendar.DATE, +i); // 得到前一天
		String yestedayDate = new SimpleDateFormat("yyyy-MM-dd")
				.format(calendar.getTime());
		return yestedayDate;
	}

	public static long getTimeSlot(Date date_late, Date date_early) {
		try {
			long hour = (date_late.getTime() - date_early.getTime())
					/ (1000 * 60 * 60);
			return hour;
		} catch (Exception ex) {
			return 0;
		}
	}

	public static boolean batchTime(Long time, int minute) {
		long millis = (long) minute * 60 * 1000;
		long currentTime = System.currentTimeMillis();
		if ((currentTime - time) > millis) {
			return true;
		}
		return false;
	}

	public static Date addMinute(Date time, int minute) {
		Calendar sl_time = Calendar.getInstance();
		sl_time.setTime(time);
		sl_time.add(Calendar.MINUTE, minute);
		return sl_time.getTime();
	}

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToStr(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	public static void main(String[] args) {
		System.out.println(strToDate("2015-07-21 17:09:05"));
		System.out.println(dateToStr(new Date()));
	}
}
