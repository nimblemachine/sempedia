package com.controller;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.delegate.SearchDelegateAjax;

public class TriplePredicateController implements Controller
{
	  protected final Log logger;
	  private SearchDelegateAjax searchDelegateAjax;
	  /**
	 * @return the searchDelegateAjax
	 */
	public SearchDelegateAjax getSearchDelegateAjax() {
		return searchDelegateAjax;
	}

	/**
	 * @param searchDelegateAjax the searchDelegateAjax to set
	 */
	public void setSearchDelegateAjax(SearchDelegateAjax searchDelegateAjax) {
		
		this.searchDelegateAjax = searchDelegateAjax;
	}

	

	public TriplePredicateController()
	  {
		
	    this.logger = LogFactory.getLog(super.getClass());
	  }

	  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException
	  {
		 
		  String triplePredicate=request.getParameter("predicateId");
		  String tripleSubject=request.getParameter("subjectId");
		  //System.out.println("pidic"+triplePredicate);
		  //System.out.println("subj"+tripleSubject);
		  //String ptid=request.getParameter("ptid");
		  
		  Hashtable ht = new Hashtable();
	    Map model = new HashMap();
	    int subjectCount=0;
	   
	    try{
	    	   
	   model=searchDelegateAjax.getObjectsForPredicate(triplePredicate, tripleSubject);
	   Set contactValues = model.entrySet();
	   Iterator contactIterator = contactValues.iterator();
	   	
	   	subjectCount=((String[])((Map.Entry)contactIterator.next()).getValue()).length;
	   while (contactIterator.hasNext())
	   {
	       Map.Entry anEntry = (Map.Entry)contactIterator.next();
	       String name = (String)anEntry.getKey(); 
	       String[] names = (String[])anEntry.getValue(); 
	    
	   }
	    }
	    catch(Exception exception){
	    	model.put("exceptionMessage",exception.getMessage());
	    	
	    }
	  
	    	// model.put("sendOne", triplePredicate);
	  	   model.put("triplecount", subjectCount);
	  //	 model.put("ptid", ptid);
	  	   
	  	 return new ModelAndView("json1", model);
	  }

}
