package com.taskManager.vo.db;

import java.util.List;

public class TableDbVo {

	/**
	 * 表名
	 */
	private String tableName;

	/**
	 * 列，按照 列名 类型 长度形式拼接好的字符串，space varchar(20)，若格式正确，后面还可追加默认值、非空等等
	 */
	private List<String> columnParams;

	/**
	 * 主键字段名
	 */
	private String keyName;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public List<String> getColumnParams() {
		return columnParams;
	}

	public void setColumnParams(List<String> columnParams) {
		this.columnParams = columnParams;
	}
}
