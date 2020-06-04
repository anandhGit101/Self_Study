/**
 * 
 */
package com.springboot.justbook.management.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author M1006601
 *
 */
@Entity
@Table(name="seat_schedule")
@EntityListeners(AuditingEntityListener.class)
public class SeatSchedule extends AbstractAuditingEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2318787091501387753L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="seatschedule_id")
	private Long seatScheduleId;
	
	@OneToOne
	private MovieSchedule schedule;
	
	@Column(name="seatsOccupied")
	private String seatsOccupied;
		
	/**
	 * @param id
	 * @param schedule
	 * @param seatsOccupied
	 */
	public SeatSchedule(Long id, MovieSchedule schedule, String seatsOccupied) {
		super();
		this.seatScheduleId = id;
		this.schedule = schedule;
		this.seatsOccupied = seatsOccupied;
	}

	public SeatSchedule() {
		super();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return seatScheduleId;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.seatScheduleId = id;
	}

	/**
	 * @return the scheduleId
	 */
	public MovieSchedule getSchedule() {
		return schedule;
	}

	/**
	 * @param scheduleId the scheduleId to set
	 */
	public void setSchedule(MovieSchedule schedule) {
		this.schedule = schedule;
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
