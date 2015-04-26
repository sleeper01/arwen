/**
 * 
 */
package com.dm.common.exception;

/**
 * @author Administrator
 *
 */
public class MyRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyRuntimeException() {
		super();
	}

	public MyRuntimeException(String message) {
		super(message);
	}
}
