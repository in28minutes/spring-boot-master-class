## What You Will Learn during this Step:
- First installment of revealing how magic happens with Spring Boot. As a Spring Boot developer, you need to understand what's happening beneath the hood of Spring Boot! 
- spring-boot-starter-web : starter for building applications with Spring MVC. Tomcat is default embedded container.
- We already added this starter in the first step! Now we will explore the features it provides
- We will enable logging in DEBUG mode to understand further

##spring-boot-starter-web
- Spring Boot Starter Web brings all dependencies needed to build normal and RESTful web applications. Look at the dependency tree. 
 - All the dependencies are added in because of spring-boot-starter-web
 - Also look at /META-INF/spring.provides inside the spring-boot-starter-web.jar
- Spring Boot Starter Web auto configures things needed to startup a web application. Look at the log
 - Mapping servlet: 'dispatcherServlet' to [/]
 - Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
 - Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
 - Look at package org.springframework.boot.autoconfigure.web in spring-boot-autoconfigure-*.jar
- Go to url http://localhost:8080/some-non-existing-url

## Useful Snippets
/src/main/resources/application.properties
```
logging.level.org.springframework: DEBUG
```

## Files List
### pom.xml
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.in28minutes.springboot</groupId>
	<artifactId>first-springboot-project</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.4.0.RELEASE</version>
	</parent>

	<properties>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
</project>
```
### src/main/java/com/in28minutes/springboot/Application.java
```
package com.in28minutes.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

	}

}
```
### src/main/java/com/in28minutes/springboot/WelcomeController.java
```
package com.in28minutes.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	//Auto wiring
	@Autowired
	private WelcomeService service;

	@RequestMapping("/welcome")
	public String welcome() {
		return service.retrieveWelcomeMessage();
	}
}
```
### src/main/java/com/in28minutes/springboot/WelcomeService.java
```
package com.in28minutes.springboot;

import org.springframework.stereotype.Component;

@Component
public class WelcomeService {

	public String retrieveWelcomeMessage() {
		//Complex Method
		return "Good Morning updated";
	}
}
```
### src/main/resources/application.properties
```
logging.level.org.springframework: DEBUG
```
