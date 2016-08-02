package com.gemt.apex.model.bom;

import java.util.List;

public class PartDetail extends Part{
	
	List<PartRev> partRev;
	
	List<PartPlant> partPlant;
	
	public PartDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PartDetail(Part p) {
		this.partNum = p.partNum;
		this.description = p.description;
		this.partClass = p.partClass;
		this.ium = p.ium;
		this.pum = p.pum;
		this.typeCode = p.typeCode;
		this.mfgComment = p.mfgComment;
		this.purComment = p.purComment;
		this.mfg = p.mfg;
		this.mfgNo = p.mfgNo;
		this.project = p.project;
		this.nonStock = p.nonStock;
		this.inActive = p.inActive;
		this.phantomBOM = p.phantomBOM;
	}

	public List<PartRev> getPartRev() {
		return partRev;
	}

	public void setPartRev(List<PartRev> partRev) {
		this.partRev = partRev;
	}

	public List<PartPlant> getPartPlant() {
		return partPlant;
	}

	public void setPartPlant(List<PartPlant> partPlant) {
		this.partPlant = partPlant;
	}

		
}
