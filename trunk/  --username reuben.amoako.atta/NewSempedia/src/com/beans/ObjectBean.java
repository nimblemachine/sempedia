/**
 * 
 */
package com.beans;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author tauseefsyed
 *
 */
public class ObjectBean {
	private Integer objectId;
	private String objectName;
	private String isOfType;
	private String newPredicate;
	private String newPredicateValue;
	
	private MultipartFile imageFile;
	private String objectDescription;
	private Integer objectDescriptionId;
	private String hiddenObjectName;


	/**
	 * @return
	 */
	public Integer getObjectId() {
		return objectId;
	}
	/**
	 * @param objectId
	 */
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	/**
	 * @return the objectName
	 */
	public String getObjectName() {
		return objectName;
	}
	/**
	 * @param objectName the objectName to set
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	/**
	 * @return the isOfType
	 */
	public String getIsOfType() {
		return isOfType;
	}
	/**
	 * @param isOfType the isOfType to set
	 */
	public void setIsOfType(String isOfType) {
		this.isOfType = isOfType;
	}
	public String getNewPredicate() {
		return newPredicate;
	}
	public void setNewPredicate(String newPredicate) {
		this.newPredicate = newPredicate;
	}
	public String getNewPredicateValue() {
		return newPredicateValue;
	}
	public void setNewPredicateValue(String newPredicateValue) {
		this.newPredicateValue = newPredicateValue;
	}
	
	/**
	 * @return
	 */
	public MultipartFile getImageFile() {
		return imageFile;
	}
	/**
	 * @param imageFile
	 */
	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	/**
	 * @return
	 */
	public String getObjectDescription() {
		return objectDescription;
	}
	/**
	 * @param objectDescription
	 */
	public void setObjectDescription(String objectDescription) {
		this.objectDescription = objectDescription;
	}
	/**
	 * @return
	 */
	public Integer getObjectDescriptionId() {
		return objectDescriptionId;
	}
	/**
	 * @param objectDescriptionId
	 */
	public void setObjectDescriptionId(Integer objectDescriptionId) {
		this.objectDescriptionId = objectDescriptionId;
	}
	
	/**
	 * @return
	 */
	public String getHiddenObjectName() {
		return hiddenObjectName;
	}
	/**
	 * @param hiddenObjectName
	 */
	public void setHiddenObjectName(String hiddenObjectName) {
		this.hiddenObjectName = hiddenObjectName;
	}

}
