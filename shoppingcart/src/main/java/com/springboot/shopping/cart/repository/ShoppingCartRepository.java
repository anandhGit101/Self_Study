/**
 * 
 */
package com.springboot.shopping.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.shopping.cart.domain.ShoppingCart;

/**
 * @author M1006601
 *
 */
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>{
	
	

}