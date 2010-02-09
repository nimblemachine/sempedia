package com.hibernate.pojo;

public class ObjectPojo {
	
	private Integer objectId;
	private String objectName;
	private Integer classId;
	private ClassPojo classObject;
	/**
	 * @return the objectId
	 */
	public Integer getObjectId() {
		return objectId;
	}
	/**
	 * @param objectId the objectId to set
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
	 * @return the classId
	 */
	public Integer getClassId() {
		return classId;
	}
	/**
	 * @param classId the classId to set
	 */
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	/**
	 * @return the classObject
	 *//*
	public ClassPojo getClassObject() {
		return classObject;
	}
	*//**
	 * @param classObject the classObject to set
	 *//*
	public void setClassObject(ClassPojo classObject) {
		this.classObject = classObject;
		this.classObject.setClassId(getClassId());
	}*/
}
