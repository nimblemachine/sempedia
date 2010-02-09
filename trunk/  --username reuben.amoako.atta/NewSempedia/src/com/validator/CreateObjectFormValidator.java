package com.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.beans.ObjectBean;

public class CreateObjectFormValidator implements Validator{
	 public boolean supports(Class clazz) {
	        return clazz.equals(ObjectBean.class);
	    }
		 public void validate(Object obj, Errors errors)
		 {
			 System.out.println("--------> LoginFormValidator: validate()");
		 	ObjectBean user = (ObjectBean) obj;
		 	System.out.println("*********** userBean : "+user);
		 	System.out.println("**********  USERNAME : "+user.getObjectName());
		 	 if (user == null) 
		 	 {
		 		 errors.rejectValue("objectName", null,"Value required.");
		     } 
		 	 else 
		     {
	            if (user.getObjectName()== null || user.getObjectName().trim().length() <= 0) 
	            {
	            	System.out.println("objectname is null value");
	                errors.rejectValue("objectName",null, "Objectname is Required.");
	            } 
	            else 
	            {
	                if (user.getIsOfType()== null || user.getIsOfType().trim().length() <= 0) 
	                {
	                	System.out.println("isOfType is null value");
	                    errors.rejectValue("isOfType", null, "IsOfType is Required.");
	                }
	            }

		      }
	    }
}
