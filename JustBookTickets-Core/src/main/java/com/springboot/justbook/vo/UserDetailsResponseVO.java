/**
 * 
 */
package com.springboot.justbook.vo;

/**
 * @author M1006601
 *
 */
public class UserDetailsResponseVO {

	private String userDetailsUserName;

	private String userDetailsEmail;

	private String userDetailsPhoneNo;
	
	private String userDetailsPassword;
	
	private Long userDetailsId;
	
	public UserDetailsResponseVO() {
	}

	/**
	 * @param userDetailsUserName
	 * @param userDetailsEmail
	 * @param userDetailsPhoneNo
	 * @param userDetailsPassword
	 * @param userDetailsId
	 */
	public UserDetailsResponseVO(String userDetailsUserName, String userDetailsEmail, String userDetailsPhoneNo,
			String userDetailsPassword, Long userDetailsId) {
		super();
		this.userDetailsUserName = userDetailsUserName;
		this.userDetailsEmail = userDetailsEmail;
		this.userDetailsPhoneNo = userDetailsPhoneNo;
		this.userDetailsPassword = userDetailsPassword;
		this.userDetailsId = userDetailsId;
	}




	/**
	 * @return the userDetailsUserName
	 */
	public String getUserDetailsUserName() {
		return userDetailsUserName;
	}

	/**
	 * @param userDetailsUserName the userDetailsUserName to set
	 */
	public void setUserDetailsUserName(String userDetailsUserName) {
		this.userDetailsUserName = userDetailsUserName;
	}

	/**
	 * @return the userDetailsEmail
	 */
	public String getUserDetailsEmail() {
		return userDetailsEmail;
	}

	/**
	 * @param userDetailsEmail the userDetailsEmail to set
	 */
	public void setUserDetailsEmail(String userDetailsEmail) {
		this.userDetailsEmail = userDetailsEmail;
	}

	
	/**
	 * @return the userDetailsPhoneNo
	 */
	public String getUserDetailsPhoneNo() {
		return userDetailsPhoneNo;
	}

	/**
	 * @param userDetailsPhoneNo the userDetailsPhoneNo to set
	 */
	public void setUserDetailsPhoneNo(String userDetailsPhoneNo) {
		this.userDetailsPhoneNo = userDetailsPhoneNo;
	}

	/**
	 * @return the userDetailPassword
	 */
	public String getUserDetailsPassword() {
		return userDetailsPassword;
	}

	/**
	 * @param userDetailPassword the userDetailPassword to set
	 */
	public void setUserDetailsPassword(String userDetailsPassword) {
		this.userDetailsPassword = userDetailsPassword;
	}

	/**
	 * @return the userDetailsId
	 */
	public Long getUserDetailsId() {
		return userDetailsId;
	}

	/**
	 * @param userDetailsId the userDetailsId to set
	 */
	public void setUserDetailsId(Long userDetailsId) {
		this.userDetailsId = userDetailsId;
	}
}
