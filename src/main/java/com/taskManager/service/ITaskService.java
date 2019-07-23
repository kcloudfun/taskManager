package com.taskManager.service;

import java.util.List;

import com.taskManager.vo.db.TaskDbVo;
import com.taskManager.vo.response.CommonResult;

public interface ITaskService {

	public CommonResult<List<TaskDbVo>> getTaskListByAccountName();

	public CommonResult<Boolean> createOneTask(TaskDbVo task);

}
