## What You Will Learn during this Step:
- First thing first : I hate the fact that we did not write tests until Step 20 of this course
 - I love TDD and use it in all my projects. However, when learning something new, I think it is important to focus on one thing at a time!
- You can learn more about unit testing here - https://www.youtube.com/playlist?list=PL83C941BB0D27A6AF
- Let's write a Integration Test for our service 

## Useful Snippets and References
First Snippet
```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

```
UnRefactored Snippet
```
package com.in28minutes.springboot.controller;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.springboot.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {

	@LocalServerPort
	private int port;

	@Test
	public void testRetrieveSurveyQuestion() {

		String url = "http://localhost:" + port
				+ "/surveys/Survey1/questions/Question1";

		TestRestTemplate restTemplate = new TestRestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(url,
				HttpMethod.GET, entity, String.class);

		String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
}


```
Refactored Snippet
```
package com.in28minutes.springboot.controller;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.springboot.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {

    @LocalServerPort
    private int port;

    private TestRestTemplate template = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Before
    public void setupJSONAcceptType() {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    @Test
    public void retrieveSurveyQuestion() throws Exception {

        String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia,options:[India,Russia,United States,China]}";

        ResponseEntity<String> response = template.exchange(
                createUrl("/surveys/Survey1/questions/Question1"),
                HttpMethod.GET, new HttpEntity<String>("DUMMY_DOESNT_MATTER",
                        headers), String.class);

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createUrl(String uri) {
        return "http://localhost:" + port + uri;
    }

}
```

## Exercises
- Try writing integration test for retrieveAllQuestions

## Files List

### /pom.xml
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.in28minutes</groupId>
    <artifactId>springboot-for-beginners-example</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>Your First Spring Boot Example</name>
    <packaging>jar</packaging>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.4.0.RELEASE</version>
    </parent>

    <dependencies>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-rest</artifactId>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-xml</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-rest-hal-browser</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

    </dependencies>


    <properties>
        <java.version>1.8</java.version>
    </properties>

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
### /src/main/java/com/in28minutes/springboot/Application.java
```
package com.in28minutes.springboot;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.springboot.configuration.BasicConfiguration;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

    @RestController
    class SomeBean {

        @Autowired
        private SomeDependency someDependency;

        @Autowired
        private BasicConfiguration configuration;

        @RequestMapping("/")
        public String index() {
            return someDependency.getSomething();
        }

        @RequestMapping("/dynamic-configuration")
        public Map dynamicConfiguration() {
            // Not the best practice to use a map to store differnt types!
            Map map = new HashMap();
            map.put("message", configuration.getMessage());
            map.put("number", configuration.getNumber());
            map.put("key", configuration.isValue());
            return map;
        }

    }

    @Component
    class SomeDependency {

        @Value("${welcome.message}")
        private String welcomeMessage;

        public String getSomething() {
            return welcomeMessage;
        }

    }

}
```
### /src/main/java/com/in28minutes/springboot/configuration/BasicConfiguration.java
```
package com.in28minutes.springboot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("basic")
public class BasicConfiguration {
    private boolean value;
    private String message;
    private int number;

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
```
### /src/main/java/com/in28minutes/springboot/controller/SurveyController.java
```
package com.in28minutes.springboot.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.springboot.model.Question;
import com.in28minutes.springboot.service.SurveyService;

@RestController
class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @GetMapping("/surveys/{surveyId}/questions")
    public List<Question> retrieveQuestions(@PathVariable String surveyId) {
        return surveyService.retrieveQuestions(surveyId);
    }

    @GetMapping(path = "/surveys/{surveyId}/questions/{questionId}")
    public Question retrieveQuestion(@PathVariable String surveyId,
            @PathVariable String questionId) {
        return surveyService.retrieveQuestion(surveyId, questionId);
    }

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

}
```
### /src/main/java/com/in28minutes/springboot/jpa/User.java
```
package com.in28minutes.springboot.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;// Not perfect!! Should be a proper object!
    private String role;// Not perfect!! An enum should be a better choice!

    protected User() {
    }

    public User(String name, String role) {
        super();
        this.name = name;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return String.format("User [id=%s, name=%s, role=%s]", id, name, role);
    }

}
```
### /src/main/java/com/in28minutes/springboot/jpa/UserCommandLineRunner.java
```
package com.in28minutes.springboot.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
    }

}
```
### /src/main/java/com/in28minutes/springboot/jpa/UserRepository.java
```
package com.in28minutes.springboot.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByRole(String description);
}
```
### /src/main/java/com/in28minutes/springboot/jpa/UserRestRepository.java
```
package com.in28minutes.springboot.jpa;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRestRepository extends
PagingAndSortingRepository<User, Long> {
    List<User> findByRole(@Param("role") String role);
}
```
### /src/main/java/com/in28minutes/springboot/model/Question.java
```
package com.in28minutes.springboot.model;

import java.util.List;

public class Question {
    private String id;
    private String description;
    private String correctAnswer;
    private List<String> options;

    // Needed by Caused by: com.fasterxml.jackson.databind.JsonMappingException:
    // Can not construct instance of com.in28minutes.springboot.model.Question:
    // no suitable constructor found, can not deserialize from Object value
    // (missing default constructor or creator, or perhaps need to add/enable
    // type information?)
    public Question() {

    }

    public Question(String id, String description, String correctAnswer,
            List<String> options) {
        super();
        this.id = id;
        this.description = description;
        this.correctAnswer = correctAnswer;
        this.options = options;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getOptions() {
        return options;
    }

    @Override
    public String toString() {
        return String
                .format("Question [id=%s, description=%s, correctAnswer=%s, options=%s]",
                        id, description, correctAnswer, options);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id == null ? 0 : id.hashCode());
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
        Question other = (Question) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
```
### /src/main/java/com/in28minutes/springboot/model/Survey.java
```
package com.in28minutes.springboot.model;

import java.util.List;

public class Survey {
    private String id;
    private String title;
    private String description;
    private List<Question> questions;

    public Survey(String id, String title, String description,
            List<Question> questions) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        return String.format(
                "Survey [id=%s, title=%s, description=%s, questions=%s]", id,
                title, description, questions);
    }

}
```
### /src/main/java/com/in28minutes/springboot/service/SurveyService.java
```
package com.in28minutes.springboot.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.in28minutes.springboot.model.Question;
import com.in28minutes.springboot.model.Survey;

@Component
public class SurveyService {
    private static List<Survey> surveys = new ArrayList<>();
    static {
        Question question1 = new Question("Question1",
                "Largest Country in the World", "Russia", Arrays.asList(
                        "India", "Russia", "United States", "China"));
        Question question2 = new Question("Question2",
                "Most Populus Country in the World", "China", Arrays.asList(
                        "India", "Russia", "United States", "China"));
        Question question3 = new Question("Question3",
                "Highest GDP in the World", "United States", Arrays.asList(
                        "India", "Russia", "United States", "China"));
        Question question4 = new Question("Question4",
                "Second largest english speaking country", "India",
                Arrays.asList("India", "Russia", "United States", "China"));

        List<Question> questions = new ArrayList<>(Arrays.asList(question1,
                question2, question3, question4));

        Survey survey = new Survey("Survey1", "My Favorite Survey",
                "Description of the Survey", questions);

        surveys.add(survey);
    }

    public List<Survey> retrieveAllSurveys() {
        return surveys;
    }

    public Survey retrieveSurvey(String surveyId) {
        for (Survey survey : surveys) {
            if (survey.getId().equals(surveyId)) {
                return survey;
            }
        }
        return null;
    }

    public List<Question> retrieveQuestions(String surveyId) {
        Survey survey = retrieveSurvey(surveyId);

        if (survey == null) {
            return null;
        }

        return survey.getQuestions();
    }

    public Question retrieveQuestion(String surveyId, String questionId) {
        Survey survey = retrieveSurvey(surveyId);

        if (survey == null) {
            return null;
        }

        for (Question question : survey.getQuestions()) {
            if (question.getId().equals(questionId)) {
                return question;
            }
        }

        return null;
    }

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
}
```
### /src/main/resources/application.properties
```
logging.level.org.springframework: INFO
app.name: In28Minutes
app.description: ${app.name} is your first Spring Boot application Properties
welcome.message: Welcome to your first Spring Boot application
basic.value: true
basic.message: Dynamic Message
basic.number: 123
```
### /src/main/resources/application.yaml
```
logging:
  level:
      org.springframework: DEBUG

app:
   name: In28Minutes
   description: ${app.name} is your first Spring Boot application

welcome:
    message: Welcome to your first Spring Boot app!
    
basic: 
   value: true
   message: Dynamic Message YAML
   number: 100
```
### /src/test/java/com/in28minutes/springboot/controller/SurveyControllerIT.java
```
package com.in28minutes.springboot.controller;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.springboot.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {

    @LocalServerPort
    private int port;

    private TestRestTemplate template = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Before
    public void setupJSONAcceptType() {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    @Test
    public void retrieveSurveyQuestion() throws Exception {

        String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia,options:[India,Russia,United States,China]}";

        ResponseEntity<String> response = template.exchange(
                createUrl("/surveys/Survey1/questions/Question1"),
                HttpMethod.GET, new HttpEntity<String>("DUMMY_DOESNT_MATTER",
                        headers), String.class);

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createUrl(String uri) {
        return "http://localhost:" + port + uri;
    }

}
```
