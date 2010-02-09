package com.hibernate.pojo;

public class Inheritance {

	private Integer inheritanceId;
	// private Integer subClass;
	// private Integer SuperClass;
	private ClassPojo subClass;
	private ClassPojo superClass;

	/**
	 * @return the subClass
	 */
	public ClassPojo getSubClass() {
		return subClass;
	}

	/**
	 * @param subClass
	 *            the subClass to set
	 */
	public void setSubClass(ClassPojo subClass) {
		this.subClass = subClass;
	}

	/**
	 * @return the superClass
	 */
	public ClassPojo getSuperClass() {
		return superClass;
	}

	/**
	 * @param superClass
	 *            the superClass to set
	 */
	public void setSuperClass(ClassPojo superClass) {
		this.superClass = superClass;
	}

	/**
	 * @return the inheritanceId
	 */
	public Integer getInheritanceId() {
		return inheritanceId;
	}

	/**
	 * @param inheritanceId
	 *            the inheritanceId to set
	 */
	public void setInheritanceId(Integer inheritanceId) {
		this.inheritanceId = inheritanceId;
	}
	// /**
	// * @return the subClass
	// */
	// public Integer getSubClass() {
	// return subClass;
	// }
	// /**
	// * @param subClass the subClass to set
	// */
	// public void setSubClass(Integer subClass) {
	// this.subClass = subClass;
	// }
	// /**
	// * @return the superClass
	// */
	// public Integer getSuperClass() {
	// return SuperClass;
	// }
	// /**
	// * @param superClass the superClass to set
	// */
	// public void setSuperClass(Integer superClass) {
	// SuperClass = superClass;
	//	}

}
