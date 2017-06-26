package net.noojn.rwe.engine;

import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.http.ResponseEntity;

public interface Part<Type> {

	public <ResultType>Part<ResultType> run(Function<Type, ResultType> f);

	public Part<Type> send(Consumer<Type> f);

	public <ResultType>ResultType map(Function<Type, ResultType> f);

	public <ResultType>ResponseEntity<ResultType> mapToEntity(Function<Type, ResultType> f);

	public ConsequencePart<Type> where(Function<Type, Boolean> f);
	
	public ConsequencePart<Type> whereNull();
	
	public Type content();
}
