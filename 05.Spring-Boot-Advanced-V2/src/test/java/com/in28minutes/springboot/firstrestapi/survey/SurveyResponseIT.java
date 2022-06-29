package com.in28minutes.springboot.firstrestapi.survey;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SurveyResponseIT {

	// 
	/*
	 
  "id": "Question1",
  "description": "Most Popular Cloud Platform Today",
  "options": [
    "AWS",
    "Azure",
    "Google Cloud",
    "Oracle Cloud"
  ],
  "correctAnswer": "AWS"
} 
	 
	 */
	
	//{"id":"Question1","description":"Most Popular Cloud Platform Today","options":["AWS","Azure","Google Cloud","Oracle Cloud"],"correctAnswer":"AWS"}

	
	@Autowired
	TestRestTemplate template;
	
	@Test
	void retrieveSpecificSurveyQuestion_basicScenario() throws JSONException {
		String url = "/surveys/Survey1/questions/Question1";
		ResponseEntity<String> response = template.getForEntity(url, String.class);
		//String expectedResponse="{\"id\":\"Question1\",\"description\":\"Most Popular Cloud Platform Today\",\"options\":[\"AWS\",\"Azure\",\"Google Cloud\",\"Oracle Cloud\"],\"correctAnswer\":\"AWS\"}";
		String expectedResponse = "{id:Question1, correctAnswer:AWS, \"description\":\"Most Popular Cloud Platform Today\"}"; 
		
		JSONAssert.assertEquals(expectedResponse, response.getBody(), false);
	}
	
	
	
	@Test
	void addNewSurveyQuestion_basicScenario() {
		
		String url = "/surveys/Survey1/questions/";
		
		String requestBody = """
					{
					  "description": "Most Popular Cloud Platform Today New",
					  "options": [
					    "AWS",
					    "Azure",
					    "Google Cloud",
					    "Oracle Cloud"
					  ],
					  "correctAnswer": "AWS"
					}
				""";
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<String> httpEntity = new HttpEntity(requestBody, headers);
		
		ResponseEntity<String> responseEntity = template.exchange(url, HttpMethod.POST, httpEntity, String.class);
		System.out.println(responseEntity);

		//<201 CREATED Created,
		assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
		
		String locationHeader = responseEntity.getHeaders().get("Location").get(0);
		
		//Location:"http://localhost:55163/surveys/Survey1/questions/2080216181"
		assertTrue(locationHeader.contains("/surveys/Survey1/questions"));
		
		
	}
}
