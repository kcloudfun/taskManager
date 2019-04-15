package com.taskManager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2配置类
 * 
 * @author 14145
 *
 */
@EnableSwagger2
@Configuration
public class Swagger2 {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				// 配置controller路径
				.apis(RequestHandlerSelectors.basePackage("com.taskManager.controller")).paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				// 页面标题
				.title("taskManager项目接口")
				// 描述信息
				.description("没事随便写写")
				// 作者人名，个人主页链接，邮箱地址
				.contact(new Contact("likai", "个人主页保密", "邮箱地址保密"))
				// 版本号
				.version("1.0").build();
	}

}
