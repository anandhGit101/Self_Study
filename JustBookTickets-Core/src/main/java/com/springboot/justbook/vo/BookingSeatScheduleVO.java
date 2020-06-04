/**
 * 
 */
package com.springboot.justbook.vo;

/**
 * @author M1006601
 *
 */
public class BookingSeatScheduleVO {
	
	private String scheduleId;
	
	private String seatsOccupied;

	public BookingSeatScheduleVO() {
	}
	
	/**
	 * @param seatScheduleId
	 * @param seatsOccupied
	 */
	public BookingSeatScheduleVO(String seatScheduleId, String seatsOccupied) {
		super();
		this.scheduleId = seatScheduleId;
		this.seatsOccupied = seatsOccupied;
	}

	/**
	 * @return the seatScheduleId
	 */
	public String getScheduleId() {
		return scheduleId;
	}

	/**
	 * @param seatScheduleId the seatScheduleId to set
	 */
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	/**
	 * @return the seatsOccupied
	 */
	public String getSeatsOccupied() {
		return seatsOccupied;
	}

	/**
	 * @param seatsOccupied the seatsOccupied to set
	 */
	public void setSeatsOccupied(String seatsOccupied) {
		this.seatsOccupied = seatsOccupied;
	}
	
	

}
