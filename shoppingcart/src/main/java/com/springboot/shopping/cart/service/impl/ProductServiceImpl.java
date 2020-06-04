/**
 * 
 */
package com.springboot.shopping.cart.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.shopping.cart.domain.BaseProduct;
import com.springboot.shopping.cart.repository.ProductRepository;
import com.springboot.shopping.cart.service.IProductService;

/**
 * @author M1006601
 *
 */
@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	ProductRepository productRepository;
	
	
	@Override
	public List<BaseProduct> getAllProducts() {
		return productRepository.findAll();
	}

	
	@Override
	public Optional<BaseProduct> getProductById(Long productId) {
		return productRepository.findById(productId);
	}


	@Override
	public void addProduct(BaseProduct product) {
		productRepository.save(product);
	}


	@Override
	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
	}

}
