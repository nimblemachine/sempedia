/**
 * 
 */
package com.dto;

/**
 * @author tauseefsyed
 *
 */
public class ObjectDTO {
private Integer objectId; 
private String objectName;
private String isOfType;


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

}
