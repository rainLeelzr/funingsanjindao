package com.funing.commonfn.exception;

/**
 * 房间成员已满
 * @author Administrator
 *
 */
public class ReadyErrorException extends RuntimeException {

	public ReadyErrorException() {
		super();
	}

	public ReadyErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReadyErrorException(String message) {
		super(message);
	}

	public ReadyErrorException(Throwable cause) {
		super(cause);
	}


}
