/**
 * 
 */
package com.springboot.shopping.cart.service;

import java.util.List;
import java.util.Optional;

import com.springboot.shopping.cart.domain.User;

/**
 * @author M1006601
 *
 */
public interface IUserService {
	
	Optional<User> findByEmail(String email);
	
	User saveUser(User user);
	
	Optional<User> findByUserName(String userName);
	
	List<User> getUsers();

}
