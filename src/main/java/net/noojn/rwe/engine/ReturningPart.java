package net.noojn.rwe.engine;

import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.http.ResponseEntity;

public class ReturningPart<Type> implements Part<Type>{

	private final ReturnStatus status;
	private final Part<Type> returnPart;
	
	public ReturningPart(Part<Type> returnPart, ReturnStatus status) {
		this.returnPart = returnPart;
		this.status = status;
	}

	@Override
	public <ResultType> Part<ResultType> run(Function<Type, ResultType> f) {
		//TODO:check
		return new ReturningPart<>(null, status);
	}

	@Override
	public Part<Type> send(Consumer<Type> f) {
		return this;
	}

	@Override
	public <ResultType> ResultType map(Function<Type, ResultType> f) {
		//TODO; null might not help a lot here...
		return null;
	}

	@Override
	public <ResultType> ResponseEntity<ResultType> mapToEntity(Function<Type, ResultType> f) {
		if (returnPart == null) {
			return new ResponseEntity<>(status.errorCode);
		}
		return new ResponseEntity<>(f.apply(returnPart.content()), status.errorCode);
	}

	@Override
	public ConsequencePart<Type> where(Function<Type, Boolean> f) {
		return new ConsequencePart<>(this, false);
	}

	@Override
	public ConsequencePart<Type> whereNull() {
		return new ConsequencePart<>(this, false);
	}

	@Override
	public Type content() {
		return returnPart.content();
	}
	
}
