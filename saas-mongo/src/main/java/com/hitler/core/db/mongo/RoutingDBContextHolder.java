package com.hitler.core.db.mongo;

/**
 * 动态切换数据库
 * ThreadLocal 数据上下文
 * @author onsoul 2018年4月23日
 */
public class RoutingDBContextHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDBName(String dbName) {
		contextHolder.set(dbName);
	}

	public static String getDBName() {
		return contextHolder.get();
	}

	public static void clearDBName() {    //注意传播
		contextHolder.remove();
	}
}