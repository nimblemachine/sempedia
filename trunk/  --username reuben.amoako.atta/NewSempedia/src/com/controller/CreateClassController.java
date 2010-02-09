package com.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.beans.ClassBean;
import com.beans.ClassDetailsBean;
import com.delegate.CreateClassDelegate;
import com.dto.ClassDTO;
import com.exceptions.DataInsertionException;

public class CreateClassController extends MultiActionController {

	private CreateClassDelegate createClassDelegate;
	Set<String> finalPropeties;
	Set<String> tempPropeties;
	Set<String> subClassProperties;
	String message = null;

	/**
	 * 
	 * classPage method is used to just open the addClass view when clicked on
	 * Add Class Image in the header.
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param command
	 * @return Returns addClass view
	 * @throws ServletException
	 */
	public ModelAndView classPage(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ClassBean command)
			throws ServletException {
		ModelAndView modelAndViewObj = new ModelAndView("addClass");
		return modelAndViewObj;
	}

	/**
	 * addClass method is used to insert a newly created class by clicking if
	 * the newly created class exists before or not
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param command
	 *            ClassBean which is the form bean for the view addClass.jsp
	 * @return Returns class properties like name of the class and Inherited
	 *         class and the message saying class created or not
	 * @throws ServletException
	 */
	public ModelAndView addClass(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ClassBean command)
			throws ServletException {
		ClassBean classBean = (ClassBean) command;
		ModelAndView modelAndViewObj = new ModelAndView("addClass");
		ClassDTO classDTO = new ClassDTO();
		ClassDetailsBean classDetailsBean = new ClassDetailsBean();
		classDTO.setClassName(classBean.getClassName());
		classDTO.setIsA(classBean.getIsA());
		try {
			classDetailsBean = createClassDelegate.insertClass(classDTO);
		} catch (DataInsertionException dataInsertionException) {
			modelAndViewObj.addObject("exceptionMessage",
					dataInsertionException.getMessage());
		} catch (Exception exception) {
			modelAndViewObj.addObject("exceptionMessage", exception
					.getMessage());
		}
		modelAndViewObj.addObject("message", classDetailsBean.getMessage());
		modelAndViewObj.addObject("messageColor", classDetailsBean.getColor());
		modelAndViewObj.addObject("classValue", classDetailsBean);
		return modelAndViewObj;
	}

	/**
	 * saveClass method is used to save the class details like class name, its
	 * super class if any and all the properties of the newly created class
	 * 
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param command
	 *            ClassBean which is the form bean for the view addClass.jsp
	 * @return Returns message based on creation of the class with properties.
	 * @throws ServletException
	 */
	public ModelAndView saveClass(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ClassBean command)
			throws ServletException {
		ModelAndView modelAndViewObj = new ModelAndView("addClass");
		ClassBean classBean = (ClassBean) command;
		ClassDTO classDTO = new ClassDTO();
		classDTO.setAllProperties(classBean.getAllProperties());
		classDTO.setSubClassId(classBean.getSubClassId());
		classDTO.setSuperClassId(classBean.getSuperClassId());
		classDTO.setClassName(classBean.getClassName());
		classDTO.setIsA(classBean.getIsA());
		String messageArray[];
		try {
			classDTO = createClassDelegate.setClassProperties(classDTO);
			message = createClassDelegate.insertPredicates(classDTO);
		} catch (Exception exception) {
			modelAndViewObj.addObject("exceptionMessage", exception
					.getMessage());
		}
		messageArray = message.split("~");
		modelAndViewObj.addObject("classMessage", messageArray[0]);
		modelAndViewObj.addObject("messageColor", messageArray[1]);
		return modelAndViewObj;
	}

	/**
	 * editClass method is used to open the class which is been selected for
	 * editing in edit mode by displaying all the selected class prop
	 * 
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param command
	 *            ClassBean which is the form bean for the view editClass.jsp
	 * @return Returns in edit mode of the class and displays all the properties
	 *         of the class and also displays the success or failure message
	 * @throws ServletException
	 */
	public ModelAndView editClass(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ClassBean command)
			throws ServletException {
		ClassBean classBean = (ClassBean) command;
		ModelAndView modelAndViewObj = new ModelAndView("editClass");
		ClassDetailsBean classDetailsBean = new ClassDetailsBean();
		classDetailsBean.setSubClassName(classBean.getClassName());
		try {
			classDetailsBean = createClassDelegate
					.getClassEditViewDetails(classBean.getClassName());
			
		} catch (Exception exception) {
			modelAndViewObj.addObject("exceptionMessage", exception.getMessage());
		}
		modelAndViewObj.addObject("classValue", classDetailsBean);
		return modelAndViewObj;
	}

	/**
	 * deleteProperty method is used to delete the selected property from edit
	 * Class Interface
	 * 
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param command
	 *            ClassBean which is the form bean for the view editClass.jsp
	 * @return The class in edit mode and a message about the property is been
	 *         successfully deleted or not
	 * @throws ServletException
	 */
	public ModelAndView deleteProperty(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ClassBean command)
			throws ServletException {
		ClassBean classBean = (ClassBean) command;
		ModelAndView modelAndViewObj = new ModelAndView("addClass");
		ClassDetailsBean classDetailsBean = new ClassDetailsBean();
		String messageArray[];
		try {
			message = createClassDelegate.deleteClassDetails(classBean
					.getSuperClassId(), classBean.getPropertyName());
			classDetailsBean = createClassDelegate
					.getClassEditViewDetails(classBean.getClassName());
		} catch (Exception exception) {
			modelAndViewObj.addObject("exceptionMessage", exception
					.getMessage());
		}
		messageArray = message.split("~");
		modelAndViewObj.addObject("editMessage", messageArray[0]);
		modelAndViewObj.addObject("messageColor", messageArray[1]);
		modelAndViewObj.addObject("classValue", classDetailsBean);
		return modelAndViewObj;
	}

	/**
	 * addProperty method is used to add newly created properties for an class
	 * which is opened in edit mode
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param command
	 *            ClassBean which is the form bean for the view addClass.jsp
	 * @return Returns the class with the message in edit mode of an class
	 * @throws ServletException
	 */
	public ModelAndView addProperties(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ClassBean command)
			throws ServletException {
		ClassBean classBean = (ClassBean) command;
		ClassDetailsBean classDetailsBean = new ClassDetailsBean();
		ModelAndView modelAndViewObj = new ModelAndView("editClass");
		ClassDTO classDTO = new ClassDTO();
		classDTO.setAllProperties(classBean.getAllProperties());
		classDTO.setSubClassId(classBean.getSubClassId());
		classDTO.setSuperClassId(classBean.getSuperClassId());
		classDTO.setClassName(classBean.getClassName());
		classDTO.setIsA(classBean.getIsA());
		classDTO = createClassDelegate.setClassProperties(classDTO);
		String messageArray[];
		try {
			message = createClassDelegate.insertPredicates(classDTO);
			classDetailsBean = createClassDelegate
					.getClassEditViewDetails(classBean.getClassName());
		} catch (Exception exception) {
			modelAndViewObj.addObject("exceptionMessage", exception
					.getMessage());
		}
		messageArray = message.split("~");
		modelAndViewObj.addObject("editMessage",
				"Property "+classBean.getAllProperties()+" Added Successfully");
		modelAndViewObj.addObject("messageColor", messageArray[1]);
		modelAndViewObj.addObject("classValue", classDetailsBean);
		return modelAndViewObj;
	}

	/**
	 * 
	 * editSelectClassProperty method is used to update the selected class
	 * property in edit class interface.
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param command
	 *            ClassBean which is the form bean for the view editClass.jsp
	 * @return Returns the edited class in edit mode and displays the respective
	 *         message for editing the class property.
	 * @throws ServletException
	 */
	public ModelAndView editSelectClassProperty(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ClassBean command)
			throws ServletException {
		ModelAndView modelAndViewObj = new ModelAndView("editClass");
		ClassBean classBean = (ClassBean) command;
		ClassDetailsBean classDetailsBean = new ClassDetailsBean();
		ClassDTO classDTO = new ClassDTO();
		classDTO.setClassName(classBean.getClassName());
		classDTO.setAllProperties(classBean.getAllProperties());
		classDTO.setPropertyName(classBean.getPropertyName());
		classDTO.setSuperClassId(classBean.getSuperClassId());
		String messageArray[];
		try {
			message = createClassDelegate.updateClassProperty(classDTO);
			messageArray = message.split("~");
			modelAndViewObj.addObject("editMessage", messageArray[0]);
			modelAndViewObj.addObject("messageColor", messageArray[1]);
			classDetailsBean = createClassDelegate
					.getClassEditViewDetails(classBean.getClassName());
		} catch (Exception exception) {
			modelAndViewObj.addObject("exceptionMessage", exception
					.getMessage());
		}
		modelAndViewObj.addObject("classValue", classDetailsBean);
		return modelAndViewObj;
	}

	/**
	 * 
	 * addClassParagraph method is used to add newly created paragraph with
	 * specified style in edit mode of an class
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param command
	 *            ClassBean which is the form bean for the view editClass.jsp
	 * @return Returns the class details in edit mode with the respective
	 *         message for adding class paragraph.
	 * @throws ServletException
	 */
	public ModelAndView addClassParagraph(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ClassBean command)
			throws ServletException {
		ModelAndView modelAndViewObj = new ModelAndView("editClass");
		ClassBean classBean = (ClassBean) command;
		ClassDetailsBean classDetailsBean = new ClassDetailsBean();
		ClassDTO classDTO = new ClassDTO();
		classDTO.setClassName(classBean.getClassName());
		classDTO.setSubClassId(classBean.getSuperClassId());
		classDTO.setClassParagraph(classBean.getClassParagraph());
		classDTO.setParagraphStyle(classBean.getParagraphStyle());
		String messageArray[] = null;
		try {
			message = createClassDelegate.addClassParagraph(classDTO);
			messageArray = message.split("~");
			classDetailsBean.setMessage(messageArray[0]);
			classDetailsBean = createClassDelegate
					.getClassEditViewDetails(classBean.getClassName());

		} catch (Exception exception) {
			modelAndViewObj.addObject("exceptionMessage", exception
					.getMessage());
		}
		modelAndViewObj.addObject("editParagraphMessage", messageArray[0]);
		modelAndViewObj.addObject("messageColor", messageArray[1]);
		modelAndViewObj.addObject("classValue", classDetailsBean);

		return modelAndViewObj;
	}

	/**
	 * 
	 * editClassParagraph method is used to update the existing and selected
	 * class paragraph from edit class interface
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param command
	 *            ClassBean which is the form bean for the view editClass.jsp
	 * @return Returns the class details in edit mode with the respective
	 *         message for editing the class paragraph
	 * @throws ServletException
	 */
	public ModelAndView editClassParagraph(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ClassBean command)
			throws ServletException {
		ModelAndView modelAndViewObj = new ModelAndView("editClass");
		Map<Integer, String> classParagraph = new HashMap<Integer, String>();
		ClassBean classBean = (ClassBean) command;
		ClassDetailsBean classDetailsBean = new ClassDetailsBean();
		ClassDTO classDTO = new ClassDTO();
		classDTO.setClassName(classBean.getClassName());
		classDTO.setSubClassId(classBean.getSuperClassId());
		classDTO.setClassParagraph(classBean.getClassParagraph());
		classParagraph.put(classBean.getParagraphKey(), classBean
				.getClassParagraph());
		classDTO.setClassParagraphs(classParagraph);
		classDTO.setParagraphStyle(classBean.getParagraphStyle());
		String messageArray[] = null;
		try {
			message = createClassDelegate.editClassParagraph(classDTO);
			messageArray = message.split("~");
			classDetailsBean.setMessage(messageArray[0]);
			classDetailsBean = createClassDelegate
					.getClassEditViewDetails(classBean.getClassName());

		} catch (Exception exception) {
			modelAndViewObj.addObject("exceptionMessage", exception
					.getMessage());
		}
		if(messageArray.length > 0){
			if(messageArray[0] != null || messageArray[1] != null){
				modelAndViewObj.addObject("editParagraphMessage", messageArray[0]);
				modelAndViewObj.addObject("messageColor", messageArray[1]);
			}
		}
		modelAndViewObj.addObject("classValue", classDetailsBean);

		return modelAndViewObj;
	}

	/**
	 * 
	 * editClassName method is used to edit the selected class name in edit mode
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param command
	 *            ClassBean which is the form bean for the view editClass.jsp
	 * @return Returns the class details in edit mode with respective message
	 *         after editing the class name
	 * @throws ServletException
	 */
	public ModelAndView editClassName(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ClassBean command)
			throws ServletException {
		ModelAndView modelAndViewObj = new ModelAndView("editClass");
		ClassBean classBean = (ClassBean) command;
		ClassDetailsBean classDetailsBean = new ClassDetailsBean();
		ClassDTO classDTO = new ClassDTO();
		classDTO.setClassName(classBean.getClassName());
		classDTO.setSubClassId(classBean.getSuperClassId());
		String className;
		String messageArray[] = null;
		try {
			message = createClassDelegate.editClassName(classDTO);
			messageArray = message.split("~");
			classDetailsBean.setMessage(messageArray[0]);
			className = createClassDelegate.getClassName(classDTO);
			classDetailsBean = createClassDelegate
					.getClassEditViewDetails(className);

		} catch (Exception exception) {
			modelAndViewObj.addObject("exceptionMessage", exception
					.getMessage());
		}
		modelAndViewObj.addObject("editClassMessage", messageArray[0]);
		modelAndViewObj.addObject("messageColor", messageArray[1]);
		modelAndViewObj.addObject("classValue", classDetailsBean);
		return modelAndViewObj;
	}

	/**
	 * @return the createClassDelegate
	 */
	public CreateClassDelegate getCreateClassDelegate() {
		return createClassDelegate;
	}

	/**
	 * @param createClassDelegate
	 *            the createClassDelegate to set
	 */
	public void setCreateClassDelegate(CreateClassDelegate createClassDelegate) {
		this.createClassDelegate = createClassDelegate;
	}

}
