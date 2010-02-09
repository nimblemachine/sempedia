/**
 * 
 */
package com.beans;

import java.util.Map;
import java.util.Set;

/**
 * @author gurupavang
 *
 */
public class ClassDetailsBean {
	private String subClassName;
	private String superClassName;
	private Set<String> superClassPredicates;
	private Integer subClassId;
	private Integer superClassId;
	private Set<String> subClassPredicates;
	private String message;
	private String operation = "addMode";
	private Map<Integer,String> classParagraph;
	private Map<Integer,String> classParagraphStyle;
	private String color;
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return the classParagraphStyle
	 */
	public Map<Integer, String> getClassParagraphStyle() {
		return classParagraphStyle;
	}
	/**
	 * @param classParagraphStyle the classParagraphStyle to set
	 */
	public void setClassParagraphStyle(Map<Integer, String> classParagraphStyle) {
		this.classParagraphStyle = classParagraphStyle;
	}
	/**
	 * @return the classParagraph
	 */
	public Map<Integer, String> getClassParagraph() {
		return classParagraph;
	}
	/**
	 * @param classParagraph the classParagraph to set
	 */
	public void setClassParagraph(Map<Integer, String> classParagraph) {
		this.classParagraph = classParagraph;
	}
	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}
	/**
	 * @param operation the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the subClassPredicates
	 */
	public Set<String> getSubClassPredicates() {
		return subClassPredicates;
	}
	/**
	 * @param subClassPredicates the subClassPredicates to set
	 */
	public void setSubClassPredicates(Set<String> subClassPredicates) {
		this.subClassPredicates = subClassPredicates;
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
	 * @return the subClassName
	 */
	public String getSubClassName() {
		return subClassName;
	}
	/**
	 * @param subClassName the subClassName to set
	 */
	public void setSubClassName(String subClassName) {
		this.subClassName = subClassName;
	}
	/**
	 * @return the superClassName
	 */
	public String getSuperClassName() {
		return superClassName;
	}
	/**
	 * @param superClassName the superClassName to set
	 */
	public void setSuperClassName(String superClassName) {
		this.superClassName = superClassName;
	}
	/**
	 * @return the superClassPredicates
	 */
	public Set<String> getSuperClassPredicates() {
		return superClassPredicates;
	}
	/**
	 * @param superClassPredicates the superClassPredicates to set
	 */
	public void setSuperClassPredicates(Set<String> superClassPredicates) {
		this.superClassPredicates = superClassPredicates;
	}
	
}
