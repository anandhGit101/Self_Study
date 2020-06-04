/**
 * 
 */
package com.springboot.justbook.util;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.springboot.justbook.vo.MovieRequestVO;

/**
 * @author M1006601
 *
 */
//@Component
public class MovieMgmtCustomValidator implements Validator{
	
	public boolean supports(Class<?> clazz) {
		
		return MovieRequestVO.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		MovieRequestVO movieReqVO = (MovieRequestVO) target;
		ValidatorFactory factory =
		          Validation.buildDefaultValidatorFactory();
		Validator validator = (Validator) factory.getValidator();
		//Set<ConstraintViolation<movieReqVO>> constraintViolations = validator.validate(movieReqVO);
	}
}
