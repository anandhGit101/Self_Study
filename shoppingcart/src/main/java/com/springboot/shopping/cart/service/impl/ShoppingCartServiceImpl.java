/**
 * 
 */
package com.springboot.shopping.cart.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.shopping.cart.domain.BaseProduct;
import com.springboot.shopping.cart.repository.ProductRepository;
import com.springboot.shopping.cart.service.IShoppingCartService;

/**
 * @author M1006601
 *
 */
@Service
@Transactional
public class ShoppingCartServiceImpl implements IShoppingCartService {

	@Autowired
	ProductRepository productRepository;

    private Map<BaseProduct, Integer> productsInCarts = new HashMap<>();

    /***
     * 
     */
	@Override
	public Map<BaseProduct, Integer> addProduct(BaseProduct product) {

		if (productsInCarts.containsKey(product)) {
            productsInCarts.replace(product, productsInCarts.get(product) + 1);
        } else {
            productsInCarts.put(product, 1);
        }
		return productsInCarts;
	}


	@Override
	public Map<BaseProduct, Integer> removeProduct(BaseProduct product) {
		
		if(productsInCarts.containsKey(product)) {
			if(productsInCarts.get(product) > 1) {
				productsInCarts.replace(product, productsInCarts.get(product)-1);
			}else if(productsInCarts.get(product) == 1) {
				productsInCarts.remove(product);
			}
		}
		return productsInCarts;
	}

	@Override
	public Map<BaseProduct, Integer> getProductsInCart() {

		return Collections.unmodifiableMap(productsInCarts);
	}


	@Override
	public BigDecimal getTotalPrice() {

		return productsInCarts.entrySet().stream()
				.map(entry -> entry.getKey().getProductPrice().multiply(BigDecimal.valueOf(entry.getValue())))
				.reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
	}
	
	@Override
	public void checkout() {
		Optional<BaseProduct> product;
		for (Map.Entry<BaseProduct, Integer> entry : productsInCarts.entrySet()) {
			Long productId = entry.getKey().getProductId();
			product = productRepository.findByProductId(productId);

			if (product.isPresent()) {

				BaseProduct baseProduct = product.get();
				if (baseProduct.getProductAvailable() > entry.getValue())
					entry.getKey().setProductAvailable(baseProduct.getProductAvailable() - entry.getValue());
				productRepository.saveAll(productsInCarts.keySet());
				productRepository.flush();
			}
		}
		productsInCarts.clear();
	}

}
