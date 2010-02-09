/**
 * 
 */
package com.dao;

import java.util.List;

import com.exceptions.DataDeletionException;
import com.exceptions.DataInsertionException;
import com.exceptions.DataRetrievalException;
import com.exceptions.DataUpdationException;
import com.hibernate.pojo.Triple;

/**
 * @author viswanathp
 * 
 */
public interface CreateTripleDAO {
	/**
	 * saveObjectDetailsToTriple method is used to insert the Details of an Object.
	 * 
	 * @author viswanathp
	 * 
	 * @param triplePojo
	 * @return sequence of inserted record.
	 * @throws DataInsertionException
	 */
	public Integer saveObjectDetailsToTriple(Triple triplePojo)
			throws DataInsertionException;

	/**
	 * updateObjectDetailsToTriple method is used to update the Details of an Object.
	 * 
	 * @author viswanathp
	 * 
	 * @param triplePojo
	 * @return sequence of update record.
	 * @throws DataUpdationException
	 */
	public Integer updateObjectDetailsToTriple(Triple triplePojo)
			throws DataUpdationException;

	/**
	 * deletePredicateValueFromTriple method is used to delete the one of the Details of an Object.
	 * 
	 * @author viswanathp
	 * 
	 * @param triplePojo
	 * @return sequence of deleted record.
	 * @throws DataDeletionException
	 */
	public Integer deletePredicateValueFromTriple(Triple triplePojo)
			throws DataDeletionException;

	/**
	 * getObjectDetailsFromTriple method is used to retrieve the all Details of subject(object)
	 * including predicates and its values.
	 * 
	 * @author viswanathp
	 * 
	 * @param subjectName
	 * @return Predicates
	 * @return Predicate Values
	 * @return Subject Name
	 * @throws DataRetrievalException
	 */
	public List getObjectDetailsFromTriple(String subjectName)
			throws DataRetrievalException;

	/**
	 * getPredicateValuesFromTriple method is used to retrieve the values of a particular Predicate 
	 * and Subject Name.
	 * 
	 * @author viswanathp
	 * 
	 * @param subjectName
	 * @param predicateName
	 * @return Predicate values
	 * @throws DataRetrievalException
	 */
	public List getPredicateValuesFromTriple(String subjectName,
			String predicateName) throws DataRetrievalException;

	/**
	 * getPredicateNamesFromTriple method is used to retrieve all predicate names of particular subject.
	 * 
	 * @author viswanathp
	 * 
	 * @param subjectName
	 * @return Predicate Names.
	 * @throws DataRetrievalException
	 */
	public List getPredicateNamesFromTriple(String subjectName)
			throws DataRetrievalException;

	/**
	 * getPredicateMultipleValuesFromTriple is used to retrieve the Predicate Values by using the combination 
	 * of subject name and predicate name.
	 * 
	 * @author viswanathp
	 * 
	 * @param subjectName
	 * @param predicateName
	 * @return predicate values.
	 * @throws DataRetrievalException
	 */
	public List getPredicateMultipleValuesFromTriple(String subjectName,
			String predicateName) throws DataRetrievalException;

	/**
	 * getPredicateMulitpleValuesByTripleId is used to retrieve the Predicate values by using the tripel Id.
	 * 
	 * @author viswanathp
	 * 
	 * @param tripleId
	 * @return Predicate Names.
	 * @return Predicate Values.
	 * @throws DataRetrievalException
	 */
	public List getPredicateMulitpleValuesByTripleId(Integer tripleId)
			throws DataRetrievalException;

	/**
	 * getTriplePojo method is used to retrieve the Triple Pojo using input as triple Id.
	 * 
	 * @author viswanathp
	 * 
	 * @param tripleId
	 * @return Triple Pojo
	 * @throws DataRetrievalException
	 */
	public Triple getTriplePojo(Integer tripleId) throws DataRetrievalException;
}
