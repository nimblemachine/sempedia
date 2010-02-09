package com.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dao.PredicateDAO;
import com.dto.ClassDTO;
import com.exceptions.DataInsertionException;
import com.exceptions.DataRetrievalException;
import com.exceptions.DataUpdationException;
import com.hibernate.pojo.ClassPojo;
import com.hibernate.pojo.ObjectPojo;
import com.hibernate.pojo.Predicate;
import com.hibernate.pojo.Triple;
import com.util.Constants;

/**
 * Contains methods for create, update, delete Predicates and basic search 
 * functionality methods.
 * 
 * @author naveenK
 */
public class PredicateDAOImpl implements PredicateDAO{
	
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
	
	/**
	 * getPredicateFromParents method is used to check whether the predicate name exists in any super class of
	 * the given class.
	 * 
	 * @author viswanathp
	 * 
	 * @param predicatName
	 * @param isOfTypeClassId
	 * @return Unique Predicate Pojo
	 * @throws DataRetrievalException
	 */
	public Predicate getPredicateFromParents(String predicateName,Integer isOfTypeClassId) throws DataRetrievalException
	{
		Session session  = sessionFactory.openSession();
		Predicate predicatPojo = null;
		try
		{
			Query query = null;
			List<Integer> classIdsList = new ArrayList<Integer>();
			Integer subClassId = isOfTypeClassId;
			Integer superClassId = 0;
			do
			{
				if(superClassId != 0 )
				{
					classIdsList.add(superClassId);
				}
				
				query = session.createQuery("select inheritPojo.superClass.classId from Inheritance inheritPojo " +
					"left join inheritPojo.superClass superCls " +
					"left join inheritPojo.subClass subCls " +
					"where subCls.classId = "+subClassId);
				superClassId = (Integer)query.uniqueResult();
				subClassId = superClassId;
			}while(superClassId != null);
			
			String HQL = "select predicate from Predicate predicate " +
					"left join predicate.classObject clsPojo " +
					"where LOWER(predicate.predicateName) = LOWER('"+predicateName+"') and clsPojo.classId in ("+isOfTypeClassId;
			
					for (Iterator itr = classIdsList.iterator(); itr.hasNext();) 
					{
						Integer classId = (Integer) itr.next();
						HQL = HQL + ","+classId+"";
					}
					
					HQL = HQL + ")";
					
			query = null;		
			query = session.createQuery(HQL);
			predicatPojo = (Predicate)query.uniqueResult();
		}
		catch (HibernateException hibernateException)
		{
			hibernateException.printStackTrace();
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION,hibernateException);
		}
		finally
		{
			session.close();
		}
		return predicatPojo;
	}
	
	
	/**
	 * getPredicatesForObject method is used to retrieve the Predicates of given subject(object). 
	 * 
	 * @author viswanathp
	 * 
	 * @param subjectId
	 * @return Predicates of the subject(object).
	 * @throws DataRetrievalException
	 */
	public List getPredicatesForObject(String subjectId) throws DataRetrievalException
	{
		Session session  = sessionFactory.openSession();
		List predicateList = null;

		
		try
		{
			StringBuffer query = new StringBuffer("select predicate from Predicate predicate");
			query.append(" inner join fetch predicate.triples triple");
			query.append(" inner join fetch triple.subject subject");
			query.append(" inner join fetch triple.object objectLik");
			query.append(" where subject.objectId = '"+ subjectId+"'");
			predicateList  = session.createQuery(query.toString()).list();
		}
		catch (HibernateException hibernateException)
		{
			hibernateException.printStackTrace();
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION,hibernateException);
		}
		finally
		{
			session.close();
		}
		return predicateList;
	}
	
	
	/**
	 * saveNewPredicate method is used to insert a new Predicate to the given class. 
	 * 
	 * @author viswanathp
	 * 
	 * @param predicatName
	 * @param classPojo
	 * @return Sequence of the inserted record.
	 * @throws DataInsertionException
	 */
	public Integer saveNewPredicate(String predicateName,ClassPojo classPojo) throws DataInsertionException
	{
		Integer saveResult=0;
		Predicate predicatePojo = new Predicate();
		predicatePojo.setClassObject(classPojo);
		predicatePojo.setPredicateName(predicateName);
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			saveResult = (Integer)session.save(predicatePojo);
			transaction.commit();
			session.flush();
		}catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
			throw new DataInsertionException(Constants.DATA_INSERT_EXCEPTION,e);
		}finally{
			session.close();
		}
		return saveResult;
	}
	
	
	/**
	 * saveTripleDetails method is used to save the value of the given predicate and subject(Object).
	 * 
	 * @author viswanathp
	 * 
	 * @param subjectId
	 * @param predicateId
	 * @param objectId
	 * @return Sequence of the inserted record.
	 * @throws DataInsertionException
	 */
	public Integer saveTripleDetails(Integer subjectId,Integer predicateId,Integer objectId) throws DataInsertionException
	{
		Session session  = sessionFactory.openSession();
		int flag = 0;
		Transaction transaction = null;
		try
		{
			transaction = session.beginTransaction();
			Triple triple = new Triple();
			ObjectPojo objectPojo = new ObjectPojo();
			ObjectPojo subjectPojo = new ObjectPojo();
			Predicate predicate = new Predicate();
			predicate.setPredicateId(predicateId);
			objectPojo.setObjectId(objectId);
			subjectPojo.setObjectId(subjectId);
			triple.setObject(objectPojo);
			triple.setSubject(subjectPojo);
			triple.setPredicate(predicate);
			session.save(triple);
			transaction.commit();
			if (true)
			{
				flag = 1;
			}	
		}
		catch (HibernateException hibernateException)
		{
			transaction.rollback();
			throw new DataInsertionException(Constants.DATA_INSERT_EXCEPTION,hibernateException);
		}
		finally
		{
			session.close();
		}
		return flag;
	}
	
	
	
	/**
	 * insertPredicates method is used to insert the predicates in to the class
	 * based on classDTO
	 * 
	 * 
	 * @author gurupavan @ returns integer value if property is deleted else an
	 *         exception
	 * @throws DataInsertionException        
	 */
	public Integer insertPredicates(ClassDTO classDTO)throws DataInsertionException {
		Predicate predicate;
		ClassPojo classPojo;
		Integer count = 0;
		String property;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try{
			Iterator<String> iterator = classDTO.getProperty().iterator();
			while (iterator.hasNext()) {
				transaction = session.beginTransaction();
		    	predicate = new Predicate();
				classPojo = new ClassPojo();
				property = (String) iterator.next();
				classPojo.setClassId(classDTO.getSubClassId());
				predicate.setClassObject(classPojo);
				predicate.setPredicateName(property);
				count = (Integer)session.save(predicate);
			 }
			transaction.commit();
			session.flush();
		}catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
			transaction.rollback();
			throw new DataInsertionException(Constants.DATA_INSERTION_EXCEPTION);
		}catch (Exception exception) {
			throw new DataInsertionException(Constants.DATA_INSERT_EXCEPTION,exception);
		}finally{
			session.close();
		}
		return count;
	}
	/**
	 * getPredicate method is used to get all the predicates based on the given
	 * class id.
	 * 
	 * @author gurupavan @ returns Predicates for the given class id
	 * @throws DataRetrievalException
	 */
	public Predicate getPredicate(String predicateName, Integer classId)
			throws DataRetrievalException {
		Session session = sessionFactory.openSession();
		Predicate predicatPojo = null;
		try {
			Query query = session
					.createQuery("select predicate from Predicate predicate "
							+ "left join predicate.classObject clsObj "
							+ "where LOWER(predicate.predicateName) = LOWER('"
							+ predicateName + "') and " + "clsObj.classId = "
							+ classId);
			predicatPojo = (Predicate) query.uniqueResult();
		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION,
					hibernateException);
		} catch (Exception exception) {
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVAL_EXCEPTION, exception);
		} finally {
			session.close();
		}
		return predicatPojo;
	}
	
	/**
	 * 
	 * updateClassProperty method is used to update a selected property in edit
	 * mode of an class
	 * 
	 * @return Integer based on updation of the property
	 * @throws DataUpdationException
	 */
	public Integer updateClassProperty(Integer predicateId, String propertyName)
			throws DataUpdationException {
		Session session = sessionFactory.openSession();
		Integer flag = 0;
		Transaction transaction = null;
		Predicate predicatePojo = new Predicate();
		try {
			transaction = session.beginTransaction();
			predicatePojo.setPredicateId(predicateId);
			predicatePojo.setPredicateName(propertyName);
			String query = "update Predicate as predicate set predicate.predicateName='"
					+ propertyName
					+ "' where predicate.predicateId="
					+ predicateId;
			Query q = session.createQuery(query);
			flag = q.executeUpdate();
			transaction.commit();
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			throw new DataUpdationException(Constants.DATA_UPDATE_EXCEPTION,
					hibernateException);

		} catch (Exception exception) {
			throw new DataUpdationException(Constants.DATA_UPDATE_EXCEPTION,
					exception);
		} finally {
			session.close();
		}
		return flag;
	}

	
	
	
}
