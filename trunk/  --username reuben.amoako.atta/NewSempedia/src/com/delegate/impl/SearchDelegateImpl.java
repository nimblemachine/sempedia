package com.delegate.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.delegate.SearchDelegate;
import com.dto.HomeDTO;
import com.exceptions.DataRetrievalException;
import com.service.SearchService;

public class SearchDelegateImpl implements SearchDelegate {
	private SearchService searchService;
	protected final Log logger;
	public SearchDelegateImpl() {
		
		    this.logger = LogFactory.getLog(super.getClass());
	}

	
	
public List getAllObjects(HomeDTO homeDTO)throws DataRetrievalException {
		
		
		List result = searchService.getAllObjects(homeDTO);
		
		return result;
	}

public List getAllClasses(HomeDTO homeDTO)throws DataRetrievalException {
	
	
	List result = searchService.getAllClasses(homeDTO);
	
	return result;
}







/**
 * @return the searchService
 */
public SearchService getSearchService() {
	return searchService;
}



/**
 * @param searchService the searchService to set
 */
public void setSearchService(SearchService searchService) {
	
	this.searchService = searchService;
}
	

}
