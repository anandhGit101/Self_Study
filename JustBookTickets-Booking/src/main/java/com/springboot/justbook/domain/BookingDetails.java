/**
 * 
 */
package com.springboot.justbook.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author M1006601
 *
 */
@Entity
@Table(name="booking_details")
@EntityListeners(AuditingEntityListener.class)
public class BookingDetails extends AbstractAuditingEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -483347954164215584L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name="booking_number")
	private String bookingNumber;
	
	@NotNull
	@Column(name="booked_date")
	private String showDate;
	
	@NotNull
	@Column(name="booked_time")
	private String showTimings;
	
	@NotNull
	@Column(name="booked_movie_title")
	private String showMovieName;
	
	@NotNull
	@Column(name="booked_theatre_Name")
	private String showCinemasName;
	
	@NotNull
	@Column(name="booked_seats")
	private String seatsSelected;
	
	@NotNull
	@Column(name="qty_seats_available", columnDefinition="integer default 60")
	private Integer seatsAvailable;
	
	@NotNull
	@Column(name="seat_category")
	private String seatCategory;
	
	@NotNull
	@Column(name="booked_user")
	private String userName;
	
	@NotNull
	@Column(name="booked_user_email")
	private String userEmail;
	
	@NotNull
	@Column(name="booked_user_contactno")
	private String userPhoneNumber;
	
	@Column(name="total_cost")
	private BigDecimal totalCost;

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
	public BookingDetails(Long id, @NotNull String bookingNumber, @NotNull String showDate, @NotNull String showTimings,
			@NotNull String showMovieName, @NotNull String showCinemasName, @NotNull String seatsSelected,
			@NotNull Integer seatsAvailable, @NotNull String seatCategory, @NotNull String userName,
			@NotNull String userEmail, @NotNull String userPhoneNumber, BigDecimal totalCost) {
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

	public BookingDetails() {
		super();
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
}