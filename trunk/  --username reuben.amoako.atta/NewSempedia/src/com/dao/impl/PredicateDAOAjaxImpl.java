package com.dao.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.dao.PredicateDAOAjax;
import com.exceptions.DataRetrievalException;
import com.util.Constants;

/**
 * Contains methods for create, update, delete Predicates and basic search 
 * functionality methods.
 * 
 * @author naveenK
 */
public class PredicateDAOAjaxImpl implements PredicateDAOAjax{
	
	private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
        this.hibernateTemplate = hibernateTemplate;
    }

    public HibernateTemplate getHibernateTemplate(){
        return hibernateTemplate;
    }

	
	/**
	 * @param subjectName
	 * @return
	 */
	public List getPredicatesForObject(String subjectId) throws DataRetrievalException//throws DataFetchException
	{
		
		
		List result =new LinkedList();
		
		StringBuffer query = new StringBuffer("select predicate from Predicate predicate");
		query.append(" inner join fetch predicate.triples triple inner join fetch triple.subject subject");
		query.append(" inner join fetch triple.object objectLik where subject.objectId = ");
		query.append(subjectId);
		query.append(" order by predicate.predicateId asc");
		try{
					
		result = hibernateTemplate.find(query.toString());
			
        
		}
		
		catch(HibernateException hibernateException){
			throw new DataRetrievalException(
					Constants.DATA_RETRIEVE_EXCEPTION , hibernateException);
		} catch (Exception exception) {
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION , exception );
		}
		
		return result;
	}
	
	public List getObjectsForPredicate(String predicateId,String subjectId) throws DataRetrievalException
	{
		
		
		List result =new ArrayList();
		
		StringBuffer query = new StringBuffer("select triple from Triple triple " );
				query.append("inner join fetch triple.object objectPojo ");
				query.append("inner join triple.subject subject ");
				query.append("inner join triple.predicate predicate ");
				query.append("where subject.objectId = "+subjectId);
				query.append(" and predicate.predicateId = "+predicateId);
		try{
			
			result = hibernateTemplate.find(query.toString());		
		
		}
		
			catch(HibernateException hibernateException){
				throw new DataRetrievalException(
						Constants.DATA_RETRIEVE_EXCEPTION , hibernateException);
			} catch (Exception exception) {
				throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION , exception );
			}
		
		return result;
	}
	
	
	
}
