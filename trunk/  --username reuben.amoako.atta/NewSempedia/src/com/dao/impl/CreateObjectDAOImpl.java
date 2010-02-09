/**
 * 
 */
package com.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.dao.CreateObjectDAO;
import com.dto.ObjectDTO;
import com.exceptions.DataInsertionException;
import com.exceptions.DataRetrievalException;
import com.exceptions.DataUpdationException;
import com.hibernate.pojo.ClassPojo;
import com.hibernate.pojo.ObjectDescription;
import com.hibernate.pojo.ObjectPojo;
import com.hibernate.pojo.Predicate;
import com.util.Constants;

/**
 * @author viswanathp
 * 
 */
public class CreateObjectDAOImpl implements CreateObjectDAO {

	private SessionFactory sessionFactory;

	public List getPredicatesOfClass(Integer classId) throws DataRetrievalException{
		List list = new ArrayList();
		Session session = sessionFactory.openSession();
		try {
			Query query = session
			.createQuery("select predicate from Predicate predicate "
					+ "left join predicate.classObject cPojo where cPojo.classId = "
					+ classId);
			list = query.list();
		}catch(HibernateException hibernateException){
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVE_EXCEPTION , hibernateException);
		} catch (Exception exception) {
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION , exception );
		} finally {
			session.close();
		}
		return list;
	}
	
	public Integer getClassIdByName(String className) throws DataRetrievalException {
		Session session = sessionFactory.openSession();
		Integer classId = 0;
		try {
			Query query = session
			.createQuery("select classPojo.classId from ClassPojo classPojo "
					+ "where LOWER(classPojo.className) = LOWER('" + className + "')");

			classId = (Integer) query.uniqueResult();
		}catch(HibernateException hibernateException){
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVE_EXCEPTION , hibernateException);
		} catch (Exception exception) {
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION , exception );
		} finally {
			session.close();
		}
		return classId;
	}
	
	public Integer getSuperClassId(Integer classId) throws DataRetrievalException {
		Session session = sessionFactory.openSession();
		Integer superClassId = 0;
		try {
			Query query = session
			.createQuery("select superClassPojo.classId from Inheritance inherit "
					+ "left join inherit.subClass subClassPojo "
					+ "left join inherit.superClass superClassPojo "
					+ "where subClassPojo.classId = " + classId);

			superClassId = (Integer) query.uniqueResult();
		}catch(HibernateException hibernateException){
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVE_EXCEPTION , hibernateException);
		} catch (Exception exception) {
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION , exception );
		} finally {
			session.close();
		}
		return superClassId;
	}
	
	public Boolean checkObject(String objectName) throws DataRetrievalException {
		Session session = sessionFactory.openSession();
		Boolean status = false;
		Integer result = 0;
		try {
			Query query = session
			.createQuery("select obj.objectId from ObjectPojo obj "
					+ "where LOWER(obj.objectName) = LOWER('"
					+ objectName + "')");
			result = (Integer) query.uniqueResult();
			if (result != null) {
				status = true;
			}
		}catch(HibernateException hibernateException){
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVE_EXCEPTION , hibernateException);
		} catch (Exception exception) {
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION , exception );
		} finally {
			session.close();
		}

		return status;
	}
	
	public ClassPojo getClass(String className) throws DataRetrievalException {
		Session session = sessionFactory.openSession();
		ClassPojo classObj = null;
		try {
			Query query = session
			.createQuery("select classSuper from ClassPojo classSuper "
					+ "where LOWER(classSuper.className) = LOWER('"
					+ className + "')");
			classObj = (ClassPojo) query.uniqueResult();
		}catch(HibernateException hibernateException){
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVE_EXCEPTION , hibernateException);
		} catch (Exception exception) {
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION , exception );
		} finally {
			session.close();
		}
		return classObj;
	}
	
	public ObjectPojo getObject(String objectName) throws DataRetrievalException {
		Session session = sessionFactory.openSession();
		ObjectPojo objectObj = null;
		try {
			Query query = session.createQuery("select obj from ObjectPojo obj "
					+ "where LOWER(obj.objectName) = LOWER('" + objectName+ "')");
			objectObj = (ObjectPojo) query.uniqueResult();
		}catch(HibernateException hibernateException){
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVE_EXCEPTION , hibernateException);
		} catch (Exception exception) {
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION , exception );
		} finally {
			session.close();
		}
		return objectObj;
	}
	
	public Integer insertObject(ObjectPojo objectPojo)
	throws DataInsertionException {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Integer saveResult = 0;
		try {
			transaction = session.beginTransaction();
			saveResult = (Integer) session.save(objectPojo);
			transaction.commit();
		}catch (HibernateException hibernateException){
			transaction.rollback();
			throw new DataInsertionException(Constants.DATA_INSERT_EXCEPTION,hibernateException);
		} catch (Exception exception) {
			transaction.rollback();
			throw new DataInsertionException(Constants.DATA_INSERT_EXCEPTION,exception);
		} finally {
			session.close();
		}
		return saveResult;
	}
	
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

	public List getAllObjects(String objectName) throws DataRetrievalException{
		

		
		Session session = sessionFactory.openSession();
		List objectList = null;

		try {
			StringBuffer query = new StringBuffer(
					" from ObjectPojo objectLik where lower(objectLik.objectName) like lower('"
					+ objectName + "%')");
			objectList = session.createQuery(query.toString()).list();
		}catch(HibernateException hibernateException){
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVE_EXCEPTION , hibernateException);
		} catch (Exception exception) {
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION , exception );
		} finally {
			session.close();
		}
		return objectList;
	}
	
	public Integer saveObjectDetails(String objectName, Integer classId) throws DataInsertionException {
		Session session = sessionFactory.openSession();
		int flag = 0;
		List objectList = null;
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			ObjectPojo objectPojo = new ObjectPojo();
			ClassPojo classPojo = new ClassPojo();
			classPojo.setClassId(classId);
			objectPojo.setClassId(classPojo.getClassId());
			objectPojo.setObjectName(objectName);
			session.save(objectPojo);
			transaction.commit();
			if (true) {
				flag = 1;
			}
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			throw new DataInsertionException(Constants.DATA_INSERT_EXCEPTION,hibernateException);
		}catch (Exception exception){
			transaction.rollback();
			throw new DataInsertionException(Constants.DATA_INSERT_EXCEPTION,exception);
		}finally {
			session.close();
		}
		return flag;
	}
	
	public ObjectPojo getObjectDetails(String objectName) throws DataRetrievalException{
		ObjectPojo objectPojo = null;
		Session session = sessionFactory.openSession();
		List objectList = null;

		try {
			String query = "select objectpojo from ObjectPojo objectpojo where LOWER(objectpojo.objectName) = LOWER('"
				+ objectName+"')";
			objectPojo = (ObjectPojo) session.createQuery(query).uniqueResult();
		}catch(HibernateException hibernateException){
			objectPojo = new ObjectPojo();
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVE_EXCEPTION, hibernateException);
		} catch (Exception exception) {
			objectPojo = new ObjectPojo();
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION, exception );
		} finally {
			session.close();
		}
		return objectPojo;
	}
	
	
	public String getClassName(String objectName) throws DataRetrievalException {
		Session session = sessionFactory.openSession();
		String className = null;
		try {
			Query query = session
			.createQuery("select cls.className from ClassPojo cls "
					+ "left join cls.objects obj "
					+ "where LOWER(obj.objectName) = LOWER('"+ objectName + "')");
			className = (String) query.uniqueResult();
		}catch(HibernateException hibernateException){
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVE_EXCEPTION , hibernateException);
		} catch (Exception exception) {
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION , exception );
		} finally {
			session.close();
		}
		System.out.println("************************************* :"+className);
		return className;
	}
	public String saveObjectImage(ObjectDescription objectDescription)throws DataInsertionException {
		Session session = sessionFactory.openSession();
		String status = null;
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			session.saveOrUpdate(objectDescription);
			tx.commit();
			status = "saved";
		}catch(HibernateException hibernateException){
			status = "notSaved";	
			tx.rollback();
			throw new DataInsertionException(Constants.DATA_INSERT_EXCEPTION,hibernateException);
		}
		catch(Exception exception){
			status = "notSaved";			
			tx.rollback();
			throw new DataInsertionException(Constants.DATA_INSERT_EXCEPTION, exception);
		}finally{
			session.close();
		}
		return status;
	}

	public ObjectDescription readObjectImage(Integer objectId)throws DataRetrievalException  {
		byte[] imageArray = null;
		ObjectDescription objectDescription = null;
		String hql = "select objectDescription from com.hibernate.pojo.ObjectDescription objectDescription " +
					 "where objectDescription.objectPojo.objectId = "+objectId;
		Session session = sessionFactory.openSession();		
		try{
			Query query = session.createQuery(hql);			
			List list = query.list();	
			if(list.size() >0){
				objectDescription = (ObjectDescription)list.get(0);
			}else{
				objectDescription = new ObjectDescription();
			}
		}
		catch(HibernateException hbExcep){	
			objectDescription = new ObjectDescription(); 
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION , hbExcep);
		}
		catch(Exception exception){
			objectDescription = new ObjectDescription(); 
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION , exception );
		}
		finally{
			session.close();
		}
		return objectDescription;
	}

	public ObjectDescription readObjectDescription(Integer objDescId)throws DataRetrievalException  {
		
		ObjectDescription objectDescription = null;
		String hql = "select objectDescription from com.hibernate.pojo.ObjectDescription objectDescription" +
					 " where objectDescription.objectDescriptionId = "+objDescId;
		
		Session session = sessionFactory.openSession();		
		try{
			Query query = session.createQuery(hql);			
			List list = query.list();	
			if(list.size() >0){
				objectDescription = (ObjectDescription)list.get(0);
			}else{
				objectDescription = new ObjectDescription();
			}
		}
		catch(HibernateException hbExcep){	
			objectDescription = new ObjectDescription(); 
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION , hbExcep);
		}
		catch(Exception exception){
			objectDescription = new ObjectDescription(); 
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION , exception );
		}
		finally{
			session.close();
		}
		return objectDescription;
	}

	public String updateObjectName(ObjectPojo objectPojo)
			throws DataUpdationException {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		String updatedResult = null;
		try {
			transaction = session.beginTransaction();
			session.update(objectPojo);
			transaction.commit();
			updatedResult = "updated";
		}catch (HibernateException hibernateException){
			transaction.rollback();
			updatedResult = "notUpdate";
			throw new DataUpdationException(Constants.DATA_UPDATE_EXCEPTION,hibernateException);
		} catch (Exception exception) {
			transaction.rollback();
			updatedResult = "notUpdate";
			throw new DataUpdationException(Constants.DATA_UPDATE_EXCEPTION, exception);
		} finally {
			session.close();
		}
		return updatedResult;		
	}

	public ObjectPojo getObjectById(ObjectPojo objectPojo) throws DataRetrievalException {
		Session session = sessionFactory.openSession();		
		String query = "select objectPojo from ObjectPojo objectPojo where objectPojo.objectId = "+objectPojo.getObjectId();
		try {
			objectPojo = (ObjectPojo) session.createQuery(query).uniqueResult();
		}catch(HibernateException hbExcep){	
			//hbExcep.printStackTrace();
				throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION , hbExcep);
		}
		catch(Exception exception){
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION , exception );
		}
		finally{
			session.close();
		}
		return objectPojo;
	}
	
}
