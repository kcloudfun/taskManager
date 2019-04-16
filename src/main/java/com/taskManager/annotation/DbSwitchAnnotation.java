package com.taskManager.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，动态切换数据源用
 * 
 * @author 14145
 *
 */
@Target({ ElementType.METHOD }) // 注解生效的范围，此处设置注解只能标注在方法上
@Retention(RetentionPolicy.RUNTIME) // 注解被保留的时间长短，当前表示运行时保留
@Documented // 加上之后可被javadoc生成出去
public @interface DbSwitchAnnotation {

	String value();
}
