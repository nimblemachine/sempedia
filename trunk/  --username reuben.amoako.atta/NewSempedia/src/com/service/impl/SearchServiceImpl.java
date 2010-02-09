/**
 * 
 */
package com.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.dao.CreateObjectDAO;
import com.dao.CreateClassDAO;
import com.dao.PredicateDAO;
import com.dto.HomeDTO;
import com.exceptions.DataRetrievalException;
import com.service.SearchService;
import com.util.Constants;

/**
 * @author tauseefsyed
 *
 */
public class SearchServiceImpl implements SearchService {
	

	private CreateObjectDAO createObjectDAO;
	private CreateClassDAO createClassDAO;
	private PredicateDAO predicateDAO;
	

	

	/**
	 * @return the createObjectDAO
	 */
	public CreateObjectDAO getCreateObjectDAO() {
		return createObjectDAO;
	}
	/**
	 * @param createObjectDAO the createObjectDAO to set
	 */
	public void setCreateObjectDAO(CreateObjectDAO createObjectDAO) {
		
		this.createObjectDAO = createObjectDAO;
	}
	/**
	 * @return the predicateDAO
	 */
	public PredicateDAO getPredicateDAO() {
		return predicateDAO;
	}
	/**
	 * @param predicateDAO the predicateDAO to set
	 */
	public void setPredicateDAO(PredicateDAO predicateDAO) {
		this.predicateDAO = predicateDAO;
	}
	public List getAllObjects(HomeDTO homeDTO) throws DataRetrievalException{
		
		List result=new ArrayList();
		try{
		result = createObjectDAO.getAllObjects(homeDTO.getObjectNameSearch());
		
		}
		catch(Exception e){
			throw new DataRetrievalException(Constants.DATA_RETRIEVAL_EXCEPTION);
		}
		return result;
	}
	
	public List getAllClasses(HomeDTO homeDTO) throws DataRetrievalException{
		
		List result=new ArrayList();
		try{
		result = createClassDAO.getAllClasses(homeDTO.getObjectNameSearch());
		
		}
		catch(Exception e){
			throw new DataRetrievalException(Constants.DATA_RETRIEVAL_EXCEPTION);
		}
		return result;
	}
	

}
