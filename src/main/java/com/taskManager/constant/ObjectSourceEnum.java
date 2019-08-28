package com.taskManager.constant;

/**
 * java对象来源方式枚举
 * 
 * @author likai
 *
 */
public enum ObjectSourceEnum {
	/**
	 * 通过cglib创建
	 */
	CGLIB(1),

	/**
	 * 正常定义的对象
	 */
	NORMAL(0);

	private int value;

	public int getValue() {
		return value;
	}

	ObjectSourceEnum(int value) {
		this.value = value;
	}
}
