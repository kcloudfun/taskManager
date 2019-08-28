package com.taskManager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.taskManager.dao.ITemplateDao;
import com.taskManager.utils.BeanUtils;
import com.taskManager.vo.db.TaskDbVo;

public class TemplateTest extends TaskManagerApplicationTests {

	@Autowired
	private ITemplateDao templateDao;

	@Test
	public void test1() throws Exception {
		List<LinkedHashMap<String, Object>> taskList = new ArrayList<>();
		// LinkedHashMap<String, Object> task1 = new LinkedHashMap<>();
		// task1.put("task_Id", "default");
		// task1.put("task_Name", "7月21任务4");
		// taskList.add(task1);
		// LinkedHashMap<String, Object> task2 = new LinkedHashMap<>();
		// task2.put("task_Id", "default");
		// task2.put("task_Name", "7月21任务5");
		// taskList.add(task2);

		TaskDbVo task2 = new TaskDbVo();
		task2.setTaskName("7月17任务2");
		taskList.add(BeanUtils.bean2map(task2, 0));
		TaskDbVo task3 = new TaskDbVo();
		task3.setTaskName("7月17任务3");
		taskList.add(BeanUtils.bean2map(task3, 0));
		TaskDbVo task4 = new TaskDbVo();
		task4.setTaskName("7月17任务4");
		taskList.add(BeanUtils.bean2map(task4, 0));
		TaskDbVo task5 = new TaskDbVo();
		task5.setTaskName("7月17任务5");
		taskList.add(BeanUtils.bean2map(task5, 0));

		int a = templateDao.batchInsert("t_task", taskList);
		System.out.println(a);
	}
}
