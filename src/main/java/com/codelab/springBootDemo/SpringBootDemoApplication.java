package com.codelab.springBootDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.codelab.springBootDemo.service.UserService;
import com.codelab.springBootDemo.util.ApplicationContextUtils;

@SpringBootApplication
public class SpringBootDemoApplication {
	private static final Logger LOGGER=LoggerFactory.getLogger(SpringBootDemoApplication.class);
	@Autowired
	 private static ApplicationContextUtils util; 
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
		 LOGGER.info("Simple log statement with inputs {}, {} and {}", 1,2,3);
		 LOGGER.info("********************"+util.getApplicationContext().getBeanDefinitionCount());
		 LOGGER.info("********************"+util.getApplicationContext().getApplicationName());
	}
	

}
/**
 * It contains a simple annotation called @SpringBootApplication 
 * which is a combination of the following more specific spring annotations -
@Configuration : Any class annotated with @Configuration annotation is bootstrapped 
by Spring and is also considered as a source of other bean definitions.
@EnableAutoConfiguration : This annotation tells Spring to automatically configure your application based 
on the dependencies that you have added in the pom.xml file.
For example, If spring-data-jpa or spring-jdbc is in the classpath, then it automatically tries to
 configure a DataSource by reading the database properties from application.properties file.

@ComponentScan : It tells Spring to scan and bootstrap other components defined in the current package (com.example.easynotes) and all the sub-packages.

The main() method calls Spring Boot’s SpringApplication.run() method to launch the application.

2. resources/
This directory, as the name suggests, is dedicated to all the static resources, templates and property files.
resources/static - contains static resources such as css, js and images.
resources/templates - contains server-side templates which are rendered by Spring.
resources/application.properties - This file is very important. It contains application-wide properties. Spring reads the properties defined in this file to configure your application. You can define server’s default port, server’s context path, database URLs etc, in this file.
You can refer this page for common application properties used in Spring Boot.
3. EasyNotesApplicationTests - Define unit and integration tests here.
4. pom.xml - contains all the project dependencies


 * 
 */
