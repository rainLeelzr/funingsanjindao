package com.funing.commonfn.exception;

/**
 * 房间成员已满
 * @author Administrator
 *
 */
public class RedisGameDataException extends RuntimeException {

	public RedisGameDataException() {
		super();
	}

	public RedisGameDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public RedisGameDataException(String message) {
		super(message);
	}

	public RedisGameDataException(Throwable cause) {
		super(cause);
	}


}
