package com.taskManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taskManager.annotation.DbSwitchAnnotation;
import com.taskManager.service.ITaskService;
import com.taskManager.vo.response.CommonResult;

@RestController
@RequestMapping("/task/v1")
public class TaskController {

	@Autowired
	private ITaskService taskService;

	@DbSwitchAnnotation("master")
	@RequestMapping(value = "/{accountName}/list/get", method = RequestMethod.GET)
	public CommonResult<?> getTaskList(@PathVariable String accountName) {
		System.out.println("执行了controller方法");
		return taskService.getTaskListByAccountName();
	}

	@RequestMapping(value = "/{accountName}/list/get1", method = RequestMethod.GET)
	public CommonResult<?> getTaskList1(@PathVariable String accountName) {
		System.out.println("执行了controller方法");
		return taskService.getTaskListByAccountName();
	}
}
