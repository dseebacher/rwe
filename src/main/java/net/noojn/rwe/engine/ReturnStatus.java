package net.noojn.rwe.engine;

import org.springframework.http.HttpStatus;

public enum ReturnStatus {
	MISSING(HttpStatus.NOT_FOUND), ERROR(HttpStatus.INTERNAL_SERVER_ERROR), INVALID(HttpStatus.BAD_REQUEST), DONE(HttpStatus.OK);

	public final HttpStatus errorCode;

	private ReturnStatus(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}
}
