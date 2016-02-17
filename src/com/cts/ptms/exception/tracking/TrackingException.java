/**
 * 
 */
package com.cts.ptms.exception.tracking;

/**
 * @author 417765
 *
 */
public class TrackingException extends RuntimeException{

	/**
	 *  Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * default constructor
	 */
	public TrackingException() {
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public TrackingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public TrackingException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public TrackingException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public TrackingException(Throwable cause) {
		super(cause);
	}
	
}
