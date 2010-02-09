package com.delegate;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dto.HomeDTO;
import com.dto.ObjectDTO;
import com.exceptions.DataRetrievalException;

public interface SearchDelegate {
	public List getAllObjects(HomeDTO homeDTO)throws DataRetrievalException;
	public List getAllClasses(HomeDTO homeDTO)throws DataRetrievalException;
	
}
