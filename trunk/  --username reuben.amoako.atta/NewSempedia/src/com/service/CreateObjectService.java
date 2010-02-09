package com.service;

import java.util.Map;

import com.dto.ObjectDTO;
import com.dto.ObjectDescriptionDTO;
import com.dto.TripleDTO;
import com.exceptions.DataDeletionException;
import com.exceptions.DataInsertionException;
import com.exceptions.DataRetrievalException;
import com.exceptions.DataUpdationException;

/**
 * @author viswanathp
 * 
 */
public interface CreateObjectService {
	/**
	 * insertObject method is used to insert a new Object.
	 * 
	 * @param objectDTO
	 * @return Object Name
	 * @return Super Class Name
	 * @return Predicate Names of all Super Classes of an Object.
	 * @throws DataInsertionException
	 * @throws DataRetrievalException
	 */
	public Map insertObject(ObjectDTO objectDTO) throws DataInsertionException,
			DataRetrievalException;

	/**
	 * editObject method is used to retrieve the details of an Object.
	 * 
	 * @param objectDTO
	 * @return Object Name
	 * @return Super Class Name
	 * @return Predicate Names of all Super Classes of an Object.
	 * @return Predicate Values of an Object.
	 * @throws DataRetrievalException
	 */
	public Map editObject(ObjectDTO objectDTO) throws DataRetrievalException;

	/**
	 * saveObjectDetailsToTriple method is used to insert the details of an Object.
	 * 
	 * @param predicatesKeyValueMap
	 * @return Sequence of a newly inserted record.
	 * @throws DataInsertionException
	 * @throws DataRetrievalException
	 */
	public Integer saveObjectDetailsToTriple(Map predicatesKeyValueMap)
			throws DataInsertionException, DataRetrievalException;

	/**
	 * updateObjectDetailsToTriple method is used to update the details of an Object.
	 * 
	 * @param predicatesKeyValueMap
	 * @return Sequence of a newly updated record.
	 * @throws DataInsertionException
	 * @throws DataRetrievalException
	 * @throws DataUpdationException
	 * @throws DataDeletionException
	 */
	public Integer updateObjectDetailsToTriple(Map predicatesKeyValueMap)
			throws DataInsertionException, DataRetrievalException,
			DataUpdationException, DataDeletionException;

	/**
	 * saveNewProperty method is used to insert a new Predicate Name to Objects super class and Predicate value
	 * to the Object.
	 * 
	 * @param predicateName
	 * @param predicateValue
	 * @param objectName
	 * @param className
	 * @return Sequence of a newly updated record.
	 * @throws DataInsertionException
	 * @throws DataRetrievalException
	 */
	public Integer saveNewProperty(String predicateName, String predicateValue,
			String objectName, String className) throws DataInsertionException,
			DataRetrievalException;

	/**
	 * getAllPredicatesOfClass method is used to retrieve all Predicate Names of a class and its Super classes.
	 * 
	 * @param className
	 * @return Predicate Names
	 * @throws DataRetrievalException
	 */
	public Map getAllPredicatesOfClass(String className)
			throws DataRetrievalException;

	/**
	 * saveObjectImage method is used to insert the image of an Object
	 * 
	 * @param objectDescriptionDTO
	 * @return 
	 * @throws DataInsertionException
	 */
	public String saveObjectImage(ObjectDescriptionDTO objectDescriptionDTO)
			throws DataInsertionException;

	/**
	 * @param objectDescId
	 * @return
	 * @throws DataRetrievalException
	 */
	public ObjectDescriptionDTO readObjectImage(String objectDescId)
			throws DataRetrievalException;

	/**
	 * getAllPredicatesAndValuesFromTriple method is used to retrieve all Predicates and Values of an Object
	 * 
	 * 
	 * @param objectName
	 * @return Predicate Names
	 * @return Predicate Values
	 * @throws DataRetrievalException
	 */
	public Map getAllPredicatesAndValuesFromTriple(String objectName)
			throws DataRetrievalException;

	public TripleDTO updatePredicateValue(TripleDTO tripleDTO) throws DataRetrievalException,DataInsertionException,DataUpdationException;

	public String updateObjectName(ObjectDTO objectDTO) throws DataRetrievalException, DataUpdationException;

	public Integer getObjectByName(String objectName)throws DataRetrievalException;

}
