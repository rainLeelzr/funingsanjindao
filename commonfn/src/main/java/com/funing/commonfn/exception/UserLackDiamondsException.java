package com.funing.commonfn.exception;

/**
 * 用户不存在
 * @author Administrator
 *
 */
public class UserLackDiamondsException extends RuntimeException {

	public UserLackDiamondsException() {
		super();
	}

	public UserLackDiamondsException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserLackDiamondsException(String message) {
		super(message);
	}

	public UserLackDiamondsException(Throwable cause) {
		super(cause);
	}


}
