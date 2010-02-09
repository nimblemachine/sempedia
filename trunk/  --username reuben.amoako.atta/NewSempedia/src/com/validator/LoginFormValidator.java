
package com.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.beans.UserBean;


public class LoginFormValidator implements Validator{

	 public boolean supports(Class clazz) {
        return clazz.equals(UserBean.class);
    }
	 
	 public void validate(Object obj, Errors errors) {
		 System.out.println("--------> LoginFormValidator: validate()");
	 	UserBean user = (UserBean) obj;
	 	System.out.println("*********** userBean : "+user);
	 	System.out.println("**********  USERNAME : "+user.getUsername());
        if (user == null) 
        {
            errors.rejectValue("username", "error.login.not-specified", null,"Value required.");
        } 
        else 
        {
        	if (user.getUsername()== null || user.getUsername().trim().length() <= 0) 
            {
            	System.out.println("user name null value");
                errors.rejectValue("username", "error.login.invalid-user",
                        null, "Username is Required.");
            } 
        	else 
            {
                if (user.getPassword()== null || user.getPassword().trim().length() <= 0) 
                {
                    errors.rejectValue("password", "error.login.invalid-pass",
                            null, "Password is Required.");
                }
            }

        }
    }
}
