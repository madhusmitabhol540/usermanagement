package com.honeywell.usermanagement.exception;

public class SessionNotExistException  extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SessionNotExistException(String message) {
        super(message);
    }

    public SessionNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
