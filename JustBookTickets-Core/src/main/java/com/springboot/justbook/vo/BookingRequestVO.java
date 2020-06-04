/**
 * 
 */
package com.springboot.justbook.vo;

import javax.validation.constraints.NotNull;

/**
 * @author M1006601
 *
 */
public class BookingRequestVO {
	
	@NotNull
	private String showDate;
	
	@NotNull
	private String showTimings;

	@NotNull
	private String showMovieName;

	@NotNull
	private String showCinemasName;

	@NotNull
	private String seatsSelected;
	
	@NotNull
	private String userName;
	
	@NotNull
	private String userEmail;
	
	@NotNull
	private String userPhoneNumber;
	
	@NotNull
	private String seatCategory;
	
	
	/**
	 * @param showDate
	 * @param showTiming
	 * @param showMovieName
	 * @param showCinemasName
	 * @param seatsSelected
	 * @param userName
	 * @param userPhoneNumber
	 */
	public BookingRequestVO(String showDate, String showTiming, String showMovieName, String showCinemasName,
			String seatsSelected, String userName, String userEmail, String userPhoneNumber, String seatCategory) {
		super();
		this.showDate = showDate;
		this.showTimings = showTiming;
		this.showMovieName = showMovieName;
		this.showCinemasName = showCinemasName;
		this.seatsSelected = seatsSelected;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhoneNumber = userPhoneNumber;
		this.seatCategory = seatCategory;
	}
	
	public BookingRequestVO() {
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
	
	

}
