package com.taskManager.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置（配置多个线程池时候可设置主次，若设置了主次则@asyn注解上不指名线程池时候取主，指定时按指定名称取）
 * 
 * @author 14145
 *
 */
@EnableAsync
@Configuration
public class ThreadPoolConfig {

	@Value("${threadpool.corePoolSize}")
	private int corePoolSize;

	@Value("${threadpool.maxPoolSize}")
	private int maxPoolSize;

	@Value("${threadpool.queueCapacity}")
	private int queueCapacity;

	@Value("${threadpool.keepAliveSeconds}")
	private int keepAliveSeconds;// yml配置不支持计算，写表达式会被当成字符串

	@Bean("threadPool1")
	@Primary
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		/* 设置核心线程数 */
		executor.setCorePoolSize(corePoolSize);
		/* 设置线程池最多线程数 */
		executor.setMaxPoolSize(maxPoolSize);// 想要速度的话一般cpu密集型可设置n个作为最大线程,n为cpu核心数。但这结论并没有多大意义，io为2n
		/* 设置队列大小 */
		executor.setQueueCapacity(queueCapacity);
		/* 设置活跃时间 */
		executor.setKeepAliveSeconds(keepAliveSeconds);
		/* 设置线程名前缀 */
		executor.setThreadNamePrefix("threadPool1-");
		/* 设置拒绝策略 */
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());// 重写这个策略能实现自定义策略，比如队列满了之后后面进来的怎么怎么处理
		/* 初始化 */
		executor.initialize();
		return executor;
	}

	@Bean("threadPool2")
	public Executor getAsyncExecutor2() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		/* 设置核心线程数 */
		executor.setCorePoolSize(corePoolSize);
		/* 设置线程池最多线程数 */
		executor.setMaxPoolSize(maxPoolSize);// 想要速度的话一般cpu密集型可设置n个作为最大线程,n为cpu核心数。但这结论并没有多大意义，io为2n
		/* 设置队列大小 */
		executor.setQueueCapacity(queueCapacity);
		/* 设置活跃时间 */
		executor.setKeepAliveSeconds(keepAliveSeconds);
		/* 设置线程名前缀 */
		executor.setThreadNamePrefix("threadPool2-");
		/* 设置拒绝策略 */
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());// 重写这个策略能实现自定义策略，比如队列满了之后后面进来的怎么怎么处理
		/* 初始化 */
		executor.initialize();
		return executor;
	}

}
