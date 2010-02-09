package com.service.impl;

import com.beans.Login;
import com.dao.LoginDAO;
import com.service.LoginService;

public class LoginServiceImpl implements LoginService {
	private LoginDAO loginDAO;

	public void checkLogin(){
		System.out.println("-------> LoginServiceImpl: checkLogin()");
		loginDAO.checkLogin();
	}
	
	public Integer insertLoginDetails(Login login){
		System.out.println("-------> LoginServiceImpl: insertLoginDetails()");
		
		return loginDAO.insertLoginDetails(login);
	}
	
	/**
	 * @return the loginDAO
	 */
	public LoginDAO getLoginDAO() {
		return loginDAO;
	}

	/**
	 * @param loginDAO the loginDAO to set
	 */
	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}
	
	
	
}
