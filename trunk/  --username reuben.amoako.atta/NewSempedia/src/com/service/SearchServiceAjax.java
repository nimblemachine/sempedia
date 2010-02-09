package com.service;

import java.util.Map;

import com.exceptions.DataRetrievalException;



public interface SearchServiceAjax {
	
	public Map getPredicatesForObject(String subjectId)throws DataRetrievalException;
	
	public Map getObjectsForPredicate(String predicateId,String subjectId)throws DataRetrievalException;
}
