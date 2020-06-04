/**
 * 
 */
package com.springboot.shopping.cart.service;

import java.math.BigDecimal;
import java.util.Map;

import com.springboot.shopping.cart.domain.BaseProduct;

/**
 * @author M1006601
 *
 */
public interface IShoppingCartService {
	
	Map<BaseProduct, Integer> addProduct(BaseProduct product);

	Map<BaseProduct, Integer> removeProduct(BaseProduct product);

    Map<BaseProduct, Integer> getProductsInCart();

	BigDecimal getTotalPrice();

	void checkout();

}
