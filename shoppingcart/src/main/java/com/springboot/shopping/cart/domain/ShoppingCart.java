/**
 * 
 */
package com.springboot.shopping.cart.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author M1006601
 *
 */
@Entity
@Table(name="SHP_CART")
@Getter
@Setter
public class ShoppingCart {
	
	@Id
	@Column(name="SHP_CART_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long cartId;
	@Column(name="SHP_CART_QTY")
	public int cartQuantity;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "cart")
	private List<BaseProduct> cart;

}
