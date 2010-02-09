package com.controller;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.delegate.SearchDelegateAjax;

public class TripleSubjectController implements Controller
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

	

	public TripleSubjectController()
	  {
		
	    this.logger = LogFactory.getLog(super.getClass());
	  }

	  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException
	  {
		 
		  String tripleSubject=request.getParameter("triplename");
		 // String tid=request.getParameter("tid");
		 
		  Hashtable ht = new Hashtable();
	    Map model = new HashMap();
	    int subjectCount=0;
	   
	    try{
	   
	   //debugStatement
	   System.out.println("Assert triple Subject: " + tripleSubject);
	   //debugStatement end
	   
	   model=searchDelegateAjax.getPredicatesForObject(tripleSubject);
	   Set contactValues = model.entrySet();
	   Iterator contactIterator = contactValues.iterator();
	   	
	   	subjectCount=((String[])((Map.Entry)contactIterator.next()).getValue()).length;
	   while (contactIterator.hasNext())
	   {
	       Map.Entry anEntry = (Map.Entry)contactIterator.next(); // Line A
	       String name = (String)anEntry.getKey(); // Line B
	       String[] names = (String[])anEntry.getValue(); // Line C
	    
	   }
	    }
	    catch(Exception exception){
	    	model.put("exceptionMessage",exception.getMessage());
	    	
	    }
	  
	    	// model.put("sendOne", tripleSubject);
	  	   model.put("triplecount", subjectCount);
	  	// model.put("tid", tid);
	  	   
	  	 return new ModelAndView("json1", model);
	  }

}
