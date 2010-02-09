/**
 * 
 */
package com.service.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


import com.beans.ClassDetailsBean;
import com.dao.CreateClassDAO;
import com.dao.PredicateDAO;
import com.dto.ClassDTO;
import com.dto.SuperClassDTO;
import com.exceptions.DataDeletionException;
import com.exceptions.DataInsertionException;
import com.exceptions.DataRetrievalException;
import com.exceptions.DataUpdationException;
import com.hibernate.pojo.ClassDescription;
import com.hibernate.pojo.Predicate;
import com.service.CreateClassService;
import com.util.Constants;

/**
 * @author gurupavan
 * 
 */
public class CreateClassServiceImpl implements CreateClassService {

	private CreateClassDAO createClassDAO;
	private PredicateDAO predicateDAO;
	private Set<String> finalPropeties;
	private Set<String> tempPropeties;
	private Set<String> subClassProperties;

	/**
	 * @return the createClassDAO
	 */
	public CreateClassDAO getCreateClassDAO() {
		return createClassDAO;
	}

	/**
	 * @param createClassDAO
	 *            the createClassDAO to set
	 */
	public void setCreateClassDAO(CreateClassDAO createClassDAO) {
		this.createClassDAO = createClassDAO;
	}

	/**
	 * insertClass method is used to insert a newly created class by clicking if
	 * the newly created class exists before or not
	 * 
	 * @return Returns ClassDetailsBean which contains all the information regarding class creation and the message regarding class insertion.
	 * @throws ServletException
	 */	
	public ClassDetailsBean insertClass(ClassDTO classDTO)
			throws DataInsertionException, DataRetrievalException {
		ClassDetailsBean classDetailsBean = new ClassDetailsBean();
		String message = "";
		String result;
		boolean superClassFlag = false; 
		try {
			if(classDTO.getIsA() != ""){
				superClassFlag = createClassDAO.checkSuperClassExists(classDTO);
			}else
				superClassFlag = true;
			if(superClassFlag){
				result = createClassDAO.insertClass(classDTO);
				SuperClassDTO superClassDTO = new SuperClassDTO();
				if (result.equalsIgnoreCase("classExists")) {
					message = "Class "+classDTO.getClassName()+" already exists, please enter new class";
					classDetailsBean.setColor("red");
				} else {
					String resultArray[] = result.split("-");
					Integer subClassId = new Integer(resultArray[0]);
					Integer superClassId = new Integer(resultArray[1]);
					if (subClassId > 0) {
						classDetailsBean.setSubClassName(classDTO.getClassName());
						classDetailsBean.setSuperClassName(classDTO.getIsA());
						classDetailsBean.setSubClassId(subClassId);
						classDetailsBean.setSuperClassId(superClassId);
						if (superClassId > 0) {
							finalPropeties = new LinkedHashSet<String>();
							tempPropeties = new LinkedHashSet<String>();
							classDetailsBean.setSubClassName(classDTO
									.getClassName());
							classDetailsBean.setSuperClassName(classDTO.getIsA());
							classDetailsBean.setSubClassId(subClassId);
							classDetailsBean.setSuperClassId(superClassId);
							generateProperties(superClassDTO, superClassId,
									classDetailsBean, classDTO);
						}
					}
					if(subClassId > 0) {
						message = "Class "+classDetailsBean.getSubClassName()+" is created successfully";
						classDetailsBean.setOperation("created");
						classDetailsBean.setColor("blue");
						if(superClassId > 0 && subClassId > 0){
							message = "Class "+classDetailsBean.getSubClassName()+" with its super class "+classDetailsBean.getSuperClassName()+" is Created Successfully";
							classDetailsBean.setOperation("created");
							classDetailsBean.setColor("blue");
						}else if(superClassId > 0){
							message = "Class "+classDetailsBean.getSubClassName()+" with its super class "+classDetailsBean.getSuperClassName()+" creation failed";
							classDetailsBean.setColor("red");
						}
				}else{
					message = "Class "+classDetailsBean.getSubClassName()+" creation failed";
					classDetailsBean.setColor("red");
				}
			}
			}else{
				message = "The parent class "+classDTO.getIsA()+" doesn't exists, please select existing parent class";
				classDetailsBean.setColor("red");
			}
		} catch (Exception exception) {
			throw new DataInsertionException(Constants.DATA_INSERTION_EXCEPTION);
		}
		classDetailsBean.setMessage(message);
		return classDetailsBean;
	}
	
	/**
	 * generateProperties method is used to generate all the properties for a class it also gets the properties of its parent class if any
	 *   
	 * 
	 * @return Returns void
	 * @throws ServletException
	 */	
	private void generateProperties(SuperClassDTO superClassDTO,
			Integer superClassId, ClassDetailsBean classDetailsBean,
			ClassDTO classBean) throws DataRetrievalException {

		superClassDTO.setSuperClassId(superClassId);
		superClassDTO.setSuperClassName(classBean.getIsA());
		superClassDTO = getClassCreatedDetails(superClassDTO);
		finalPropeties.addAll(superClassDTO.getSuperClassProperties());
		classDetailsBean.setSuperClassPredicates(finalPropeties);
		Set<Integer> superClassIdList;
		superClassIdList = checkAnySuperClassExists(superClassId);
		Integer superClassIdValue = 0;
		do {
			Iterator<Integer> superClassIdListiterator = superClassIdList
					.iterator();
			while (superClassIdListiterator.hasNext()) {
				superClassIdValue = (Integer) superClassIdListiterator.next();
				superClassDTO.setSuperClassId(superClassIdValue);
				superClassDTO = getClassCreatedDetails(superClassDTO);
				tempPropeties.addAll(superClassDTO.getSuperClassProperties());
				Iterator<String> tempPropetiesIterator = tempPropeties
						.iterator();
				while (tempPropetiesIterator.hasNext()) {
					String string = (String) tempPropetiesIterator.next();
					finalPropeties.add(string);
				}
			}
		} while ((superClassIdList = checkAnySuperClassExists(superClassIdValue))
				.size() != 0);
	}
	
	/**
	 * setClassProperties method is used to set the class properties to classDTO
	 *   
	 * 
	 * @return Returns ClassDTO
	 * @throws ServletException
	 */	

	public ClassDTO setClassProperties(ClassDTO classDTO) {

		ClassDTO classDTOObj = new ClassDTO();
		String predicateArray[] = classDTO.getAllProperties().split("::");
		Set<String> property = new LinkedHashSet<String>();
		for (int propertiesCount = 0; propertiesCount < predicateArray.length; propertiesCount++) {
			property.add(predicateArray[propertiesCount]);
		}
		classDTOObj.setClassName(classDTO.getClassName());
		classDTOObj.setIsA(classDTO.getIsA());
		classDTOObj.setSubClassId(classDTO.getSubClassId());
		classDTOObj.setSuperClassId(classDTO.getSuperClassId());
		classDTOObj.setProperty(property);
		return classDTOObj;
	}

	/**
	 * getClassCreatedDetails method is used to get the class created details by calling getClassCreatedDetails method in createClassDAO
	 *   
	 * 
	 * @return Returns ClassDTO
	 * @throws ServletException
	 */	
	public SuperClassDTO getClassCreatedDetails(SuperClassDTO superClassObj)
			throws DataRetrievalException {
		SuperClassDTO result;
		try {
			result = createClassDAO.getClassCreatedDetails(superClassObj);
		} catch (Exception exception) {
			throw new DataRetrievalException(Constants.DATA_RETRIEVAL_EXCEPTION);
		}
		return result;
	}

	/**
	 * insertPredicates method is used to insert predicates into predicate table by calling insertPredicates method in predicateDAO
	 *   
	 * 
	 * @return Returns String message which contains message after inserting the predicates
	 * @throws ServletException
	 */	
	public String insertPredicates(ClassDTO classDTO)
			throws DataInsertionException {
		Integer result = 0;
		try {
			result = predicateDAO.insertPredicates(classDTO);
		} catch (Exception exception) {
			throw new DataInsertionException(Constants.DATA_INSERTION_EXCEPTION);
		}
		String message;
		if (result > 0)
			message = "Class "+classDTO.getClassName()+" saved successfully ~blue";
		else
			message = "Class "+classDTO.getClassName()+" not saved ~red";
		return message;
	}
	
	/**
	 * checkAnySuperClassExists method is used to check any super class exists for a given class id
	 *   
	 * 
	 * @return Returns Integer which contains the set of super classes
	 * @throws ServletException
	 */	
	public Set<Integer> checkAnySuperClassExists(Integer superClassId)
			throws DataRetrievalException {
		Set<Integer> result = null;
		try {
			result = createClassDAO.checkAnySuperClassExists(superClassId);
		} catch (Exception exception) {
			throw new DataRetrievalException(Constants.DATA_RETRIEVAL_EXCEPTION);
		}
		return result;
	}

	/**
	 * getClassEditViewDetails method is used to get the edit mode of an class
	 * by passing its class name, it gets all the details of class properties,
	 * paragraphs, class name and super class of the present class if any. And
	 * also if the class has any Object than that class can be opened in edit
	 * mode but properties to that class cannot be added or edited
	 * 
	 * 
	 * @return Returns ClassDetailsBean which has all the properties for that
	 *         particular class in edit mode.
	 * @throws ServletException
	 */	
	public ClassDetailsBean getClassEditViewDetails(String className)
			throws DataRetrievalException {
		Integer subClassId;
		boolean objectFlag = false;
		SuperClassDTO superClassDTO = new SuperClassDTO();
		ClassDTO classDTO = new ClassDTO();
		ClassDetailsBean classDetailsBean = new ClassDetailsBean();
		List<ClassDescription> classDescriptionList = new LinkedList<ClassDescription>();
		Map<Integer, String> classDescriptionMap = new LinkedHashMap<Integer, String>();
		Map<Integer, String> classStyleMap = new LinkedHashMap<Integer, String>();
		try {
			subClassId = createClassDAO.getClassId(className);
			finalPropeties = new LinkedHashSet<String>();
			tempPropeties = new LinkedHashSet<String>();
			subClassProperties = new LinkedHashSet<String>();
			subClassProperties = new LinkedHashSet<String>();
			classDTO.setSubClassId(subClassId);
			classDetailsBean.setSuperClassId(subClassId);
			superClassDTO.setSuperClassId(subClassId);
			superClassDTO.setSuperClassName(className);
			objectFlag = checkObjectExists(className);
			superClassDTO = getClassCreatedDetails(superClassDTO);
			subClassProperties.addAll(superClassDTO.getSuperClassProperties());
			classDetailsBean.setSubClassPredicates(subClassProperties);
			generateProperties(superClassDTO, subClassId, classDetailsBean,
					classDTO);
			classDescriptionList = createClassDAO
					.getClassParagraphs(subClassId);
			for (Iterator<ClassDescription> iterator = classDescriptionList
					.iterator(); iterator.hasNext();) {
				ClassDescription classDescriptionObject = (ClassDescription) iterator
						.next();
				classDescriptionMap.put(
						classDescriptionObject.getClassTextId(),
						classDescriptionObject.getText());
				classStyleMap.put(classDescriptionObject.getClassTextId(),
						classDescriptionObject.getStyle());
			}
			classDetailsBean.setMessage("");
			classDetailsBean.setSubClassName(className);
			classDetailsBean.setClassParagraph(classDescriptionMap);
			classDetailsBean.setClassParagraphStyle(classStyleMap);
			classDetailsBean.setOperation("editClass");
			if (objectFlag) {
				classDetailsBean
						.setMessage("Could not open in edit mode as object exists for "
								+ className + " class");
				classDetailsBean.setOperation("objectExists");
			}

		} catch (Exception exception) {
			throw new DataRetrievalException(Constants.DATA_RETRIEVAL_EXCEPTION);
		}
		return classDetailsBean;
	}
	
	/**
	 * checkObjectExists method is used to check whether a class contains any
	 * objects or not by passing the class name
	 * 
	 * 
	 * @return Returns boolean variable true if exits or false if object doesn't
	 *         exists
	 * @throws ServletException
	 */	
	public boolean checkObjectExists(String className)
			throws DataRetrievalException {
		boolean result = false;
		try {
			result = createClassDAO.checkObjectExists(className);
		} catch (Exception exception) {
			throw new DataRetrievalException(Constants.DATA_RETRIEVAL_EXCEPTION);
		}
		return result;
	}

	/**
	 * deleteClassDetails method is used to delete the selected property of an class in edit mode
	 *   
	 * 
	 * @return Returns String after deleting the property 
	 * @throws ServletException
	 */	
	public String deleteClassDetails(Integer classId, String predicateName)
			throws DataDeletionException {
		Integer deletedValueResult = 0;
		String message = "";
		try {
			deletedValueResult = createClassDAO.deleteClassDetails(classId,
					predicateName);
		} catch (Exception exception) {
			throw new DataDeletionException(Constants.DATA_DELETION_EXCEPTION);
		}
		if (deletedValueResult > 0)
			message = "Property " + predicateName
					+ " deleted successfully ~blue";
		else
			message = "Property " + predicateName + " deletion failed ~red";
		return message;

	}

	/**
	 * 
	 * updateClassProperty method is used to update a selected property in edit mode of an class
	 * 
	 * @return Returns String after updating the property 
	 * @throws ServletException
	 */
	public String updateClassProperty(ClassDTO classDTO)
			throws DataUpdationException {
		String message = "";
		Integer result = 0;
		Predicate predicate;
		try {
			predicate = predicateDAO.getPredicate(classDTO.getPropertyName(),
					classDTO.getSuperClassId());
			result = predicateDAO.updateClassProperty(predicate
					.getPredicateId(), classDTO.getAllProperties());
			if (result > 0) {
				message = "Property " + classDTO.getAllProperties()
						+ " updated successfully ~blue";
			} else {
				message = "Could not update the property ~red";
			}
		} catch (Exception exception) {
			throw new DataUpdationException(Constants.DATA_UPDATE_EXCEPTION);
		}
		return message;
	}

	/**
	 * 
	 * addClassParagraph method is used to add new paragraph with selected style for a class in edit mode
	 * 
	 * @return Returns String after adding new paragraph for a class 
	 * @throws ServletException
	 */
	public String addClassParagraph(ClassDTO classDTO)
			throws DataInsertionException {
		String message = "";
		Integer result = 0;
		try {
			result = createClassDAO.addClassParagraph(classDTO);
			if (result > 0) {
				message = "Paragraph added successfully ~blue";
			} else {
				message = "Paragraph could not be added ~red";
			}
		} catch (Exception exception) {
			throw new DataInsertionException(Constants.DATA_INSERTION_EXCEPTION);
		}
		return message;
	}

	/**
	 * 
	 * editClassParagraph method is used to edit the selected paragraph of a class in edit mode
	 * 
	 * @return Returns String after editing the paragraph for a class 
	 * @throws ServletException
	 */
	public String editClassParagraph(ClassDTO classDTO)
			throws DataUpdationException {
		String message = "";
		Integer result = 0;
		try {
			result = createClassDAO.editClassParagraph(classDTO);
			if (result > 0) {
				message = "Paragraph saved successfully ~blue";
			} else {
				message = "Paragraph could not be saved ~red";
			}
		} catch (Exception exception) {
			throw new DataUpdationException(Constants.DATA_UPDATE_EXCEPTION);
		}
		return message;
	}

	/**
	 * 
	 * editClassName method is used to edit the class name in edit mode by checking if any class exists with the same name
	 * and than check if any objects exists for given class name or not 
	 * and than edits the class name to the selected class based on the class id.
	 * 
	 * @return Returns String after editing the class name 
	 * @throws ServletException
	 */
	public String editClassName(ClassDTO classDTO) throws DataUpdationException {
		String message = "";
		Integer result = 0;
		boolean classExists = false;
		boolean objectExists = false;
		try {
			classExists = createClassDAO.checkClassExists(classDTO);
			if (classExists) {
				message = "Class " + classDTO.getClassName()
						+ " already exists, please enter new class ~red";
			} else {
				objectExists = createClassDAO.checkObjectExists(classDTO
						.getClassName());
				if (objectExists) {
					message = "Class name " + classDTO.getClassName()
							+ " cannot be edited as object exists ~red";
				} else {
					result = createClassDAO.editClassName(classDTO);
					if (result > 0) {
						message = "Class name " + classDTO.getClassName()
								+ " edited successfully ~blue";
					} else {
						message = "Class name " + classDTO.getClassName()
								+ " could not be edited ~red";
					}
				}
			}
		} catch (Exception exception) {
			throw new DataUpdationException(Constants.DATA_UPDATE_EXCEPTION);
		}
		return message;
	}
	
	/**
	 * 
	 * getClassName method is used to get the class name of an class by passing the class id
	 * 
	 * @return Returns class name of an class 
	 * @throws ServletException
	 */
	public String getClassName(ClassDTO classDTO) throws DataRetrievalException {
		String className = "";
		try {
			className = createClassDAO.getClassName(classDTO);
		} catch (Exception exception) {
			throw new DataRetrievalException(Constants.DATA_RETRIEVAL_EXCEPTION);
		}
		return className;
	}

	/**
	 * @return the predicateDAO
	 */
	public PredicateDAO getPredicateDAO() {
		return predicateDAO;
	}

	/**
	 * @param predicateDAO
	 *            the predicateDAO to set
	 */
	public void setPredicateDAO(PredicateDAO predicateDAO) {
		this.predicateDAO = predicateDAO;
	}

}
