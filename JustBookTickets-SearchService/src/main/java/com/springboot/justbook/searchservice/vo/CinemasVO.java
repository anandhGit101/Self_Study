/**
 * 
 */
package com.springboot.justbook.searchservice.vo;

import org.elasticsearch.common.Nullable;

/**
 * @author M1006601
 *
 */
public class CinemasVO implements GenericVO{

	@Nullable
	private Long cinemasId;
	private String cinemasName;
	private String cinemasAddress;
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
	 * @return the cinemasLocation
	 */
	public String getCinemasLocation() {
		return cinemasLocation;
	}
	/**
	 * @param cinemasLocation the cinemasLocation to set
	 */
	public void setCinemasLocation(String cinemasLocation) {
		this.cinemasLocation = cinemasLocation;
	}

	@Override
	public String toString() {
		return "CinemasVO [cinemasId=" + cinemasId + ", cinemasName=" + cinemasName + ", cinemasAddress="
				+ cinemasAddress + ", cinemasLocation=" + cinemasLocation + "]";
	}

	
}
