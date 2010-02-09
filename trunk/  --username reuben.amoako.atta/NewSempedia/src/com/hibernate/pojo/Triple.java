package com.hibernate.pojo;

public class Triple {
	private Integer tripleId;
	private ObjectPojo subject;
	private Predicate predicate;
	private ObjectPojo object;
	/**
	 * @return the tripleId
	 */
	public Integer getTripleId() {
		return tripleId;
	}
	/**
	 * @param tripleId the tripleId to set
	 */
	public void setTripleId(Integer tripleId) {
		this.tripleId = tripleId;
	}
	/**
	 * @return the subject
	 */
	public ObjectPojo getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(ObjectPojo subject) {
		this.subject = subject;
	}
	/**
	 * @return the predicate
	 */
	public Predicate getPredicate() {
		return predicate;
	}
	/**
	 * @param predicate the predicate to set
	 */
	public void setPredicate(Predicate predicate) {
		this.predicate = predicate;
	}
	/**
	 * @return the object
	 */
	public ObjectPojo getObject() {
		return object;
	}
	/**
	 * @param object the object to set
	 */
	public void setObject(ObjectPojo object) {
		this.object = object;
	}
	

}
