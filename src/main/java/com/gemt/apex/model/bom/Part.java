package com.gemt.apex.model.bom;

public class Part {
	
	String partNum;
	
	String description;
	
	String partClass;
	
	String ium;
	
	String pum;
	
	String typeCode;
	
	String mfgComment;
	
	String purComment;
	
	String mfg;
	
	String mfgNo;
	
	String project;
	
	boolean nonStock;
	
	boolean inActive;
	
	boolean phantomBOM;

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

	public String getPartClass() {
		return partClass;
	}

	public void setPartClass(String partClass) {
		this.partClass = partClass;
	}

	public String getIum() {
		return ium;
	}

	public void setIum(String ium) {
		this.ium = ium;
	}

	public String getPum() {
		return pum;
	}

	public void setPum(String pum) {
		this.pum = pum;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
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

	public String getMfg() {
		return mfg;
	}

	public void setMfg(String mfg) {
		this.mfg = mfg;
	}

	public String getMfgNo() {
		return mfgNo;
	}

	public void setMfgNo(String mfgNo) {
		this.mfgNo = mfgNo;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public boolean isNonStock() {
		return nonStock;
	}

	public void setNonStock(boolean nonStock) {
		this.nonStock = nonStock;
	}

	public boolean isInActive() {
		return inActive;
	}

	public void setInActive(boolean inActive) {
		this.inActive = inActive;
	}

	public boolean isPhantomBOM() {
		return phantomBOM;
	}

	public void setPhantomBOM(boolean phantomBOM) {
		this.phantomBOM = phantomBOM;
	}
	
	
}
