package com.hibernate.pojo;

import java.util.Set;

public class ClassPojo {

	private Integer classId;
	private String className;
	private Set<ObjectPojo> objects;
	private Set<Inheritance> subClassInheritances;
	private Set<Inheritance> superClassInheritances;
	private Set<Predicate> predicates;

	/**
	 * @return the classId
	 */
	public Integer getClassId() {
		return classId;
	}

	/**
	 * @param classId
	 *            the classId to set
	 */
	public void setClassId(Integer classId) {
		this.classId = classId;
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
	 * @return the objects
	 */
	public Set<ObjectPojo> getObjects() {
		return objects;
	}

	/**
	 * @param objects
	 *            the objects to set
	 */
	public void setObjects(Set<ObjectPojo> objects) {
		this.objects = objects;
	}

	/**
	 * @return the subClassInheritances
	 */
	public Set<Inheritance> getSubClassInheritances() {
		return subClassInheritances;
	}

	/**
	 * @param subClassInheritances
	 *            the subClassInheritances to set
	 */
	public void setSubClassInheritances(Set<Inheritance> subClassInheritances) {
		this.subClassInheritances = subClassInheritances;
	}

	/**
	 * @return the superClassInheritances
	 */
	public Set<Inheritance> getSuperClassInheritances() {
		return superClassInheritances;
	}

	/**
	 * @param superClassInheritances
	 *            the superClassInheritances to set
	 */
	public void setSuperClassInheritances(
			Set<Inheritance> superClassInheritances) {
		this.superClassInheritances = superClassInheritances;
	}

	/**
	 * @return the predicates
	 */
	public Set<Predicate> getPredicates() {
		return predicates;
	}

	/**
	 * @param predicates
	 *            the predicates to set
	 */
	public void setPredicates(Set<Predicate> predicates) {
		this.predicates = predicates;
	}

}
