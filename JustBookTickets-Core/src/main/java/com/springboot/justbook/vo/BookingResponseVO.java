/**
 * 
 */
package com.springboot.justbook.vo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author M1006601
 *
 */
public class BookingResponseVO {

	@JsonIgnore
	private Long id;
	
	private String bookingNumber;
	
	private String showDate;
	
	private String showTimings;

	private String showMovieName;

	private String showCinemasName;

	private String seatsSelected;
	
	private String userName;
	
	private String userEmail;
	
	private String userPhoneNumber;
	
	private Integer seatsAvailable;
	
	private String seatCategory;
	
	private BigDecimal totalPrice;

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
	 * @return the bookingId
	 */
	public String getBookingNumber() {
		return bookingNumber;
	}

	/**
	 * @param bookingId the bookingId to set
	 */
	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	/**
	 * @return the showDate
	 */
	public String getShowDate() {
		return showDate;
	}

	/**
	 * @param showDate the showDate to set
	 */
	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}

	/**
	 * @return the showTiming
	 */
	public String getShowTimings() {
		return showTimings;
	}

	/**
	 * @param showTiming the showTiming to set
	 */
	public void setShowTimings(String showTiming) {
		this.showTimings = showTiming;
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

	/**
	 * @return the seatsSelected
	 */
	public String getSeatsSelected() {
		return seatsSelected;
	}

	/**
	 * @param seatsSelected the seatsSelected to set
	 */
	public void setSeatsSelected(String seatsSelected) {
		this.seatsSelected = seatsSelected;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userPhoneNumber
	 */
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	/**
	 * @param userPhoneNumber the userPhoneNumber to set
	 */
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	/**
	 * @return the seatsAvailable
	 */
	public Integer getSeatsAvailable() {
		return seatsAvailable;
	}

	/**
	 * @param seatsAvailable the seatsAvailable to set
	 */
	public void setSeatsAvailable(Integer seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	/**
	 * @return the seatCategory
	 */
	public String getSeatCategory() {
		return seatCategory;
	}

	/**
	 * @param seatCategory the seatCategory to set
	 */
	public void setSeatCategory(String seatCategory) {
		this.seatCategory = seatCategory;
	}

	/**
	 * @return the totalPrice
	 */
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	

}
