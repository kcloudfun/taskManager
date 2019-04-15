package com.taskManager.service.impl;

import java.util.List;

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

}
