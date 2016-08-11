package com.gemt.apex.model.bom;


public class PartMaterial {

	String parentPartNum;
	
	String parentPartRev;
	
	int mtlSeq;

	String bubbleNum;

	String partNum;
	
	String partRev;
	
	float qtyPer;
	
	String partDescription;
	
	String partClass;
	
	String typeCode;
	
	boolean nonStock;
	
	String project;
	
	String ium;
	
	boolean fixedQty;
	
	String mfgComment;
	
	String purComment;
	
	boolean pullAsAsm;
	
	boolean viewAsAsm;

	public String getParentPartNum() {
		return parentPartNum;
	}

	public void setParentPartNum(String parentPartNum) {
		this.parentPartNum = parentPartNum;
	}

	public String getParentPartRev() {
		return parentPartRev;
	}

	public void setParentPartRev(String parentPartRev) {
		this.parentPartRev = parentPartRev;
	}

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

	public String getPartRev() {
		return partRev;
	}

	public void setPartRev(String partRev) {
		this.partRev = partRev;
	}

	public float getQtyPer() {
		return qtyPer;
	}

	public void setQtyPer(float qtyPer) {
		this.qtyPer = qtyPer;
	}

	public String getPartDescription() {
		return partDescription;
	}

	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
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
	
	public boolean isNonStock() {
		return nonStock;
	}

	public void setNonStock(boolean nonStock) {
		this.nonStock = nonStock;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getIum() {
		return ium;
	}

	public void setIum(String ium) {
		this.ium = ium;
	}

	public boolean isFixedQty() {
		return fixedQty;
	}

	public void setFixedQty(boolean fixedQty) {
		this.fixedQty = fixedQty;
	}

	public String getMfgComment() {
		return mfgComment;
	}

	public void setMfgComment(String mfgComment) {
		this.mfgComment = mfgComment;
	}

	public String getPurComment() {
		return purComment;
	}

	public void setPurComment(String purComment) {
		this.purComment = purComment;
	}

	public boolean isPullAsAsm() {
		return pullAsAsm;
	}

	public void setPullAsAsm(boolean pullAsAsm) {
		this.pullAsAsm = pullAsAsm;
	}

	public boolean isViewAsAsm() {
		return viewAsAsm;
	}

	public void setViewAsAsm(boolean viewAsAsm) {
		this.viewAsAsm = viewAsAsm;
	}	

}
