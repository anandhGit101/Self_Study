/**
 * 
 */
package com.springboot.justbook.vo;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


/**
 * @author M1006601
 *
 */
public class UserDetailsRequestVO {

	@NotNull
	@Size(min = 6)
	private String userDetailsFName;
	
	private String userDetailsLName;
	
	@NotNull
	@Size(min = 6)
	@Pattern(regexp="^[A-Za-z0-9@.]*$")
	private String userDetailsUserName;
	
	@NotNull
	@Email
	@NotEmpty
	private String userDetailsEmail;
	
	@NotBlank
	@NotEmpty
	@NotNull
	@Size(min = 8)
	private String userDetailsPassword;
	
	@NotNull
	@Pattern(regexp="(^$|[0-9]{10})")
	private String userDetailsPhoneNo;
	
	@Size(max = 200)
	@NotEmpty
	private String userDetailsAddress;
	
	
	public UserDetailsRequestVO() {
		
	}
	/**
	 * @param userDetailsFName
	 * @param userDetailsLName
	 * @param userDetailsUserName
	 * @param userDetailsEmail
	 * @param userDetailsPassword
	 * @param userDetailsPhoneNo
	 * @param userDetailsAddress
	 */
	public UserDetailsRequestVO(String userDetailsFName, String userDetailsLName,
			String userDetailsUserName, String userDetailsEmail, String userDetailsPassword,
			String userDetailsPhoneNo, String userDetailsAddress) {
		super();
		this.userDetailsFName = userDetailsFName;
		this.userDetailsLName = userDetailsLName;
		this.userDetailsUserName = userDetailsUserName;
		this.userDetailsEmail = userDetailsEmail;
		this.userDetailsPassword = userDetailsPassword;
		this.userDetailsPhoneNo = userDetailsPhoneNo;
		this.userDetailsAddress = userDetailsAddress;
	}

	/**
	 * @return the userDetailsFName
	 */
	public String getUserDetailsFName() {
		return userDetailsFName;
	}

	/**
	 * @param userDetailsFName the userDetailsFName to set
	 */
	public void setUserDetailsFName(String userDetailsFName) {
		this.userDetailsFName = userDetailsFName;
	}

	/**
	 * @return the userDetailsLName
	 */
	public String getUserDetailsLName() {
		return userDetailsLName;
	}

	/**
	 * @param userDetailsLName the userDetailsLName to set
	 */
	public void setUserDetailsLName(String userDetailsLName) {
		this.userDetailsLName = userDetailsLName;
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
	 * @return the userDetailsPassword
	 */
	public String getUserDetailsPassword() {
		return userDetailsPassword;
	}

	/**
	 * @param userDetailsPassword the userDetailsPassword to set
	 */
	public void setUserDetailsPassword(String userDetailsPassword) {
		this.userDetailsPassword = userDetailsPassword;
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
	 * @return the userDetailsAddress
	 */
	public String getUserDetailsAddress() {
		return userDetailsAddress;
	}

	/**
	 * @param userDetailsAddress the userDetailsAddress to set
	 */
	public void setUserDetailsAddress(String userDetailsAddress) {
		this.userDetailsAddress = userDetailsAddress;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userDetailsEmail, userDetailsUserName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDetailsRequestVO other = (UserDetailsRequestVO) obj;
		return Objects.equals(userDetailsEmail, other.userDetailsEmail)
				&& Objects.equals(userDetailsUserName, other.userDetailsUserName);
	}
	
	
}
