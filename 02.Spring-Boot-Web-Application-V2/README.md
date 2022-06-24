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
- Spring Initializr Lecture
- Lombok
- Functional Programming
- Docker
- JPA section
- Which Java Version to Use?
- Topical videos (HTML, JS, CSS, JSTL, Spring Tags)
- My favorite Eclipse shortcuts ctrl 1, ctrl space and ctrl shift o (ctrl c and ctrl v), ctrl shift r and ctrl shift t

### Step Wise Details
- Step 01: Basic Spring Boot Web Application Setup
- Step 02: First Spring MVC Controller, @ResponseBody, @Controller
- Step 03: Demystifying some of the Spring Boot magic
- Step 04: Redirect to Login JSP - LoginController, @ResponseBody and View Resolver
- Step 05: Show userid and password on the welcome page - ModelMap and @RequestParam
- Step 06: DispatcherServlet and Spring MVC Flow
- Step 07: Your First HTML form
- Step 08: Add hard-coded validation of userid and password
- Step 09: Magic of Spring
- Step 10: Create TodoController and list-todos view. Make TodoService a @Service and inject it.
- Step 11: Architecture of Web Applications
- Step 12: Session vs Model vs Request - @SessionAttributes
- Step 13: Add new todo
- Step 14: Display Todos in a table using JSTL Tags
- Step 15: Bootstrap for Page Formatting using webjars
- Step 16: Let's delete a Todo
- Step 17: Format Add Todo Page and Adding Basic HTML5 form validation
- Step 18: Introduce JSR 349 Validations using Hibernate Validator - First Command Bean.
- Step 19: Updating a todo
- Step 20: Let's add a Target Date for Todo - Use initBinder to Handle Date Fields
- Step 21: JSP Fragments and Navigation Bar
- Step 22: Preparing for Spring Security
- Step 23: Initial Spring Security Setup
- Step 24: Refactor and add Logout Functionality using Spring Security
- Step 25: Exception Handling

##### After Completing "Introduction to JPA"

- Step 26: Adding Dependencies for JPA and H2
- Step 27: Configuring H2 Console
- Step 28: Create Todo Entity and JPA Repository
- Step 29: Insert Todo using JPA Repository
- Step 30: Update, Delete and Retrieve Todos using JPA Repository
- Step 31: Data initialization with data.sql
- Step 32: Connecting JPA to other databases
- Step 33: Upgrading to Spring Boot 2 and Spring 5

## Installing and Setting Up MySQL

- Install MySQL https://dev.mysql.com/doc/en/installing.html
  - More details : http://www.mysqltutorial.org/install-mysql/
  - Trouble Shooting - https://dev.mysql.com/doc/refman/en/problems.html
- Startup the Server (as a service)
- Go to command prompt (or terminal)
   - Execute following commands to create a database and a user

```
mysql --user=user_name --password db_name
create database todo_example;
create user 'todouser'@'localhost' identified by 'YOUR_PASSWORD';
grant all on todo_example.* to 'todouser'@'localhost';
```

- Execute following sql queries to create the table and insert the data

Table
```sql
create table todo 
(id integer not null, 
desc varchar(255), 
is_done boolean not null, 
target_date timestamp, 
user varchar(255), 
primary key (id));
```

Data
```sql
INSERT INTO TODO
VALUES(10001, 'Learn Spring Boot', false, sysdate(),'in28Minutes');

INSERT INTO TODO
VALUES(10002, 'Learn RESTful Web Services', false, sysdate(),'in28Minutes');

INSERT INTO TODO
VALUES(10003, 'Learn SOAP Web Services', false, sysdate(),'in28Minutes');
```

## Code changes to connect to MySQL
- Add dependency to pom.xml (and remove H2 dependency)
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```
- Configure application.properties

```properties
spring.jpa.hibernate.ddl-auto=none
spring.datasource.url=jdbc:mysql://localhost:3306/todo_example
spring.datasource.username=todouser
spring.datasource.password=YOUR_PASSWORD
```

- Restart and You are ready!
