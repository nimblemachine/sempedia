package com.service;

import java.util.List;
import java.util.Set;

import com.dto.HomeDTO;
import com.exceptions.DataRetrievalException;



public interface SearchService {
	public List getAllObjects(HomeDTO homeDTO)throws DataRetrievalException;
	public List getAllClasses(HomeDTO homeDTO)throws DataRetrievalException;
	
}
