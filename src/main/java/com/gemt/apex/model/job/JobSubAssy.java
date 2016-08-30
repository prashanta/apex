package com.gemt.apex.model.job;

public class JobSubAssy {

	String jobNum;
	
	boolean jobComplete;
	
	int assemblySeq;
	
	String partNum;
	
	String description;
	
	float qtyPer;
	
	float requiredQty;
	
	String ium;
	
	int Parent;

	public String getJobNum() {
		return jobNum;
	}

	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}

	public boolean isJobComplete() {
		return jobComplete;
	}

	public void setJobComplete(boolean jobComplete) {
		this.jobComplete = jobComplete;
	}

	public int getAssemblySeq() {
		return assemblySeq;
	}

	public void setAssemblySeq(int assemblySeq) {
		this.assemblySeq = assemblySeq;
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

	public String getIum() {
		return ium;
	}

	public void setIum(String ium) {
		this.ium = ium;
	}

	public int getParent() {
		return Parent;
	}

	public void setParent(int parent) {
		Parent = parent;
	}
}
