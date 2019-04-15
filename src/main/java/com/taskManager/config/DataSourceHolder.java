package com.taskManager.config;

/**
 * 数据源持有者（负责新建或是设置数据源）
 * 
 * @author 14145
 *
 */
public class DataSourceHolder {

	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static Object changeToTargetDataSource() {
		return null;
	}
}
