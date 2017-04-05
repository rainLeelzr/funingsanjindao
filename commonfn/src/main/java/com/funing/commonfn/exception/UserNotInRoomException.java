package com.funing.commonfn.exception;

/**
 * 房间成员已满
 * @author Administrator
 *
 */
public class UserNotInRoomException extends RuntimeException {

	public UserNotInRoomException() {
		super();
	}

	public UserNotInRoomException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotInRoomException(String message) {
		super(message);
	}

	public UserNotInRoomException(Throwable cause) {
		super(cause);
	}


}
