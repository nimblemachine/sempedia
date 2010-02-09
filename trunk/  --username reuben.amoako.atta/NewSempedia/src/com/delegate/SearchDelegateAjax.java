package com.delegate;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dto.HomeDTO;
import com.dto.ObjectDTO;
import com.exceptions.DataRetrievalException;

public interface SearchDelegateAjax {
	
	public Map getPredicatesForObject(String subjectId)throws DataRetrievalException;
	
	public Map getObjectsForPredicate(String predicateId,String subjectId)throws DataRetrievalException;
}
