package com.taskManager;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.taskManager.service.IFactoryService;

public class CglibTest extends TaskManagerApplicationTests {

	@Autowired
	private IFactoryService factoryService;

	@Test
	public void testCreate() {
		Map<String, String> map = new HashMap<>();
		map.put("id", "int");
		map.put("name", "String");
		Object object = factoryService.createObject(map);
		factoryService.setOneFieldValue(object, "name", "李凯");
		System.out.println(factoryService.getFieldValue(object, "name"));

	}
}
