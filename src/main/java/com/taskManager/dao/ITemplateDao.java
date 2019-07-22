package com.taskManager.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ITemplateDao {

	/**
	 * 通用性批量插入（不限定表，不限定字段数量，不支持自增主键的反向填充）<BR/>
	 * 参数说明:表名必须与数据库一致，大小写不限。数据中一个map表示一行数据库记录，且map键必须与数据库一致，大小写不限，两个map之间put顺序必须一致
	 * 
	 * @param tableName
	 *            表名
	 * @param paramMapList
	 *            数据
	 * @return 影响行数
	 * @author likai 2019年7月21日 下午10:40:24
	 */
	public int batchInsert(@Param("tableName") String tableName,
			@Param("paramMapList") List<LinkedHashMap<String, Object>> paramMapList);
}
