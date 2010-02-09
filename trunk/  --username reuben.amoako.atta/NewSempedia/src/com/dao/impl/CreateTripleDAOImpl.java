/**
 * 
 */
package com.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.controller.TriplePredicateController;
import com.dao.CreateTripleDAO;
import com.exceptions.DataDeletionException;
import com.exceptions.DataInsertionException;
import com.exceptions.DataRetrievalException;
import com.exceptions.DataUpdationException;
import com.hibernate.pojo.Predicate;
import com.hibernate.pojo.Triple;
import com.util.Constants;

/**
 * @author viswanathp
 *
 */
public class CreateTripleDAOImpl implements CreateTripleDAO
{
	private SessionFactory sessionFactory;
	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Integer saveObjectDetailsToTriple(Triple triplePojo) throws DataInsertionException
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Integer saveResult = 0;
		try{
			transaction = session.beginTransaction();
			saveResult = (Integer) session.save(triplePojo);
			transaction.commit();
		}catch (HibernateException hibernateException){
			transaction.rollback();
			throw new DataInsertionException(Constants.DATA_INSERT_EXCEPTION,hibernateException);
		} catch (Exception exception) {
			transaction.rollback();
			throw new DataInsertionException(Constants.DATA_INSERT_EXCEPTION,exception);
		}finally{
			session.close();
		}
		return saveResult;
	}
	public Integer updateObjectDetailsToTriple(Triple triplePojo) throws DataUpdationException
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Integer updateResult = 0;
		try{
			transaction = session.beginTransaction();
			session.update(triplePojo);
			transaction.commit();
			updateResult = 1;
		}catch(HibernateException e){
			transaction.rollback();
			throw new DataUpdationException(Constants.DATA_UPDATE_EXCEPTION ,e);
		}catch(Exception ee){
			transaction.rollback();
			throw new DataUpdationException(Constants.DATA_UPDATE_EXCEPTION ,ee);
		}
		finally{
			session.close();
		}
		return updateResult;
	}
	public Integer deletePredicateValueFromTriple(Triple triplePojo) throws DataDeletionException
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Integer updateResult = 0;
		try{
			transaction = session.beginTransaction();
			session.delete(triplePojo);
			transaction.commit();
			updateResult = 1;
		}catch(HibernateException e){
			transaction.rollback();
			throw new DataDeletionException(Constants.DATA_DELETE_EXCEPTION ,e);
		}catch(Exception ee){
			transaction.rollback();
			throw new DataDeletionException(Constants.DATA_DELETE_EXCEPTION ,ee);
		}
		finally{
			session.close();
		}
		return updateResult;
	}
	public List getObjectDetailsFromTriple(String subjectName) throws DataRetrievalException
	{
		Session session = sessionFactory.openSession();
		List triplePojoList = null;
		try
		{
			Query query = session.createQuery("select triple from Triple triple " +
				"left join triple.subject sub " +
				"left join triple.predicate pre " +
				"left join triple.object obj "+
				"where LOWER(sub.objectName)=LOWER('"+subjectName+"') order by pre.predicateName");
			triplePojoList = query.list();
		}catch(HibernateException e){
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION ,e);
		}catch(Exception ee){
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION ,ee);
		}
		finally{
			session.close();
		}
		return triplePojoList;
	}
	public List getPredicateValuesFromTriple(String subjectName,String predicateName) throws DataRetrievalException
	{

		Session session = sessionFactory.openSession();
		List list = null;
		Triple triplePojo = null;
		try
		{
			Query query = session.createQuery("select triple from Triple triple " +
				"left join triple.subject sub " +
				"left join triple.predicate pre " +
				"left join triple.object obj "+
				"where LOWER(sub.objectName) = LOWER('"+subjectName+"') and " +
				"LOWER(pre.predicateName) = LOWER('"+predicateName+"')");
			list = (List)query.list();
		}catch(HibernateException e){
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION ,e);
		}catch(Exception ee){
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION ,ee);
		}
		finally{
			session.close();
		}
		return list;
	}
	public List getPredicateNamesFromTriple(String subjectName) throws DataRetrievalException
	{
		Session session = sessionFactory.openSession();
		List resultList = null;
		try
		{
			Query query = session.createQuery("select pre.predicateName from Triple triple " +
					"left join triple.subject sub " +
					"left join triple.predicate pre " +
					"left join triple.object obj "+
					"where LOWER(sub.objectName) = LOWER('"+subjectName+"') order by pre.predicateName");
			resultList = (List)query.list();
		}catch(HibernateException e){
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION ,e);
		}catch(Exception ee){
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION ,ee);
		}
		finally{
			session.close();
		}
		return resultList;
	}
	public List getPredicateMultipleValuesFromTriple(String subjectName,String predicateName) throws DataRetrievalException
	{

		Session session = sessionFactory.openSession();
		List resultList = null;
		try
		{
			Query query = session.createQuery("select triple from Triple triple " +
				"left join triple.subject sub " +
				"left join triple.predicate pre " +
				"left join triple.object obj "+
				"where LOWER(sub.objectName) = LOWER('"+subjectName+"') and " +
				"LOWER(pre.predicateName) = LOWER('"+predicateName+"') order by obj.objectName");
			resultList = (List)query.list();
		}catch(HibernateException e){
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION ,e);
		}catch(Exception ee){
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION ,ee);
		}
		finally{
			session.close();
		}
		return resultList;
	}
	
	public List getPredicateMulitpleValuesByTripleId(Integer tripleId) throws DataRetrievalException
	{
		Session session = sessionFactory.openSession();
		List resultList = null;
		try
		{
			StringBuffer HQL = new StringBuffer("select triple from Triple triple " );
			HQL.append("inner join fetch triple.object objectPojo ");
			HQL.append("inner join triple.subject subject ");
			HQL.append("inner join triple.predicate predicate ");
			HQL.append("where triple.tripleId = "+tripleId);
			Query query = session.createQuery(HQL.toString());
			resultList = (List)query.list();
		}catch(HibernateException e){
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION ,e);
		}catch(Exception ee){
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION ,ee);
		}
		finally{
			session.close();
		}
		return resultList;
	}
	public Triple getTriplePojo(Integer tripleId) throws DataRetrievalException
	{
		Triple triple = null;
		Session session = sessionFactory.openSession();
		try
		{
			StringBuffer HQL = new StringBuffer("select triple from Triple triple " );
			HQL.append("inner join fetch triple.object objectPojo ");
			HQL.append("inner join triple.subject subject ");
			HQL.append("inner join triple.predicate predicate ");
			HQL.append("where triple.tripleId = "+tripleId);
			Query query = session.createQuery(HQL.toString());
			triple = (Triple) query.uniqueResult();
			
		}catch(HibernateException hibernateExcep){
			hibernateExcep.printStackTrace();
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION ,hibernateExcep);
		}catch(Exception exception){
			exception.printStackTrace();
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION ,exception);
		}
		finally{
			session.close();
		}
		return triple;
	}
}
