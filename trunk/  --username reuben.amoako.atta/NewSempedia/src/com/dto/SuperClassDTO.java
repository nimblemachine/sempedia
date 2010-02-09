/**
 * 
 */
package com.dto;

import java.util.Set;

/**
 * @author gurupavang
 *
 */
public class SuperClassDTO {
	private Integer superClassId;
	private String superClassName;
	private Set<String> superClassProperties;
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
	 * @return the superClassProperties
	 */
	public Set getSuperClassProperties() {
		return superClassProperties;
	}
	/**
	 * @param superClassProperties the superClassProperties to set
	 */
	public void setSuperClassProperties(Set superClassProperties) {
		this.superClassProperties = superClassProperties;
	}
}
