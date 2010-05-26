package model;

public class PredObj {

	int preId;
	int objId;
	
	public PredObj(int preId, int objId){
		this.preId = preId;
		this.objId = objId;
	}
	
	public int getPreId() {
		return preId;
	}

	public void setPreId(int preId) {
		this.preId = preId;
	}

	public int getObjId() {
		return objId;
	}

	public void setObjId(int objId) {
		this.objId = objId;
	}
}
