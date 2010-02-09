/**
 * 
 */
package com.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dao.PredicateDAOAjax;
import com.exceptions.DataRetrievalException;
import com.hibernate.pojo.ObjectPojo;
import com.hibernate.pojo.Predicate;
import com.hibernate.pojo.Triple;
import com.service.SearchServiceAjax;
import com.util.Constants;

/**
 * @author tauseefsyed
 *
 */
public class SearchServiceAjaxImpl implements SearchServiceAjax {
	

	
	private PredicateDAOAjax predicateDAOAjax;
	protected final Log logger;

	public SearchServiceAjaxImpl() {
		 this.logger = LogFactory.getLog(super.getClass());
	}
	 public static List removeDuplicate(List arlList)
	  {
	   HashSet h = new LinkedHashSet(arlList);
	   arlList.clear();
	   arlList.addAll(h);
	   return arlList;
	  }
	public Map getPredicatesForObject(String subjectId)throws DataRetrievalException{
		
		Map resultAfterProcess=new HashMap();
		try{
		List result = new LinkedList();
			result=predicateDAOAjax.getPredicatesForObject(subjectId);
		
		
		
		String[] pidics=new String[result.size()];
		String[] pidicIds=new String[result.size()];
	   
	    if(result.size()>0){
	    	result=removeDuplicate(result);
	    	
	    	pidics=new String[result.size()];
	    	pidicIds=new String[result.size()];
	    	for(int i=0;i<result.size();i++){
	    		Predicate predicate=(Predicate)result.get(i);
	    		Iterator it = predicate.getTriples().iterator();
	    	    
	    	        Triple triple = (Triple)it.next();
	    	       
		    		pidics[i]=triple.getPredicate().getPredicateName();
		    		pidicIds[i]=triple.getPredicate().getPredicateId()+"";
		    		
	    	}
	    }
	    resultAfterProcess.put("pidic", pidics);
	    resultAfterProcess.put("pidicIds", pidicIds);
	  
		}
		catch(Exception e){
			String[] pidics=new String[0];
			String[] pidicIds=new String[0];
			resultAfterProcess.put("pidic", pidics);
		    resultAfterProcess.put("pidicIds", pidicIds);
			throw new DataRetrievalException(Constants.DATA_RETRIEVAL_EXCEPTION);
		}
		return resultAfterProcess;
	}
	
	public Map getObjectsForPredicate(String predicateId, String subjectId)
			throws DataRetrievalException {
		Map resultAfterProcess=new HashMap();
		try{
		List result = predicateDAOAjax.getObjectsForPredicate(predicateId, subjectId);
		
	
	    String objs[]=new String[result.size()];
	  String objsIds[]=new String[result.size()];
	    if(result.size()>0){
	    	int i=0;
	    		
	    		
	    		Iterator it = result.iterator();
	    	    while(it.hasNext()){
	    	    	Triple triple = (Triple)it.next();
	    	    	ObjectPojo objectPojo=(ObjectPojo)triple.getObject();
	    	    	objs[i]=objectPojo.getObjectName();
		    		objsIds[i]=objectPojo.getObjectId()+"";
		    		i++;
	    	    }
	    	        
	    	
	    }
	  
	    resultAfterProcess.put("objct", objs);
	    resultAfterProcess.put("objctId", objsIds);
	    
	
		}
		catch(Exception e){
			String[] objs=new String[0];
			String[] objsIds=new String[0];
			resultAfterProcess.put("objct", objs);
		    resultAfterProcess.put("objctId", objsIds);
			throw new DataRetrievalException(Constants.DATA_RETRIEVAL_EXCEPTION);
		}
		return resultAfterProcess;
	}
	
	/**
	 * @return the predicateDAOAjax
	 */
	public PredicateDAOAjax getPredicateDAOAjax() {
		return predicateDAOAjax;
	}

	/**
	 * @param predicateDAOAjax the predicateDAOAjax to set
	 */
	public void setPredicateDAOAjax(PredicateDAOAjax predicateDAOAjax) {
		this.predicateDAOAjax = predicateDAOAjax;
	}

}
