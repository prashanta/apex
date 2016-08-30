package com.gemt.apex.model.job;

public class JobMtl {

	int mtlSeq;
	
	String bubbleNum;
	
	String partNum;
	
	String description;
	
	float qtyPer;
	
	float requiredQty;
	
	boolean issuedComplete;
	
	float issuedQty;
	
	String ium;

	public int getMtlSeq() {
		return mtlSeq;
	}

	public void setMtlSeq(int mtlSeq) {
		this.mtlSeq = mtlSeq;
	}

	public String getBubbleNum() {
		return bubbleNum;
	}

	public void setBubbleNum(String bubbleNum) {
		this.bubbleNum = bubbleNum;
	}

	public String getPartNum() {
		return partNum;
	}

	public void setPartNum(String partNum) {
		this.partNum = partNum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getQtyPer() {
		return qtyPer;
	}

	public void setQtyPer(float qtyPer) {
		this.qtyPer = qtyPer;
	}

	public float getRequiredQty() {
		return requiredQty;
	}

	public void setRequiredQty(float requiredQty) {
		this.requiredQty = requiredQty;
	}

	public boolean isIssuedComplete() {
		return issuedComplete;
	}

	public void setIssuedComplete(boolean issuedComplete) {
		this.issuedComplete = issuedComplete;
	}

	public float getIssuedQty() {
		return issuedQty;
	}

	public void setIssuedQty(float issuedQty) {
		this.issuedQty = issuedQty;
	}

	public String getIum() {
		return ium;
	}

	public void setIum(String ium) {
		this.ium = ium;
	}
	
}
