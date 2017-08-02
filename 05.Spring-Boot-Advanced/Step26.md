## What You Will Learn during this Step:
- Securing our services with Basic Authentication using Spring Security
- Executing Requests using Basic Authentication with Postman
 - default user name is user
 - default security password is printed in console

## Useful Snippets and References
First Snippet
```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
```

Second Snippet
```
Using default security password:
```

Third Snippet : Executing a GET to http://localhost:8080/surveys/Survey1/questions/
```
{
  "timestamp": 1483514297025,
  "status": 401,
  "error": "Unauthorized",
  "message": "Full authentication is required to access this resource",
  "path": "/surveys/Survey1/questions/"
}
```
