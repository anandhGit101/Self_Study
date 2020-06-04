/**
 * 
 */
package com.springboot.shopping.cart.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author M1006601
 *
 */
@RestController
public class LoginController {

	@RequestMapping(value="/hi", method=RequestMethod.GET)
	public String printhi() {
		return "Hi";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String getLogin() {
		return null;
		
	}
}
