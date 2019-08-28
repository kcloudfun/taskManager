package com.taskManager.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.taskManager.constant.ObjectSourceEnum;

/**
 * javaBean 相关工具方法
 * 
 * @author likai
 *
 */
public class BeanUtils {

	private final static Logger log = LoggerFactory.getLogger(BeanUtils.class);

	/**
	 * 将一个list的java对象转化为一个list的LinkedHashMap
	 * 
	 * @param paramList
	 *            javaBean集合
	 * @return 有序map集合
	 * @author likai 2019年7月22日 下午9:24:35
	 */
	public static List<LinkedHashMap<String, Object>> listBean2ListMap(List<Object> paramList) {
		if (CollectionUtils.isEmpty(paramList)) {
			log.info("listBean2ListMap方法参数异常，方法结束：paramList为null或者空集合");
			return null;
		}
		List<LinkedHashMap<String, Object>> resultList = new ArrayList<>();
		for (int i = 0; i < paramList.size(); i++) {
			resultList.add(bean2map(paramList.get(i), ObjectSourceEnum.NORMAL.getValue()));
		}
		return resultList;
	}

	/**
	 * 将java对象转化为有序的map（屏蔽掉为null的属性，map键为TASK_STATUS样式）
	 * 
	 * @param obj
	 * @param objectSourceFlg
	 *            java对象是否为通过cglib创建，1表示为cglib创建，0表示正常对象
	 * @return
	 * @author likai 2019年8月27日 下午10:02:27
	 */
	public static LinkedHashMap<String, Object> bean2map(Object obj, int objectSourceFlg) {
		if (obj == null) {
			log.info("bean2map方法参数异常，方法结束：obj==null");
			return null;
		}
		Long start = System.currentTimeMillis();
		log.info("bean2map开始，参数为：" + JSON.toJSONString(obj));

		// 通过反射获取class对象，并获取全部的属性及方法
		Class<?> objClass = obj.getClass();
		Field[] declaredFields = objClass.getDeclaredFields();
		// 此处遗留了一个问题没有处理:这样获取的方法跟属性是不包含父类的。
		// 若想包含父类可考虑getSuperClass，然后再做一次同样操作，考虑和数据库交互的javaBean一般不会很复杂，故此处不做处理
		// 另外一个getFields()方法是获取public修饰的属性，范围更窄
		Method[] declaredMethods = objClass.getDeclaredMethods();

		LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
		for (int i = 0; i < declaredMethods.length; i++) {
			String methodName = declaredMethods[i].getName();
			String upperCaseName = methodName.toUpperCase();
			for (int j = 0; j < declaredFields.length; j++) {
				String fieldName = declaredFields[j].getName();

				if (ObjectSourceEnum.CGLIB.getValue() == objectSourceFlg) {
					// 通过cglib创建的会有特殊前缀，截取去掉前缀部分
					fieldName = fieldName.substring(12);
				}

				String upperCaseFieldName = fieldName.toUpperCase();
				// 判断方法是否包含属性，且是否是get方法
				if (upperCaseName.contains(upperCaseFieldName) && upperCaseName.startsWith("GET")) {
					Object getMethodReturnData = null;
					try {
						getMethodReturnData = declaredMethods[i].invoke(obj);
					} catch (Exception e) {
						log.error("bean2map动态调用get方法异常", e);
					}
					if (getMethodReturnData != null) {
						// 动态调用get方法获取的返回值不为null则放入map之中
						resultMap.put(javaStrToDbStr(fieldName), getMethodReturnData);
					}
				}
			}
		}
		log.info("bean2map结束，耗时：" + (System.currentTimeMillis() - start) + "ms");
		return resultMap;
	}

	/**
	 * 将驼峰的名称转换为带下划线的名称（全大写）
	 * 
	 * @param javaStr
	 *            驼峰式属性名
	 * @return 带下划线式数据库式字段名
	 * @author likai 2019年7月22日 下午7:18:48
	 */
	public static String javaStrToDbStr(String javaStr) {
		if (StringUtils.isEmpty(javaStr)) {
			// 参数校验
			log.info("javaStrToDbStr方法参数异常，方法结束：javaStr==" + javaStr);
			return null;
		}
		char[] charArray = javaStr.toCharArray();
		StringBuffer temporaryStr = new StringBuffer();
		for (int i = 0; i < charArray.length; i++) {
			char oneChar = charArray[i];
			// 判断字符是否大写
			if (Character.isUpperCase(oneChar)) {
				temporaryStr.append("_" + oneChar);
			} else {
				temporaryStr.append(oneChar);
			}
		}
		return temporaryStr.toString().toUpperCase();
	}
}
