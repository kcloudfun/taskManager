package com.taskManager.config;

import org.springframework.stereotype.Component;

/**
 * 数据源持有者（负责新建或是设置数据源）
 * 
 * @author 14145
 *
 */
@Component
public class DataSourceHolder {

	private static ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static Object changeToTargetDataSource() {
		return contextHolder.get();
	}

	// 设置数据库名
	public static void setDbName(String dbName) {
		contextHolder.set(dbName);
	}

	// 清空
	public static void clear() {
		contextHolder.remove();
	}
}
