/**
 * 
 */
package com.springboot.shopping.cart.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.shopping.cart.domain.Apparal;
import com.springboot.shopping.cart.domain.BaseProduct;
import com.springboot.shopping.cart.domain.Book;
import com.springboot.shopping.cart.service.IProductService;
import com.springboot.shopping.cart.service.IShoppingCartService;

/**
 * @author M1006601
 *
 */
@RestController
public class ShoppingCartController {
	
	@Autowired
	IShoppingCartService shoppingCartService;
	
	@Autowired
	IProductService productService;
	
	private Map<BaseProduct, Integer> cartProducts = new HashMap<>();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(path="/shoppingCart")
	public Map<String, Object> shoppingCart() {
		Map<String, Object> modelView = new HashMap<String, Object>();
		String json = null;
		List bookList = new ArrayList();
		List apparalList = new ArrayList();
		JSONObject element = new JSONObject();
		Map<BaseProduct, Integer> productsMap = shoppingCartService.getProductsInCart();

		for (Map.Entry<BaseProduct, Integer> entry : productsMap.entrySet()) {
			try {
				Object product = entry.getKey();
				
				if (product.getClass()== Book.class) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("QuantityPurchased", entry.getValue());
					map.put("item", new ObjectMapper().writeValueAsString(product));					
					bookList.add(new JSONObject(new ObjectMapper().writeValueAsString(map)));
					element.put("Books", bookList);
				} else if (product.getClass()==Apparal.class) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("QuantityPurchased", entry.getValue());
					map.put("item",new ObjectMapper().writeValueAsString(product));
					apparalList.add(new JSONObject(new ObjectMapper().writeValueAsString(map)));
					element.put("Apparal", apparalList);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		System.err.println(element.toString());
		modelView.put("products", element.toString());
		modelView.put("Total", shoppingCartService.getTotalPrice().toString());
		return modelView;
	}
	
	@PostMapping(path="/shoppingCart/addProduct/{productId}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> addProductToCart(@PathVariable("productId") Long productId) {
		
		Optional<BaseProduct> productById = productService.getProductById(productId);
		if(productById.isPresent()) {
			cartProducts = shoppingCartService.addProduct(productById.get());
		}
		return shoppingCart();
		
	}
	
	@RequestMapping(value="/shoppingCart/deleteProduct/{productId}", method=RequestMethod.DELETE)
	public Map<String, Object> deleteProductToCart(@PathVariable("productId") Long productId) {
		
		productService.getProductById(productId).ifPresent(shoppingCartService::removeProduct);
		return shoppingCart();
	}
	
	@GetMapping("/shoppingCart/checkout")
	public Map<String, Object> checkout() {
		shoppingCartService.checkout();
		return shoppingCart();
	}

}
