package model;

public class ObjectClassPOJO {

	private int objClaId;
	private String theObject;
	private String theClass;

	public ObjectClassPOJO(int id, String obj, String cla) {
		this.objClaId = id;
		this.theObject = obj;
		this.theClass = cla;
	}

	public int getObjClaId() {
		return objClaId;
	}

	public void setObjClaId(int objClaId) {
		this.objClaId = objClaId;
	}

	public String getTheObject() {
		return theObject;
	}

	public void setTheObject(String theObject) {
		this.theObject = theObject;
	}

	public String getTheClass() {
		return theClass;
	}

	public void setTheClass(String theClass) {
		this.theClass = theClass;
	}
}
