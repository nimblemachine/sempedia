/**
 * 
 */
package com.dao.impl;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dao.CreateClassDAO;
import com.dto.ClassDTO;
import com.dto.SuperClassDTO;
import com.exceptions.DataDeletionException;
import com.exceptions.DataInsertionException;
import com.exceptions.DataRetrievalException;
import com.exceptions.DataUpdationException;
import com.hibernate.pojo.ClassDescription;
import com.hibernate.pojo.ClassPojo;
import com.hibernate.pojo.Inheritance;
import com.hibernate.pojo.ObjectPojo;
import com.hibernate.pojo.Predicate;
import com.util.Constants;

/**
 * @author gurupavan
 * 
 */
public class CreateClassDAOImpl implements CreateClassDAO {

	private SessionFactory sessionFactory;
	
	/**
	 * insertClass method is used to insert the class detail based by checking
	 * if class exists or not
	 * 
	 * @author gurupavan
	 * 
	 * @ returns String after inserting the class
	 * @throws DataInsertionException
	 */
	public String insertClass(ClassDTO classDTO) throws DataInsertionException {

		Session session = sessionFactory.openSession();
		List<ClassPojo> classList = new LinkedList<ClassPojo>();
		Transaction transaction = null;
		Integer result = 0;
		Integer superClassId = 0;
		String finalResult = "";
		boolean classExistsFlag = false;
		try {
				String checkClassQuery = "select classObj from ClassPojo classObj where LOWER(classObj.className)=LOWER('"
						+ classDTO.getClassName() + "')";
				Query checkQuery = session.createQuery(checkClassQuery);
				classList = checkQuery.list();
				
				
				
				
				
				if (classList.size() > 0) {
				
					classExistsFlag = true;
					
				} else {
					
					ClassPojo classPojo = new ClassPojo();
					transaction = session.beginTransaction();
					classPojo.setClassName(classDTO.getClassName());
					ClassPojo superClass = null;
					result =  (Integer) session.save(classPojo);
					
					
					if (!StringUtils.isEmpty(classDTO.getIsA())) {
						Query query = session
								.createQuery("select classSuper from ClassPojo classSuper where LOWER(classSuper.className)=LOWER('"
										+ classDTO.getIsA() + "')");
						List list = query.list();
						for (Iterator iterator = list.iterator(); iterator
								.hasNext();) {
							superClass = (ClassPojo) iterator.next();
							superClassId = superClass.getClassId();
						}
						
						
						
						
						Inheritance inheritance = new Inheritance();
						inheritance.setSubClass(classPojo);
						inheritance.setSuperClass(superClass);
						session.save(inheritance);
					}
					transaction.commit();
				}
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			throw new DataInsertionException(
					Constants.DATA_INSERTION_EXCEPTION, hibernateException);
		} catch (Exception exception) {
			transaction.rollback();
			throw new DataInsertionException(
					Constants.DATA_INSERTION_EXCEPTION, exception);
		} finally {
			session.close();

		}
		if (classExistsFlag)
			finalResult = "classExists";
		else
			finalResult = result + "-" + superClassId;
		return finalResult;
	}
	
	/**
	 * getClassCreatedDetails method is used to get all the class details for
	 * edit mode
	 * 
	 * @author gurupavan
	 * 
	 *  @ returns SuperClassDTO object which contains all
	 *         details of the class
	 * @throws DataRetrievalException
	 */
	public SuperClassDTO getClassCreatedDetails(SuperClassDTO superClassObj)
			throws DataRetrievalException {

		SuperClassDTO superClassDTOResult = new SuperClassDTO();
		List<String> stringList = new LinkedList<String>();
		Set<String> predicateSet = new LinkedHashSet<String>();
		List<String> predicateList = new LinkedList<String>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			if (superClassObj.getSuperClassId() != null) {
				String query = "select predicate from Predicate predicate where predicate.classObject.classId="
						+ superClassObj.getSuperClassId();
				Query q = session.createQuery(query);
				predicateList = q.list();
			}
		} catch (HibernateException hibernateException) {
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVAL_EXCEPTION, hibernateException);
		} catch (Exception exception) {
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION,
					exception);
		} finally {
			session.close();
		}
		for (Iterator iterator = predicateList.iterator(); iterator.hasNext();) {
			Predicate predicate = (Predicate) iterator.next();
			stringList.add(predicate.getPredicateName());
		}
		predicateSet.addAll(stringList);
		superClassDTOResult.setSuperClassProperties(predicateSet);
		return superClassDTOResult;
	}
	
	/**
	 * checkAnySuperClassExists method is used to check if any super class
	 * exists based on superclass id
	 * 
	 * @author gurupavan
	 * 
	 * @ returns Integer set that will contain all the super
	 *         class ids
	 * @throws DataRetrievalException
	 */
	public Set<Integer> checkAnySuperClassExists(Integer superClassId)
			throws DataRetrievalException {

		Set<Integer> superClassIdList = new LinkedHashSet<Integer>();
		List<Inheritance> queryList = new LinkedList<Inheritance>();
		List<Integer> superClassList = new LinkedList<Integer>();
		Session session = sessionFactory.openSession();
		try {
			Query query = session
					.createQuery("select inheritance from Inheritance inheritance where inheritance.subClass.classId="
							+ superClassId);
			queryList = query.list();
		} catch (HibernateException hibernateException) {
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVAL_EXCEPTION, hibernateException);
		} catch (Exception exception) {
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION,
					exception);
		} finally {
			session.close();
		}
		for (Iterator<Inheritance> iterator = queryList.iterator(); iterator
				.hasNext();) {
			Inheritance InheritanceObj = (Inheritance) iterator.next();
			superClassList.add(InheritanceObj.getSuperClass().getClassId());
		}
		superClassIdList.addAll(superClassList);
		return superClassIdList;
	}
	
	/**
	 * getClassId method is used to return class id based on class name
	 * 
	 * @author gurupavan
	 * 
	 * @ returns Integer that is class id
	 * @throws DataRetrievalException
	 */
	
	public Integer getClassId(String className) throws DataRetrievalException {

		Integer superClassId = 0;
		List<ClassPojo> queryList = new LinkedList<ClassPojo>();
		Session session = sessionFactory.openSession();
		try {
			Query query = session
					.createQuery("select classObj from ClassPojo classObj where classObj.className='"
							+ className + "'");
			queryList = query.list();
		} catch (HibernateException hibernateException) {
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVAL_EXCEPTION, hibernateException);
		} catch (Exception exception) {
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION,
					exception);
		} finally {
			session.close();
		}
		for (Iterator<ClassPojo> iterator = queryList.iterator(); iterator
				.hasNext();) {
			ClassPojo classObj = (ClassPojo) iterator.next();
			superClassId = classObj.getClassId();
		}
		return superClassId;
	}
	
public List getAllClasses(String className) throws DataRetrievalException{
	
		

		Session session = sessionFactory.openSession();
		List objectList = null;

		try {
			StringBuffer query = new StringBuffer(
					" from ClassPojo classLik where lower(classLik.className) like lower('"
					+ className + "%')");
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
	
	/**
	 * checkObjectExists method is used to check if any objects exists for the given class name
	 * 
	 * @author gurupavan
	 * 
	 * @ returns Boolean 
	 * @throws DataRetrievalException
	 */
	public boolean checkObjectExists(String className)
			throws DataRetrievalException {

		boolean flag = false;
		Session session = sessionFactory.openSession();
		List<ObjectPojo> objectList = new LinkedList<ObjectPojo>();
		try {
			String query = "select objectObj from ObjectPojo objectObj where objectObj.classId in ("
					+ "select classObj.classId from ClassPojo classObj where lower(classObj.className) = lower('"
					+ className + "'))";

			Query classObject = session.createQuery(query);
			objectList = classObject.list();
			if (objectList.size() > 0) {
				flag = true;
			}
		} catch (HibernateException hibernateException) {
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVAL_EXCEPTION, hibernateException);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION,
					exception);
		} finally {
			session.close();
		}
		return flag;
	}
	
	
	/**
	 * deleteClassDetails method is used to delete all the class details based on class id
	 * 
	 * @author gurupavan
	 * 
	 * @ returns Integer based on deletion 
	 * @throws DataDeletionException
	 */
	public Integer deleteClassDetails(Integer classId, String predicateName)
			throws DataDeletionException {

		Integer flag = 0;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			StringBuffer query = new StringBuffer(
					"select predicate from Predicate predicate where predicate.predicateName = '");
			query.append(predicateName);
			query.append("' and predicate.classObject.classId = ");
			query.append(classId);
			Predicate predicate = (Predicate) session.createQuery(
					query.toString()).uniqueResult();
			if (predicate.getPredicateId() != null) {
				session.delete(predicate);
				if (true) {
					flag = 1;
				}
			}
			transaction.commit();
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			throw new DataDeletionException(Constants.DATA_DELETION_EXCEPTION);
		} catch (Exception exception) {
			throw new DataDeletionException(Constants.DATA_DELETION_EXCEPTION,
					exception);
		} finally {
			session.close();
		}
		return flag;
	}
	
	
	/**
	 * addClassParagraph method is used to add new class paragraph for the give class
	 * 
	 * @author gurupavan
	 * 
	 * @ returns Integer based on insertion 
	 * @throws DataInsertionException
	 */
	public Integer addClassParagraph(ClassDTO classDTO)
			throws DataInsertionException {
		Integer flag = 0;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			ClassDescription classDescription = new ClassDescription();
			ClassPojo classPojo = new ClassPojo();
			classPojo.setClassId(classDTO.getSubClassId());
			classPojo.setClassName(classDTO.getClassName());
			classDescription.setClassPojo(classPojo);
			classDescription.setText(classDTO.getClassParagraph());
			classDescription.setStyle(classDTO.getParagraphStyle());
			transaction = session.beginTransaction();
			flag = (Integer) session.save(classDescription);
			transaction.commit();
			session.flush();
		} catch (HibernateException hibernateException) {
			hibernateException.printStackTrace();
			transaction.rollback();
			throw new DataInsertionException(
					Constants.DATA_INSERTION_EXCEPTION, hibernateException);
		} catch (Exception exception) {
			throw new DataInsertionException(
					Constants.DATA_INSERTION_EXCEPTION, exception);
		} finally {
			session.close();
		}
		return flag;
	}
	
	/**
	 * getClassParagraphs method is used to get all the class paragraphs based on class id 
	 *
	 * @author gurupavan
	 * 
	 * @ returns List of all the paragraphs of that class
	 * @throws DataRetrievalException
	 */
	public List<ClassDescription> getClassParagraphs(Integer classId)
			throws DataRetrievalException {

		Session session = sessionFactory.openSession();
		List<ClassDescription> classDescriptionList = new LinkedList<ClassDescription>();
		try {
			String query = "select classDescription from ClassDescription classDescription where classDescription.classPojo.classId="
					+ classId + " order by classDescription.classTextId";
			Query classDescriptionQuery = session.createQuery(query);
			classDescriptionList = classDescriptionQuery.list();
		} catch (HibernateException hibernateException) {
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVAL_EXCEPTION, hibernateException);
		} catch (Exception exception) {
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVAL_EXCEPTION, exception);
		} finally {
			session.close();
		}
		return classDescriptionList;
	}
	
	/**
	 * editClassParagraph method is used to edit the selected class paragraph based on the paragraph id
	 *
	 * @author gurupavan
	 * 
	 * @ returns Integer based on editing the class paragraph
	 * @throws DataUpdationException
	 */
	public Integer editClassParagraph(ClassDTO classDTO)
			throws DataUpdationException {
		Integer flag = 0;
		Session session = sessionFactory.openSession();
		Integer classParagraphId = classDTO.getClassParagraphs().keySet()
				.iterator().next();
		Transaction transaction = null;
		try {
			ClassDescription classDescription = new ClassDescription();
			ClassPojo classPojo = new ClassPojo();
			classPojo.setClassId(classDTO.getSubClassId());
			classPojo.setClassName(classDTO.getClassName());
			classDescription.setClassPojo(classPojo);
			classDescription.setText(classDTO.getClassParagraph());
			classDescription.setClassTextId(classDTO.getClassParagraphs()
					.keySet().iterator().next());
			classDescription.setStyle(classDTO.getParagraphStyle());
			transaction = session.beginTransaction();
			session.update(classDescription);
			transaction.commit();
			flag = 1;
		} catch (HibernateException hibernateException) {
			flag = 0;
			transaction.rollback();
			throw new DataUpdationException(Constants.DATA_UPDATE_EXCEPTION,
					hibernateException);
		} catch (Exception exception) {
			throw new DataUpdationException(Constants.DATA_UPDATE_EXCEPTION,
					exception);
		}
		return flag;
	}
	
	/**
	 * editClassName method is used to edit the class name of the selected class in edit mode
	 *
	 * @author gurupavan
	 * 
	 * @ returns Integer based on editing the class name
	 * @throws DataUpdationException
	 */
	public Integer editClassName(ClassDTO classDTO)
			throws DataUpdationException {
		Integer flag = 0;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			ClassPojo classPojo = null;
			classPojo = (ClassPojo) session
					.createQuery(
							"select classPojo from ClassPojo classPojo "
									+ "left join fetch classPojo.objects objectPojo "
									+ "left join fetch classPojo.subClassInheritances subIn "
									+ "left join fetch classPojo.superClassInheritances superIn "
									+ "left join fetch classPojo.predicates predicate "
									+ "where classPojo.classId="
									+ classDTO.getSubClassId()).uniqueResult();
			classPojo.setClassName(classDTO.getClassName());
			session.update(classPojo);
			transaction.commit();
			flag = 1;
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			flag = 0;
			throw new DataUpdationException(Constants.DATA_UPDATE_EXCEPTION,
					hibernateException);
		} catch (Exception exception) {
			throw new DataUpdationException(Constants.DATA_UPDATE_EXCEPTION,
					exception);
		}
		return flag;
	}
	
	/**
	 * checkClassExists method is used to check the class exists or not based on classDTO details
	 *
	 * @author gurupavan
	 * 
	 * @ returns boolean based on retrieval 
	 * @throws DataRetrievalException
	 */
	public boolean checkClassExists(ClassDTO classDTO)
			throws DataRetrievalException {
		List<ClassPojo> classList = new LinkedList<ClassPojo>();
		boolean classExistsFlag = false;
		Session session = sessionFactory.openSession();
		try {
			String checkClassQuery = "select classObj from ClassPojo classObj where LOWER(classObj.className)=LOWER('"
					+ classDTO.getClassName() + "')";
			Query checkQuery = session.createQuery(checkClassQuery);
			classList = checkQuery.list();
			if (classList.size() > 0) {
				classExistsFlag = true;
			} else {
				classExistsFlag = false;
			}
		} catch (HibernateException hibernateException) {
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVAL_EXCEPTION, hibernateException);
		} catch (Exception exception) {
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVAL_EXCEPTION, exception);
		}
		return classExistsFlag;
	}
	
	/**
	 * checkSuperClassExists method is used to check any super class exits or not based on classDTO details
	 *
	 * @author gurupavan
	 * 
	 * @ returns boolean based on retrieval 
	 * @throws DataRetrievalException
	 */
	public boolean checkSuperClassExists(ClassDTO classDTO)
	throws DataRetrievalException {
		List<ClassPojo> classList = new LinkedList<ClassPojo>();
		boolean superClassExistsFlag = false;
		Session session = sessionFactory.openSession();
		try {
			String checkClassQuery = "select classObj from ClassPojo classObj where LOWER(classObj.className)=LOWER('"
					+ classDTO.getIsA() + "')";
			Query checkQuery = session.createQuery(checkClassQuery);
			classList = checkQuery.list();
			if (classList.size() > 0) {
				superClassExistsFlag = true;
			} else {
				superClassExistsFlag = false;
			}
		} catch (HibernateException hibernateException) {
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVAL_EXCEPTION, hibernateException);
		} catch (Exception exception) {
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVAL_EXCEPTION, exception);
		}
		return superClassExistsFlag;
	}
	
	/**
	 * getClassName method is used to get the class name based on classDTO details
	 *
	 * @author gurupavan
	 * 
	 * @ returns String the class name based on retrieval 
	 * @throws DataRetrievalException
	 */
	public String getClassName(ClassDTO classDTO) throws DataRetrievalException {
		String className = "";
		Session session = sessionFactory.openSession();
		List<ClassPojo> classList = new LinkedList<ClassPojo>();
		try {
			String query = "select classObj from ClassPojo classObj where classObj.classId="
					+ classDTO.getSubClassId();
			Query q = session.createQuery(query);
			classList = q.list();
			if (classList.size() != 0) {
				className = classList.get(0).getClassName();
			}
		} catch (HibernateException hibernateException) {
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVAL_EXCEPTION, hibernateException);
		} catch (Exception exception) {
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVAL_EXCEPTION, exception);
		}
		return className;
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

}
