## Initial Notes to Get Started

```
private static List<Survey> surveys = new ArrayList<>();
	static {
	}

public class Question {
	private String id;
	private String description;
	private String correctAnswer;
	private List<String> options;

public class Survey {
	private String id;
	private String title;
	private String description;
	private List<Question> questions;

SurveyService
List<Survey> retrieveAllSurveys()
Survey retrieveSurvey(String surveyId)
List<Question> retrieveQuestions(String surveyId)
retrieveQuestion(String surveyId, String questionId)

private SecureRandom random = new SecureRandom();

	public Question addQuestion(String surveyId, Question question) {
		Survey survey = retrieveSurvey(surveyId);

		if (survey == null) {
			return null;
		}

		String randomId = new BigInteger(130, random).toString(32);
		question.setId(randomId);

		survey.getQuestions().add(question);

		return question;
	}
```

server.error.include-stacktrace=never

if(survey==null)
	throw new ResponseStatusException(
			  HttpStatus.NOT_FOUND, "entity not found"
			);


- Create a REST Service for Retrieving all questions for a survey
 - Autowire SurveyService
 - Create @GetMapping("/surveys/{surveyId}/questions")
 - How does the Bean get converted to a JSON?
  - Auto Configuration : If Jackson jar is on the class path, message converters are auto created! (Search in log :Creating shared instance of singleton bean 'mappingJackson2HttpMessageConverter')

    @GetMapping("/surveys/{surveyId}/questions")
    public List<Question> retrieveQuestions(@PathVariable String surveyId) {


- Adding the second method to rest service to retrieve a specific question
- This will be a very short step
- http://localhost:8080/surveys/Survey1/questions/Question1

- Create a REST Service to add a new question to survey
 - @PostMapping("/surveys/{surveyId}/questions")
 - @RequestBody Question question
 - What should be Response Status for create?
 - ResponseEntity.created(location).build()
 - ResponseEntity.noContent().build()
 - Using Postman : https://www.getpostman.com
 - URL to POST to - http://localhost:8080/surveys/Survey1/questions


    @PostMapping("/surveys/{surveyId}/questions")
    ResponseEntity<?> add(@PathVariable String surveyId,
            @RequestBody Question question) {

        Question createdTodo = surveyService.addQuestion(surveyId, question);

        if (createdTodo == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();

        return ResponseEntity.created(location).build();

    }


- Understand Content Negotiation
 - Accept:application/xml
- Deliver XML Responses from the REST Services
- http://localhost:8080/surveys/Survey1/questions/

#### Useful Snippets and References
First Snippet
```
        <dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-xml</artifactId>
        </dependency>

```


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;// Not perfect!! Should be a proper object!
    private String role;// Not perfect!! An enum should be a better choice!


@Component
public class UserCommandLineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory
            .getLogger(UserCommandLineRunner.class);

    @Autowired
    private UserRepository repository;

    @Override
    public void run(String... args) {
        // save a couple of customers
        repository.save(new User("Ranga", "Admin"));
        repository.save(new User("Ravi", "User"));
        repository.save(new User("Satish", "Admin"));
        repository.save(new User("Raghu", "User"));

        log.info("-------------------------------");
        log.info("Finding all users");
        log.info("-------------------------------");
        for (User user : repository.findAll()) {
            log.info(user.toString());
        }


public interface UserRepository extends CrudRepository<User, Long> {


First Snippet
```

        log.info("-------------------------------");
        log.info("Finding user with id 1");
        log.info("-------------------------------");
        User user = repository.findOne(1L);
        log.info(user.toString());

        log.info("-------------------------------");
        log.info("Finding all Admins");
        log.info("-------------------------------");
        for (User admin : repository.findByRole("Admin")) {
            log.info(admin.toString());
            // Do something...
        }

```
Second Snippet
```
package com.in28minutes.springboot.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByRole(String description);
}
```

- Introduction to Spring Data Rest
 - Hit http://localhost:8080/users in POSTMAN
 - http://localhost:8080/users/1
 - http://localhost:8080/users/?size=4
 - http://localhost:8080/users/?sort=name,desc
 - @Param("role")
 - http://localhost:8080/users/search/findByRole?role=Admin
- Good for quick prototype! Be cautious about using this in Big applications!

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRestRepository extends
PagingAndSortingRepository<User, Long> {
    List<User> findByRole(@Param("role") String role);
}

## Spring Boot Starter Web

- Starter for building applications with Spring MVC. 
- Tomcat is default embedded container. How to change to other embedded things?
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jetty</artifactId>
</dependency>
```

- Enable logging in DEBUG mode to understand further
- Spring Boot Starter Web brings all dependencies needed to build normal and RESTful web applications. Look at the dependency tree.
- Also look at /META-INF/spring.provides inside the spring-boot-starter-web.jar
- Spring Boot Starter Web auto configures things needed to startup a web application. Look at the log
```
Mapping servlet: 'dispatcherServlet' to [/]
Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
```
- Look at package org.springframework.boot.autoconfigure.web in spring-boot-autoconfigure-*.jar
- Go to url http://localhost:8080/some-non-existing-url


## Starter Parent

- Dependency Versions
- Java Versions
- Default Plugins


