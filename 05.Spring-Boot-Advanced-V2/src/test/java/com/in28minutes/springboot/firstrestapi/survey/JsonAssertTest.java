package com.in28minutes.springboot.firstrestapi.survey;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JsonAssertTest {

	@Test
	void testJsonAssert() throws JSONException {
		JSONAssert.assertEquals("{}", "{}", false);
		JSONAssert.assertEquals("{id:5}", "{ id : 5 }", false);
		JSONAssert.assertEquals("{id:6, name:Ranga}", "{ id : 6, attr:5, name: \"Ranga\" }", false);	
	}

}
