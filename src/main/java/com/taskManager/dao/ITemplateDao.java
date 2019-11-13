package com.taskManager.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.taskManager.vo.db.TableDbVo;

@Mapper
public interface ITemplateDao {

	/**
	 * 通用型批量插入（不限定表，不限定字段数量，不支持自增主键的反向填充）<BR/>
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

	/**
	 * 通用型单行插入
	 * 
	 * @param tableName
	 *            表名
	 * @param paramMap
	 *            数据
	 * @return 影响行数
	 * @author likai 2019年7月23日 下午8:18:43
	 */
	public int oneInsert(@Param("tableName") String tableName,
			@Param("paramMap") LinkedHashMap<String, Object> paramMap);

	/**
	 * 根据参数创建一张表
	 * 
	 * @param tableDbVo
	 *            目标表相关参数
	 * @author likai 2019年11月13日 下午9:03:00
	 */
	public void createTable(TableDbVo tableDbVo);
}
