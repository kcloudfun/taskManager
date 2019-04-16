package com.taskManager.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DataSourceConfig {

	@Value("${spring.datasource.druid.master-url}")
	private String masterUrl;

	@Value("${spring.datasource.druid.master-username}")
	private String masterUsername;

	@Value("${spring.datasource.druid.master-password}")
	private String masterPassword;

	@Value("${spring.datasource.druid.slave-url1}")
	private String slaveUrl1;

	@Value("${spring.datasource.druid.slave-username1}")
	private String slaveUsername1;

	@Value("${spring.datasource.druid.slave-password1}")
	private String slavePassword1;

	@Value("${spring.datasource.druid.driver-class-name}")
	private String driverClass;

	@Value("${mybatis.type-aliases-package}")
	private String typeAlias;

	@Value("${mybatis.mapperLocations}")
	private String mapperLocation;

	@Bean(name = "master")
	@Primary // 两个都是DataSource，都是同一个接口的实现@Primary表示在使用@Autowired时优先选用
	public DataSource master() {
		DruidDataSource source = new DruidDataSource();

		source.setDriverClassName(driverClass);
		source.setUrl(masterUrl);
		source.setUsername(masterUsername);
		source.setPassword(masterPassword);

		// 连接池基本属性配置
		return source;
	}

	@Bean(name = "slave1")
	public DataSource slave1() {
		DruidDataSource source = new DruidDataSource();

		source.setDriverClassName(driverClass);
		source.setUrl(slaveUrl1);
		source.setUsername(slaveUsername1);
		source.setPassword(slavePassword1);

		// 连接池基本属性配置
		return source;
	}

	@Bean(name = "dynamicDataSource")
	public DataSource dynamicDataSource() {
		DataSourceRouter dynamicRoutingDataSource = new DataSourceRouter();
		// 配置多数据源
		Map<Object, Object> dataSourceMap = new HashMap<>(2);
		dataSourceMap.put("master", master());
		dataSourceMap.put("slave1", slave1());
		// 将 master 数据源作为默认指定的数据源
		dynamicRoutingDataSource.setDefaultTargetDataSource(master());
		// 将 master 和 slave1 数据源作为指定的数据源
		dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
		return dynamicRoutingDataSource;
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
		sqlSessionFactoryBean.setTypeAliasesPackage(typeAlias);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocation));
		sqlSessionFactoryBean.setDataSource(dynamicDataSource());
		return sqlSessionFactoryBean;
	}

	@Bean(name = "jdbcTemplate")
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
