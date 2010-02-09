package com.delegate.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.delegate.SearchDelegateAjax;
import com.exceptions.DataRetrievalException;
import com.service.SearchServiceAjax;

public class SearchDelegateAjaxImpl implements SearchDelegateAjax {
	private SearchServiceAjax searchServiceAjax; 
	protected final Log logger;
	public SearchDelegateAjaxImpl() {
		 //System.out.println("SearchDelegateImpl ");
		    this.logger = LogFactory.getLog(super.getClass());
	}


public Map getPredicatesForObject(String subjectId)throws DataRetrievalException{
	//System.out.println("get triples DelegateImpl......");
	Map result = searchServiceAjax.getPredicatesForObject(subjectId);
	//System.out.println("im in after service impl : ");
	return result;
}

public Map getObjectsForPredicate(String predicateId, String subjectId)
		throws DataRetrievalException {
	Map result = searchServiceAjax.getObjectsForPredicate(predicateId,subjectId);
	//System.out.println("im in after service impl : ");
	return result;
}

	/**
	 * @return the searchServiceAjax
	 */
	public SearchServiceAjax getSearchServiceAjax() {
		return searchServiceAjax;
	}

	/**
	 * @param searchServiceAjax the searchServiceAjax to set
	 */
	public void setSearchServiceAjax(SearchServiceAjax searchServiceAjax) {
		this.searchServiceAjax = searchServiceAjax;
	}
	

}
