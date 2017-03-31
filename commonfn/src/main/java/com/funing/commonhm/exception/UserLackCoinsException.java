package com.funing.commonfn.exception;

/**
 * 用户不存在
 * @author Administrator
 *
 */
public class UserLackCoinsException extends RuntimeException {

	public UserLackCoinsException() {
		super();
	}

	public UserLackCoinsException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserLackCoinsException(String message) {
		super(message);
	}

	public UserLackCoinsException(Throwable cause) {
		super(cause);
	}


}
