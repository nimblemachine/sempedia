/**
 * 
 */
package com.controller;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.beans.ObjectBean;
import com.beans.ObjectDetailsBean;
import com.beans.SavePredicateValueBean;
import com.delegate.CreateObjectDelegate;
import com.dto.ObjectDTO;
import com.dto.ObjectDescriptionDTO;
import com.dto.TripleDTO;
import com.exceptions.DataRetrievalException;
import com.exceptions.DataUpdationException;
import com.util.Constants;

/**
 * @author viswanathp
 * 
 */
public class CreateObjectController extends MultiActionController {
	private CreateObjectDelegate createObjectDelegate;

	/**
	 * addObject method is used to insert a new object with one as its super class.
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param command ObjectBean which is the form bean for createObject.jsp and editObject.jsp
	 * @return Object Name
	 * @return Super Class Name
	 * @return Predicate names of all super classes.
	 * @throws ServletException 
	 * @throws Exception
	 */
	public ModelAndView addObject(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ObjectBean command) throws ServletException, Exception {
		ModelAndView modelAndViewObj = new ModelAndView("createObject");
		try {
			ObjectBean objectBean = (ObjectBean) command;
			if (objectBean.getObjectName() != null
					&& objectBean.getIsOfType() != null) {
				if(!(objectBean.getObjectName().equalsIgnoreCase("") || objectBean.getIsOfType().equalsIgnoreCase("")))
				{
					
				ObjectDTO objectDTO = new ObjectDTO();
				objectDTO.setObjectName((objectBean.getObjectName()));
				objectDTO.setIsOfType(objectBean.getIsOfType());
				Map<String,String> objectMap = null;
				Map<String,String> predicatesMap = null;
				String message = null;
				String color = null;
				List<Map<String,String>> totalMaps = createObjectDelegate.insertObject(objectDTO);
				if (totalMaps != null) {
					objectMap = totalMaps.get(0);
					message = objectMap.get("message");
					objectMap.remove("message");
					color = objectMap.get("color");
					objectMap.remove("color");
					predicatesMap = totalMaps.get(1);
				}
				modelAndViewObj.addObject("message", message);
				modelAndViewObj.addObject("color",color);
				modelAndViewObj.addObject("objectsList", objectMap);
				modelAndViewObj.addObject("predicatesList", predicatesMap);
				}
			}
		} catch (Exception exception) {
			modelAndViewObj.addObject("exceptionMessage", exception
					.getMessage());
		}
		return modelAndViewObj;
	}

	/**
	 * saveObjectDetails method is used to insert predicate values of an Object.
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param command ObjectBean which is the form bean for createObject.jsp and editObject.jsp
	 * @return nothing.
	 * @throws ServletException 
	 * @throws Exception
	 */
	public ModelAndView saveObjectDetails(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ObjectBean command) throws ServletException, Exception {
		ModelAndView modelAndViewObj = new ModelAndView("createObject");
		try {
			String message = null;
			String color = null;
			Integer saveResult = 0;
				
			Map formSubmition = request.getParameterMap();
			saveResult = createObjectDelegate
					.saveObjectDetailsToTriple(formSubmition);
			if (saveResult > 0) {
				message = "Object details stored successfully";
				color = "blue";
			} else {
				message = "Object details not stored successfully";
				color = "red";
			}
			modelAndViewObj.addObject("message", message);
			modelAndViewObj.addObject("color",color);
		} catch (Exception exception) {
			modelAndViewObj.addObject("exceptionMessage", exception
					.getMessage());
		}
		return modelAndViewObj;
	}

	/**
	 * editObject method is used to retrieve the details of an Object.
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param command ObjectBean which is the form bean for createObject.jsp and editObject.jsp
	 * @return Object Name
	 * @return Super Class Name
	 * @return Predicate names of all super classes.
	 * @return Predicate values of an Object.
	 * @throws ServletException 
	 * @throws Exception
	 */
	public ModelAndView editObject(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ObjectBean command) throws ServletException, Exception {
		ObjectBean objectBean = (ObjectBean) command;
		ModelAndView modelAndViewObj = new ModelAndView("editObject");
		try {
			if (objectBean.getObjectName() != null) {
				String message = null;
				String color = null;
				
				Map<String,String> objectMap = null;
				Map<String,List<ObjectDetailsBean>> predicatesMap = null;
				ObjectDTO objectDTO = new ObjectDTO();
				objectDTO.setObjectName((objectBean.getObjectName()));
				List totalMaps = createObjectDelegate.editObject(objectDTO);

				if (totalMaps != null) {
					objectMap = (Map) totalMaps.get(0);
					message = (String) objectMap.get("message");
					objectMap.remove("message");
					color = (String) objectMap.get("color");
					objectMap.remove("color");
					predicatesMap = (Map) totalMaps.get(1);
				}
				modelAndViewObj.addObject("message", message);
				modelAndViewObj.addObject("color", color);
				modelAndViewObj.addObject("objectsList", objectMap);
				modelAndViewObj.addObject("predicatesList", predicatesMap);
			}
		} catch (Exception exception) {
			modelAndViewObj.addObject("exceptionMessage", exception
					.getMessage());
		}
		return modelAndViewObj;
	}

	/**
	 * updateObjectDetails method is used to update the details of an Object.
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param command ObjectBean which is the form bean for createObject.jsp and editObject.jsp
	 * @return nothing
	 * @throws ServletException 
	 */
	public ModelAndView updateObjectDetails(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ObjectBean command) throws ServletException {
		ModelAndView modelAndViewObj = new ModelAndView("createObject");
		try {
			Map<String, String> formSubmition = request.getParameterMap();
			String message = null;
			String color = null;
			Integer saveResult = createObjectDelegate
					.updateObjectDetailsToTriple(formSubmition);
			if (saveResult > 0) {
				message = "Object details updated successfully";
				color = "blue";
			} else {
				message = "Object details not updated successfully";
				color = "red";
			}
			modelAndViewObj.addObject("message", message);
			modelAndViewObj.addObject("color", color);
		} catch (Exception exception) {
			modelAndViewObj.addObject("exceptionMessage", exception
					.getMessage());
		}
		return modelAndViewObj;
	}

	/**
	 * saveNewProperty method is used to insert a new Predicate name to super class of an Object and 
	 * Predicate values of an Object.
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param command ObjectBean which is the form bean for createObject.jsp and editObject.jsp
	 * @return Object Name
	 * @return Super Class Name
	 * @return Predicate names of all super classes.
	 * @return Predicate values of an Object.
	 * @throws ServletException 
	 * @throws Exception
	 */
	public ModelAndView saveNewProperty(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ObjectBean command) throws ServletException, Exception {
		ModelAndView modelAndViewObj = new ModelAndView("editObject");
		try {
			ObjectBean objectBean = (ObjectBean) command;
			Integer savePropertyResult = 0;
			if (objectBean.getNewPredicate() != null
					&& objectBean.getNewPredicateValue() != null
					&& objectBean.getIsOfType() != null
					&& objectBean.getObjectName() != null) {
				if(!(objectBean.getNewPredicate().equalsIgnoreCase("") || objectBean.getNewPredicateValue().equalsIgnoreCase("")))
				{
				savePropertyResult = createObjectDelegate.saveNewProperty(
						objectBean.getNewPredicate(), objectBean
								.getNewPredicateValue(), objectBean
								.getObjectName(), objectBean.getIsOfType());
				}
			}
			if (objectBean.getObjectName() != null) {
				String message = null;
				String color = null;
				Map<String,String> objectMap = null;
				Map<String,List<ObjectDetailsBean>> predicatesMap = null;
				ObjectDTO objectDTO = new ObjectDTO();
				objectDTO.setObjectName((objectBean.getObjectName()));
				List totalMaps = createObjectDelegate.editObject(objectDTO);
				if (totalMaps != null) {
					objectMap = (Map) totalMaps.get(0);
					message = (String) objectMap.get("message");
					objectMap.remove("message");
					color = (String)objectMap.get("color");
					objectMap.remove("color");
					predicatesMap = (Map) totalMaps.get(1);
				}
				if(savePropertyResult > 0)
				{
					message = "New property and its value are saved successfully";
					color = "blue";
				}
				else
				{
					message = "New property and its value are not saved successfully";
					color = "red";
				}
				modelAndViewObj.addObject("message", message);
				modelAndViewObj.addObject("color", color);
				modelAndViewObj.addObject("objectsList", objectMap);
				modelAndViewObj.addObject("predicatesList", predicatesMap);
			}
		} catch (Exception exception) {
			modelAndViewObj.addObject("exceptionMessage", exception
					.getMessage());
		}
		return modelAndViewObj;
	}

	/**
	 * saveCroppedImage method is used to insert a new object Image 
	 * and description also updates the Object's description and image .
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param command ObjectBean which is the form bean used to get the object details from the jsp.
	 * @return Object Name
	 * @return Super Class Name
	 * @return Predicate names of all super classes.
	 * @return Object Description Details.
	 * @throws ServletException 
	 * @throws Exception
	 */
	public ModelAndView saveCroppedImage(HttpServletRequest request,HttpServletResponse response,HttpSession session, ObjectBean command) throws ServletException, Exception
	{
		String status = null;
		String message = null;
		String updatedMessage = null;
		ModelAndView modelAndViewObj = new ModelAndView("editObjectForDescription");	
		
		ObjectBean objectBean = (ObjectBean) command;
		ObjectDescriptionDTO objectDescDTO = new ObjectDescriptionDTO();		
			
		MultipartFile file = objectBean.getImageFile();
		String objectName = objectBean.getObjectName().trim();
		
		String objectDescription = objectBean.getObjectDescription();
		Integer objectDescriptionId = objectBean.getObjectDescriptionId();
		Integer objectId = objectBean.getObjectId();
		String hiddenObjectName = objectBean.getHiddenObjectName();
		objectDescDTO.setObjectName(objectName);
		objectDescDTO.setObjectDescriptionId(objectDescriptionId);
		objectDescDTO.setObjectDescription(objectDescription);
		
		if(file.getSize() > Constants.IMAGE_SIZE){
			modelAndViewObj.addObject("exceptionMessage","Image size is too big. Maximum size allowed is "+Constants.IMAGE_SIZE_LIMIT+" KB.");
			modelAndViewObj.addObject("objectNameFromObjectPage", objectName);
			modelAndViewObj.addObject("objectDescription", objectDescDTO.getObjectDescription());
			modelAndViewObj.addObject("objectDescriptionId", objectDescDTO.getObjectDescriptionId());
			modelAndViewObj.addObject("objectDescriptionHeading", objectDescDTO.getObjectDescriptionHeading());
			return modelAndViewObj;
		}
		if(StringUtils.isNotBlank(file.getOriginalFilename())){
			objectDescDTO.setImageFile(file.getBytes());
		}
		else{
			objectDescDTO.setImageFile(null);
		}
		
		try{
			ObjectDTO objectDTO = new ObjectDTO();
			objectDTO.setObjectId(objectId);
			objectDTO.setObjectName(objectName);
			Integer forCheckObjectId = 0;
			if(objectName !=null && !objectName.equalsIgnoreCase(hiddenObjectName) ){
				forCheckObjectId = createObjectDelegate.getObjectByName(objectName);
			}
			
			if (forCheckObjectId == 0)
			{	
				if(objectId == null || objectId == 0){				
					objectId =createObjectDelegate.getObjectByName(hiddenObjectName);
				}
				objectDescDTO.setObjectId(objectId);	
				
				updatedMessage = createObjectDelegate.updateObjectName(objectDTO);				
				status = createObjectDelegate.saveObjectImage(objectDescDTO); 				
				Map<String,String> objectMap = null;				
				Map<String,List<ObjectDetailsBean>> predicatesMap = null;
				ObjectDTO objectDTO1 = new ObjectDTO();
				objectDTO1.setObjectName((objectBean.getObjectName()));
				List totalMaps = createObjectDelegate.editObject(objectDTO1);

				if (totalMaps != null) {
					objectMap = (Map) totalMaps.get(0);
					message = (String) objectMap.get("message");
					objectMap.remove("message");
					predicatesMap = (Map) totalMaps.get(1);
				}
				modelAndViewObj.addObject("message", message);
				modelAndViewObj.addObject("objectsList", objectMap);
				modelAndViewObj.addObject("predicatesList", predicatesMap);
				
				ObjectDescriptionDTO objectDescriptionDTO = createObjectDelegate.readObjectImage(objectName);
				modelAndViewObj.addObject("objectNameFromObjectPage", objectName);
				modelAndViewObj.addObject("objectIdFromObjectPage", objectId);
				modelAndViewObj.addObject("objectDescription", objectDescriptionDTO.getObjectDescription());
				modelAndViewObj.addObject("objectDescriptionId", objectDescriptionDTO.getObjectDescriptionId());
				modelAndViewObj.addObject("objectDescriptionHeading", objectDescriptionDTO.getObjectDescriptionHeading());
				modelAndViewObj.addObject("userMessage","Changes have been saved");
			
			}
			else
			{
				Map<String,String> objectMap = null;				
				Map<String,List<ObjectDetailsBean>> predicatesMap = null;
				ObjectDTO objectDTO1 = new ObjectDTO();
				objectDTO1.setObjectName(hiddenObjectName);
				List totalMaps = createObjectDelegate.editObject(objectDTO1);

				if (totalMaps != null) {
					objectMap = (Map) totalMaps.get(0);
					message = (String) objectMap.get("message");
					objectMap.remove("message");
					predicatesMap = (Map) totalMaps.get(1);
				}
				modelAndViewObj.addObject("message", message);
				modelAndViewObj.addObject("objectsList", objectMap);
				modelAndViewObj.addObject("predicatesList", predicatesMap);
				
				ObjectDescriptionDTO objectDescriptionDTO = createObjectDelegate.readObjectImage(hiddenObjectName);
				modelAndViewObj.addObject("objectNameFromObjectPage", hiddenObjectName);
				modelAndViewObj.addObject("objectIdFromObjectPage", objectId);
				modelAndViewObj.addObject("objectDescription", objectDescriptionDTO.getObjectDescription());
				modelAndViewObj.addObject("objectDescriptionId", objectDescriptionDTO.getObjectDescriptionId());
				modelAndViewObj.addObject("objectDescriptionHeading", objectDescriptionDTO.getObjectDescriptionHeading());
				modelAndViewObj.addObject("exceptionMessage","Object Name '"+objectName+"' already exists, give another name if you want to change Object Name");
			}
		}
		catch (Exception exception) {
			modelAndViewObj.addObject("exceptionMessage",exception.getMessage());
		}		
		return modelAndViewObj;
	}
	
	/**
	 * Read image method is used to read the image from database this method is called from the src tag action.
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param command ObjectBean which is the form bean used to get the object details from the jsp.
	 * @return Object's Image.
	 * @throws ServletException 
	 * @throws Exception
	 */
	public void readImage(HttpServletRequest request,HttpServletResponse response,HttpSession session, ObjectBean command) throws ServletException, Exception
	{
		ObjectBean objectBean = (ObjectBean) command;		
		String objectName = objectBean.getObjectName();	
		ObjectDescriptionDTO objectDescriptionDTO = null;
		ModelAndView modelAndViewObj = new ModelAndView("editObjectForDescription");
		
		if(StringUtils.isNotBlank(objectName)){
			objectDescriptionDTO = createObjectDelegate.readObjectImage(objectName);
			byte[] imageArray = objectDescriptionDTO.getImageFile();
			
			if(imageArray != null){
				OutputStream output = response.getOutputStream();
				output.write(imageArray);
				output.flush();
				output.close();
				//System.out.println("..........."+output.toString());
				 
			}
		}		
		//return modelAndViewObj;
	}
	
	/**
	 * readDescription method is to read the Object description and other details. Thease are used to show the user. 
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param command ObjectBean which is the form bean used to get the object details from the jsp.
	 * @return Object Name
	 * @return Super Class Name
	 * @return Predicate names of all super classes.
	 * @return Object Description Details.
	 * @throws ServletException 
	 * @throws Exception
	 */
	public ModelAndView readDescription(HttpServletRequest request,HttpServletResponse response,HttpSession session, ObjectBean command) throws ServletException, Exception
	{
		String message = null;
		ObjectBean objectBean = (ObjectBean) command;		
		String objectName = objectBean.getObjectName();	
		Integer objectId = objectBean.getObjectId();
		ModelAndView modelAndViewObj = new ModelAndView("editObjectForDescription");
		try{
			if(StringUtils.isNotBlank(objectName)){
				if(objectId == null || objectId == 0){				
					objectId =createObjectDelegate.getObjectByName(objectName);
				}
				Map<String,String> objectMap = null;
				Map<String,List<ObjectDetailsBean>> predicatesMap = null;
				ObjectDTO objectDTO = new ObjectDTO();
				objectDTO.setObjectName((objectBean.getObjectName()));
			///	objectDTO.setObjectId(objectId);
				List totalMaps = createObjectDelegate.editObject(objectDTO);
				if (totalMaps != null) {
					objectMap = (Map) totalMaps.get(0);
					message = (String) objectMap.get("message");
					objectMap.remove("message");
					predicatesMap = (Map) totalMaps.get(1);
				}
				modelAndViewObj.addObject("message", message);
				modelAndViewObj.addObject("objectsList", objectMap);
				modelAndViewObj.addObject("predicatesList", predicatesMap);
				ObjectDescriptionDTO objectDescriptionDTO = createObjectDelegate.readObjectImage(objectName);
				String superClass = objectMap.get(objectName);
				modelAndViewObj.addObject("objectNameFromObjectPage", objectName);
				modelAndViewObj.addObject("objectIdFromObjectPage", objectId);			
				modelAndViewObj.addObject("superClass", superClass);
				modelAndViewObj.addObject("objectDescription", objectDescriptionDTO.getObjectDescription());
				modelAndViewObj.addObject("objectDescriptionId", objectDescriptionDTO.getObjectDescriptionId());
				modelAndViewObj.addObject("objectDescriptionHeading", objectDescriptionDTO.getObjectDescriptionHeading());
			}
		}
		catch (Exception exception) {
			modelAndViewObj.addObject("exceptionMessage",exception.getMessage());
		}
		return modelAndViewObj;
	}
	
	/**
	 * getObjectDetailsFromSearch method is used to get the object details of Object.
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param command ObjectBean which is the form bean used to get the object details from the jsp.
	 * @return Object Name
	 * @return Super Class Name
	 * @return Predicate names of all super classes.
	 * @return Object Description Details.
	 * @throws ServletException 
	 * @throws Exception
	 */
	public ModelAndView getObjectDetailsFromSearch(HttpServletRequest request,HttpServletResponse response,HttpSession session, ObjectBean command) throws ServletException, Exception
	{
		String message = null;
		ObjectBean objectBean = (ObjectBean) command;		
		String objectName = objectBean.getObjectName().trim();	
		Integer objectId = objectBean.getObjectId();
		ModelAndView modelAndViewObj = new ModelAndView("objectDescriptionPage");		
		try{
			if(StringUtils.isNotBlank(objectName) && objectId != null && objectId != 0){
				Map<String,String> objectMap = null;
				Map<String,List<ObjectDetailsBean>> predicatesMap = null;
				ObjectDTO objectDTO = new ObjectDTO();
				objectDTO.setObjectName((objectBean.getObjectName()));
				List totalMaps = createObjectDelegate.editObject(objectDTO);
				if (totalMaps != null) {
					objectMap = (Map) totalMaps.get(0);
					message = (String) objectMap.get("message");
					objectMap.remove("message");
					predicatesMap = (Map) totalMaps.get(1);
				}
				modelAndViewObj.addObject("message", message);
				modelAndViewObj.addObject("objectsList", objectMap);
				modelAndViewObj.addObject("predicatesList", predicatesMap);
				
				ObjectDescriptionDTO objectDescriptionDTO = createObjectDelegate.readObjectImage(objectName);
				String superClass = objectMap.get(objectName);
				modelAndViewObj.addObject("objectNameFromObjectPage", objectName);
				modelAndViewObj.addObject("superClass", superClass);
				modelAndViewObj.addObject("objectIdFromObjectPage", objectId);
				modelAndViewObj.addObject("objectDescription", objectDescriptionDTO.getObjectDescription());
				modelAndViewObj.addObject("objectDescriptionId", objectDescriptionDTO.getObjectDescriptionId());
				modelAndViewObj.addObject("objectDescriptionHeading", objectDescriptionDTO.getObjectDescriptionHeading());
			}
			//modelAndViewObj.addObject("exceptionMessage","Unable to update the '"+objectName+"'");
		}
		catch (DataRetrievalException exception) {			
			modelAndViewObj.addObject("exceptionMessage",exception.getMessage());
		}
		catch (Exception exception) {			
			modelAndViewObj.addObject("exceptionMessage",exception.getMessage());
		}
		return modelAndViewObj;
	}
	
	/**
	 * @param request
	 * @param response
	 * @param session
	 * @param command
	 * @return
	 * @throws ServletException
	 * @throws Exception
	 */
	public ModelAndView saveTripleValues(HttpServletRequest request,HttpServletResponse response,HttpSession session, SavePredicateValueBean command) throws ServletException, Exception
	{
		SavePredicateValueBean predicateBean = (SavePredicateValueBean) command;					
		ModelAndView modelAndViewObj = new ModelAndView("objectDescriptionPage");
		
		String objectName = predicateBean.getObjectName();
		Integer objectId = predicateBean.getObjectId();
		String message = null;
		try{
			TripleDTO tripleDTO = new TripleDTO();
			tripleDTO.setTripleId(predicateBean.getTripleId());
			tripleDTO.setPredicateValue(predicateBean.getPredicateValue());
			tripleDTO.setObjectName(objectName);
			
			createObjectDelegate.UpdatePredicateValue(tripleDTO);
			
			if(StringUtils.isNotBlank(objectName)){
				Map<String,String> objectMap = null;
				Map<String,List<ObjectDetailsBean>> predicatesMap = null;
				ObjectDTO objectDTO = new ObjectDTO();
				objectDTO.setObjectName(objectName);
				List totalMaps = createObjectDelegate.editObject(objectDTO);

				if (totalMaps != null) {
					objectMap = (Map) totalMaps.get(0);
					message = (String) objectMap.get("message");
					objectMap.remove("message");
					predicatesMap = (Map) totalMaps.get(1);
				}
				modelAndViewObj.addObject("message", message);
				modelAndViewObj.addObject("objectsList", objectMap);
				modelAndViewObj.addObject("predicatesList", predicatesMap);
				
				ObjectDescriptionDTO objectDescriptionDTO = createObjectDelegate.readObjectImage(objectName);
				String superClass = objectMap.get(objectName);
				modelAndViewObj.addObject("objectNameFromObjectPage", objectName);
				modelAndViewObj.addObject("objectIdFromObjectPage", objectId);
				modelAndViewObj.addObject("superClass", superClass);
				modelAndViewObj.addObject("objectIdFromObjectPage", objectId);
				modelAndViewObj.addObject("objectDescription", objectDescriptionDTO.getObjectDescription());
				modelAndViewObj.addObject("objectDescriptionId", objectDescriptionDTO.getObjectDescriptionId());
				modelAndViewObj.addObject("objectDescriptionHeading", objectDescriptionDTO.getObjectDescriptionHeading());
				modelAndViewObj.addObject("usermessage", "Changes have been saved");
			}
		}
		catch (Exception exception) {
			modelAndViewObj.addObject("exceptionMessage",exception.getMessage());
		}
		
		return modelAndViewObj;
	}
	public ModelAndView updateSubjectName(HttpServletRequest request,HttpServletResponse response,HttpSession session, ObjectBean command) throws ServletException, Exception
	{
		String message = null;
		ObjectBean objectBean = (ObjectBean) command;		
		String objectName = objectBean.getObjectName().trim();	
		Integer objectId = objectBean.getObjectId();
		String hiddenObjectName = objectBean.getHiddenObjectName();		
		ModelAndView modelAndViewObj = new ModelAndView("objectDescriptionPage");
		try{
			if(StringUtils.isNotBlank(objectName)){
				ObjectDTO objectDTO = new ObjectDTO();
				objectDTO.setObjectName(objectName);
				objectDTO.setObjectId(objectId);
				Integer forCheckObjectId = 0;
				if(objectName != null && !objectName.equalsIgnoreCase(hiddenObjectName)){
					forCheckObjectId = createObjectDelegate.getObjectByName(objectName);
				}
				
				if (forCheckObjectId == 0)
				{
					String updatedStatus = createObjectDelegate.updateObjectName(objectDTO);
					
					Map<String,String> objectMap = null;
					Map<String,List<ObjectDetailsBean>> predicatesMap = null;
					List totalMaps = createObjectDelegate.editObject(objectDTO);
	
					if (totalMaps != null) {
						objectMap = (Map) totalMaps.get(0);
						message = (String) objectMap.get("message");
						objectMap.remove("message");
						predicatesMap = (Map) totalMaps.get(1);
					}
					modelAndViewObj.addObject("message", message);
					modelAndViewObj.addObject("objectsList", objectMap);
					modelAndViewObj.addObject("predicatesList", predicatesMap);	
					
					ObjectDescriptionDTO objectDescriptionDTO = createObjectDelegate.readObjectImage(objectName);
					String superClass = objectMap.get(objectName);
					modelAndViewObj.addObject("objectNameFromObjectPage", objectName);
					modelAndViewObj.addObject("superClass", superClass);
					modelAndViewObj.addObject("objectIdFromObjectPage", objectId);
					modelAndViewObj.addObject("objectDescription", objectDescriptionDTO.getObjectDescription());
					modelAndViewObj.addObject("objectDescriptionId", objectDescriptionDTO.getObjectDescriptionId());
					modelAndViewObj.addObject("objectDescriptionHeading", objectDescriptionDTO.getObjectDescriptionHeading());
				}
				else{
					Map<String,String> objectMap = null;
					Map<String,List<ObjectDetailsBean>> predicatesMap = null;
					ObjectDTO objectDTO1 = new ObjectDTO();
					objectDTO1.setObjectName((objectBean.getHiddenObjectName()));
					objectDTO1.setObjectId(objectId);
					List totalMaps = createObjectDelegate.editObject(objectDTO1);

					if (totalMaps != null) {
						objectMap = (Map) totalMaps.get(0);
						message = (String) objectMap.get("message");
						objectMap.remove("message");
						predicatesMap = (Map) totalMaps.get(1);
					}
					modelAndViewObj.addObject("message", message);
					modelAndViewObj.addObject("objectsList", objectMap);
					modelAndViewObj.addObject("predicatesList", predicatesMap);	
					
					ObjectDescriptionDTO objectDescriptionDTO = createObjectDelegate.readObjectImage(hiddenObjectName);
					String superClass = objectMap.get(hiddenObjectName);
					modelAndViewObj.addObject("objectNameFromObjectPage", hiddenObjectName);
					modelAndViewObj.addObject("superClass", superClass);
					modelAndViewObj.addObject("objectIdFromObjectPage", objectId);
					modelAndViewObj.addObject("objectDescription", objectDescriptionDTO.getObjectDescription());
					modelAndViewObj.addObject("objectDescriptionId", objectDescriptionDTO.getObjectDescriptionId());
					modelAndViewObj.addObject("objectDescriptionHeading", objectDescriptionDTO.getObjectDescriptionHeading());
					modelAndViewObj.addObject("exceptionMessage","Object Name '"+objectName+"' already exists, give another name if you want to change Object Name");
				}
			}
		}
		catch (DataRetrievalException exception) {
			modelAndViewObj.addObject("exceptionMessage",exception.getMessage());
		}
		catch (DataUpdationException exception) {
			modelAndViewObj.addObject("exceptionMessage",exception.getMessage());
		}
		return modelAndViewObj;
	}
	/**
	 * @return the createClassDelegate
	 */
	public CreateObjectDelegate getCreateObjectDelegate() {
		return createObjectDelegate;
	}

	/**
	 * @param createObjectDelegate
	 *            the createObjectDelegate to set
	 */
	public void setCreateObjectDelegate(
			CreateObjectDelegate createObjectDelegate) {
		this.createObjectDelegate = createObjectDelegate;
	}

}
