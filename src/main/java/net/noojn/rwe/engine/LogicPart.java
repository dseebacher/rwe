package net.noojn.rwe.engine;

import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class LogicPart<Type> implements Part<Type>{
	
	private final Type model;
	
	public LogicPart(Type model) {
		this.model = model;
	}

	@Override
	public <ResultType>Part<ResultType> run(Function<Type, ResultType> f) {
		return new LogicPart<ResultType>(f.apply(model));
	}
	
	@Override
	public Part<Type> send(Consumer<Type> f) {
		f.accept(model);
		return this;
	}
	
	@Override
	public <ResultType>ResultType map(Function<Type, ResultType> f) {
		return f.apply(model);
	}
	
	@Override
	public <ResultType> ResponseEntity<ResultType> mapToEntity(Function<Type, ResultType> f) {
		return new ResponseEntity<>(f.apply(model), HttpStatus.OK);
	}

	@Override
	public ConsequencePart<Type> where(Function<Type, Boolean> f) {
		return new ConsequencePart<>(this, f.apply(model));
	}

	@Override
	public ConsequencePart<Type> whereNull() {
		return new ConsequencePart<>(this, model == null);
	}

	@Override
	public Type content() {
		return model;
	}
}
