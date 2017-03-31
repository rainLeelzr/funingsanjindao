package com.funing.commonfn.exception;

/**
 * 房间成员已满
 * @author Administrator
 *
 */
public class UnReadyException extends RuntimeException {

	public UnReadyException() {
		super();
	}

	public UnReadyException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnReadyException(String message) {
		super(message);
	}

	public UnReadyException(Throwable cause) {
		super(cause);
	}


}
