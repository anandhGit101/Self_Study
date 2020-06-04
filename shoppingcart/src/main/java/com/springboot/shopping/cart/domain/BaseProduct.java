/**
 * 
 */
package com.springboot.shopping.cart.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * @author M1006601
 *
 */
@Entity
@Table(name="PRODUCT")
@JsonAutoDetect
public class BaseProduct {
	
	@Id
	@Column(name="PRODUCT_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long productId;
	
	@Column(name="PRODUCT_NAME")
	private String productName;
	
	@Column(name="PRODUCT_DESCRIPTION")
	private String productDescription;
	
	@Column(name="PRODUCT_QTY")
	@Min(value = 0, message = "*Quantity cannot be a negative number")
	private int productAvailable;
	
	@Column(name="PRODUCT_PRICE")
	@DecimalMin(value = "0.00", message = "Price cannot be in negative")
	private BigDecimal productPrice;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartId", nullable = true)
    private ShoppingCart cart;

	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productDescription
	 */
	public String getProductDescription() {
		return productDescription;
	}

	/**
	 * @param productDescription the productDescription to set
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	/**
	 * @return the productAvailable
	 */
	public int getProductAvailable() {
		return productAvailable;
	}

	/**
	 * @param productAvailable the productAvailable to set
	 */
	public void setProductAvailable(int productAvailable) {
		this.productAvailable = productAvailable;
	}

	/**
	 * @return the productPrice
	 */
	public BigDecimal getProductPrice() {
		return productPrice;
	}

	/**
	 * @param productPrice the productPrice to set
	 */
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	/**
	 * @return the cart
	 */
	public ShoppingCart getCart() {
		return cart;
	}

	/**
	 * @param cart the cart to set
	 */
	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseProduct product = (BaseProduct) o;

        return productId.equals(product.productId);
    }

    @Override
    public int hashCode() {
        return productId.hashCode();
    }

	@Override
	public String toString() {
		return "productId=" + productId + ", productName=" + productName + ", productDescription="
				+ productDescription + ", productAvailable=" + productAvailable + ", productPrice=" + productPrice
				+ "]";
	}
	
}
