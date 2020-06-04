/**
 * 
 */
package com.springboot.shopping.cart.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.shopping.cart.domain.User;
import com.springboot.shopping.cart.service.IUserService;

/**
 * @author M1006601
 *
 */
@Controller
public class RegistrationController {

	@Autowired
	IUserService userService;
	
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public ModelAndView registerUser(@Valid User user, BindingResult result) {
		
		if(userService.findByEmail(user.getUserEmail()).isPresent()) {
			result.rejectValue("Email", "error.user", "User already present with the given email address");
		}
		if(userService.findByUserName(user.getUserName()).isPresent()) {
			result.rejectValue("Email", "error.user", "User already present with the given user name");
		}
		
		ModelAndView modelView = new ModelAndView();
		if(result.hasErrors()) {
			modelView.setViewName("/registration");
		} else {
			userService.saveUser(user);
			
			modelView.addObject("success", "New User registered successfully");
			modelView.addObject("user", new User());
			modelView.setViewName("/registration");
		}
		return modelView;
	}
}
