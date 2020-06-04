/**
 * 
 */
package com.springboot.shopping.cart.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

/**
 * @author M1006601
 *
 */
@Entity
@Table(name="SHP_USER")
public class User {
	
	@Id
	@Column(name="SHP_USER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	@Column(name="SHP_NAME")
	@NotEmpty(message = "*Please provide Name") 
	private String name;
	
	@Column(name="SHP_USER_NAME")
	@NotEmpty(message = "*Please provide an valid UserName")
	private String userName;
	
	@Column(name="SHP_USER_EMAIL")
	@Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
	private String userEmail;
	
	@Column(name="SHP_PASSWORD")
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	private String password;
	
	@Column(name="SHP_ISADMIN")
	private boolean isAdmin;
	
	@OneToOne
	@JoinColumn(name="SHP_CART_ID")
	public ShoppingCart shpCart;

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the shpCart
	 */
	public ShoppingCart getShpCart() {
		return shpCart;
	}

	/**
	 * @param shpCart the shpCart to set
	 */
	public void setShpCart(ShoppingCart shpCart) {
		this.shpCart = shpCart;
	}

}
