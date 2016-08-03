package com.gemt.apex.model.bom;

import java.sql.Date;

public class Supply {
	
	String partNum;
	
	String revisionNum;
	
	boolean requirementFlag;
	
	Date dueDate;
	
	float  Quantity;
	
	String ium;
	
	int poNum;
	
	int poLine;
	
	String vendorName;
	
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
	
	public int getPoNum() {
		return poNum;
	}

	public void setPoNum(int poNum) {
		this.poNum = poNum;
	}

	public int getPoLine() {
		return poLine;
	}

	public void setPoLine(int poLine) {
		this.poLine = poLine;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}
	
		
}
