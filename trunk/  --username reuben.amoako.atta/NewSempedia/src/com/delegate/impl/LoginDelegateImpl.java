package com.delegate.impl;

import com.beans.Login;
import com.delegate.LoginDelegate;
import com.service.LoginService;

public class LoginDelegateImpl implements LoginDelegate {
	
	private LoginService loginService;
	
	public void checkLogin(){
		System.out.println("--------> LoginDelegateImpl: checkLogin()");
		loginService.checkLogin();
	}

	public Integer insertLoginDetails(Login login) {
		System.out.println("--------> LoginDelegateImpl: insertLoginDetails()");
		return loginService.insertLoginDetails(login);
	}
	
	
	
	/**
	 * @return the loginService
	 */
	public LoginService getLoginService() {
		return loginService;
	}

	/**
	 * @param loginService the loginService to set
	 */
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	

}
