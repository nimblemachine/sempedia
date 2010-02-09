
package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.beans.UserBean;
import com.beans.Login;
import com.delegate.LoginDelegate;



public class LoginController extends SimpleFormController{
	private LoginDelegate loginDelegate;
	 public Object formBackingObject(HttpServletRequest request) throws ServletException
	   {
		 System.out.println("********************** "+getFormView());
	 	UserBean backingObject = new UserBean();
	 	System.out.println("--------> LoginController: formBackingObject() ===== formBackingObject : "+backingObject.getPassword()+" "+backingObject.getUsername());
	      
	      /* The backing object should be set up here, with data for the initial values
	       * of the form’s fields. This could either be hard-coded, or retrieved from a
	       * database.
	       */
	      return backingObject;
	   }
	   
	 public ModelAndView onSubmit(Object command) throws ServletException {
		 
		System.out.println("----------------------------------- logincontroller------------------------------");
	 	UserBean user = (UserBean)command;
	 	Login loginDetails = new Login();
	 	System.out.println("username :"+user.getUsername());
	 	System.out.println("password :"+user.getPassword());
	 	
	 	loginDetails.setUsername(user.getUsername());
	 	loginDetails.setPassword(user.getPassword());
	 	
	 	//Now you can validate to database
	 	
	 	List userProfile = new ArrayList();
	 	userProfile.add(user.getUsername());
	 	userProfile.add(user.getPassword());
	 	
	 	loginDelegate.insertLoginDetails(loginDetails);
	 	System.out.println("=============== getSuccessView() : "+getSuccessView());
	 	System.out.println("----------------------------------- logincontroller------------------------------");
        return new ModelAndView(getSuccessView(),"userProfile",userProfile);
    }

	/**
	 * @return the loginDelegate
	 */
	public LoginDelegate getLoginDelegate() {
		return loginDelegate;
	}

	/**
	 * @param loginDelegate the loginDelegate to set
	 */
	public void setLoginDelegate(LoginDelegate loginDelegate) {
		this.loginDelegate = loginDelegate;
	}
	
	  

}
