/**
 * 
 */
package com.springboot.justbook.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author M1006601
 *
 */
@NoArgsConstructor
public class BookingDetails {
	
	private Long id;
	private String bookingNumber;
	private String showDate;
	private String showTimings;
	private String showMovieName;
	private String showCinemasName;
	private String seatsSelected;
	private Integer seatsAvailable;
	private String seatCategory;
	private String userName;
	private String userEmail;
	private String userPhoneNumber;
	private BigDecimal totalCost;
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
	 * @return the showTimings
	 */
	public String getShowTimings() {
		return showTimings;
	}
	/**
	 * @param showTimings the showTimings to set
	 */
	public void setShowTimings(String showTimings) {
		this.showTimings = showTimings;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * @return the totalCost
	 */
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	/**
	 * @param id
	 * @param bookingNumber
	 * @param showDate
	 * @param showTimings
	 * @param showMovieName
	 * @param showCinemasName
	 * @param seatsSelected
	 * @param seatsAvailable
	 * @param seatCategory
	 * @param userName
	 * @param userEmail
	 * @param userPhoneNumber
	 * @param totalCost
	 */
	public BookingDetails(Long id, String bookingNumber, String showDate, String showTimings, String showMovieName,
			String showCinemasName, String seatsSelected, Integer seatsAvailable, String seatCategory, String userName,
			String userEmail, String userPhoneNumber, BigDecimal totalCost) {
		super();
		this.id = id;
		this.bookingNumber = bookingNumber;
		this.showDate = showDate;
		this.showTimings = showTimings;
		this.showMovieName = showMovieName;
		this.showCinemasName = showCinemasName;
		this.seatsSelected = seatsSelected;
		this.seatsAvailable = seatsAvailable;
		this.seatCategory = seatCategory;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhoneNumber = userPhoneNumber;
		this.totalCost = totalCost;
	}
}