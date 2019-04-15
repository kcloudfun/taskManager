package com.taskManager.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@EnableAspectJAutoProxy
@Aspect
@Component
public class DbSwitchAspect {

	@Pointcut("@annotation(com.taskManager.annotation.DbSwitchAnnotation)") // 注解声明切点
	public void annotationPointcut() {
	}

	@Before("annotationPointcut()")
	public void before(JoinPoint joinPoint) {
		System.out.println("执行了before方法");
	}

	@After("annotationPointcut()")
	public void after(JoinPoint joinPoint) {
		System.out.println("执行了after方法");
	}
}
