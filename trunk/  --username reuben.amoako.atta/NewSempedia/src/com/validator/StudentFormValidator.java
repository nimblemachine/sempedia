
package com.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.beans.StudentBean;


public class StudentFormValidator implements Validator{

	 public boolean supports(Class clazz) {
        return clazz.equals(StudentBean.class);
    }
	 
	 public void validate(Object obj, Errors errors) 
	 {
		 StudentBean stud = (StudentBean) obj;
        if (stud == null) 
        {
            errors.rejectValue("username", "error.login.not-specified", null,"Value required.");
        } else 
        {
            if (stud.getSname()== null || stud.getSname().trim().length() <= 0) 
            {
            	System.out.println("student name null value");
                errors.rejectValue("username", "error.login.invalid-user",
                        null, "Username is Required.");
            } 
            if (stud.getSroll() == 0 ) 
            {
                System.out.println("student roll null value");
                errors.rejectValue("roll", "not_zero","Can't be free");
             }
            if (stud.getSmarks()==0)
    		{
    			System.out.println("student marks null value");
    			errors.rejectValue("smarks", "not_zero", "Can't be free!");
    		}
  
        }
    }
}
