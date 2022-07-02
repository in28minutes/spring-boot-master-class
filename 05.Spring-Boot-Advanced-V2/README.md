# REST with Spring Boot

Developing your first Spring Boot REST API is fun.

### Survey Questionnaire Data

```
Question question1 = new Question("Question1",
        "Most Popular Cloud Platform Today", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
Question question2 = new Question("Question2",
        "Fastest Growing Cloud Platform", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "Google Cloud");
Question question3 = new Question("Question3",
        "Most Popular DevOps Tool", Arrays.asList(
                "Kubernetes", "Docker", "Terraform", "Azure DevOps"), "Kubernetes");

List<Question> questions = new ArrayList<>(Arrays.asList(question1,
        question2, question3));

Survey survey = new Survey("Survey1", "My Favorite Survey",
        "Description of the Survey", questions);

surveys.add(survey);

```

### URLs

#### Basic Authorization Header

- Authorization - Basic YWRtaW46cGFzc3dvcmQ=

#### GET

- http://localhost:8080/surveys
- http://localhost:8080/surveys/Survey1
- http://localhost:8080/surveys/Survey1/questions
- http://localhost:8080/surveys/Survey1/questions/Question1
- http://localhost:8080/userDetailses?size=1

##### Response

```
[
    {
        "id": "Survey1",
        "title": "My Favorite Survey",
        "description": "Description of the Survey",
        "questions": [
            {
                "id": "Question1",
                "description": "Most Popular Cloud Platform Today",
                "options": [
                    "AWS",
                    "Azure",
                    "Google Cloud",
                    "Oracle Cloud"
                ],
                "correctAnswer": "AWS"
            },
            {
                "id": "Question2",
                "description": "Fastest Growing Cloud Platform",
                "options": [
                    "AWS",
                    "Azure",
                    "Google Cloud",
                    "Oracle Cloud"
                ],
                "correctAnswer": "Google Cloud"
            },
            {
                "id": "Question3",
                "description": "Most Popular DevOps Tool",
                "options": [
                    "Kubernetes",
                    "Docker",
                    "Terraform",
                    "Azure DevOps"
                ],
                "correctAnswer": "Kubernetes"
            }
        ]
    }
]

```

#### DELETE 

- http://localhost:8080/surveys/Survey1/questions/Question1

##### POST

**URL**: http://localhost:8080/surveys/Survey1/questions/
**Header**: Content-Type:application/json

**Request Body**
```
{
    "description": "Your Favorite Cloud Platform",
    "options": [
        "AWS",
        "Azure",
        "Google Cloud",
        "Oracle Cloud"
    ],
    "correctAnswer": "Google Cloud"
}

```

**URL**: http://localhost:8080/userDetailses
**Header**: Content-Type:application/json
**Request Body**
```
{
"name": "Sathish",
"role": "Admin"
}
```


##### PUT

**URL**: http://localhost:8080/surveys/Survey1/questions/Question1
**Header**: Content-Type:application/json
**Request Body**
```
{
    "id": "Question1",
    "description": "Most Popular Cloud Platform Today Change",
    "options": [
        "AWS",
        "Azure",
        "Google Cloud",
        "Oracle Cloud"
    ],
    "correctAnswer": "Google Cloud"
}

```



## Step By Step Details

- Step 01 - Quick Introduction to REST - Understand Resource and Actions
- Step 02 - Creating Spring Boot Project for REST with Maven and Eclipse
- Step 03 - Creating your first Spring Boot Resource - Hello World
- Step 04 - Creating a Second Spring Boot Resource Method - Hello World Bean
- Step 05 - Exploring Path Params and Path Variables with Spring Boot
- Step 06 - Getting Ready for Survey Questionnaire REST API
- Step 07 - Creating First Survey Spring Boot REST API - GET all surveys
- Step 08 - Creating Second Survey Spring Boot REST API Method - GET a survey
- Step 09 - Exploring REST API Best Practices - Request Methods and Response Status
- Step 10 - Exercise - Creating Survey Question related Spring Boot REST API Methods
- Step 11 - Creating Spring Boot REST API to create Survey Question - POST
- Step 12 - Improving POST Method - Status CREATED and Location Header
- Step 13 - Implementing Spring Boot REST API Method to DELETE a Question
- Step 14 - Implementing Spring Boot REST Method to Update a Question - PUT
- Step 15 - Setting up Spring Boot Data JPA with H2 Database and User Entity
- Step 16 - Exploring Spring Boot Data JPA using Command Line Runner
- Step 17 - Creating User REST API with Spring Boot Starter Rest
- Step 18 - Writing Your First Spring Boot Integration Test
- Step 19 - Writing Asserts for JSON in Spring Boot Tests - JsonAssert
- Step 20 - Improving JUnit Asserts for Spring Boot Integration Test
- Step 21 - Writing Spring Boot Integration Test for GET method returning List
- Step 22 - Writing Spring Boot Integration Test for POST method creating a Question
- Step 23 - Understanding JUnit Best Practice - Have ZERO Side Effects
- Step 24 - Writing Your First Spring Boot Mock MVC Unit Test
- Step 25 - Improving Asserts for Spring Boot Mock MVC Unit Test
- Step 26 - Writing Spring Boot Mock MVC Unit Test for POST Method
- Step 27 - Getting Started with Spring Boot Starter Security
- Step 28 - Configuring Spring Security for Spring Boot REST API
- Step 29 - Fixing Spring Boot Unit and Integration Tests
