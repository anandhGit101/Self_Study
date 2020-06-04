/**
 * 
 */
package com.springboot.justbook.searchservice.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author M1006601
 *
 */
@Entity
@Table(name="movie_schedule")
public class MovieSchedule {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="movie_schedule_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="movie_id", nullable=false)
	private Movies movie;
	
	@JoinColumn(name="cinemas_id", nullable=false)
	@ManyToOne
	private Cinemas cinemas;
	
	@Column(name="show_timings")
	@JsonFormat(pattern="HH:mm:ss")
	private LocalTime showTimings;
	
	@Column(name="show_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate showDate;
	
	@Column(name="tickets_available", columnDefinition="integer default 60")
	private Integer ticketsAvailable;

}
