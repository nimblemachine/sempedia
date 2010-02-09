/**
 * 
 */
package com.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.beans.ObjectDetailsBean;
import com.dao.CreateObjectDAO;
import com.dao.CreateTripleDAO;
import com.dao.PredicateDAO;
import com.dto.ObjectDTO;
import com.dto.ObjectDescriptionDTO;
import com.dto.TripleDTO;
import com.exceptions.DataDeletionException;
import com.exceptions.DataInsertionException;
import com.exceptions.DataRetrievalException;
import com.exceptions.DataUpdationException;
import com.hibernate.pojo.ClassPojo;
import com.hibernate.pojo.ObjectDescription;
import com.hibernate.pojo.ObjectPojo;
import com.hibernate.pojo.Predicate;
import com.hibernate.pojo.Triple;
import com.service.CreateObjectService;
import com.util.Constants;
/**
 * @author viswanathp
 *
 */

public class CreateObjectServiceImpl implements CreateObjectService {
	
	private CreateObjectDAO createObjectDAO;
	private CreateTripleDAO createTripleDAO;
	private PredicateDAO predicateDAO;
/**
 * 
 * @return
 */
	public CreateTripleDAO getCreateTripleDAO() {
		return createTripleDAO;
	}
/**
 * 
 * @param createTripleDAO
 */
	public void setCreateTripleDAO(CreateTripleDAO createTripleDAO) {
		this.createTripleDAO = createTripleDAO;
	}
	/**
	 * @return the createObjectDAO
	 */
	public CreateObjectDAO getCreateObjectDAO() {
		return createObjectDAO;
	}

	/**
	 * @param createObjectDAO the createObjectDAO to set
	 */
	public void setCreateObjectDAO(CreateObjectDAO createObjectDAO) {
		this.createObjectDAO = createObjectDAO;
	}
	/**
	 * 
	 * @return
	 */
	public PredicateDAO getPredicateDAO() {
		return predicateDAO;
	}
/**
 * 
 * @param predicateDAO
 */
	public void setPredicateDAO(PredicateDAO predicateDAO) {
		this.predicateDAO = predicateDAO;
	}
	
	
	/**
	 * insertObject method is used to insert a new Object.
	 * 
	 * @param objectDTO
	 * @return Object Name
	 * @return Super Class Name
	 * @return Predicate Names of all Super Classes of an Object.
	 * @throws DataInsertionException
	 * @throws DataRetrievalException
	 */
	public Map insertObject(ObjectDTO objectDTO) throws DataInsertionException, DataRetrievalException{	
		Map result = new LinkedHashMap();
		ObjectPojo objectObj = new ObjectPojo();
		ClassPojo classObj = null;
		String message = null;
		String color = null;
		try
		{
			Boolean checkObjectStatus = false;
			checkObjectStatus = createObjectDAO.checkObject(objectDTO.getObjectName().trim());
			if(checkObjectStatus != true)
			{
				classObj = createObjectDAO.getClass(objectDTO.getIsOfType().trim());
				if(classObj != null)
				{
					objectObj.setObjectName(objectDTO.getObjectName().trim());
					objectObj.setClassId(classObj.getClassId());
					Integer insertValue = createObjectDAO.insertObject(objectObj);
					if(insertValue > 0)
					{
						message = "Object "+objectDTO.getObjectName()+" inserted";
						color = "blue";
					}
					else
					{
						message = "Object "+objectDTO.getObjectName()+" not inserted";
						color = "red";
					}
					result.put(objectDTO.getObjectName(), classObj.getClassName());
				}
				else
				{
					message = "Object "+objectDTO.getObjectName()+" cannot inserted because "+objectDTO.getIsOfType()+" doesn't exist";
					color = "red";
				}
			}
			else
			{
				message = "Object "+objectDTO.getObjectName()+" already exists";
				color = "red";
			}
		}catch(DataInsertionException die)
		{
			throw new DataInsertionException(Constants.DATA_INSERT_EXCEPTION);
		}catch(DataRetrievalException dre)
		{
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION);
		}
		result.put("message", message);
		result.put("color", color);
		return result;
	}
	
	/**
	 * editObject method is used to retrieve the details of an Object.
	 * 
	 * @param objectDTO
	 * @return Object Name
	 * @return Super Class Name
	 * @return Predicate Names of all Super Classes of an Object.
	 * @return Predicate Values of an Object.
	 * @throws DataRetrievalException
	 */
	public Map editObject(ObjectDTO objectDTO) throws DataRetrievalException
	{
		Map result = new LinkedHashMap();
		String message = null;
		String color = null;
		try{
			ObjectPojo object = createObjectDAO.getObject(objectDTO.getObjectName().trim());
			if(object != null)
			{
				
				result.put(object.getObjectName(), createObjectDAO.getClassName(object.getObjectName().trim()));
			}
			message = "The Object details are shown below";
			color = "blue";
		}catch(DataRetrievalException dre)
		{
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION);
		}
		result.put("message", message);
		result.put("color", color);
		return result;
	}
	
	/**
	 * getAllPredicatesOfClass method is used to retrieve all Predicate Names of a class and its Super classes.
	 * 
	 * @param className
	 * @return Predicate Names
	 * @throws DataRetrievalException
	 */
	public Map getAllPredicatesOfClass(String className) throws DataRetrievalException
	{
		Predicate predicateObj = null;
		List classPredicatesList = null;
		List totalPredicateList = new ArrayList();
		Map resultMap = new LinkedHashMap();
		Integer subClassId = 0;
		Predicate predicatePojo = null;
		try{
			subClassId = createObjectDAO.getClassIdByName(className.trim());
			if(subClassId != null)
			{
				do
				{
					classPredicatesList = createObjectDAO.getPredicatesOfClass(subClassId);
					if(classPredicatesList != null)
					{
						for (Iterator iterator = classPredicatesList.iterator(); iterator.hasNext();) 
						{
							predicateObj = (Predicate) iterator.next();
							totalPredicateList.add(predicateObj);
						}
					}
					subClassId = createObjectDAO.getSuperClassId(subClassId);
				}while(subClassId != null);
				for (Iterator iterator = totalPredicateList.iterator(); iterator.hasNext();) 
				{
					predicatePojo = (Predicate) iterator.next();
					resultMap.put(predicatePojo.getPredicateName(), null);
				}
			}
			else
			{
				resultMap = null;
			}
		}catch(DataRetrievalException dre)
		{
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION);
		}
		return resultMap;
	}
	

	/**
	 * getAllPredicatesAndValuesFromTriple method is used to retrieve all Predicates and Values of an Object
	 * 
	 * 
	 * @param objectName
	 * @return Predicate Names
	 * @return Predicate Values
	 * @throws DataRetrievalException
	 */
	public Map getAllPredicatesAndValuesFromTriple(String objectName) throws DataRetrievalException 
	{
		Map predicatesMap = new LinkedHashMap();
		try{
			List tripleList = createTripleDAO.getPredicateNamesFromTriple(objectName.trim());
			Set triplePredicateNames = new LinkedHashSet(tripleList);
			tripleList = new ArrayList(triplePredicateNames);
			for (Iterator iterator = tripleList.iterator(); iterator.hasNext();) 
			{
				String preNames = (String) iterator.next();
			}
			String className = createObjectDAO.getClassName(objectName.trim());
			Map totalPredicates = null;
			String value[] = null;
			if(className != null)
			{
				totalPredicates = getAllPredicatesOfClass(className.trim());
			}
			if(tripleList != null)
			{
				String oldPredicateName = null;
				String oldPredicateValueArray[] = null;
				String predicateName = null;
				String predicateValue = null;
				String[] predicateValueArray = new String[tripleList.size()];
				List<String> oldPredicateValueList = new ArrayList<String>();
				String predicateValues[] = null;
				ObjectDetailsBean objectDetailsBean = null;
				List predicateValuesList = null;
				for (Iterator iterator = tripleList.iterator(); iterator.hasNext();) 
				{
					predicateName = (String) iterator.next();
					predicateValuesList = new ArrayList();
					List triplePojoList = createTripleDAO.getPredicateMultipleValuesFromTriple(objectName, predicateName);
					predicateValues = new String[triplePojoList.size()];
					int arrCount = 0;
					for (Iterator iterator2 = triplePojoList.iterator(); iterator2.hasNext();) 
					{
						Triple triplePojo = (Triple) iterator2.next();
						objectDetailsBean = new ObjectDetailsBean();
						objectDetailsBean.setTripleId(triplePojo.getTripleId());
						objectDetailsBean.setPredicateValue(triplePojo.getObject().getObjectName());
						predicateValuesList.add(objectDetailsBean);
						predicateValues[arrCount] = triplePojo.getObject().getObjectName();
					}
					predicatesMap.put(predicateName, predicateValuesList);
				}
			}
			if(totalPredicates != null)
			{
			 Iterator totalPredicatesItr = totalPredicates.entrySet().iterator();
			    while (totalPredicatesItr.hasNext()) 
			    {
			        Map.Entry pairs = (Map.Entry)totalPredicatesItr.next();
			        Iterator predicatesMapItr = predicatesMap.entrySet().iterator();
			        Boolean status = false;
				    while (predicatesMapItr.hasNext()) 
				    {
				        Map.Entry itemPairs = (Map.Entry)predicatesMapItr.next();
				        
				        if(pairs.getKey().toString().equalsIgnoreCase(itemPairs.getKey().toString()))
				        {
				        	status = true;
				        }
				    }
				    if(status == false)
				    {
				    	predicatesMap.put(pairs.getKey().toString(),null);
				    }
			    }
			}
		}catch(DataRetrievalException dre)
		{
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION);
		}
		return predicatesMap;
	}
	
	/**
	 * saveObjectDetailsToTriple method is used to insert the details of an Object.
	 * 
	 * @param predicatesKeyValueMap
	 * @return Sequence of a newly inserted record.
	 * @throws DataInsertionException
	 * @throws DataRetrievalException
	 */
	public Integer saveObjectDetailsToTriple(Map tripleDataMap) throws DataInsertionException,DataRetrievalException
	{
		Integer saveResult = 0;
		Triple triplePojo = new Triple();
		ObjectPojo forSubject = null;
		ObjectPojo forObject = null;
		Predicate forPredicate = null;
		String[] value = null;
		Integer predicateId = 0;
		Integer newObjectInsertResult = 0;
		String key = null;
		Integer keyInt = 0;
		List objectPojoLlist = null;
		List triplelist = new ArrayList();
		Map formSubmition = tripleDataMap;
		try
		{
			String subjectName[] = (String[])formSubmition.get("objectName");
			String isOfType[] = (String[])formSubmition.get("isOfType");
			ClassPojo classPojo = createObjectDAO.getClass(isOfType[0].trim());
			
			if(subjectName[0] != null)
			{
				forSubject = createObjectDAO.getObject(subjectName[0].trim());
				triplePojo.setSubject(forSubject);
			}
			 Iterator formSubmitionItr = formSubmition.entrySet().iterator();
			    while (formSubmitionItr.hasNext()) 
			    {
			    	
			        Map.Entry pairs = (Map.Entry)formSubmitionItr.next();
			        if (!(pairs.getKey().toString().equalsIgnoreCase("objectName")
							|| pairs.getKey().toString().equalsIgnoreCase("save")
							|| pairs.getKey().toString().equalsIgnoreCase("action")
							|| pairs.getKey().toString().equalsIgnoreCase(
									"newPropertyAddingToSuperClassValue")
							|| pairs.getKey().toString().equalsIgnoreCase("isOfType")
							|| pairs.getKey().toString().equalsIgnoreCase(
									"newPropertyAddingToSuperClass")
							|| pairs.getKey().toString().equalsIgnoreCase("propertyHidden")
							|| pairs.getKey().toString().equalsIgnoreCase("propertiesCountHidden")
							|| pairs.getKey().toString().equalsIgnoreCase("") || pairs
							.getKey() == null))	
			        {
			        	if(pairs.getValue() != null)
				        {
				        	value = (String[])pairs.getValue();
				        }
			        	key = pairs.getKey().toString();
			        	forPredicate = predicateDAO.getPredicateFromParents(key,classPojo.getClassId());
			        	
			        	for(int valuesCounter=0;valuesCounter<value.length;valuesCounter++)
			        	{
			        		if(!(value[valuesCounter].equalsIgnoreCase("") || value[valuesCounter] == null ))
			        		{
								forObject = createObjectDAO.getObject(value[valuesCounter].trim());
								triplePojo.setPredicate(forPredicate);
								if(forObject == null)
								{
									forObject = new ObjectPojo();
									forObject.setObjectName(value[valuesCounter]);
									forObject.setClassId(null);
									newObjectInsertResult = 0;
									newObjectInsertResult = createObjectDAO.insertObject(forObject);
									if(newObjectInsertResult > 0)
									{
										forObject = createObjectDAO.getObject(value[valuesCounter].trim());
										if(forObject != null)
										{
											triplePojo.setObject(forObject);
											saveResult = saveResult + createTripleDAO.saveObjectDetailsToTriple(triplePojo);
										}
									}
								}
								else 
								{
									triplePojo.setObject(forObject);
									saveResult = saveResult + createTripleDAO.saveObjectDetailsToTriple(triplePojo);
								}
			        		}
			        	}
			        }
			    }
			        	/*objectPojoLlist = new ArrayList();
			        	for(int valuesCounter=0;valuesCounter<value.length;valuesCounter++)
			        	{
			        		
			        		if(!(value[valuesCounter].equalsIgnoreCase("") || value[valuesCounter] == null ))
			        		{
								forObject = createObjectDAO.getObject(value[valuesCounter]);
								
								if(forObject == null)
								{
									forObject = new ObjectPojo();
									forObject.setObjectName(value[valuesCounter]);
									forObject.setClassId(null);
									newObjectInsertResult = 0;
									//---------------------------------
									
									objectPojoLlist.add(forObject);
									
									//---------------------------------
									newObjectInsertResult = createObjectDAO.insertObject(forObject);
									if(newObjectInsertResult > 0)
									{
										forObject = createObjectDAO.getObject(value[valuesCounter]);
										if(forObject != null)
										{
											triplePojo.setObject(forObject);
											//triplelist.add(triplePojo);
											saveResult = saveResult + createTripleDAO.saveObjectDetailsToTriple(triplePojo);
										}
									}
									
								}
								else 
								{
									System.out.println("1object name : "+forObject.getObjectName()+"  predicate name  : "+forPredicate.getPredicateName());
									triplePojo = new Triple();
									triplePojo.setPredicate(forPredicate);
									triplePojo.setObject(forObject);
									triplelist.add(triplePojo);
									saveResult = saveResult + createTripleDAO.saveObjectDetailsToTriple(triplePojo);
								}
			        		}
			        	}
			        	newObjectInsertResult = createObjectDAO.insertObjectForList(objectPojoLlist);
			        	for(int valuesCounter=0;valuesCounter<value.length;valuesCounter++)
			        	{
			        		if(!(value[valuesCounter].equalsIgnoreCase("") || value[valuesCounter] == null ))
			        		{
					        	if(newObjectInsertResult > 0)
								{
									forObject = createObjectDAO.getObject(value[valuesCounter]);
									
									if(forObject != null)
									{
										System.out.println("object name : "+forObject.getObjectName()+"  predicate name  : "+forPredicate.getPredicateName());
										triplePojo = new Triple();
										triplePojo.setPredicate(forPredicate);
										triplePojo.setObject(forObject);
										triplelist.add(triplePojo);
										saveResult = saveResult + createTripleDAO.saveObjectDetailsToTriple(triplePojo);
									}
								}
			        		}
			        	}
			        }
			    }//while
//			    saveResult = saveResult + createTripleDAO.saveObjectDetailsToTripleList(triplelist);
			    
			    System.out.println("------------triplelist length-----------"+triplelist.size());*/

		}catch(DataInsertionException die)
		{
			die.printStackTrace();
			throw new DataInsertionException(Constants.DATA_INSERT_EXCEPTION);
		}catch(DataRetrievalException dre)
		{
			dre.printStackTrace();
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION);
		}
		
		return saveResult;
	}
	
	
	/**
	 * updateObjectDetailsToTriple method is used to update the details of an Object.
	 * 
	 * @param predicatesKeyValueMap
	 * @return Sequence of a newly updated record.
	 * @throws DataInsertionException
	 * @throws DataRetrievalException
	 * @throws DataUpdationException
	 * @throws DataDeletionException
	 */
	public Integer updateObjectDetailsToTriple(Map predicatesKeyValueMap) throws DataInsertionException,DataRetrievalException,DataUpdationException,DataDeletionException
	{
		Integer saveResult = 1;
		Triple triplePojo = new Triple();
		String superClass = null;
		Integer superClassId = 0;
		Integer newObjectInsertResult = 0;
		ObjectPojo forSubject = null;
		ObjectPojo forObject = null;
		Predicate forPredicate = null;
		String[] value = null;
		Map formSubmition = predicatesKeyValueMap;
		try
		{
			String subjectName[] = (String[])formSubmition.get("objectName");
			String isOfType[] = (String[])formSubmition.get("isOfType");
			ClassPojo classPojo = createObjectDAO.getClass(isOfType[0].trim());
			if(subjectName[0] != null)
			{
				forSubject = createObjectDAO.getObject(subjectName[0].trim());
				superClass = createObjectDAO.getClassName(subjectName[0].trim());
			}
			if(superClass != null)
			{
				superClassId = createObjectDAO.getClassIdByName(superClass.trim());
			}
			Iterator mapIterator = formSubmition.entrySet().iterator();
			 while(mapIterator.hasNext())
			 {
				 Map.Entry pairs = (Map.Entry)mapIterator.next();
			     if(pairs.getValue() != null)
			        {
			        	value = (String[])pairs.getValue();
			        }
			     if (!(pairs.getKey().toString().equalsIgnoreCase("objectName")
						|| pairs.getKey().toString().equalsIgnoreCase("save")
						|| pairs.getKey().toString().equalsIgnoreCase("action")
						|| pairs.getKey().toString().equalsIgnoreCase(
								"newPropertyAddingToSuperClassValue")
						|| pairs.getKey().toString().equalsIgnoreCase("propertyHidden")
						|| pairs.getKey().toString().equalsIgnoreCase("propertiesCountHidden")
						|| pairs.getKey().toString().equalsIgnoreCase("isOfType")
						|| pairs.getKey().toString().equalsIgnoreCase(
								"newPropertyAddingToSuperClass")
						|| pairs.getKey().toString().equalsIgnoreCase("") || pairs
						.getKey() == null))
			        {
			    	 	boolean status = false;
			    	 	String testInput = pairs.getKey().toString();
			    	 	char check = testInput.charAt(0);
			    	 	boolean stringStatus = false;
			    	 	char[] testStr = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','.',',','/',';',':','"','[',']','{','}','(',')','|','/','?','<','>','+','=','-','_','*','&','^','%','$','#','@','!','~','`'};
			    	 	
			    	 	for(int i=0;i<testStr.length;i++)
			    	 	{
			    	 		if((""+check).equalsIgnoreCase(""+testStr[i]) )
			    	 		{
			    	 			stringStatus = true;
			    	 		}
			    	 	}
			    	 	if(stringStatus == true)
			    	 	{
			    	 		String key = pairs.getKey().toString();
			    	 		String[] values = (String[])pairs.getValue();
			    	 		List predicateValuesList = createTripleDAO.getPredicateValuesFromTriple(subjectName[0].trim(), key);
			    	 		for(int i=0;i<values.length;i++)
			    	 		{
			    	 			boolean insertNewPredicateValueStatus = false;
			    	 			for (Iterator iterator = predicateValuesList.iterator(); iterator.hasNext();) 
				    	 		{
									Triple triPojo = (Triple) iterator.next();
									if(values[i].equalsIgnoreCase(triPojo.getObject().getObjectName()))
									{
										insertNewPredicateValueStatus = true;
									}
								}
			    	 			if(insertNewPredicateValueStatus != true)
			    	 			{
			    	 				forObject = createObjectDAO.getObject(values[i].trim());
			    	 				forPredicate = predicateDAO.getPredicateFromParents(key,classPojo.getClassId());
									triplePojo.setPredicate(forPredicate);
									triplePojo.setSubject(forSubject);
									if(forObject == null)
									{
										forObject = new ObjectPojo();
										forObject.setObjectName(values[i]);
										forObject.setClassId(null);
										newObjectInsertResult = 0;
										newObjectInsertResult = createObjectDAO.insertObject(forObject);
										if(newObjectInsertResult > 0)
										{
											forObject = createObjectDAO.getObject(values[i].trim());
											if(forObject != null)
											{
												triplePojo.setObject(forObject);
												saveResult = saveResult + createTripleDAO.saveObjectDetailsToTriple(triplePojo);
											}
										}
									}
									else 
									{
										triplePojo.setObject(forObject);
										saveResult = saveResult + createTripleDAO.saveObjectDetailsToTriple(triplePojo);
									}
			    	 			}
			    	 		}
			    	 	}
			    	 	else
			    	 	{
				    	 	String key = pairs.getKey().toString();
				        	Integer formTripleId = Integer.parseInt(key.trim());
				        	Triple predicateValueFromTriple = null;
				        	List predicateValuesList = createTripleDAO.getPredicateMulitpleValuesByTripleId(formTripleId);
			        		if(predicateValuesList != null)
			        		{
			        			for (Iterator iterator = predicateValuesList.iterator(); iterator.hasNext();) 
					        	{
					        		predicateValueFromTriple = (Triple) iterator.next();
					        		if(formTripleId.intValue() == predicateValueFromTriple.getTripleId().intValue())
					        		{
					        			status = true;
					        			if(value[0].equalsIgnoreCase(""))
					        			{
					        				createTripleDAO.deletePredicateValueFromTriple(predicateValueFromTriple);
					        			}
					        			else if(!value[0].equalsIgnoreCase(predicateValueFromTriple.getObject().getObjectName().trim()))
					        			{
					        				forObject = createObjectDAO.getObject(value[0].trim());
					        				if(forObject == null)
											{
												forObject = new ObjectPojo();
												forObject.setObjectName(value[0]);
												forObject.setClassId(null);
												newObjectInsertResult = 0;
												newObjectInsertResult = createObjectDAO.insertObject(forObject);
												if(newObjectInsertResult > 0)
												{
													forObject = createObjectDAO.getObject(value[0].trim());
													if(forObject != null)
													{
														predicateValueFromTriple.setObject(forObject);
														saveResult = createTripleDAO.updateObjectDetailsToTriple(predicateValueFromTriple);
													}
												}
											}
											else 
											{
												predicateValueFromTriple.setObject(forObject);
												saveResult = saveResult + createTripleDAO.updateObjectDetailsToTriple(predicateValueFromTriple);
											}
					        			}
					        			
					        		}
					        	}//predicateValues[] From DataBase (for loop)
				    	 	
			        			if(status == false)
			        			{
			        			}
			        		}//if predicateValuesList is null 
			    	 	}
			        }
			 }//while end
		}catch(DataInsertionException die)
		{
			throw new DataInsertionException(Constants.DATA_INSERT_EXCEPTION);
		}catch(DataRetrievalException dre)
		{
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION);
		}catch(DataUpdationException due)
		{
			throw new DataUpdationException(Constants.DATA_UPDATE_EXCEPTION);
		}catch(DataDeletionException dde)
		{
			throw new DataDeletionException(Constants.DATA_DELETE_EXCEPTION);
		}
		 
		return saveResult;
	}
	
	/**
	 * saveNewProperty method is used to insert a new Predicate Name to Objects super class and Predicate value
	 * to the Object.
	 * 
	 * @param predicateName
	 * @param predicateValue
	 * @param objectName
	 * @param className
	 * @return Sequence of a newly updated record.
	 * @throws DataInsertionException
	 * @throws DataRetrievalException
	 */
	public Integer saveNewProperty(String predicateName,String predicateValue,String objectName,String className) throws DataInsertionException,DataRetrievalException
	{
		Integer saveResult = 0;
		try
		{
			ClassPojo classPojo = createObjectDAO.getClass(className.trim());
			if(classPojo != null)
			{
				Predicate predicatePojo = predicateDAO.getPredicateFromParents(predicateName.trim(),classPojo.getClassId());
				
				if(predicatePojo == null)
				{
					saveResult = predicateDAO.saveNewPredicate(predicateName.trim(),classPojo);
					if(saveResult > 0)
					{
						Triple triplePojo = new Triple();
						ObjectPojo objectPojo = createObjectDAO.getObject(predicateValue.trim());
						if(objectPojo == null)
						{
							objectPojo = new ObjectPojo();
							objectPojo.setClassId(null);
							objectPojo.setObjectName(predicateValue.trim());
							Integer saveObject = createObjectDAO.insertObject(objectPojo);
						}
						objectPojo = createObjectDAO.getObject(predicateValue.trim());
						if(objectPojo != null)
						{
							ObjectPojo subjectPojo = createObjectDAO.getObject(objectName.trim());
							predicatePojo = predicateDAO.getPredicate(predicateName.trim(), classPojo.getClassId());
							triplePojo.setSubject(subjectPojo);
							triplePojo.setObject(objectPojo);
							triplePojo.setPredicate(predicatePojo);
							createTripleDAO.saveObjectDetailsToTriple(triplePojo);
						}
					}
					
				}
			}
		}catch(DataInsertionException die)
		{
			throw new DataInsertionException(Constants.DATA_INSERT_EXCEPTION);
		}catch(DataRetrievalException dre)
		{
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION);
		}
		return saveResult;
	}

	/* (non-Javadoc)
	 * @see com.service.CreateObjectService#saveObjectImage(byte[], java.lang.String, java.lang.String)
	 */
	public String saveObjectImage(ObjectDescriptionDTO objectDescriptionDTO)throws DataInsertionException {
		String status = null;
		Integer objDescId = null;
		byte[] objectImage = null;
		Integer objectId = null;
		String objectDescriptionFromPage = null;
		ObjectDescription objectDescription = null;
		ObjectPojo objectPojo = new ObjectPojo();
		try {
			objectDescriptionFromPage = objectDescriptionDTO.getObjectDescription();
			objectImage = objectDescriptionDTO.getImageFile();
			objDescId = objectDescriptionDTO.getObjectDescriptionId();
			objectId = objectDescriptionDTO.getObjectId();
			System.out.println("objectDescriptionDTO.getObjectId()...................."+objectDescriptionDTO.getObjectId());
			objectPojo.setObjectId(objectId);			
			
			if(objDescId != null && objDescId != 0){
				objectDescription = createObjectDAO.readObjectDescription(objDescId);
			}
			else{
				objectDescription = new ObjectDescription();
			}
			if(objectImage != null){
				objectDescription.setObjectImage(objectImage);
			}
			objectDescription.setObjectDesc(objectDescriptionFromPage);			
			objectDescription.setObjectDescriptionId(objDescId);			
			objectDescription.setObjectPojo(objectPojo);
			status = createObjectDAO.saveObjectImage(objectDescription);
			
		} catch (DataRetrievalException drExcep) {			
			drExcep.printStackTrace();
			throw new DataInsertionException(Constants.DATA_INSERT_EXCEPTION, drExcep);
		}catch (Exception excep) {
			excep.printStackTrace();
			throw new DataInsertionException(Constants.DATA_INSERT_EXCEPTION, excep);
		}
		return status;
	}

	/* (non-Javadoc)
	 * @see com.service.CreateObjectService#readObjectImage(java.lang.String)
	 */
	public ObjectDescriptionDTO readObjectImage(String objectName)throws DataRetrievalException {
		ObjectDescriptionDTO objectDescripDTO = new ObjectDescriptionDTO();		
		try {
			ObjectPojo objectPojo = createObjectDAO.getObjectDetails(objectName.trim());
			Integer objectId = null;
			if(objectPojo != null){
				objectId = objectPojo.getObjectId();
			}			
			ObjectDescription objectDescription = createObjectDAO.readObjectImage(objectId);				
			
			objectDescripDTO.setImageFile(objectDescription.getObjectImage());
			objectDescripDTO.setObjectDescription(objectDescription.getObjectDesc());
			objectDescripDTO.setObjectDescriptionId(objectDescription.getObjectDescriptionId());
			ObjectPojo objectPoj = objectDescription.getObjectPojo();			
			if(objectPoj != null){
				objectDescripDTO.setObjectName(objectPoj.getObjectName());
			}
			if(StringUtils.isNotBlank(objectDescription.getObjectDesc())){
				objectDescripDTO.setObjectDescriptionHeading("Object Description :");
			}else{
				objectDescripDTO.setObjectDescriptionHeading("Enter the Object Description here :");
			}
		} catch (DataRetrievalException dtException) {
			dtException.printStackTrace();
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION , dtException );			
		}catch(Exception exception){
			exception.printStackTrace();
			throw new DataRetrievalException(Constants.DATA_RETRIEVE_EXCEPTION , exception );
		}
		return objectDescripDTO;
	}
	
	/* (non-Javadoc)
	 * @see com.service.CreateObjectService#updatePredicateValue(com.dto.TripleDTO)
	 */
	public TripleDTO updatePredicateValue(TripleDTO tripleDTO) throws DataRetrievalException ,DataInsertionException, DataUpdationException{
		
		ObjectPojo objectPojo = createObjectDAO.getObject(tripleDTO.getPredicateValue().trim());
		Integer saveObjectResult = 0;
		if(objectPojo == null)
		{
			objectPojo = new ObjectPojo();
			objectPojo.setObjectName(tripleDTO.getPredicateValue().trim());
			objectPojo.setClassId(null);
			saveObjectResult = createObjectDAO.insertObject(objectPojo);
		}
		if(saveObjectResult >0){
			objectPojo = createObjectDAO.getObject(tripleDTO.getPredicateValue().trim());
		}
		Triple triple = createTripleDAO.getTriplePojo(tripleDTO.getTripleId());
		
		if(triple != null){
			triple.setObject(objectPojo);			
			createTripleDAO.updateObjectDetailsToTriple(triple);
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.service.CreateObjectService#updateObjectName(com.dto.ObjectDTO)
	 */
	public String updateObjectName(ObjectDTO objectDTO) throws DataRetrievalException, DataUpdationException {
		ObjectPojo objectPojo = new ObjectPojo();
		objectPojo.setObjectId(objectDTO.getObjectId());
		objectPojo = createObjectDAO.getObjectById(objectPojo);
		objectPojo.setObjectName(objectDTO.getObjectName().trim());
		String updatedStatus = createObjectDAO.updateObjectName(objectPojo);
		
		if(updatedStatus!=null && updatedStatus.equals(updatedStatus)){
			updatedStatus = "Changes have been saved for '"+objectDTO.getIsOfType()+"'.";
		}
		else{
			updatedStatus = "Unable to update '"+objectDTO.getIsOfType()+"'.";
		}
		return updatedStatus;
	}
	
	/* (non-Javadoc)
	 * @see com.service.CreateObjectService#getObjectByName(java.lang.String)
	 */
	public Integer getObjectByName(String objectName)
			throws DataRetrievalException {
		ObjectPojo objectPojo = createObjectDAO.getObject(objectName.trim());
		Integer objectId = 0;
		if(objectPojo != null){
			objectId = objectPojo.getObjectId();
		}
		return objectId;
	}


}
