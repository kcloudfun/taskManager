package com.taskManager.springboot.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DemoSchedule {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Scheduled(fixedRate = 5000)
	public void doSome() {
		logger.info("DemoSchedule执行了");
	}
}
