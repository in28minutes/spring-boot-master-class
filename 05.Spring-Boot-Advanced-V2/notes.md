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


package com.in28minutes.springboot.firstrestapi.survey;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class SurveyResourceIT {
    
    
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testRetrieveSurveyQuestion() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null,createBasicAuthHeader());

        ResponseEntity<String> response = restTemplate.exchange("/surveys/Survey1/questions/Question1",
                HttpMethod.GET, entity, String.class);

        String expected = "{id:Question1,description:\"Most Popular Cloud Platform Today\",correctAnswer:AWS}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }


    private MultiValueMap<String, String> createBasicAuthHeader() {
        HttpHeaders headers = new HttpHeaders();
        
        headers.add("Authorization", createHttpAuthenticationHeaderValue(
                "user1", "secret1"));
        headers.setAccept(List.of(MediaType.APPLICATION_JSON)); // replaced Arrays.asList with List.of

        return headers;
    }



    @Test
    public void testRetrieveAllSurveyQuestion() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null,createBasicAuthHeader());

        ResponseEntity<List<Question>> response = restTemplate.exchange("/surveys/Survey1/questions",
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<Question>>() {
                } );

        Question question1 = new Question("Question1", "Most Popular Cloud Platform Today",
                Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
        
        assertTrue(response.getBody().contains(question1));

    }
    
    
    @Test
    public void addQuestion() {

        Question question = new Question("Question10", "Most Popular Cloud Platform Today",
                Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");

        HttpEntity<Question> entity = new HttpEntity<Question>(question, createBasicAuthHeader());

        ResponseEntity<String> response = restTemplate.exchange("/surveys/Survey1/questions",
                HttpMethod.POST, entity, String.class);

        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        assertTrue(actual.contains("/surveys/Survey1/questions/"));

    }

}


package com.in28minutes.springboot.firstrestapi.survey;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(value = SurveyResource.class)
@WithMockUser
public class SurveyResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SurveyService surveyService;

    @Test
    public void retrieveDetailsForQuestion() throws Exception {

        Question mockQuestion = new Question("Question1", "Largest Country in the World",
                Arrays.asList("India", "Russia", "United States", "China"), "Russia");

        Mockito.when(surveyService.retrieveSpecificSurveyQuestion(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(mockQuestion);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/surveys/Survey1/questions/Question1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{id:Question1,description:\"Largest Country in the World\",correctAnswer:Russia}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void createSurveyQuestion() throws Exception {

        String questionJson = "{\"description\":\"Smallest Number\",\"correctAnswer\":\"1\",\"options\":[\"1\",\"2\",\"3\",\"4\"]}";

        Mockito.when(surveyService.addNewSurveyQuestion(Mockito.anyString(), Mockito.any(Question.class)))
                .thenReturn("DUMMY_ID");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/surveys/Survey1/questions").with(csrf())
                .accept(MediaType.APPLICATION_JSON).content(questionJson).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost/surveys/Survey1/questions/DUMMY_ID", response.getHeader(HttpHeaders.LOCATION));
    }

}


package com.in28minutes.springboot.firstrestapi;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {

        UserDetails userDetails1 = createNewUser("user1", "secret1", "USER");
        UserDetails userDetails2 = createNewUser("admin1", "secret1", "USER", "ADMIN");

        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    private UserDetails createNewUser(String username, String password, String... roles) {
        Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);

        UserDetails userDetails = User.builder().passwordEncoder(passwordEncoder).username(username).password(password)
                .roles(roles).build();
        return userDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.httpBasic(withDefaults());

        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated());

        http.csrf().disable();

        http.headers().frameOptions().disable();

        return http.build();
    }

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


