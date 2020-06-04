/**
 * 
 */
package com.springboot.justbook.vo;

/**
 * @author M1006601
 *
 */
public class CinemasRequestVO {
	
	private String cinemasName;

	/**
	 * @param cinemasName
	 */
	public CinemasRequestVO(String cinemasName) {
		super();
		this.cinemasName = cinemasName;
	}
	
	public CinemasRequestVO() {
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
	
	

}
