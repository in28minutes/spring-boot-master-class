# Spring Boot Master Class
Understand and love the power of Spring Boot - All its features are illustrated developing a web application managing todos and a basic API for survey questionnaire. Also covers unit testing, mocking and integration testing.

[![Image](https://www.springboottutorial.com/images/Course-Learn-Spring-Boot-in-100-Steps---Beginner-to-Expert.png "Learn Spring Boot in 100 Steps - Beginner to Expert")](https://www.udemy.com/course/spring-boot-tutorial-for-beginners/)

## Installing Tools

### Our Recommendations

- Use **latest version** of Java
- Use **latest version** of "Eclipse IDE for Enterprise Java Developers"
- Remember: Spring Boot 3+ works only with Java 17+

### Installing Java

- Windows - https://www.youtube.com/watch?v=I0SBRWVS0ok
- Linux - https://www.youtube.com/watch?v=mHvFpyHK97A
- Mac - https://www.youtube.com/watch?v=U3kTdMPlgsY

#### Troubleshooting

- Troubleshooting Java Installation - https://www.youtube.com/watch?v=UI_PabQ1YB0

### Installing Eclipse

- Windows - https://www.youtube.com/watch?v=toY06tsME-M
- Others - https://www.youtube.com/watch?v=XveQ9Gq41UM

#### Troubleshooting
- Configuring Java in Eclipse - https://www.youtube.com/watch?v=8i0r_fcE3L0


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


## Lecture Uploads

### Files to Upload 20220627
```
00 Spring Boot Master Class 00 - Promo 20220627.mp4
00 Spring Boot Master Class 01 - Course Preview 20220627.mp4
00 Spring Boot Master Class 03 - Installing Java and Eclipse 20220627.mp4

92 Step 01 - Getting Started with Spring Boot - Goals.mp4
92 Step 02 - Setting up New Spring Boot Project with Spring Initializr.mp4
92 Step 03 - Build a Hello World API with Spring Boot.mp4
92 Step 04 - Understanding the World Before Spring Boot - 10000 Feet Overview.mp4
92 Step 05 - Understanding Spring Boot Magic - Spring Boot Starter Projects.mp4
92 Step 06 - Understanding Spring Boot Magic - Auto Configuration.mp4
92 Step 07 - Playing with Spring Boot.mp4
92 Step 08 - Understanding Spring Boot Magic - Embedded Servers.mp4
92 Step 09 - Exploring Spring Boot Actuator.mp4
92 Step 10 - Exploring Spring Boot DevTools.mp4
92 Step 11 - Understanding Spring Boot vs Spring vs Spring MVC.mp4
92 Step 12 - Getting Started with Spring Boot - Review.mp4

Step 01 - Creating Spring Boot Web Application with Spring Initializr 20220627.mp4
Step 02 - Quick overview of Spring Boot Project 20220627.mp4
Step 03 - First Spring MVC Controller, @ResponseBody, @Controller 20220627.mp4
Step 04 - 01 - Enhancing Spring MVC Controller to provide HTML response 20220627.mp4
Step 04 - 02 - Exploring Step By Step Coding and Debugging Guide 20220627.mp4
Step 05 - Redirect to a JSP using Spring Boot - Controller, @ResponseBody and View Resolver 20220627.mp4
Step 06 - Exercise - Creating LoginController and login view 20220627.mp4
Step 07 - Quick Overview - How does web work - Request and Response 20220627.mp4
Step 08 - Capturing QueryParams using RequestParam and First Look at Model 20220627.mp4
Step 09 - Quick Overview - Importance of Logging with Spring Boot 20220627.mp4
Step 10 - Understanding DispatcherServlet, Model 1, Model 2 and Front Controller 20220627.mp4
Step 11 - Creating a Login Form 20220627.mp4
Step 12 - Displaying Login Credentials in a JSP using Model 20220627.mp4
Step 13 - Add hard coded validation of userid and password 20220627.mp4
Step 14 - Getting started with Todo Features - Creating Todo and TodoService 20220627.mp4
Step 15 - Creating first version of List Todos Page 20220627.mp4
Step 16 - Understanding Session vs Model vs Request - @SessionAttributes 20220627.mp4
Step 17 - Adding JSTL to Spring Boot Project and Showing Todos in a Table 20220627.mp4
Step 18 - Adding Bootstrap CSS framework to Spring Boot Project using webjars 20220627.mp4
Step 19 - Formatting JSP pages with Bootstrap CSS framework 20220627.mp4
Step 20 - Lets Add a New Todo - Create a new View 20220627.mp4
Step 21 - Enhancing TodoService to add the todo 20220627.mp4
Step 22 - Adding Validations using Spring Boot Starter Validation 20220627.mp4
Step 23 - Using Command Beans to implement New Todo Page Validations 20220627.mp4
Step 24 - Implementing Delete Todo Feature - New View 20220627.mp4
Step 25 - Implementing Update Todo - 1 - Show Update Todo Page 20220627.mp4
Step 26 - Implementing Update Todo - 1 - Save changes to Todo 20220627.mp4
Step 27 - Adding Target Date Field to Todo Page 20220627.mp4
Step 28 - Adding a Navigation Bar and Implementing JSP Fragments 20220627.mp4
Step 29 - Preparing for Spring Security 20220627.mp4
Step 30 - Setting up Spring Security with Spring Boot Starter Security 20220627.mp4
Step 31 - Configuring Spring Security with Custom User and Password Encoder 20220627.mp4
Step 32 - Refactoring and Removing Hardcoding of User Id 20220627.mp4
Step 33 - Setting up a New User for Todo Application 20220627.mp4
Step 34 - Adding Spring Boot Starter Data JPA and Getting H2 database ready 20220627.mp4
Step 35 - 01 - Configuring Spring Security to Get H2 console Working 20220627.mp4
Step 35 - 02 - JDBC to Spring JDBC to JPA to Spring Data JPA - 10000 Feet Overview.mp4
Step 36 - Making Todo an Entity and Population Todo Data into H2 20220627.mp4
Step 37 - Creating TodoRepository and Connecting List Todos page from H2 database 20220627.mp4
Step 38 - 01 - Connecting All Todo App Features to H2 Database 20220627.mp4
Step 38 - 02 - Exploring Magic of Spring Boot Starter JPA and JpaRepository 20220627.mp4
Step 39 - OPTIONAL - Overview of Connecting Todo App to MySQL database 20220627.mp4
Step 40 - OPTIONAL - Installing Docker 20220627.mp4
Step 41 - OPTIONAL - Connecting Todo App to MySQL database 20220627.mp4

Step 00 - Introduction to Functional Programming - Overview 20220627.mp4
Step 01 - Getting Started with Functional Programming with Java.mp4
Step 02 - Writing Your First Java Functional Program.mp4
Step 03 - Improving Java Functional Program with filter.mp4
Step 04 - Using Lambda Expression to enhance your Functional Program.mp4
Step 05 - Do Functional Programming Exercises with Streams, Filters and Lambdas.mp4
Step 06 - Using map in Functional Programs - with Exercises.mp4
Step 07 - Quick Review of Functional Programming Basics.mp4
```

### Files to Upload 20220702
```
Step 01 - Quick Introduction to REST - Understand Resource and Actions 20220701.mp4
Step 02 - Creating Spring Boot Project for REST with Maven and Eclipse 20220701.mp4
Step 03 - Creating your first Spring Boot Resource - Hello World 20220701.mp4
Step 04 - Creating a Second Spring Boot Resource Method - Hello World Bean 20220701.mp4
Step 05 - Exploring Path Params and Path Variables with Spring Boot 20220701.mp4
Step 06 - Getting Ready for Survey Questionnaire REST API 20220701.mp4
Step 07 - Creating First Survey Spring Boot REST API - GET all surveys 20220701.mp4
Step 08 - Creating Second Survey Spring Boot REST API Method - GET a survey 20220701.mp4
Step 09 - Exploring REST API Best Practices - Request Methods and Response Status 20220701.mp4
Step 10 - Exercise - Creating Survey Question related Spring Boot REST API Methods 20220701.mp4
Step 11 - Creating Spring Boot REST API to create Survey Question - POST 20220701.mp4
Step 12 - Improving POST Method - Status CREATED and Location Header 20220701.mp4
Step 13 - Implementing Spring Boot REST API Method to DELETE a Question 20220701.mp4
Step 14 - Implementing Spring Boot REST Method to Update a Question - PUT 20220701.mp4
Step 15 - Setting up Spring Boot Data JPA with H2 Database and User Entity 20220701.mp4
Step 16 - Exploring Spring Boot Data JPA using Command Line Runner 20220701.mp4
Step 17 - Creating User REST API with Spring Boot Starter Rest 20220701.mp4
Step 18 - Writing Your First Spring Boot Integration Test 20220701.mp4
Step 19 - Writing Asserts for JSON in Spring Boot Tests - JsonAssert 20220701.mp4
Step 20 - Improving JUnit Asserts for Spring Boot Integration Test 20220701.mp4
Step 21 - Writing Spring Boot Integration Test for GET method returning List 20220701.mp4
Step 22 - Writing Spring Boot Integration Test for POST method creating a Question 20220701.mp4
Step 23 - Understanding JUnit Best Practice - Have ZERO Side Effects 20220701.mp4
Step 24 - Writing Your First Spring Boot Mock MVC Unit Test 20220701.mp4
Step 25 - Improving Asserts for Spring Boot Mock MVC Unit Test 20220701.mp4
Step 26 - Writing Spring Boot Mock MVC Unit Test for POST Method 20220701.mp4
Step 27 - Getting Started with Spring Boot Starter Security 20220701.mp4
Step 28 - Configuring Spring Security for Spring Boot REST API 20220701.mp4
Step 29 - Fixing Spring Boot Unit and Integration Tests 20220701.mp4

01 Step 01 - What is JUnit and Unit Testing? 20220701.mp4
01 Step 02 - Your First JUnit Project and Green Bar 20220701.mp4
01 Step 03 - Your First Code and First Unit Test 20220701.mp4
01 Step 04 - Exploring other assert methods 20220701.mp4
01 Step 05 - Exploring few important JUnit annotations 20220701.mp4

02 Step 00 - Introduction to Section - Mockito in 5 Steps 20220701.mp4
02 Step 01 - Setting up a Spring Boot Project 20220701.mp4
02 Step 02 - Understanding problems with Stubs 20220701.mp4
02 Step 03 - Writing your first Mockito test with Mocks 20220701.mp4
02 Step 04 - Simplifying Tests with Mockito Annotations - @Mock, @InjectMocks 20220701.mp4
02 Step 05 - Exploring Mocks further by Mocking List interface 20220701.mp4
```