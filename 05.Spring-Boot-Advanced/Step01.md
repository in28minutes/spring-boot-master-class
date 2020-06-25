## What You Will Learn during this Step:
- Set up an Maven Project with Eclipse. 
 - Intellij Link : https://www.jetbrains.com/help/idea/2016.2/getting-started-with-maven.html#create_maven_project
- Copy Two Files pom.xml and Application.java
- Launch Your First Spring Boot Application.
- You will be introduced to Maven
  - Dependency Management

## Cool thing to note!
- Without a lot of configuration, we are up and running with a web application
 - Refer https://github.com/in28minutes/SpringMvcStepByStep/blob/master/Step15.md to understand the sort of stuff - web.xml, dispatcher servlet configuration, maven dependency management and plugins - that are need to launch a typical web application without Spring Boot!

## What You Will NOT Learn during this Step:
- Spring Boot does a lot of magic. This magic is called Auto Configuration. We will discuss about different terms related to Spring Boot - Starter Parent, Starter projects, Auto configuration - in depth during our first 10 steps.
- As far as this step is concerned, we will focus on getting up and running with Spring Boot. We will understand all the magic a little later. 
- We will copy a lot of code in this step - just to avoid typos

## Exercises
- If you are comfortable with Spring, try to create a few dependencies and see if are automatically auto-wired!

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
		<version>2.3.1.RELEASE</version>
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
