/**
 * 
 */
package com.springboot.justbook.vo;

/**
 * @author M1006601
 *
 */
public class MovieRequestVO {
	

	public MovieRequestVO() {
	}
	
	/**
	 * @param movieName
	 */
	public MovieRequestVO(String movieName) {
		super();
		this.movieName = movieName;
	}

	private String movieName;

	/**
	 * @return the movieName
	 */
	public String getMovieName() {
		return movieName;
	}

	/**
	 * @param movieName the movieName to set
	 */
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
}
