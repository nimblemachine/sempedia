package com.hibernate.pojo;

public class ObjectDescription {

	private Integer objectDescriptionId;
	private ObjectPojo objectPojo;
	private byte[] objectImage;
	private String objectDesc;

	public Integer getObjectDescriptionId() {
		return objectDescriptionId;
	}

	public void setObjectDescriptionId(Integer objectDescriptionId) {
		this.objectDescriptionId = objectDescriptionId;
	}

	public ObjectPojo getObjectPojo() {
		return objectPojo;
	}

	public void setObjectPojo(ObjectPojo objectPojo) {
		this.objectPojo = objectPojo;
	}

	public String getObjectDesc() {
		return objectDesc;
	}

	public void setObjectDesc(String objectDesc) {
		this.objectDesc = objectDesc;
	}

	public byte[] getObjectImage() {
		return objectImage;
	}

	public void setObjectImage(byte[] objectImage) {
		this.objectImage = objectImage;
	}
}
