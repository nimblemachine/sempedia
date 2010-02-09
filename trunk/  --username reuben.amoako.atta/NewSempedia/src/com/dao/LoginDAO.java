package com.dao;

import com.beans.Login;
public interface LoginDAO {
	public void checkLogin();
	public Integer insertLoginDetails(Login login);
}
