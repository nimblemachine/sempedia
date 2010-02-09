package com.dto;

public class ObjectDescriptionDTO {
	private Integer objectId;
	private String ObjectName;
	private byte[] imageFile;
	private String objectDescription;
	private Integer objectDescriptionId;
	private String objectDescriptionHeading;
	
	
	public Integer getObjectId() {
		return objectId;
	}
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	public String getObjectName() {
		return ObjectName;
	}
	public void setObjectName(String objectName) {
		ObjectName = objectName;
	}
	public byte[] getImageFile() {
		return imageFile;
	}
	public void setImageFile(byte[] imageFile) {
		this.imageFile = imageFile;
	}
	public String getObjectDescription() {
		return objectDescription;
	}
	public void setObjectDescription(String objectDescription) {
		this.objectDescription = objectDescription;
	}
	public Integer getObjectDescriptionId() {
		return objectDescriptionId;
	}
	public void setObjectDescriptionId(Integer objectDescriptionId) {
		this.objectDescriptionId = objectDescriptionId;
	}
	public void setObjectDescriptionHeading(String objectDescriptionHeading){
		this.objectDescriptionHeading = objectDescriptionHeading;
	}
	public String getObjectDescriptionHeading() {		
		return objectDescriptionHeading;
	}

}
