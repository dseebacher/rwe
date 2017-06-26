package net.noojn.rwe;

import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class RweTest {

	@Autowired
	TestRestTemplate rest;

	@Test
	public void testPositive() throws JSONException {
		String expectedJson = "{ \"value\": \"1\" }";

		ResponseEntity<String> responseEntity = sendRequestAndCheckforCode("/foo?bar=1", 200);
		checkContent(responseEntity, expectedJson);
	}

	@Test
	public void emptyParamShouldReturn400() {
		sendRequestAndCheckforCode("/foo?bar", 400);
	}

	@Test
	public void ZeroValueShouldReturn404() {
		sendRequestAndCheckforCode("/foo?bar=0", 404);
	}

//	@Test
//	public void HundredValueShouldReturn200Early() throws JSONException {
//		String expectedJson = "{ \"value\": \"100\" }";
//
//		ResponseEntity<String> responseEntity = sendRequestAndCheckforCode("/foo?bar=100", 200);
//		checkContent(responseEntity, expectedJson);
//	}

	@Test
	public void MinusOneValueShouldReturn500() {
		sendRequestAndCheckforCode("/foo?bar=-1", 500);
	}

	private ResponseEntity<String> sendRequestAndCheckforCode(String url, int code) {
		ResponseEntity<String> entity = rest.getForEntity(url, String.class);
		Assertions.assertThat(entity.getStatusCodeValue()).isEqualTo(code);
		return entity;
	}

	private void checkContent(ResponseEntity<String> responseEntity, String expectedJson) throws JSONException {
		JSONAssert.assertEquals(expectedJson, responseEntity.getBody(), false);
	}
}
