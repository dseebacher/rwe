package net.noojn.rwe.engine;

public class ConsequencePart<Type> {

	private final Part<Type> previous;
	private final boolean triggered;

	public ConsequencePart(Part<Type> previous, boolean triggered) {
		this.previous = previous;
		this.triggered = triggered;
	}

//	public Part<Type> shouldSkipRest() {
//		return evaluate(ReturnStatus.DONE);
//	}

	public Part<Type> meansError() {
		return evaluate(ReturnStatus.ERROR);
	}
	
	public Part<Type> meansMissing() {
		return evaluate(ReturnStatus.MISSING);
	}

	public Part<Type> meansValidationFailed() {
		return evaluate(ReturnStatus.INVALID);
	}
	
	private Part<Type> evaluate(final ReturnStatus status) {
		if (nothingToDo()) {
			return previous;
		}
		
//		if (ReturnStatus.DONE.equals(status)) {
//			return new ReturningPart<>(previous, status);
//		}
		
		// TODO: is it ok to continue with null?
		return new ReturningPart<>(null, status);
	}

	private boolean nothingToDo() {
		return !triggered || previous instanceof ReturningPart;
	}
}
