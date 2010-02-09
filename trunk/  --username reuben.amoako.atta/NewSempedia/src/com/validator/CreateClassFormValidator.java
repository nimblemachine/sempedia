package com.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.beans.ClassBean;

public class CreateClassFormValidator implements Validator{
	 public boolean supports(Class clazz) {
	        return clazz.equals(ClassBean.class);
	    }
		 public void validate(Object obj, Errors errors) {
			 System.out.println("--------> LoginFormValidator: validate()");
		 	ClassBean user = (ClassBean) obj;
		 	System.out.println("*********** userBean : "+user);
		 	System.out.println("**********  USERNAME : "+user.getClassName());
	        if (user == null) 
	        {
	            errors.rejectValue("className", "error.login.not-specified", null,"Value required.");
	        } 
	        else 
	        {
	        	if (user.getClassName()== null || user.getClassName().trim().length() <= 0) 
	            {
	            	System.out.println("user name null value");
	                errors.rejectValue("className", "error.login.invalid-user",
	                        null, "className is Required.");
	            } 
	        	else 
	            {
//	                if (user.getIsA()== null || user.getIsA().trim().length() <= 0) 
//	                {
//	                    errors.rejectValue("is a", "error.login.invalid-pass",
//	                            null, "is a is Required.");
//	                }
	            }

	        }
	    }
}
