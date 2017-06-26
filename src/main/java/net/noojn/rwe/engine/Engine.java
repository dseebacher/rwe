package net.noojn.rwe.engine;

public class Engine {

	public static <Type> LogicPart<Type> with(Type model) {
		return new LogicPart<>(model);
	}
}
