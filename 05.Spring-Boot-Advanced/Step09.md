## What You Will Learn during this Step:
- I hate the fact that I've to stop and start the server each time. Can somebody save me?
 - Yeah. Spring Boot Developer Tools
  - By default, any entry on the classpath that points to a folder  will be monitored for changes.
  - These will not trigger restart - /META-INF/maven, /META-INF/resources ,/resources ,/static ,/public or /templates 
  - Folders can be configured : spring.devtools.restart.exclude=static/**,public/** 
  - Additional Paths : spring.devtools.restart.additional-paths
  - LiveReload http://livereload.com/extensions/
   - Technology in progress!! So, expect a few problems!
- Programming Tip 
 - Become an expert at your IDE - https://www.youtube.com/watch?v=dN9GYsG1v_c

## Useful Snippets and References
First Snippet
```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>
```

## Exercises
- Make changes and see if they reflect immediately
