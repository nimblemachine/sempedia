package model;

import java.util.List;

public class ClassPOJO {
	
	private String className;
	private String superClass;
	private List<String> predicates;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSuperClass() {
		return superClass;
	}
	public void setSuperClass(String superClass) {
		this.superClass = superClass;
	}
	public List<String> getPredicates() {
		return predicates;
	}
	public void setPredicates(List<String> predicates) {
		this.predicates = predicates;
	}
	
	

}
