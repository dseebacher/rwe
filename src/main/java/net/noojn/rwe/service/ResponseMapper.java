package net.noojn.rwe.service;

import org.springframework.stereotype.Component;

import net.noojn.rwe.model.TestResponse;
import net.noojn.rwe.model.TestResult;

@Component
public class ResponseMapper {

	public TestResponse toResponse(TestResult result) {
		return new TestResponse(result.data);
	}
}
