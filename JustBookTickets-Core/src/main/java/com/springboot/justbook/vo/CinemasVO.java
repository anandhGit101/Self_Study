/**
 * 
 */
package com.springboot.justbook.vo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author M1006601
 *
 */
public class CinemasVO {
	
	private Long cinemasId;
	
	@NotNull
	@NotEmpty
	private String cinemasName;
	
	@NotNull
	@NotEmpty
	private String cinemasAddress;

	@NotNull
	@NotEmpty
	private String cinemasLocation;
	

	/**
	 * @return the cinemasId
	 */
	public Long getCinemasId() {
		return cinemasId;
	}

	/**
	 * @param cinemasId the cinemasId to set
	 */
	public void setCinemasId(Long cinemasId) {
		this.cinemasId = cinemasId;
	}

	/**
	 * @return the cinemasName
	 */
	public String getCinemasName() {
		return cinemasName;
	}

	/**
	 * @param cinemasName the cinemasName to set
	 */
	public void setCinemasName(String cinemasName) {
		this.cinemasName = cinemasName;
	}

	/**
	 * @return the cinemasAddress
	 */
	public String getCinemasAddress() {
		return cinemasAddress;
	}

	/**
	 * @param cinemasAddress the cinemasAddress to set
	 */
	public void setCinemasAddress(String cinemasAddress) {
		this.cinemasAddress = cinemasAddress;
	}

	/**
	 * @return the cinemaLocation
	 */
	public String getCinemasLocation() {
		return cinemasLocation;
	}

	/**
	 * @param cinemaLocation the cinemaLocation to set
	 */
	public void setCinemasLocation(String cinemasLocation) {
		this.cinemasLocation = cinemasLocation;
	}
}
