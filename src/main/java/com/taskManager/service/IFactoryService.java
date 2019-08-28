package com.taskManager.service;

import java.util.Map;

public interface IFactoryService {

	/**
	 * 利用cglib动态创建一个含有制定属性的Object类
	 * 
	 * @param fields
	 *            Map<K,V> k为属性名，v为属性对应的java数据类型
	 * @return
	 * @author likai 2019年8月28日 下午9:41:45
	 */
	public Object createObject(Map<String, String> fields);

	/**
	 * set值
	 * 
	 * @param object
	 *            目标对象
	 * @param fieldValues
	 *            Map<K,V> k为属性名称，v为属性对应的值
	 * @author likai 2019年8月28日 下午10:09:23
	 */
	public void setFieldValues(Object object, Map<String, Object> fieldValues);

	/**
	 * set值，单个
	 * 
	 * @param object
	 * @param fieldName
	 * @param fieldValue
	 * @author likai 2019年8月28日 下午10:32:00
	 */
	public void setOneFieldValue(Object object, String fieldName, Object fieldValue);

	/**
	 * 根据属性名获取属性值
	 * 
	 * @param object
	 *            目标对象
	 * @param fieldName
	 *            属性名称
	 * @return
	 * @author likai 2019年8月28日 下午10:08:06
	 */
	public Object getFieldValue(Object object, String fieldName);
}
