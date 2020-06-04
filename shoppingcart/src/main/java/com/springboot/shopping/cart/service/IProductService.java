/**
 * 
 */
package com.springboot.shopping.cart.service;

import java.util.List;
import java.util.Optional;

import com.springboot.shopping.cart.domain.BaseProduct;

/**
 * @author M1006601
 *
 */
public interface IProductService {

	public List<BaseProduct> getAllProducts();
	
	public Optional<BaseProduct> getProductById(Long productId);

	public void addProduct(BaseProduct product);

	public void deleteProduct(Long productId);
	
}
