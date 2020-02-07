package com.codelab.springBootDemo;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {
	@Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.codelab.springBootDemo.entity");
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.put("hibernate.show_sql", true);
        hibernateProperties.put("hibernate.hbm2ddl.auto", "create");
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }
	 @Bean
	    public DataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	        dataSource.setUrl("jdbc:mysql://localhost:3306/world");
	        dataSource.setUsername("root");
	        dataSource.setPassword("root");
	        return dataSource;
	    }

	    @Bean
	    public HibernateTransactionManager transactionManager() {
	        HibernateTransactionManager txManager = new HibernateTransactionManager();
	        txManager.setSessionFactory(sessionFactory().getObject());
	        return txManager;
	    }
}
