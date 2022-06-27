# Web Application with Spring Boot

Developing your first Spring Boot Web Application is fun.

### Navigation - HTML + Bootstrap

```
<nav class="navbar navbar-expand-md navbar-light bg-light mb-3 p-1">
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

### Docker

Launch MySQL using Docker
```
docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=todos-user --env MYSQL_PASSWORD=dummytodos --env MYSQL_DATABASE=todos --name mysql --publish 3306:3306 mysql:8-oracle
```
application.properties configuration

```
#spring.datasource.url=jdbc:h2:mem:testdb

spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/todos
spring.datasource.username=todos-user
spring.datasource.password=dummytodos
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

#todos-user@localhost:3306
```

mysqlsh commands
```
mysqlsh
\connect todos-user@localhost:3306
\sql
use todos
select * from todo;
\quit
```

Docker Commands
```
docker container ls
docker container stop ID
```

## Installation

https://dev.mysql.com/doc/mysql-shell/8.0/en/mysql-shell-install.html

## Spring Security

```
@Configuration
public class SecurityConfiguration {
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
    	
    	Function<String, String> encoder = input -> passwordEncoder().encode(input);
		
    	UserDetails user = User.builder().passwordEncoder(
    			encoder
    			)
            .username("in28minutes")
            .password("dummy")
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } 
}
```

### Todo

- Future: Lombok
- Future: My favorite Eclipse shortcuts ctrl 1, ctrl space and ctrl shift o (ctrl c and ctrl v), ctrl shift r and ctrl shift t, ctrl click to see the source! Practice, Practice and Practice
- Future: Which Java Version to Use?
- Future: Spring Boot Feature Lectures
    - Future: Understand Spring Boot Project Structure!
    - Future: Spring Annotations - Component, Controller, Repository, Service, Configuration, Bean
- Future: Topical Videos (HTML, JS, CSS, JSTL, Spring Tags)

### Step Wise Details
- Step 01 - Creating Spring Boot Web Application with Spring Initializr
- Step 02 - Quick overview of Spring Boot Project
- Step 03 - First Spring MVC Controller, @ResponseBody, @Controller
- Step 04 - Enhancing Spring MVC Controller to provide HTML response
- Step 05 - Redirect to a JSP using Spring Boot - Controller, @ResponseBody and View Resolver
- Step 06 - Exercise - Creating LoginController and login view
- Step 07 - Quick Overview - How does web work - Request and Response
- Step 08 - Capturing QueryParams using RequestParam and First Look at Model
- Step 09 - Quick Overview - Importance of Logging with Spring Boot
- Step 10 - Understanding DispatcherServlet, Model 1, Model 2 and Front Controller
- Step 11 - Creating a Login Form
- Step 12 - Displaying Login Credentials in a JSP using Model
- Step 13 - Add hard-coded validation of userid and password
- Step 14 - Getting started with Todo Features - Creating Todo and TodoService
- Step 15 - Creating first version of List Todos Page
- Step 16 - Understanding Session vs Model vs Request - @SessionAttributes
- Step 17 - Adding JSTL to Spring Boot Project and Showing Todos in a Table
- Step 18 - Adding Bootstrap CSS framework to Spring Boot Project using webjars
- Step 19 - Formatting JSP pages with Bootstrap CSS framework
- Step 20 - Lets Add a New Todo - Create a new View
- Step 21 - Enhancing TodoService to add the todo
- Step 22 - Adding Validations using Spring Boot Starter Validation
- Step 23 - Using Command Beans to implement New Todo Page Validations
- Step 24 - Implementing Delete Todo Feature - New View
- Step 25 - Implementing Update Todo - 1 - Show Update Todo Page
- Step 26 - Implementing Update Todo - 1 - Save changes to Todo
- Step 27 - Adding Target Date Field to Todo Page
- Step 28 - Adding a Navigation Bar and Implementing JSP Fragments
- Step 29 - Preparing for Spring Security
- Step 30 - Setting up Spring Security with Spring Boot Starter Security
- Step 31 - Configuring Spring Security with Custom User and Password Encoder
- Step 32 - Refactoring and Removing Hardcoding of User Id
- Step 33 - Setting up a New User for Todo Application
- Step 34 - Adding Spring Boot Starter Data JPA and Getting H2 database ready
- Step 35 - Configuring Spring Security to Get H2-console Working
- Step 36 - Making Todo an Entity and Population Todo Data into H2
- Step 37 - Creating TodoRepository and Connecting List Todos page from H2 database
- Step 38 - Connecting All Todo App Features to H2 Database
- Step 39 - OPTIONAL - Overview of Connecting Todo App to MySQL database
- Step 40 - OPTIONAL - Installing Docker
- Step 41 - OPTIONAL - Connecting Todo App to MySQL database


## Renaming files while recording
```
Step17 -> Step19
Step22 -> Step25
Step23 -> Step26
Step24 -> Step27
Step25 -> Step28
Step26 -> Step29
Step28 -> Step31
Step30 -> Step33
Step32 -> Step35
Step33 -> Step36
Step36 -> Step38
Step38 -> Step41
```

## Files to Upload 20220627
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