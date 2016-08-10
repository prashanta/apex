package com.gemt.apex.model.bom;

public class Parent {
	private String partNum;
	private String revisionNum;
	private String partDescription;
	private float qtyPer;						
	private String bubbleNum;						
	private int mtlSeq;						
	private String partClass;
	private String typeCode;
	private boolean revApproved;

	public String getPartNum() {
		return partNum;
	}
	public void setPartNum(String partNum) {
		this.partNum = partNum;
	}
	public String getRevisionNum() {
		return revisionNum;
	}
	public void setRevisionNum(String revisionNum) {
		this.revisionNum = revisionNum;
	}
	public String getPartDescription() {
		return partDescription;
	}
	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}
	public float getQtyPer() {
		return qtyPer;
	}
	public void setQtyPer(float qtyPer) {
		this.qtyPer = qtyPer;
	}
	public String getBubbleNum() {
		return bubbleNum;
	}
	public void setBubbleNum(String bubbleNum) {
		this.bubbleNum = bubbleNum;
	}
	public int getMtlSeq() {
		return mtlSeq;
	}
	public void setMtlSeq(int mtlSeq) {
		this.mtlSeq = mtlSeq;
	}
	public String getPartClass() {
		return partClass;
	}
	public void setPartClass(String partClass) {
		this.partClass = partClass;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public boolean isRevApproved() {
		return revApproved;
	}
	public void setRevApproved(boolean revApproved) {
		this.revApproved = revApproved;
	}
}
