/**
 * 
 */
package com.springboot.justbook.vo;

/**
 * @author M1006601
 *
 */
public class SeatScheduleVO {

	private Long id;

	private Long scheduleId;
	
	private String seatsOccupied;

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
	 * @return the scheduleId
	 */
	public Long getScheduleId() {
		return scheduleId;
	}

	/**
	 * @param scheduleId the scheduleId to set
	 */
	public void setScheduleId(Long scheduleId) {
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
