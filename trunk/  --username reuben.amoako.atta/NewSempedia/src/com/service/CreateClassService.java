package com.service;

import java.util.Set;

import com.beans.ClassDetailsBean;
import com.dto.ClassDTO;
import com.dto.SuperClassDTO;
import com.exceptions.DataDeletionException;
import com.exceptions.DataInsertionException;
import com.exceptions.DataRetrievalException;
import com.exceptions.DataUpdationException;

public interface CreateClassService {
	public ClassDetailsBean insertClass(ClassDTO classDTO)
			throws DataInsertionException, DataRetrievalException;

	public SuperClassDTO getClassCreatedDetails(SuperClassDTO superClassObj)
			throws DataRetrievalException;

	public String insertPredicates(ClassDTO classDTO)
			throws DataInsertionException;

	public Set<Integer> checkAnySuperClassExists(Integer superClassId)
			throws DataRetrievalException;

	public ClassDetailsBean getClassEditViewDetails(String className)
			throws DataRetrievalException;

	public boolean checkObjectExists(String className)
			throws DataRetrievalException;

	public String deleteClassDetails(Integer classId, String predicateName)
			throws DataDeletionException;

	public ClassDTO setClassProperties(ClassDTO classDTO);

	public String updateClassProperty(ClassDTO classDTO)
			throws DataUpdationException;

	public String addClassParagraph(ClassDTO classDTO)
			throws DataInsertionException;

	public String editClassParagraph(ClassDTO classDTO)
			throws DataUpdationException;

	public String editClassName(ClassDTO classDTO) throws DataUpdationException;

	public String getClassName(ClassDTO classDTO) throws DataRetrievalException;
}
