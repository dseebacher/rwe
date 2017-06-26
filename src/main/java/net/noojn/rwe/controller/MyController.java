package net.noojn.rwe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.noojn.rwe.engine.Engine;
import net.noojn.rwe.model.TestRequestData;
import net.noojn.rwe.model.TestResponse;
import net.noojn.rwe.service.LogicComponent;
import net.noojn.rwe.service.ResponseMapper;

@RestController
public class MyController {

	private final LogicComponent logic;
	private final ResponseMapper mapper;
	
	
	public MyController(LogicComponent logic, ResponseMapper mapper) {
		this.logic = logic;
		this.mapper = mapper;
	}


	@RequestMapping("/foo")
	public ResponseEntity<TestResponse> foo(@RequestParam("bar") String bar) {
		return Engine.with(TestRequestData.validate(bar))
				.whereNull().meansValidationFailed()
//				.where(data -> "100".equals(data.value)).shouldSkipRest()
				.run(logic::getData)
				.whereNull().meansMissing()
				.run(logic::compileResult)
				.whereNull().meansError()
				.mapToEntity((mapper::toResponse));
	}
}
