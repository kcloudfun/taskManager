package com.taskManager.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.taskManager.annotation.DbSwitchAnnotation;
import com.taskManager.config.DataSourceHolder;

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

	private final Logger logger = LoggerFactory.getLogger(DbSwitchAspect.class);

	@Pointcut("@annotation(com.taskManager.annotation.DbSwitchAnnotation)") // 声明切点，此处设置为加了@DbSwitchAnnotation注解就切面生效
	public void annotationPointcut() {
		logger.debug("======切入点@DbSwitchAnnotation");
	}

	@Before("annotationPointcut()")
	public void before(JoinPoint joinPoint) {
		logger.debug("@DbSwitchAnnotation切面生效，并执行了before方法");

		// 获取所切controller class对象
		Class<?> targetClass = joinPoint.getTarget().getClass();
		// 获取所切方法的参数类型
		Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
		try {
			// 根据方法名及参数类型，获取所切方法
			Method m = targetClass.getMethod(joinPoint.getSignature().getName(), parameterTypes);

			// 获取自定义注解上的value
			DbSwitchAnnotation data = m.getAnnotation(DbSwitchAnnotation.class);
			String dataSourceName = data.value();

			DataSourceHolder.setDbName(dataSourceName);
		} catch (Exception e) {
			logger.error("current thread " + Thread.currentThread().getName() + " add data to ThreadLocal error", e);
		}

	}

	@After("annotationPointcut()")
	public void after(JoinPoint joinPoint) {
		logger.debug("@DbSwitchAnnotation切面生效，并执行了after方法");
	}

	/**
	 * around会在方法前后都执行
	 */
	@Around("annotationPointcut()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		// 这是原方法前，比before还前
		logger.debug("@DbSwitchAnnotation切面生效，并进入了around方法");

		// 这是执行原方法
		Object res = proceedingJoinPoint.proceed();

		// 这是原方法后，比after要前
		logger.debug("@DbSwitchAnnotation切面生效，around方法执行完毕");
		return res;
	}

	@AfterReturning(returning = "res", pointcut = "annotationPointcut()")
	public void afterReturning(JoinPoint joinPoint, Object res) {
		// @AfterReturning必须获取@Around中返回的参数，也就是假如有@Around，@Around对应方法要有返回值，否则前端拿到的是空
		logger.debug("@DbSwitchAnnotation切面生效，并执行了afterReturning方法，原方法执行结果为：" + JSON.toJSONString(res));
	}
}
