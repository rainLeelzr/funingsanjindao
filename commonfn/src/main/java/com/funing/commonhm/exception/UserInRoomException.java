package com.funing.commonfn.exception;

/**
 * 房间成员已满
 * @author Administrator
 *
 */
public class UserInRoomException extends RuntimeException {

	public UserInRoomException() {
		super();
	}

	public UserInRoomException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserInRoomException(String message) {
		super(message);
	}

	public UserInRoomException(Throwable cause) {
		super(cause);
	}


}
