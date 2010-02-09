package com.delegate;

import com.beans.Login;

public interface LoginDelegate {
	public void checkLogin();
	public Integer insertLoginDetails(Login login);
	
}
