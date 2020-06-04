/**
 * 
 */
package com.springboot.justbook.management.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author M1006601
 *
 */
@Entity
@Table(name="seats")
@EntityListeners(AuditingEntityListener.class)
public class Seats extends AbstractAuditingEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5515624276543160521L;

	@Id
	@Column(name="seat_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="seat_number")
	private String seatNumber;
	
	@Column(name="seat_type")
	private String seatType;
	
	@Column(name="ticket_price")
	private BigDecimal unitPrice;
	
	
	/**
	 * @param id
	 * @param seatNumber
	 * @param seatType
	 */
	public Seats(Long id, String seatNumber, String seatType, BigDecimal unitPrice) {
		super();
		this.id = id;
		this.seatNumber = seatNumber;
		this.seatType = seatType;
		this.unitPrice = unitPrice;
	}
	
	public Seats() {
		
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
	 * @return the seatNumber
	 */
	public String getSeatNumber() {
		return seatNumber;
	}

	/**
	 * @param seatNumber the seatNumber to set
	 */
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	/**
	 * @return the seatType
	 */
	public String getSeatType() {
		return seatType;
	}

	/**
	 * @param seatType the seatType to set
	 */
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	/**
	 * @return the unitPrice
	 */
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
}
