/**
 * 
 */
package com.springboot.shopping.cart.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.shopping.cart.domain.User;
import com.springboot.shopping.cart.repository.UserRepository;
import com.springboot.shopping.cart.service.IUserService;

/**
 * @author M1006601
 *
 */
@Service
public class UserServiceImpl implements IUserService {
	
	UserRepository userRepository;
    //PasswordEncoder passwordEncoder;

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByUserEmail(email);
	}

	@Override
	public User saveUser(User user) {
		
		user.setPassword(user.getPassword());
		return userRepository.saveAndFlush(user);
	}

	@Override
	public Optional<User> findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

}
