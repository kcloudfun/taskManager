package com.taskManager;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.taskManager.dao.ITaskDao;
import com.taskManager.vo.db.TaskDbVo;

public class TaskTest extends TaskManagerApplicationTests {

	@Autowired
	private ITaskDao taskDao;
	
	@Test
	public void test1() {
		List<TaskDbVo> taskList = new ArrayList<>();
		TaskDbVo task1 = new TaskDbVo();
		task1.setTaskName("7月17任务1");
		taskList.add(task1);
		TaskDbVo task2 = new TaskDbVo();
		task2.setTaskName("7月17任务2");
		taskList.add(task2);
		TaskDbVo task3 = new TaskDbVo();
		task3.setTaskName("7月17任务3");
		taskList.add(task3);
		TaskDbVo task4 = new TaskDbVo();
		task4.setTaskName("7月17任务4");
		taskList.add(task4);
		TaskDbVo task5 = new TaskDbVo();
		task5.setTaskName("7月17任务5");
		taskList.add(task5);
		
		int a = taskDao.batchinsert(taskList);
		System.out.println(a);
	}
}
