package com.taskManager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.taskManager.constant.TaskConstant;
import com.taskManager.dao.ITaskDao;
import com.taskManager.service.ITaskService;
import com.taskManager.service.ITemplateService;
import com.taskManager.vo.db.TaskDbVo;
import com.taskManager.vo.response.CommonResult;

@Service
public class TaskServiceImpl implements ITaskService {

	@Autowired
	private ITaskDao taskDao;

	@Autowired
	private ITemplateService templateService;

	@Override
	public CommonResult<List<TaskDbVo>> getTaskListByAccountName() {
		CommonResult<List<TaskDbVo>> result = new CommonResult<>();
		List<TaskDbVo> taskList = taskDao.selectTaskList();
		if (CollectionUtils.isEmpty(taskList)) {
			result.setData(new ArrayList<>());
		} else {
			result.setData(taskList);
		}
		return result;
	}

	@Override
	public CommonResult<Boolean> createOneTask(TaskDbVo task) {
		CommonResult<Boolean> result = new CommonResult<>();
		result.setData(templateService.insertOne(TaskConstant.TASK_TABLE_NAME, task));
		return result;
	}

}
