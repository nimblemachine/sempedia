/**
 * 
 */
package com.controller;

import java.util.List;
import com.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.beans.HomeBean;
import com.delegate.SearchDelegate;
import com.dto.HomeDTO;


public class SempediaHomeController extends MultiActionController 
{
	private SearchDelegate searchDelegate;
	
	
	/**
	 * @return the searchDelegate
	 */
	public SearchDelegate getSearchDelegate() {
		return searchDelegate;
	}


	/**
	 * @param searchDelegate the searchDelegate to set
	 */
	public void setSearchDelegate(SearchDelegate searchDelegate) {
		this.searchDelegate = searchDelegate;
	}


	public ModelAndView searchObjSemp(HttpServletRequest request,HttpServletResponse response,HttpSession session, HomeBean command) throws ServletException
	{
	
	
		HomeBean objectBean = (HomeBean) command;
		ModelAndView modelAndViewObj = new ModelAndView("sempediaHome");
		try{
			String message="";

			if(objectBean.getObjectNameSearch() !=null )
			{
				HomeBean homeBean=(HomeBean)command;
				HomeDTO homeDTO=new HomeDTO();
				

				homeDTO.setObjectNameSearch(homeBean.getObjectNameSearch().trim());
				

				String searchItem=homeBean.getObjectNameSearch();
				List objectSet= searchDelegate.getAllObjects(homeDTO);

				
				if(objectSet.size() == 0){
					message=Constants.SEARCH_NO_RESULT+homeBean.getObjectNameSearch();
				}
				if(objectSet.size() == 1){
					message=Constants.SEARCH_ONE_RESULT+homeBean.getObjectNameSearch();
				}
				else{
					message=objectSet.size()+Constants.SEARCH_MANY_RESULT+homeBean.getObjectNameSearch();
				}
				modelAndViewObj.addObject("classesList", objectSet);
				modelAndViewObj.addObject("message",message);
				modelAndViewObj.addObject("sendOne",searchItem);
			}
			else{
				modelAndViewObj.addObject("message",message);
			}
			
		}
		catch(Exception exception){
			modelAndViewObj.addObject("exceptionMessage",exception.getMessage());
		}
		return modelAndViewObj;
	}
	public ModelAndView searchSeedObjSemp(HttpServletRequest request,HttpServletResponse response,HttpSession session, HomeBean command) throws ServletException 
	{
	
		
		HomeBean objectBean = (HomeBean) command;
		
		ModelAndView modelAndViewObj = new ModelAndView("sempediaHome");
		try{
			
		
		String message=null;
		if(objectBean.getObjectNameSearch() !=null )
		{
		HomeBean homeBean=(HomeBean)command;
		HomeDTO homeDTO=new HomeDTO();
		homeDTO.setObjectNameSearch(homeBean.getObjectNameSearch().trim());
		
		String searchItem=homeBean.getObjectNameSearch();
		
		
		List objectSet= searchDelegate.getAllObjects(homeDTO);
		//List objectSet= searchDelegate.getAllClasses(homeDTO);
		
		
		if(objectSet.size() == 0){
			message=Constants.SEARCH_NO_RESULT+homeBean.getObjectNameSearch();
		}
		if(objectSet.size() == 1){
			message=Constants.SEARCH_ONE_RESULT+homeBean.getObjectNameSearch();
		}
		else{
			message=objectSet.size()+Constants.SEARCH_MANY_RESULT+homeBean.getObjectNameSearch();
		}
		modelAndViewObj.addObject("classesList", objectSet);
		modelAndViewObj.addObject("message",message);
		modelAndViewObj.addObject("sendOne",searchItem);
		}
		}
		catch(Exception exception){
			modelAndViewObj.addObject("exceptionMessage",exception.getMessage());
		}
		return modelAndViewObj;
	}
	

}
