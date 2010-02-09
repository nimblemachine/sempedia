package com.hibernate.pojo;

import java.util.HashSet;
import java.util.Set;

public class Predicate {

	private Integer predicateId;
	private String predicateName;
	private ClassPojo classObject;
	private Set triples;

	public Predicate() {
		triples = new HashSet();
	}

	/**
	 * @return the predicateId
	 */
	public Integer getPredicateId() {
		return predicateId;
	}

	/**
	 * @param predicateId
	 *            the predicateId to set
	 */
	public void setPredicateId(Integer predicateId) {
		this.predicateId = predicateId;
	}

	/**
	 * @return the predicateName
	 */
	public String getPredicateName() {
		return predicateName;
	}

	/**
	 * @param predicateName
	 *            the predicateName to set
	 */
	public void setPredicateName(String predicateName) {
		this.predicateName = predicateName;
	}

	/**
	 * @return the classId
	 */
	public ClassPojo getClassObject() {
		return classObject;
	}

	/**
	 * @param classId
	 *            the classId to set
	 */
	public void setClassObject(ClassPojo classId) {
		this.classObject = classId;
	}

	/**
	 * @return
	 */
	public Set getTriples() {
		return triples;
	}

	/**
	 * @param triples
	 */
	public void setTriples(Set triples) {
		this.triples = triples;
	}

}
