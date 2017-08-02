## What You Will Learn during this Step:
- Understand Content Negotiation
 - Accept:application/xml
- Deliver XML Responses from the REST Services
- http://localhost:8080/surveys/Survey1/questions/

## Useful Snippets and References
First Snippet
```
        <dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-xml</artifactId>
        </dependency>

```
Second Snippet
```
<List>
    <item>
        <id>Question1</id>
        <description>Largest Country in the World</description>
        <correctAnswer>Russia</correctAnswer>
        <options>
            <options>India</options>
            <options>Russia</options>
            <options>United States</options>
            <options>China</options>
        </options>
    </item>
    <item>
        <id>Question2</id>
        <description>Most Populus Country in the World</description>
        <correctAnswer>China</correctAnswer>
        <options>
            <options>India</options>
            <options>Russia</options>
            <options>United States</options>
            <options>China</options>
        </options>
    </item>
    <item>
        <id>Question3</id>
        <description>Highest GDP in the World</description>
        <correctAnswer>United States</correctAnswer>
        <options>
            <options>India</options>
            <options>Russia</options>
            <options>United States</options>
            <options>China</options>
        </options>
    </item>
    <item>
        <id>Question4</id>
        <description>Second largest english speaking country</description>
        <correctAnswer>India</correctAnswer>
        <options>
            <options>India</options>
            <options>Russia</options>
            <options>United States</options>
            <options>China</options>
        </options>
    </item>
</List>
```



## Exercises
- Execute other services using xml and see what happens!
