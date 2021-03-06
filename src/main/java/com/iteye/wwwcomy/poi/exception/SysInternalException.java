package com.iteye.wwwcomy.poi.exception;

/**
 * Indicates a system internal exception which should be transparent to the end
 * user.
 * 
 * @author wwwcomy@gmail.com
 *
 */
@SuppressWarnings("serial")
public class SysInternalException extends RuntimeException {

	public SysInternalException() {
	}

	public SysInternalException(String s) {
		super(s);
	}

	public SysInternalException(String string, Exception e) {
		super(string, e);
	}

}
