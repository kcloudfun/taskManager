package com.taskManager.service.impl;

import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import com.taskManager.constant.DataTypeEnum;
import com.taskManager.service.IFactoryService;

@Service
public class FactoryServiceImpl implements IFactoryService {

	@Override
	public Object createObject(Map<String, String> fields) {
		BeanGenerator beanGenerator = new BeanGenerator();

		// 循环map，根据map添加不同类型的属性
		for (String key : fields.keySet()) {
			// 枚举在switch中的使用
			DataTypeEnum dataType = DataTypeEnum.getByValue(fields.get(key));
			switch (dataType) {
			case INT:
				beanGenerator.addProperty(key, int.class);
				break;
			case STRING:
				beanGenerator.addProperty(key, String.class);
				break;
			default:
				break;
			}
		}

		return beanGenerator.create();
	}

	@Override
	public void setFieldValues(Object object, Map<String, Object> fieldValues) {
		BeanMap beanMap = BeanMap.create(object);
		for (String key : fieldValues.keySet()) {
			beanMap.put(key, fieldValues.get(key));
		}
	}

	@Override
	public void setOneFieldValue(Object object, String fieldName, Object fieldValue) {
		BeanMap beanMap = BeanMap.create(object);
		beanMap.put(fieldName, fieldValue);
	}

	@Override
	public Object getFieldValue(Object object, String fieldName) {
		Object res = null;
		Class<?> objClass = object.getClass();
		// 通过反射获取到全部方法
		Method[] methods = objClass.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			String methodName = methods[i].getName();
			// 循环遍历，如果是get方法则做进一步处理
			if (methodName.startsWith("get")) {
				String upperCaseMethodName = methodName.toUpperCase();
				// 将入参中的属性名及方法都转大写，判断方法结尾是否与属性名相同
				if (upperCaseMethodName.endsWith(fieldName.toUpperCase())) {
					try {
						// 相同则调用该方法
						res = methods[i].invoke(object, new Object[] {});
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}
		}
		return res;
	}

}
