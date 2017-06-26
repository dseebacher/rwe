package net.noojn.rwe.service;

import org.springframework.stereotype.Component;

import net.noojn.rwe.model.TestData;
import net.noojn.rwe.model.TestRequestData;
import net.noojn.rwe.model.TestResult;

@Component
public class LogicComponent {

	public TestData getData(TestRequestData requestData) {
		if ("0".equals(requestData.value)) {
			return null;
		}
		return new TestData(requestData.value);
	}
	
	public TestResult compileResult(TestData data) {
		if ("-1".equals(data.value)) {
			return null;
		}
		return new TestResult(data.value);
	}
}
