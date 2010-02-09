package com.dao;

import java.util.List;
import java.util.Set;

import com.dto.ClassDTO;
import com.exceptions.DataRetrievalException;

/**
 * Contains methods for create, update, delete Predicates and basic search 
 * functionality methods.
 * 
 * @author naveenK
 */
public interface PredicateDAOAjax {
	
	/**
	 * @param subjectName
	 * @return
	 */
	public List getPredicatesForObject(String subjectName)throws DataRetrievalException; //throws DataFetchException;
	public List getObjectsForPredicate(String predicateId,String subjectId) throws DataRetrievalException;
	
}
