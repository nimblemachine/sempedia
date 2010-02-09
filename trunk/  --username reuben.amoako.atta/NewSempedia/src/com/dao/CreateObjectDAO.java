package com.dao;

import java.util.List;
import java.util.Map;

import com.dto.ObjectDTO;
import com.exceptions.DataInsertionException;
import com.exceptions.DataRetrievalException;
import com.exceptions.DataUpdationException;
import com.hibernate.pojo.ClassPojo;
import com.hibernate.pojo.ObjectDescription;
import com.hibernate.pojo.ObjectPojo;
import com.hibernate.pojo.Predicate;

/**
 * @author viswanathp
 * 
 */
public interface CreateObjectDAO {
	/**
	 * insertObject method is used to insert new Object.
	 * 
	 * @param objecPojo
	 * @return
	 * @throws DataInsertionException
	 */
	public Integer insertObject(ObjectPojo objecPojo)
			throws DataInsertionException;

	/**
	 * getPredicatesOfClass method is used to retrieve the Predicates of class
	 * 
	 * 
	 * @param classId
	 * @return
	 * @throws DataRetrievalException
	 */
	public List getPredicatesOfClass(Integer classId)
			throws DataRetrievalException;

	/**
	 * getClassIdByName method is used to retrieve the class id using className as its input
	 * 
	 * 
	 * @param className
	 * @return
	 * @throws DataRetrievalException
	 */
	public Integer getClassIdByName(String className)
			throws DataRetrievalException;

	/**
	 * getSuperClassId method is used to retireve the Super Class Id 
	 * 
	 * @param classId
	 * @return
	 * @throws DataRetrievalException
	 */
	public Integer getSuperClassId(Integer classId)
			throws DataRetrievalException;

	/**
	 * @param objectName
	 * @return
	 * @throws DataRetrievalException
	 */
	public List getAllObjects(String objectName) throws DataRetrievalException;

	/**
	 * @param objectName
	 * @param classId
	 * @return
	 * @throws DataInsertionException
	 */
	public Integer saveObjectDetails(String objectName, Integer classId)
			throws DataInsertionException;

	/**
	 * @param objectName
	 * @return
	 * @throws DataRetrievalException
	 */
	public Boolean checkObject(String objectName) throws DataRetrievalException;

	/**
	 * @param className
	 * @return
	 * @throws DataRetrievalException
	 */
	public ClassPojo getClass(String className) throws DataRetrievalException;

	/**
	 * @param objectName
	 * @return
	 * @throws DataRetrievalException
	 */
	public ObjectPojo getObject(String objectName)
			throws DataRetrievalException;


	/**
	 * @param objectName
	 * @return
	 * @throws DataRetrievalException
	 */
	public String getClassName(String objectName) throws DataRetrievalException;

	/**
	 * @param objectDescription
	 * @return
	 * @throws DataInsertionException
	 */
	public String saveObjectImage(ObjectDescription objectDescription)
			throws DataInsertionException;

	/**
	 * @param objectDescId
	 * @return
	 * @throws DataRetrievalException
	 */
	public ObjectDescription readObjectImage(Integer objectDescId)
			throws DataRetrievalException;

	/**
	 * @param objectName
	 * @return
	 * @throws DataRetrievalException
	 */
	public ObjectPojo getObjectDetails(String objectName)
			throws DataRetrievalException;

	/**
	 * @param objDescId
	 * @return
	 * @throws DataRetrievalException
	 */
	public ObjectDescription readObjectDescription(Integer objDescId)
			throws DataRetrievalException;

	public String updateObjectName(ObjectPojo objectPojo) throws DataUpdationException;

	public ObjectPojo getObjectById(ObjectPojo objectPojo)throws DataRetrievalException;

	
}
