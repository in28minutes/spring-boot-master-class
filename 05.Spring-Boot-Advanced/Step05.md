## What You Will Learn during this Step:
- Spring Boot vs Spring
- What Spring Boot is Not!

## Spring Boot vs Spring

### Spring
- Spring is just a dependency injection framework. Spring focuses on the "plumbing" of enterprise applications so that teams can focus on application-level business logic, without unnecessary ties to specific deployment environments.
- First half of the 2000 decade! EJBs
- EJBs were NOT easy to develop. 
 - Write a lot of xml and plumbing code to get EJBs running
 - Impossible to Unit Test
 - Alternative - Writing simple JDBC Code involved a lot of plumbing
- Spring framework started with aim of making Java EE development simpler. 
 - Goals
  - Make applications testable. i.e. easier to write unit tests
  - Reduce plumbing code of JDBC and JMS
  - Simple architecture. Minus EJB.
  - Integrates well with other popular frameworks.

### Applications with Spring Framework
- Over the next few years, a number of applications were developed with Spring Framework
 - Testable but
 - Lot of configuration (XML and Java)
  - Developing Spring Based application need configuration of a lot of beans!
  - Integration with other frameworks need configuration as well!
- In the last few years, focus is moving from monolith applications to microservices. We need to be able to start project quickly. Minimum or Zero start up time
 - Framework Setup
 - Deployment - Configurability
 - Logging, Transaction Management
 - Monitoring
 - Web Server Configuration
 
### Spring Boot
- Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can “just run”. 
- We take an opinionated view of the Spring platform and third-party libraries so you can get started with minimum fuss. 
- Example Problem Statements
 - You want to add Hibernate to your project. You dont worry about configuring a data source and a session factory. I will do if for you! 
- Goals
  - Provide quick start for projects with Spring.
  - Be opinionated but provide options.
  - Provide a range of non-functional features that are common to large classes of projects (e.g. embedded servers, security, metrics, health checks, externalized configuration).

#### What Spring Boot is NOT?
- It’s not an app or a web server
- Does not implement any specific framework - for example, JPA or JMS
- Does not generate code

