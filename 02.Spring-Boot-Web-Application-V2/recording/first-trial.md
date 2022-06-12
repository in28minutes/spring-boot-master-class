<!---
Current Directory : /Ranga/001.Notes/00.CoursePreparations/2022-06-Spring-Boot-Upgrade-Code/trial
-->

## Complete Code Example


### /pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.0.0-M3</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.in28minutes.springboot.web</groupId>
	<artifactId>spring-boot-first-web-application</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>spring-boot-first-web-application</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>18</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
		</dependency>
		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
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
	<repositories>
		<repository>
			<id>spring-milestones</id>
			<name>Spring Milestones</name>
			<url>https://repo.spring.io/milestone</url>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
		</repository>
	</repositories>
	<pluginRepositories>
		<pluginRepository>
			<id>spring-milestones</id>
			<name>Spring Milestones</name>
			<url>https://repo.spring.io/milestone</url>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
		</pluginRepository>
	</pluginRepositories>

</project>
```
---

### /src/main/java/com/in28minutes/springboot/web/SpringBootFirstWebApplication.java

```java
package com.in28minutes.springboot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootFirstWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFirstWebApplication.class, args);
	}

}
```
---

### /src/main/java/com/in28minutes/springboot/web/login/AuthenticationService.java

```java
package com.in28minutes.springboot.web.login;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {
	public boolean authenticateUser(String user, String password) {
		boolean isValidUser = user.equalsIgnoreCase("in28minutes");
		boolean isValidPassword = password.equalsIgnoreCase("dummy");
		return isValidUser && isValidPassword;
	}
}
```
---

### /src/main/java/com/in28minutes/springboot/web/login/LoginController.java

```java
package com.in28minutes.springboot.web.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	public LoginController(AuthenticationService service) {
		this.service = service;
	}

	private final AuthenticationService service;

	// say-hello-jsp -> login.jsp
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLoginPage() {
		return "login";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String showWelcomePage(ModelMap model, @RequestParam String name
			, @RequestParam String password) {
		boolean isValidUser = service.authenticateUser(name, password);
		
		if(!isValidUser) {
			model.addAttribute("errorMessage", "Invalid Credentials. Please try again!");
			return "login";
		}
		
		model.addAttribute("name", name);
		return "welcome";
	}

}
```
---

### /src/main/java/com/in28minutes/springboot/web/sayhello/SayHelloController.java

```java
package com.in28minutes.springboot.web.sayhello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
	
	// localhost:8080/say-hello - "Hello World"
	@RequestMapping("/say-hello")
	@ResponseBody
	public String sayHello() {
		return "Hello World v2";
	}
	
	//THIS IS NOT COOL
	//THIS IS NOT RECOMMENDED!!
	@RequestMapping("/say-hello-html")
	@ResponseBody
	public String sayHelloWithHtml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>Hello World From HTML</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("Hello World From Body");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}

	//say-hello-jsp -> login.jsp
	@RequestMapping("/say-hello-jsp")
	public String sayHelloWithJSP() {
		return "sayHello";
	}

}
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/login.jsp

```
<html>
<head>
<title>Yahoo!!</title>
</head>
<body>

<pre>${errorMessage}</pre>

<form method="post">
	Name : <input name="name" type="text" />
	Password : <input type="password" name="password" /> 
	<input type="submit" />
</form>

</body>
</html>
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp

```
<html>
<head>
<title>Yahoo!!</title>
</head>
<body>
My First JSP!!!
</body>
</html>
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/welcome.jsp

```
<html>
<head>
<title>Yahoo!!</title>
</head>
<body>
Welcome ${name}!
I'm sure you will have a wonderful learning experience!

</body>
</html>
```
---

### /src/main/resources/application.properties

```properties
logging.level.org.springframework=DEBUG
spring.mvc.view.prefix: /WEB-INF/jsp/
spring.mvc.view.suffix: .jsp
```
---

### /src/test/java/com/in28minutes/springboot/web/SpringBootFirstWebApplicationTests.java

```java
package com.in28minutes.springboot.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootFirstWebApplicationTests {

	@Test
	void contextLoads() {
	}

}
```
---
