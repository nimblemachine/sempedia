package com.dao;

import java.util.List;

import com.dto.ClassDTO;
import com.exceptions.DataInsertionException;
import com.exceptions.DataRetrievalException;
import com.exceptions.DataUpdationException;
import com.hibernate.pojo.ClassPojo;
import com.hibernate.pojo.Predicate;

/**
 * Contains methods for create, update, delete Predicates and basic search
 * functionality methods.
 * 
 * @author naveenK
 */
public interface PredicateDAO {

	/**
	 * getPredicatesForObject method is used to retrieve the Predicates of given subject(object). 
	 * 
	 * @author viswanathp
	 * 
	 * @param subjectId
	 * @return Predicates of the subject(object).
	 * @throws DataRetrievalException
	 */
	public List getPredicatesForObject(String subjectName)
			throws DataRetrievalException; // throws DataFetchException;

	
	/**
	 * saveTripleDetails method is used to save the value of the given predicate and subject(Object).
	 * 
	 * @author viswanathp
	 * 
	 * @param subjectId
	 * @param predicateId
	 * @param objectId
	 * @return Sequence of the inserted record.
	 * @throws DataInsertionException
	 */
	public Integer saveTripleDetails(Integer subjectId, Integer predicateId,
			Integer objectId) throws DataInsertionException;

	
	/**
	 * saveNewPredicate method is used to insert a new Predicate to the given class. 
	 * 
	 * @author viswanathp
	 * 
	 * @param predicatName
	 * @param classPojo
	 * @return Sequence of the inserted record.
	 * @throws DataInsertionException
	 */
	public Integer saveNewPredicate(String predicateName, ClassPojo classPojo)
			throws DataInsertionException;
	
	
	/**
	 * getPredicateFromParents method is used to check whether the predicate name exists in any super class of
	 * the given class.
	 * 
	 * @author viswanathp
	 * 
	 * @param predicatName
	 * @param isOfTypeClassId
	 * @return Unique Predicate Pojo
	 * @throws DataRetrievalException
	 */
	public Predicate getPredicateFromParents(String predicatName,
			Integer isOfTypeClassId) throws DataRetrievalException;
	
	public Integer insertPredicates(ClassDTO classDTO)
			throws DataInsertionException;

	public Integer updateClassProperty(Integer predicateId, String propertyName)
			throws DataUpdationException;

	public Predicate getPredicate(String predicateName, Integer classId)
			throws DataRetrievalException;


}
