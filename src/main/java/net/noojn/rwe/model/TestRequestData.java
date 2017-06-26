package net.noojn.rwe.model;

public class TestRequestData {

	public final String value;
	
	public TestRequestData(String value) {
		this.value = value;
	}

	public static TestRequestData validate(String value) {
		if (!value.isEmpty()) {
			return new TestRequestData(value);
		}
		return null;
	}
}
