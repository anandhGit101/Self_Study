/**
 * 
 */
package com.springboot.shopping.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.shopping.cart.domain.BaseProduct;

/**
 * @author M1006601
 *
 */
public interface ProductRepository extends JpaRepository<BaseProduct, Long> {
	
	Optional<BaseProduct> findByProductId(Long productId);

}
