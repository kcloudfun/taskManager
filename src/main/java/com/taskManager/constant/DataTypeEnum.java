package com.taskManager.constant;

public enum DataTypeEnum {

	INT("int"),

	STRING("String");

	private String value;

	public String getValue() {
		return value;
	}

	DataTypeEnum(String value) {
		this.value = value;
	}

	/**
	 * 根据value获取对应的枚举对象，供switch-case处使用
	 * 
	 * @param value
	 * @return
	 * @author likai 2019年8月28日 下午11:03:55
	 */
	public static DataTypeEnum getByValue(String value) {
		for (DataTypeEnum dataTypeEnum : values()) {
			if (dataTypeEnum.getValue() == value) {
				return dataTypeEnum;
			}
		}
		return null;
	}
}
