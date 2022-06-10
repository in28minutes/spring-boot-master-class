## Complete Code Example


### /pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.in28minutes.springboot.web</groupId>
	<artifactId>spring-boot-first-web-application-git</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>spring-boot-first-web-application</name>
	<description>Demo project for Spring Boot</description>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.0.0-M3</version> <!-- Changed from 2.3.1.RELEASE -->
		<relativePath /> <!-- lookup parent from repository -->
	</parent>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>18</java.version>
		<maven-jar-plugin.version>3.1.1</maven-jar-plugin.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<!-- START AND STOP THE APPLICATION AFTER MAKING THE CHANGE! -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>

		<dependency>
			<groupId>jakarta.servlet.jsp.jstl</groupId>
			<artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
		</dependency>
		
		<dependency>
			<groupId>org.eclipse.jetty</groupId>
			<artifactId>glassfish-jstl</artifactId>
		</dependency>

		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>bootstrap</artifactId>
			<version>5.1.3</version>
		</dependency>

		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>bootstrap-datepicker</artifactId>
			<version>1.9.0</version> <!-- Changed from 1.0.1 -->
		</dependency>

		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>jquery</artifactId>
			<version>3.6.0</version> <!-- Changed from 1.9.1 -->
		</dependency>

		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
			<scope>provided</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
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
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.in28minutes.springboot.web")
public class SpringBootFirstWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFirstWebApplication.class, args);
	}
}
```
---

### /src/main/java/com/in28minutes/springboot/web/controller/ErrorController.java

```java
package com.in28minutes.springboot.web.controller;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller("error")
public class ErrorController {
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException
		(HttpServletRequest request, Exception ex){
		ModelAndView mv = new ModelAndView();

		mv.addObject("exception", ex.getLocalizedMessage());
		mv.addObject("url", request.getRequestURL());
		
		mv.setViewName("error");
		return mv;
	}

}
```
---

### /src/main/java/com/in28minutes/springboot/web/controller/TodoController.java

```java
package com.in28minutes.springboot.web.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.in28minutes.springboot.web.model.Todo;
import com.in28minutes.springboot.web.service.TodoService;

import jakarta.validation.Valid;

@Controller
public class TodoController {
	
//	@Autowired
//	TodoRepository repository;

	@Autowired
	TodoService service;

	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		String name = getLoggedInUserName(model);
		//model.put("todos", repository.findByUser(name));
		model.put("todos", service.findByUser(name));
		return "list-todos";
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		
		return principal.toString();
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		model.addAttribute("todo", new Todo(0, getLoggedInUserName(model),
				"Default Desc", LocalDate.now(), false));
		return "todo";
	}

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {

		//if(id==1)
			//throw new RuntimeException("Something went wrong");
		//repository.deleteById(id);
		service.deleteById(id);
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		//Todo todo = repository.findById(id).get();
		Todo todo = service.findById(id);
		model.put("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo,
			BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}

		todo.setUser(getLoggedInUserName(model));

		//repository.save(todo);
		service.updateTodo(todo);

		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}

		todo.setUser(getLoggedInUserName(model));
		//repository.save(todo);
		service.addTodo(getLoggedInUserName(model), todo.getDesc(), todo.getTargetDate(),
				false);
		return "redirect:/list-todos";
	}
}
```
---

### /src/main/java/com/in28minutes/springboot/web/controller/WelcomeController.java

```java
package com.in28minutes.springboot.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showWelcomePage(ModelMap model) {
		model.put("name", getLoggedinUserName());
		return "welcome";
	}

	private String getLoggedinUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		
		return principal.toString();
	}

}
```
---

### /src/main/java/com/in28minutes/springboot/web/model/Todo.java

```java
package com.in28minutes.springboot.web.model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Todo {
    
	@Id
	@GeneratedValue
	private int id;
    
	private String user;
    
    @Size(min=10, message="Enter at least 10 Characters...")
    private String desc;

    private LocalDate targetDate;
    private boolean isDone;

    public Todo() {
    		super();
    }
    
    public Todo(int id, String user, String desc, LocalDate targetDate,
            boolean isDone) {
        super();
        this.id = id;
        this.user = user;
        this.desc = desc;
        this.targetDate = targetDate;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Todo other = (Todo) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format(
                "Todo [id=%s, user=%s, desc=%s, targetDate=%s, isDone=%s]", id,
                user, desc, targetDate, isDone);
    }

}
```
---

### /src/main/java/com/in28minutes/springboot/web/security/SecurityConfiguration.java

```java
package com.in28minutes.springboot.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User
				.withUsername("in28minutes").password("{noop}dummy").roles("ADMIN", "USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests((authorize) -> {
			try {
				authorize
					.antMatchers("/login", "/h2-console/**").permitAll()
					.antMatchers("/", "/*todo*/**").hasRole("USER")
					.and().formLogin()
					.and().csrf().disable().headers().frameOptions().disable();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
		return http.build();
	}

}
```
---

### /src/main/java/com/in28minutes/springboot/web/service/TodoRepository.java

```java
package com.in28minutes.springboot.web.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.springboot.web.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer>{
	List<Todo> findByUser(String userName);	
}
```
---

### /src/main/java/com/in28minutes/springboot/web/service/TodoService.java

```java
package com.in28minutes.springboot.web.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.in28minutes.springboot.web.model.Todo;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<Todo>();

	private static int todoCount = 3;

	static {
		todos.add(new Todo(1, "in28minutes", "Learn Spring MVC", LocalDate.now(), false));
		todos.add(new Todo(2, "in28minutes", "Learn Struts", LocalDate.now(), false));
		todos.add(new Todo(3, "in28minutes", "Learn Hibernate", LocalDate.now(), false));
	}

	public List<Todo> findByUser(String user) {
		return todos.stream().filter(todo -> todo.getUser().equalsIgnoreCase(user)).toList();
	}

	public Todo findById(int id) {
		return todos.stream().filter(todo -> todo.getId() == id).findFirst().orElse(null);
	}

	public void updateTodo(Todo todo) {
		todos.remove(todo);
		todos.add(todo);
	}

	public void addTodo(String name, String desc, LocalDate targetDate, boolean isDone) {
		todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
	}

	public void deleteById(int id) {
		todos.removeIf(todo -> todo.getId() == id);
	}
}
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/common/footer.jspf

```
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
<script
	src="webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.js"></script>

<script>
	$('#targetDate').datepicker({
		format : 'dd/mm/yyyy'
	});
</script>

</body>
</html>


```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/common/header.jspf

```
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>

<head>
<title>Todo Web Application</title>
<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css"
	rel="stylesheet">

</head>

<body>
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/common/navigation.jspf

```
<nav class="navbar navbar-expand-md navbar-light bg-light m-3 p-1">
		<a class="navbar-brand m-1" href="https://courses.in28minutes.com">in28Minutes</a>
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="/">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="/list-todos">Todos</a></li>
			</ul>
		</div>
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
		</ul>	
</nav>
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/error.jsp

```
<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
An exception occurred! Please contact Support!
</div>

<%@ include file="common/footer.jspf"%>
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/list-todos.jsp

```
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	
<main class="container m-4">

	<h1>Your Todos</h1>
	
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Description</th>
				<th>Target Date</th>
				<th>Is it Done?</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="todo">
				<tr>
					<td>${todo.desc}</td>
					<fmt:parseDate value="${todo.targetDate}" pattern="yyyy-MM-dd" var="targetDate" type="date"/>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${targetDate}"/></td>
					<td>${todo.done}</td>
					<td><a type="button" class="btn btn-success"
						href="/update-todo?id=${todo.id}">Update</a></td>
					<td><a type="button" class="btn btn-warning"
						href="/delete-todo?id=${todo.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<a class="button" href="/add-todo">Add a Todo</a>
	</div>
</main>

<%@ include file="common/footer.jspf" %>
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/todo.jsp

```
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<main class="container m-4">
	
	<h1>Enter Your Todo Details</h1>
	
	<form:form method="post" modelAttribute="todo">

		<form:hidden path="id" />

		<fieldset class="form-group mb-3">
			<form:label path="desc" class="form-label">Description</form:label>
			<form:input path="desc" type="text" class="form-control"
				required="required" />
			<form:errors path="desc" cssClass="text-warning" />
		</fieldset>

		<fieldset class="form-group mb-3">
			<form:label path="targetDate" class="form-label">Target Date</form:label>
			<form:input path="targetDate" type="text" class="form-control"
				required="required" />
			<form:errors path="targetDate" cssClass="text-warning" />
		</fieldset>

		<button type="submit" class="btn btn-success">Add</button>

	</form:form>

</main>

<%@ include file="common/footer.jspf" %>
```
---

### /src/main/resources/META-INF/resources/WEB-INF/jsp/welcome.jsp

```
<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<main class="container m-4">

		<h1>Welcome</h1>

		Welcome ${name}!! <a href="/list-todos">Click here</a> to manage your
		todo's.

</main>

<%@ include file="common/footer.jspf"%>
```
---

### /src/main/resources/application.properties

```properties
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp
logging.level.org.springframework.web=INFO

spring.jpa.show-sql=true
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb;NON_KEYWORDS=USER
spring.jpa.defer-datasource-initialization=true
```
---

### /src/main/resources/data.sql

```
/* Changed from sysdate()(NotAvailable in Latest H2 Functions) to CURRENT_DATE */
insert into TODO
values(10001, 'Learn Spring Boot', false, CURRENT_DATE(), 'in28minutes');
insert into TODO
values(10002, 'Learn Angular JS', false, CURRENT_DATE(), 'in28minutes');
insert into TODO
values(10003, 'Learn to Dance', false, CURRENT_DATE(), 'in28minutes');
```
---

### /src/test/java/com/in28minutes/springboot/web/SpringBootFirstWebApplicationTests.java

```java
package com.in28minutes.springboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// replaced @RunWith with @ExtendWith
// replaced SpringRunner.class with SpringExtension.class
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SpringBootFirstWebApplicationTests {

	@Test
	public void contextLoads() {
	}

}

```
---
