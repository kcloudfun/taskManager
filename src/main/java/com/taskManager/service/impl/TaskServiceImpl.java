package com.taskManager.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskManager.dao.ITaskDao;
import com.taskManager.service.ITaskService;
import com.taskManager.vo.db.TaskDbVo;
import com.taskManager.vo.response.CommonResult;

@Service
public class TaskServiceImpl implements ITaskService {

	@Autowired
	private ITaskDao taskDao;

	@Override
	public CommonResult<List<TaskDbVo>> getTaskListByAccountName() {
		CommonResult<List<TaskDbVo>> result = new CommonResult<>();
		List<TaskDbVo> taskList = taskDao.selectTaskList();
		if (taskList != null && !taskList.isEmpty()) {
			result.setData(taskList);
		}
		return result;
	}

	@Override
	public boolean createTestDatas(String tableName) {
		List<TaskDbVo> taskList = new ArrayList<>();
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(dt);
		for (int i = 0; i < 5000; i++) {
			TaskDbVo task = new TaskDbVo();
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			task.setTaskName(dateStr + ":" + uuid);
			taskList.add(task);
		}
		int a = taskDao.batchinsert(taskList);
		if (a == 5000) {
			return true;
		} else {
			return false;
		}
	}

}
