# Spring Boot Master Class
Understand and love the power of Spring Boot - All its features are illustrated developing a web application managing todos and a basic API for survey questionnaire. Also covers unit testing, mocking and integration testing.

[![Image](https://www.springboottutorial.com/images/Course-Learn-Spring-Boot-in-100-Steps---Beginner-to-Expert.png "Learn Spring Boot in 100 Steps - Beginner to Expert")](https://www.udemy.com/course/spring-boot-tutorial-for-beginners/)

### Introduction

Spring Boot has a lot of magic going for it. 

Developing applications with it is cool and fun.

Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can “just run”. Most Spring Boot applications need very little Spring configuration.

In this course, you will learn the cool things about Spring Boot and Spring Boot Starter Projects. We will develop 
- A web application to manage your todos
- A basic REST Service to manage questions of a survey

You will learn about Spring Boot step by step - in more than 90 steps. This course would be a perfect first step as an introduction to Spring Boot.

Here is a quick overview of different sections of the course:
- Introduction to Spring in 10 Steps
- Introduction to the power of Spring Boot in 10 Steps
- Develop a Todo Management Web Application in 30 Steps
- Introduction to Unit Testing with JUnit in 5 Steps 
- Introduction to Mocking with Mockito in 5 Steps
- Advanced Features of Spring Boot in 25 Steps - We learn these developing a simple API for managing survey questionnaire.
- Introduction to JPA in 10 Steps
- Connecting our Todo Management Web Application to JPA

You will be using REST Services, Spring (Dependency Management), Spring MVC, Spring Boot, Spring Security (Authentication and Authorization), BootStrap (Styling Pages), Maven (dependencies management), Eclipse (IDE) and Tomcat Embedded Web Server. We will help you set up each one of these. 

You will learn about
- Basics of Spring Boot
- Basics of Auto Configuration and Spring Boot Magic
- Spring Boot Starter Projects
- Spring Initializr
- DispatcherServlet
- Basic Todo Management Application with Login/Logout
- Model, Controllers, ViewResolver and Filters
- Forms - DataBinding, Validation
- Annotation based approach - @RequestParam, @ModelAttribute, @SessionAttributes etc
- Bootstrap to style the page
- Basic REST Services using Spring Boot Starter Web
- REST Service Content Negotiation with JSON and XML
- Embedded servlet containers : Tomcat, Jetty and Undertow
- Writing Unit and Integration tests using Spring Boot Starter Test
- Profiles and Dynamic Configuration with Spring Boot
- Spring Boot Data JPA
- Spring Boot Actuator
- Spring Security
- Spring Boot Developer Tools and LiveReload

### Goals
- Provide quick start for projects with Spring.
- Be opinionated but provide options.
- Provide a range of non-functional features that are common to large classes of projects (e.g. embedded servers, security, metrics, health checks, externalized configuration).
- Absolutely no code generation and no requirement for XML configuration.

### 2.3.1 Spring Boot Upgrade

RECOMMENDED SPRING BOOT VERSION 
```
<parent>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>2.3.1.RELEASE</version>
</parent>
```


#### REST API

Github Folder - https://github.com/in28minutes/spring-boot-master-class/blob/master/05.Spring-Boot-Advanced

Final Code - https://github.com/in28minutes/spring-boot-master-class/blob/master/05.Spring-Boot-Advanced/2-3-1-upgrade.md

Commit - https://github.com/in28minutes/spring-boot-master-class/commit/362dde4914be0dfdf222eb5ec360808ff29a3bc4

New Code
```

<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
</dependency>

@RunWith(SpringRunner.class)
@WebMvcTest(value = SurveyController.class)
@WithMockUser
public class SurveyControllerTest {

```

Old Code
```

@RunWith(SpringRunner.class)
@WebMvcTest(value = SurveyController.class, secure = false)
public class SurveyControllerTest {
```

## Web Application

Github Folder - https://github.com/in28minutes/spring-boot-master-class/blob/master/02.Spring-Boot-Web-Application/

Final Code - https://github.com/in28minutes/spring-boot-master-class/blob/master/02.Spring-Boot-Web-Application/2-3-1-upgrade.md

Commit - https://github.com/in28minutes/spring-boot-master-class/commit/362dde4914be0dfdf222eb5ec360808ff29a3bc4

Add this in pom.xml

New Code
```

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-validation</artifactId>
</dependency>

```

Commit - https://github.com/in28minutes/spring-boot-master-class/commit/e1fcbe1e6599708a0a7ba3698f1e84eebfe1756b

TodoController.java
- `deleteTodo` - Use `repository.deleteById(id);` instead of `repository.delete(id);`
- `showUpdateTodoPage` - Use `Todo todo = repository.findById(id).get();` instead of `Todo todo = repository.findOne(id);`
- `todo.jsp` - Use `modelAttribute` instead of `commandName`

New Code
```
auth.inMemoryAuthentication()
            .passwordEncoder(NoOpPasswordEncoder.getInstance())
        		.withUser("in28Minutes").password("dummy")
```

Old Code
```
auth.inMemoryAuthentication().withUser("in28Minutes").password("dummy")
```


### Step Wise Details
Refer each steps

### Expectations
- You should know Java. You should understand usage of Annotations.
- A basic understanding of Spring is a bonus but NOT mandatory. We have seperate sections to introduce Spring.
- A basic understanding of JPA, Spring Security will be useful.
- You are NOT expected to have any experience with Eclipse or Maven.
- We will help you install Eclipse and get up and running with Maven.

## Installing Tools
- Installation Video : https://www.youtube.com/playlist?list=PLBBog2r6uMCSmMVTW_QmDLyASBvovyAO3
- GIT Repository For Installation : https://github.com/in28minutes/getting-started-in-5-steps
- PDF : https://github.com/in28minutes/SpringIn28Minutes/blob/master/InstallationGuide-JavaEclipseAndMaven_v2.pdf

## Running Examples
- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open Command Prompt and Change directory (cd) to folder containing pom.xml
- Open Eclipse 
   - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
   - Select the right project
- Choose the Spring Boot Application file (search for @SpringBootApplication)
- Right Click on the file and Run as Java Application
- You are all Set
- For help : use our installation guide - https://www.youtube.com/playlist?list=PLBBog2r6uMCSmMVTW_QmDLyASBvovyAO3


## Notes

```
Introduction

Spring Boot Master Class - Preview
Spring Boot Master Class - Course Overview
Spring Boot Master Class - Git Repository
Spring Boot Master Class - Installing Basic Tools


Step 0 : Web Application with Spring Boot - Section Introduction
Step 01: Part 1 Basic Spring Boot Web Application Setup
Step 01: Part 2 Pom.xml, Spring Boot Application and application properties
Step 02: Part 1 First Spring MVC Controller, @ResponseBody, @Controller
Step 02: Part 2 Understanding HTTP Request Flow
Step 03: Demystifying some of the Spring Boot magic
Step 04: Redirect to Login JSP - @ResponseBody and View Resolver
Step 05: Show userid and password on welcome page - ModelMap and @RequestParam
Step 06: DispatcherServlet and Spring MVC Flow
Step 07: Your First HTML form
Step 08: Add hard-coded validation of userid and password
Step 09: Magic of Spring
Step 10: Create TodoController and list-todos view. Make TodoService a @Service
Step 11: Architecture of Web Applications
Step 12: Session vs Model vs Request - @SessionAttributes
Step 13: Add new todo
Step 14: Display Todos in a table using JSTL Tags
Step 15: Bootstrap for Page Formatting using webjars
Step 16: Let's delete a Todo
Step 17: Format Add Todo Page and Adding Basic HTML5 form validation
Step 18: Part 1 Validations with Hibernate Validator - Using Command Bean
Step 18: Part 2 Using JSR 349 Validations
Step 19: Updating a todo
Step 20: Let's add a Target Date for Todo - Use initBinder to Handle Date Fields
Step 21: JSP Fragments and Navigation Bar
Step 22: Preparing for Spring Security
Step 23: Initial Spring Security Setup
Step 24: Refactor and add Logout Functionality using Spring Security
Step 25: Exception Handling


Introduction to JUnit in 5 Steps
Step 0 : JUnit in 5 Steps - Section Introduction
Step 1 : What is JUnit and Unit Testing?
Step 2 : First JUnit Project and Green Bar
Step 3 : First Code and First Unit Test
Step 4 : Other assert methods
Step 5 : Important annotations

Introduction to Mockito in 5 Steps
Step 0 : Mockito in 5 Steps - Section Introduction
Step 1 : Setting up an example using http://start.spring.io.
Step 2 : Using Stubs - Disadvantages
Step 3 : Your first Mock.
Step 4 : Using Mockito Annotations - @Mock, @InjectMocks, @RunWith(MockitoJUnitRunner.class)
Step 5 : Mocking List interface

Section Introduction - Spring Boot Deep Dive with a simple API

Introduction to JPA in 10 Steps
Step 0 : JPA with Spring Boot in 10 Steps - Section Introduction
Step 1 : Object Relational Impedence Mismatch - Understanding the problem that JPA solves
Step 2 : World before JPA - JDBC, Spring JDBC and myBatis
Step 3 : Introduction to JPA
Step 4 : Creating a JPA Project using Spring Initializr
Step 5 : Defining a JPA Entity - User
Step 6 : Defining a Service to manage the Entity - UserService and EntityManager
Step 7 : Using a Command Line Runner to save the User to Database
Step 8 : Magic of Spring Boot and In Memory Database H2
Step 9 : Introduction to Spring Data JPA
Step 10 : More JPA Repository : findById and findAll

Section Introduction - Connecting Web Application with JPA
Step 26: Adding Dependencies for JPA and H2
Step 27: Configuring H2 Console
Step 28: Create Todo Entity and JPA Repository
Step 29: Insert Todo using JPA Repository
Step 30: Update, Delete and Retrieve Todos using JPA Repository
Step 31: Data initialization with data.sql
Step 32: Connecting JPA to other databases
Step 33: Upgrading to Spring Boot 2 and Spring 5


Appendix Section Introduction - First 10 Steps in Spring 
Step 1 : Setting up a Spring Project using htttp://start.spring.io
Step 2 : Understanding Tight Coupling using the Binary Search Algorithm Example
Step 3 : Making the Binary Search Algorithm Example Loosely Coupled
Step 4 : Using Spring to Manage Dependencies - @Component, @Autowired
Step 5 : What is happening in the background?
Step 6 : Dynamic auto wiring and Troubleshooting - @Primary
Step 7 : Constructor and Setter Injection
Step 8 : Spring Modules
Step 9 : Spring Projects
Step 10 : Why is Spring Popular?

Congratulations!
```

### Troubleshooting
- Refer our TroubleShooting Guide - https://github.com/in28minutes/in28minutes-initiatives/tree/master/The-in28Minutes-TroubleshootingGuide-And-FAQ

## Youtube Playlists - 500+ Videos

[Click here - 30+ Playlists with 500+ Videos on Spring, Spring Boot, REST, Microservices and the Cloud](https://www.youtube.com/user/rithustutorials/playlists?view=1&sort=lad&flow=list)

## Keep Learning in28Minutes

in28Minutes is creating amazing solutions for you to learn Spring Boot, Full Stack and the Cloud - Docker, Kubernetes, AWS, React, Angular etc. - [Check out all our courses here](https://github.com/in28minutes/learn)
