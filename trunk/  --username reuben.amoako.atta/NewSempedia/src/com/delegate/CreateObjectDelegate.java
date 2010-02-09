package com.delegate;

import java.util.List;
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
public interface CreateObjectDelegate {
	/**
	 * insertObject method is used to insert a new object.
	 * 
	 * @param objectDTO
	 * @return Object Name
	 * @return Super class name
	 * @return Predicate Names of all super classes
	 * @throws DataInsertionException
	 * @throws DataRetrievalException
	 */
	public List<Map<String, String>> insertObject(ObjectDTO objectDTO)
			throws DataInsertionException, DataRetrievalException;

	/**
	 * editObject method is retrieve an details of an Object.
	 * 
	 * @param objectDTO
	 * @return Object Name
	 * @return Super class name
	 * @return Predicate Names of all super classes
	 * @return Predicate Values of an Object
	 * @throws DataRetrievalException
	 */
	public List editObject(ObjectDTO objectDTO) throws DataRetrievalException;

	/**
	 * saveObjectDetailsToTriple method is used to insert details of an object.
	 * 
	 * 
	 * @param predicatesKeyValueMap
	 * @return Sequence of the newly inserted record
	 * @throws DataInsertionException
	 * @throws DataRetrievalException
	 */
	public Integer saveObjectDetailsToTriple(Map predicatesKeyValueMap)
			throws DataInsertionException, DataRetrievalException;

	/**
	 * updateObjectDetailsToTriple method is used to update the details of an Object.
	 * 
	 * @param predicatesKeyValueMap
	 * @return Sequence of the updated record
	 * @throws DataInsertionException
	 * @throws DataRetrievalException
	 * @throws DataUpdationException
	 * @throws DataDeletionException
	 */
	public Integer updateObjectDetailsToTriple(Map predicatesKeyValueMap)
			throws DataInsertionException, DataRetrievalException,
			DataUpdationException, DataDeletionException;

	/**
	 * saveNewProperty method is used to insert the new Predicate Name and its value for an Object
	 * 
	 * 
	 * @param predicateName
	 * @param predicateValue
	 * @param objectName
	 * @param className
	 * @return Sequence of the newly inserted Predicate and Predicate value
	 * @throws DataInsertionException
	 * @throws DataRetrievalException
	 */
	public Integer saveNewProperty(String predicateName, String predicateValue,
			String objectName, String className) throws DataInsertionException,
			DataRetrievalException;

	/**
	 * @param objectDescDTO
	 * @return 
	 * @throws DataInsertionException
	 */
	public String saveObjectImage(ObjectDescriptionDTO objectDescDTO)
			throws DataInsertionException;

	/**
	 * @param objectDescId
	 * @return
	 * @throws DataRetrievalException
	 */
	public ObjectDescriptionDTO readObjectImage(String objectDescId)
			throws DataRetrievalException;

	public TripleDTO UpdatePredicateValue(TripleDTO tripleDTO) throws DataRetrievalException, DataInsertionException,DataUpdationException;

	public String updateObjectName(ObjectDTO objectDTO) throws DataRetrievalException, DataUpdationException;

	public Integer getObjectByName(String objectName)throws DataRetrievalException;

}
