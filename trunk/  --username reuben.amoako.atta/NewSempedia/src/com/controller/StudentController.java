
package com.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.beans.StudentBean;


public class StudentController extends SimpleFormController{
	
	 public Object formBackingObject(HttpServletRequest request) throws ServletException
	   {
		 System.out.println("================ getFormView() : "+getFormView());
	 	StudentBean backingObject = new StudentBean();
	 	System.out.println("formBackingObject"+backingObject.getSname()+" "+backingObject.getSmarks());
	      
	      /* The backing object should be set up here, with data for the initial values
	       * of the form’s fields. This could either be hard-coded, or retrieved from a
	       * database.
	       */
	      
	     
	      return backingObject;
	   }
	   
	 public ModelAndView onSubmit(Object command) throws ServletException {
		 StudentBean user = (StudentBean)command;
	 	System.out.println("username :"+user.getSname());
	 	System.out.println("marks :"+user.getSmarks());
	 	//Now you can validate to database
	 	/*List userProfile = new ArrayList();
	 	userProfile.add(user.getUserName());
	 	userProfile.add(user.getPassword());*/
	 	System.out.println("================== success : "+getSuccessView());
        return new ModelAndView("success");
    }
	
	  

}
