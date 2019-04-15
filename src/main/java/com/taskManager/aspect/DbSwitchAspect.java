package com.taskManager.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 自定义切面，动态切换数据源用
 * 
 * @author 14145
 *
 */
@EnableAspectJAutoProxy
@Aspect
@Component
public class DbSwitchAspect {

	@Pointcut("@annotation(com.taskManager.annotation.DbSwitchAnnotation)") // 声明切点，此处设置为加了@DbSwitchAnnotation注解的切面生效
	public void annotationPointcut() {
	}

	@Before("annotationPointcut()")
	public void before(JoinPoint joinPoint) {
		System.out.println("切面生效，并执行了before方法");
	}

	@After("annotationPointcut()")
	public void after(JoinPoint joinPoint) {
		System.out.println("切面生效，并执行了after方法");
	}
}
