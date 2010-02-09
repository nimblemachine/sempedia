package com.dto;

public class TripleDTO {
	public Integer tripleId;
	public String predicateValue;
	public String objectName;

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getPredicateValue() {
		return predicateValue;
	}

	public void setPredicateValue(String predicateValue) {
		this.predicateValue = predicateValue;
	}

	public Integer getTripleId() {
		return tripleId;
	}

	public void setTripleId(Integer tripleId) {
		this.tripleId = tripleId;
	}
	
}
