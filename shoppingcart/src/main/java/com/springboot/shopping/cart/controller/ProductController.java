/**
 * 
 */
package com.springboot.shopping.cart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.shopping.cart.domain.BaseProduct;
import com.springboot.shopping.cart.service.IProductService;

/**
 * @author M1006601
 *
 */
@RestController
public class ProductController {
	
	@Autowired
	IProductService productService;
	
	private static final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@GetMapping(path="/shoppingCart/product/list", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<BaseProduct> getProducts() {
		
		log.info("Entering into getProducts() method in "+ProductController.class);
		List<BaseProduct> productsList = productService.getAllProducts();
		return productsList;		
	}
	
	@PostMapping("/addProduct")
	public void addProduct(@RequestBody BaseProduct product) {
		
		log.info("Entering into addProduct() method in "+ProductController.class);
		productService.addProduct(product);
	}
	
	@DeleteMapping("/removeProduct/{productId}")
	public void deleteProduct(Long productId) {
		
		log.info("Entering into deleteProduct() method in "+ProductController.class);
		productService.deleteProduct(productId);
	}

}
