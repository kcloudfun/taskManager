package com.taskManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taskManager.annotation.DbSwitchAnnotation;
import com.taskManager.service.ITaskService;
import com.taskManager.vo.db.TaskDbVo;
import com.taskManager.vo.response.CommonResult;

@RestController
@RequestMapping("/task/v1")
public class TaskController {

	@Autowired
	private ITaskService taskService;

	@DbSwitchAnnotation("master")
	@RequestMapping(value = "/{accountName}/list/get", method = RequestMethod.GET)
	public CommonResult<?> getTaskList(@PathVariable String accountName) {
		// accountName作为预留参数，后续补充权限管理
		return taskService.getTaskListByAccountName();
	}

	@RequestMapping(value = "/{accountName}/task/insert", method = RequestMethod.POST)
	public CommonResult<?> getTaskList1(@PathVariable String accountName, @RequestBody TaskDbVo task) {
		// accountName作为预留参数，后续补充权限管理
		return taskService.createOneTask(task);
	}
}
