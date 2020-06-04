/**
 * 
 */
package com.springboot.justbook.usermgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.justbook.usermgmt.domain.User;
import com.springboot.justbook.usermgmt.exception.ResourceNotFoundException;
import com.springboot.justbook.usermgmt.repository.UserRepository;
import com.springboot.justbook.usermgmt.security.CurrentUser;
import com.springboot.justbook.usermgmt.security.UserPrincipal;



/**
 * @author M1006601
 *
 */
@RestController
public class UserController {
	
	 @Autowired
	 private UserRepository userRepository;

	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();		
	}
	
	@GetMapping("/user/me")
	@PreAuthorize("hasRole('USER')")
	public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
		return userRepository.findById(userPrincipal.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
	}	 
}
