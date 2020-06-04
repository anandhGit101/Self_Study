/**
 * 
 */
package com.springboot.sample.notification.domain;

/**
 * @author M1006601
 *
 */
public class Booking {


	private Long id;

	private String bookingNumber;
	
	private String showMovieName;
	
	private String showCinemasName;
	
	
	/**
	 * @param id
	 * @param bookingNumber
	 * @param showMovieName
	 * @param showCinemasName
	 */
	public Booking(Long id, String bookingNumber, String showMovieName, String showCinemasName) {
		super();
		this.id = id;
		this.bookingNumber = bookingNumber;
		this.showMovieName = showMovieName;
		this.showCinemasName = showCinemasName;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the bookingNumber
	 */
	public String getBookingNumber() {
		return bookingNumber;
	}

	/**
	 * @param bookingNumber the bookingNumber to set
	 */
	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	/**
	 * @return the showMovieName
	 */
	public String getShowMovieName() {
		return showMovieName;
	}

	/**
	 * @param showMovieName the showMovieName to set
	 */
	public void setShowMovieName(String showMovieName) {
		this.showMovieName = showMovieName;
	}

	/**
	 * @return the showCinemasName
	 */
	public String getShowCinemasName() {
		return showCinemasName;
	}

	/**
	 * @param showCinemasName the showCinemasName to set
	 */
	public void setShowCinemasName(String showCinemasName) {
		this.showCinemasName = showCinemasName;
	}
	
}
