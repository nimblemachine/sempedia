package com.delegate.impl;

import java.util.Set;

import com.beans.ClassDetailsBean;
import com.delegate.CreateClassDelegate;
import com.dto.ClassDTO;
import com.dto.SuperClassDTO;
import com.exceptions.DataDeletionException;
import com.exceptions.DataInsertionException;
import com.exceptions.DataRetrievalException;
import com.exceptions.DataUpdationException;
import com.service.CreateClassService;
import com.util.Constants;

public class CreateClassDelegateImpl implements CreateClassDelegate {

	private CreateClassService createClassService;

	public ClassDetailsBean insertClass(ClassDTO classDTO)
			throws DataInsertionException, DataRetrievalException {
		ClassDetailsBean result = null;
		try {
			result = createClassService.insertClass(classDTO);
		} catch (Exception exception) {
			throw new DataInsertionException(Constants.DATA_INSERTION_EXCEPTION);
		}
		return result;
	}

	public SuperClassDTO getClassCreatedDetails(SuperClassDTO superClassObj)
			throws DataRetrievalException {
		SuperClassDTO result = null;
		try {
			result = createClassService.getClassCreatedDetails(superClassObj);
		} catch (Exception exception) {
			throw new DataRetrievalException(Constants.DATA_RETRIEVAL_EXCEPTION);
		}

		return result;
	}

	public String insertPredicates(ClassDTO classDTO)
			throws DataInsertionException {
		String result = "";
		try {
			result = createClassService.insertPredicates(classDTO);
		} catch (Exception e) {
			throw new DataInsertionException(Constants.DATA_INSERTION_EXCEPTION);
		}

		return result;
	}

	public Set<Integer> checkAnySuperClassExists(Integer superClassId)
			throws DataRetrievalException {
		Set<Integer> result;
		try {
			result = createClassService.checkAnySuperClassExists(superClassId);
		} catch (Exception exception) {
			throw new DataRetrievalException(Constants.DATA_RETRIEVAL_EXCEPTION);
		}
		return result;
	}

	public ClassDetailsBean getClassEditViewDetails(String className)
			throws DataRetrievalException {
		ClassDetailsBean result = null;
		try {
			result = createClassService.getClassEditViewDetails(className);
		} catch (Exception exception) {
			throw new DataRetrievalException(Constants.DATA_RETRIEVAL_EXCEPTION);
		}
		return result;
	}

	public String deleteClassDetails(Integer classId, String predicateName)
			throws DataDeletionException {
		String result;
		try {
			result = createClassService.deleteClassDetails(classId,
					predicateName);
		} catch (Exception exception) {
			throw new DataDeletionException(Constants.DATA_DELETION_EXCEPTION);
		}
		return result;
	}

	public ClassDTO setClassProperties(ClassDTO classDTO) {

		ClassDTO result = createClassService.setClassProperties(classDTO);

		return result;
	}

	public String updateClassProperty(ClassDTO classDTO)
			throws DataUpdationException {
		String result = "";
		try {
			result = createClassService.updateClassProperty(classDTO);
		} catch (Exception exception) {
			throw new DataUpdationException(Constants.DATA_UPDATE_EXCEPTION);
		}
		return result;
	}

	public String addClassParagraph(ClassDTO classDTO)
			throws DataInsertionException {
		String result = "";
		try {
			result = createClassService.addClassParagraph(classDTO);
		} catch (Exception exception) {
			throw new DataInsertionException(Constants.DATA_INSERTION_EXCEPTION);
		}
		return result;
	}

	public String editClassParagraph(ClassDTO classDTO)
			throws DataUpdationException {
		String result = "";
		try {
			result = createClassService.editClassParagraph(classDTO);
		} catch (Exception exception) {
			throw new DataUpdationException(Constants.DATA_UPDATE_EXCEPTION);
		}
		return result;
	}

	public String editClassName(ClassDTO classDTO) throws DataUpdationException {
		String result = "";
		try {
			result = createClassService.editClassName(classDTO);
		} catch (Exception exception) {
			throw new DataUpdationException(Constants.DATA_UPDATE_EXCEPTION);
		}
		return result;
	}

	public String getClassName(ClassDTO classDTO) throws DataRetrievalException {
		String className = "";
		try {
			className = createClassService.getClassName(classDTO);
		} catch (Exception exception) {
			throw new DataRetrievalException(Constants.DATA_RETRIEVAL_EXCEPTION);
		}
		return className;
	}

	/**
	 * @return the createClassService
	 */
	public CreateClassService getCreateClassService() {
		return createClassService;
	}

	/**
	 * @param createClassService
	 *            the createClassService to set
	 */
	public void setCreateClassService(CreateClassService createClassService) {
		this.createClassService = createClassService;
	}

}
