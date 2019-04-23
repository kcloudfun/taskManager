package com.taskManager.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 测试用线程
 * 
 * @author 14145
 *
 */
@Component
public class TestThread {

	private final Logger logger = LoggerFactory.getLogger(TestThread.class);

	@Async("threadPool2")
	public void doSome() {
		logger.debug("测试线程开始执行》》》》》");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("测试线程执行结束《《《《《");
	}
}
