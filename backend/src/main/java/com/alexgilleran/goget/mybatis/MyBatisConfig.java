package com.alexgilleran.goget.mybatis;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@MapperScan("com.alexgilleran.goget.mybatis")
public class MyBatisConfig {

    @Value("${mysql.url}")
    private String mySqlUrl;
    @Value("${mysql.user}")
    private String mySqlUser;
    @Value("${mysql.password}")
    private String mySqlPassword;
    @Value("${mysql.driver}")
    private String mySqlDriver;

    @Bean
    public DriverManagerDataSource buildDriverManagerDataSource() {
	DriverManagerDataSource datasource = new DriverManagerDataSource(mySqlUrl, mySqlUser, mySqlPassword);
	datasource.setDriverClassName(mySqlDriver);
	return datasource;
    }

    @Bean(name = "sqlSessionFactory")
    @Autowired
    public SqlSessionFactoryBean provideFactoryBean(DriverManagerDataSource dataSource) {
	SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
	bean.setDataSource(dataSource);
	return bean;
    }

}
