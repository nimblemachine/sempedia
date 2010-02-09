package com.dao;

import java.util.List;
import java.util.Set;

import com.dto.ClassDTO;
import com.dto.SuperClassDTO;
import com.exceptions.DataDeletionException;
import com.exceptions.DataInsertionException;
import com.exceptions.DataRetrievalException;
import com.exceptions.DataUpdationException;
import com.hibernate.pojo.ClassDescription;

public interface CreateClassDAO {
	public String insertClass(ClassDTO classDTO) throws DataInsertionException;

	public SuperClassDTO getClassCreatedDetails(SuperClassDTO superClassObj)
			throws DataRetrievalException;

	public boolean checkObjectExists(String className)
			throws DataRetrievalException;

	public Integer deleteClassDetails(Integer classId, String predicateName)
			throws DataDeletionException;

	public Set<Integer> checkAnySuperClassExists(Integer superClassId)
			throws DataRetrievalException;
	
	/**
	 * @param objectName
	 * @return
	 * @throws DataRetrievalException
	 */
	public List getAllClasses(String className) throws DataRetrievalException;


	public Integer getClassId(String className) throws DataRetrievalException;

	public Integer addClassParagraph(ClassDTO classDTO)
			throws DataInsertionException;

	public List<ClassDescription> getClassParagraphs(Integer classId)
			throws DataRetrievalException;

	public Integer editClassParagraph(ClassDTO classDTO)
			throws DataUpdationException;

	public Integer editClassName(ClassDTO classDTO)
			throws DataUpdationException;

	public boolean checkClassExists(ClassDTO classDTO)
			throws DataRetrievalException;
	
	public boolean checkSuperClassExists(ClassDTO classDTO)
			throws DataRetrievalException;

	public String getClassName(ClassDTO classDTO) throws DataRetrievalException;
}
