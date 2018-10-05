package com.bub.sho.validator;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<ValidDate,Date> {
	
	private final Date cDate = new Date(0);
	
	
	@Override
	public boolean isValid(Date tDate ,ConstraintValidatorContext constraintValidatorContext) {
		
		
		boolean result;
		
		if(tDate != null) {
				result = tDate.compareTo(cDate)>=0?true:false;
		}else {
			result=true;
		}
		
		return result;
	}

}
