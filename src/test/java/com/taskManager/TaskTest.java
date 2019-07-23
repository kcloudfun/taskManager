package com.taskManager;

import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.taskManager.service.ITaskService;
import com.taskManager.vo.db.TaskDbVo;

public class TaskTest extends TaskManagerApplicationTests {

	@Autowired
	private ITaskService taskService;

	@Test
	public void test1() {
		try {
			TaskDbVo task = new TaskDbVo();
			task.setTaskId(UUID.randomUUID().toString().replaceAll("-", ""));
			task.setTaskName("测试任务2");
			task.setFinishType("4");
			task.setCreateBy("TaskTest");
			task.setUpdateBy("TaskTest");
			taskService.createOneTask(task);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
