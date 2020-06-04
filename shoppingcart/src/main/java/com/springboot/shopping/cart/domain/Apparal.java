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
@DiscriminatorValue("Apparal")
@JsonAutoDetect
public class Apparal extends BaseProduct {
	
	@Column(name="APP_TYPE")
	public String apptype;
	@Column(name="APP_BRAND")
	public String brand;
	@Column(name="APP_DESIGN")
	public String design;

	@Override
	public String toString() {
		return "Apparal [productId=" + super.getProductId()+ ", productName=" + super.getProductName() + ", productDescription="
				+ super.getProductDescription() + ", productAvailable=" + super.getProductAvailable() + ", productPrice=" + super.getProductPrice()
				+", apptype=" + apptype + ", brand=" + brand + ", design=" + design + "]";
	}
}
