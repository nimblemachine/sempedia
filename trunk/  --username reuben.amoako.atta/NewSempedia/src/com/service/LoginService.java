package com.service;

import com.beans.Login;

public interface LoginService {

	public void checkLogin();
	public Integer insertLoginDetails(Login login);
}
