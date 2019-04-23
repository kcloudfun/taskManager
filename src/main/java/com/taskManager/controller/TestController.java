package com.taskManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taskManager.thread.TestThread;
import com.taskManager.vo.response.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "测试入口")
@RequestMapping("/task/test")
public class TestController {

	@Autowired
	private TestThread testThread;

	@ApiOperation(value = "测试线程池")
	@RequestMapping(value = "/threadPool", method = RequestMethod.GET)
	public CommonResult<?> testThreadPool() {
		System.out.println("1111");
		testThread.doSome();
		System.out.println("22222");
		return null;
	}
}
