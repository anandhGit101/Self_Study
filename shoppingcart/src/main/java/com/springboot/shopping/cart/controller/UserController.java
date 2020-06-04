/**
 * 
 */
package com.springboot.shopping.cart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.shopping.cart.domain.User;
import com.springboot.shopping.cart.service.IUserService;

/**
 * @author M1006601
 *
 */
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@RequestMapping(value="/shoppingCart/users", method=RequestMethod.GET)
	public List<User> getAvailableUsers(){
		
		List<User> usersList = new ArrayList<User>();
		usersList = userService.getUsers();
		return usersList;
	}
	

}
