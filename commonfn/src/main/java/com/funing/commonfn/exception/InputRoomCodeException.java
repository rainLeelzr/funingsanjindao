package com.funing.commonfn.exception;

/**
 * 是否输入房间号
 * @author Administrator
 *
 */
public class InputRoomCodeException extends RuntimeException {

	public InputRoomCodeException() {
		super();
	}

	public InputRoomCodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InputRoomCodeException(String message) {
		super(message);
	}

	public InputRoomCodeException(Throwable cause) {
		super(cause);
	}


}
