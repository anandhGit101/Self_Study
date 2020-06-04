/**
 * 
 */
package com.springboot.shopping.cart.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Getter;
import lombok.Setter;

/**
 * @author M1006601
 *
 */
@Getter
@Setter
@Entity
@DiscriminatorValue("Book")
@JsonAutoDetect
public class Book extends BaseProduct {
	
	@Column(name="Genre")
	public String genre;
	@Column(name="Author")
	public String author;
	@Column(name="Publications")
	public String publications;

	@Override
	public String toString() {
		return "Book [productId=" + super.getProductId()+ ", productName=" + super.getProductName() + ", productDescription="
				+ super.getProductDescription() + ", productAvailable=" + super.getProductAvailable() + ", productPrice=" + super.getProductPrice()
				+ ", genre=" + genre + ", author=" + author + ", publications=" + publications + "]";
	}
}
