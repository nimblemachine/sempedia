package com.dto;

import java.util.Map;
import java.util.Set;

public class ClassDTO {
	private String className;
	private String isA;
	private Set<String> property;
	private Integer subClassId;
	private Integer superClassId;
	private String allProperties;
	private String propertyName;
	private String classParagraph;
	private Map<Integer,String> classParagraphs;
	private String paragraphStyle;
	
	
	/**
	 * @return the paragraphStyle
	 */
	public String getParagraphStyle() {
		return paragraphStyle;
	}

	/**
	 * @param paragraphStyle the paragraphStyle to set
	 */
	public void setParagraphStyle(String paragraphStyle) {
		this.paragraphStyle = paragraphStyle;
	}

	/**
	 * @return the classParagraphs
	 */
	public Map<Integer, String> getClassParagraphs() {
		return classParagraphs;
	}

	/**
	 * @param classParagraphs the classParagraphs to set
	 */
	public void setClassParagraphs(Map<Integer, String> classParagraphs) {
		this.classParagraphs = classParagraphs;
	}

	/**
	 * @return the classParagraph
	 */
	public String getClassParagraph() {
		return classParagraph;
	}

	/**
	 * @param classParagraph the classParagraph to set
	 */
	public void setClassParagraph(String classParagraph) {
		this.classParagraph = classParagraph;
	}

	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * @param propertyName the propertyName to set
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * @return the allProperties
	 */
	public String getAllProperties() {
		return allProperties;
	}

	/**
	 * @param allProperties the allProperties to set
	 */
	public void setAllProperties(String allProperties) {
		this.allProperties = allProperties;
	}

	/**
	 * @return the subClassId
	 */
	public Integer getSubClassId() {
		return subClassId;
	}

	/**
	 * @param subClassId the subClassId to set
	 */
	public void setSubClassId(Integer subClassId) {
		this.subClassId = subClassId;
	}

	/**
	 * @return the superClassId
	 */
	public Integer getSuperClassId() {
		return superClassId;
	}

	/**
	 * @param superClassId the superClassId to set
	 */
	public void setSuperClassId(Integer superClassId) {
		this.superClassId = superClassId;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the isA
	 */
	public String getIsA() {
		return isA;
	}

	/**
	 * @param isA
	 *            the isA to set
	 */
	public void setIsA(String isA) {
		this.isA = isA;
	}

	/**
	 * @return the property
	 */
	public Set<String> getProperty() {
		return property;
	}

	/**
	 * @param property the property to set
	 */
	public void setProperty(Set<String> property) {
		this.property = property;
	}

}
