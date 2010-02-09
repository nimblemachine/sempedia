package com.delegate.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.delegate.CreateObjectDelegate;
import com.dto.ObjectDTO;
import com.dto.ObjectDescriptionDTO;
import com.dto.TripleDTO;
import com.exceptions.DataDeletionException;
import com.exceptions.DataInsertionException;
import com.exceptions.DataRetrievalException;
import com.exceptions.DataUpdationException;
import com.service.CreateObjectService;
import com.util.Constants;

/**
 * @author viswanathp
 *
 */
public class CreateObjectDelegateImpl implements CreateObjectDelegate {
	private CreateObjectService createObjectService;

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
	public List<Map<String,String>> insertObject(ObjectDTO objectDTO)
			throws DataInsertionException, DataRetrievalException {
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		String className = null;
		Map objectMap = null;
		Map predicatesMap = null;
		try
		{
			objectMap = createObjectService.insertObject(objectDTO);
			if (objectMap != null) {
				Iterator objectMapIterator = objectMap.entrySet().iterator();
				while (objectMapIterator.hasNext()) {
					Map.Entry pairs = (Map.Entry) objectMapIterator.next();
					if (pairs.getKey().toString() != "message" && pairs.getKey() != "color") {
						className = (String) pairs.getValue();
					}
				}
			}
			if (className != null) {
				predicatesMap = createObjectService
						.getAllPredicatesOfClass(className);
			}
		}catch(DataInsertionException die)
		{
			throw new DataInsertionException(Constants.DATA_INSERT_EXCEPTION);
		}catch(DataRetrievalException dre)
		{
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION);
		}
		result.add(0, objectMap);
		result.add(1, predicatesMap);
		return result;
	}


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
	public List editObject(ObjectDTO objectDTO) throws DataRetrievalException {
		List result = new ArrayList();
		String className = null;
		String objectName = null;
		Map objectMap = null;
		Map predicatesMap = null;
		try
		{
			objectMap = createObjectService.editObject(objectDTO);			
			if (objectMap != null) {
				Iterator objectMapIterator = objectMap.entrySet().iterator();
				while (objectMapIterator.hasNext()) {
					Map.Entry pairs = (Map.Entry) objectMapIterator.next();
					if (pairs.getKey().toString() != "message" && pairs.getKey() != "color") {
						objectName = (String) pairs.getKey();
					}
				}
			}
			if (objectName != null) {
				predicatesMap = createObjectService
						.getAllPredicatesAndValuesFromTriple(objectName);
			}
		}catch(DataRetrievalException dre)
		{
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION);
		}
		result.add(0, objectMap);
		result.add(1, predicatesMap);
		return result;
	}

	/**
	 * saveObjectDetailsToTriple method is used to insert details of an object.
	 * 
	 * 
	 * @param predicatesKeyValueMap
	 * @return Sequence of the newly inserted record
	 * @throws DataInsertionException
	 * @throws DataRetrievalException
	 */
	public Integer saveObjectDetailsToTriple(Map tripleDataMap) throws DataInsertionException,DataRetrievalException{
		Integer saveResult = 0;
		try
		{
			saveResult = createObjectService
					.saveObjectDetailsToTriple(tripleDataMap);
		}catch(DataInsertionException die)
		{
			throw new DataInsertionException(Constants.DATA_INSERT_EXCEPTION);
		}catch(DataRetrievalException dre)
		{
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION);
		}
		return saveResult;
	}

	
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
	public Integer updateObjectDetailsToTriple(Map predicatesKeyValueMap) throws DataInsertionException,DataRetrievalException,DataUpdationException,DataDeletionException {
		Integer updateResult = 0;
		try
		{
			updateResult = createObjectService
					.updateObjectDetailsToTriple(predicatesKeyValueMap);
		}catch(DataInsertionException die)
		{
			throw new DataInsertionException(Constants.DATA_INSERT_EXCEPTION);
		}catch(DataRetrievalException dre)
		{
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION);
		}catch(DataUpdationException due)
		{
			throw new DataUpdationException(Constants.DATA_UPDATE_EXCEPTION);
		}catch(DataDeletionException dde)
		{
			throw new DataDeletionException(Constants.DATA_DELETE_EXCEPTION);
		}
		
		return updateResult;
	}

	
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
	public Integer saveNewProperty(String predicateName,String predicateValue,String objectName, String className) throws DataInsertionException,DataRetrievalException{
		Integer saveResult = 0;
		try
		{
			saveResult = createObjectService.saveNewProperty(predicateName,predicateValue,objectName,className);
		}catch(DataInsertionException die)
		{
			throw new DataInsertionException(Constants.DATA_INSERT_EXCEPTION);
		}catch(DataRetrievalException dre)
		{
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION);
		}
		return saveResult;
	}
	public String saveObjectImage (ObjectDescriptionDTO objectDescriptionDTO) throws DataInsertionException{
		String status = null;
		try
		{
			status = createObjectService.saveObjectImage(objectDescriptionDTO);
		}catch(DataInsertionException die)
		{
			throw new DataInsertionException(Constants.DATA_INSERT_EXCEPTION);
		}
		return status;
	}
	
	public ObjectDescriptionDTO readObjectImage(String objectName)throws DataRetrievalException {
		ObjectDescriptionDTO objectDescriptionDTO = null;
		try
		{
			 objectDescriptionDTO = createObjectService.readObjectImage(objectName);
		}catch(DataRetrievalException dre)
		{
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION);
		}
		return objectDescriptionDTO;
	}
	
	/* (non-Javadoc)
	 * @see com.delegate.CreateObjectDelegate#UpdatePredicateValue(com.dto.TripleDTO)
	 */
	public TripleDTO UpdatePredicateValue(TripleDTO tripleDTO)throws DataRetrievalException, DataInsertionException,DataUpdationException {
		
		TripleDTO tripleD = createObjectService.updatePredicateValue(tripleDTO);
		
		return tripleD;
	}

	/**
	 * @return the createObjectService
	 */
	public CreateObjectService getCreateObjectService() {
		return createObjectService;
	}

	/**
	 * @param createObjectService
	 *            the createObjectService to set
	 */
	public void setCreateObjectService(CreateObjectService createObjectService) {
		this.createObjectService = createObjectService;
	}


	public String updateObjectName(ObjectDTO objectDTO)
			throws DataRetrievalException, DataUpdationException {
		String updatedStatus = createObjectService.updateObjectName(objectDTO);
		
		return updatedStatus;
	}


	public Integer getObjectByName(String objectName)
			throws DataRetrievalException {
		Integer objectId = createObjectService.getObjectByName(objectName);
		return objectId;
	}

	

}
