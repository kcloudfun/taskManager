package com.taskManager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.taskManager.dao.ITemplateDao;

public class TemplateTest extends TaskManagerApplicationTests {

	@Autowired
	private ITemplateDao templateDao;

	@Test
	public void test1() {
		List<LinkedHashMap<String, Object>> taskList = new ArrayList<>();
		LinkedHashMap<String, Object> task1 = new LinkedHashMap<>();
		task1.put("task_Id", "default");
		task1.put("task_Name", "7月21任务4");
		taskList.add(task1);
		LinkedHashMap<String, Object> task2 = new LinkedHashMap<>();
		task2.put("task_Id", "default");
		task2.put("task_Name", "7月21任务5");
		taskList.add(task2);
		

		int a = templateDao.batchInsert("t_task", taskList);
		System.out.println(a);
	}
}
