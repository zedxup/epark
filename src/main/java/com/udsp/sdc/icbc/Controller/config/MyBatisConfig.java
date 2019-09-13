package com.udsp.sdc.icbc.Controller.config;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * mybatis配置
 * 
 * @author 李炀
 *
 */
@Configuration
@MapperScan(basePackages = {"com.udsp.sdc.icbc"})
public class MyBatisConfig {

	@Bean("mybatis.sqlsession.simple")
	@Primary
	public SqlSessionTemplate simpleSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.SIMPLE);
	}

	@Bean("mybatis.sqlsession.batch")
	public SqlSessionTemplate batchSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
	}
}
