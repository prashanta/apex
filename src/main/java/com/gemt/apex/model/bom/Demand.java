package com.gemt.apex.model.bom;

import java.sql.Date;

public class Demand {
	
	String partNum;
	
	String revisionNum;
	
	boolean requirementFlag;
	
	Date dueDate;
	
	float  Quantity;
	
	String ium;
	
	String jobNum;
	
	int orderNum;
		
	String sourceFile;

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

	public boolean isRequirementFlag() {
		return requirementFlag;
	}

	public void setRequirementFlag(boolean requirementFlag) {
		this.requirementFlag = requirementFlag;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public float getQuantity() {
		return Quantity;
	}

	public void setQuantity(float quantity) {
		Quantity = quantity;
	}

	public String getIum() {
		return ium;
	}

	public void setIum(String ium) {
		this.ium = ium;
	}

	public String getJobNum() {
		return jobNum;
	}

	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}

	
	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public String getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}
	
		
}
