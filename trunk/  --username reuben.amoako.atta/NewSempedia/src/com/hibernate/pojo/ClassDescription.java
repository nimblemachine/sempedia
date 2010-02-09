package com.hibernate.pojo;

public class ClassDescription {

	private Integer classTextId;
	private String text;
	private String style;
	private ClassPojo classPojo;

	public Integer getClassTextId() {
		return classTextId;
	}

	public void setClassTextId(Integer classTextId) {
		this.classTextId = classTextId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public ClassPojo getClassPojo() {
		return classPojo;
	}

	public void setClassPojo(ClassPojo classPojo) {
		this.classPojo = classPojo;
	}

}
