/**
 * 
 */
package com.springboot.justbook.management.exception;

/**
 * @author M1006601
 *
 */
public class SeatsMgmtException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1757631305233786074L;


	public SeatsMgmtException() {
		super();
	}
	
	
	/**
	 * @param message
	 */
	public SeatsMgmtException(String message) {
		super(message);
	}

}
