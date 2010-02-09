package com.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.beans.Login;
import com.dao.LoginDAO;
import com.hibernate.pojo.User;

public class LoginDAOImpl implements LoginDAO {
	private SessionFactory sessionFactory;
	
	public void checkLogin(){
		System.out.println("-------> LoginDAOImpl: checkLogin()");
	}
	public Integer insertLoginDetails(Login login){
		System.out.println("-------> LoginDAOImpl: insertLoginDetails()--"+sessionFactory);
		Integer result = 0;
		Session session = sessionFactory.openSession();
		
		Transaction transaction = null;
		try{
			User user = new User();
			transaction = session.beginTransaction();
			
			user.setUsername(login.getUsername());
			user.setPassword(login.getPassword());
			
			result =(Integer)session.save(user);
			transaction.commit();
			
			if(result > 0){
				System.out.println("=====> inserted successfully : "+result);
			}
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
		}catch(Exception ee){
			transaction.rollback();
			ee.printStackTrace();
		}
		finally{
			session.close();
		}
		return result;
		
	}
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
