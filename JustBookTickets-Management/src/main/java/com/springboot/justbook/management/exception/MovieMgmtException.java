/**
 * 
 */
package com.springboot.justbook.management.exception;

/**
 * @author M1006601
 *
 */
public class MovieMgmtException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1468448109608280054L;
	
	public MovieMgmtException() {
		super();
	}

	public MovieMgmtException(String errorMessage) {
		super(errorMessage);
	}
	
	

}
