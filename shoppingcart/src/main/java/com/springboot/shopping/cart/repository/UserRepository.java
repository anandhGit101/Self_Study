/**
 * 
 */
package com.springboot.shopping.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.springboot.shopping.cart.domain.User;

/**
 * @author M1006601
 *
 */
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUserName(@Param("userName") String userName);
	
	Optional<User> findByUserEmail(@Param("userEmail") String userEmail);

}
