package com.gemt.apex.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MaterialInfoBean {

	String partNum;
	String mtlPartNum;
	int mtlSeq;
	String bubbleNum;
	boolean pullAsAsm;
	boolean viewAsAsm;
	float qtyPer;
	String mtlPartDescription;
	String mtlPartClass;
	String mtlPartType;
	String mtlPartRev;
	String mtlPartProject;
	String invUM;
	
	
	public String getPartNum() {
		return partNum;
	}
	public void setPartNum(String partNum) {
		this.partNum = partNum;
	}
	public String getMtlPartNum() {
		return mtlPartNum;
	}
	public void setMtlPartNum(String mtlPartNum) {
		this.mtlPartNum = mtlPartNum;
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
	public float getQtyPer() {
		return qtyPer;
	}
	public void setQtyPer(float qtyPer) {
		this.qtyPer = qtyPer;
	}
	public String getMtlPartDescription() {
		return mtlPartDescription;
	}
	public void setMtlPartDescription(String mtlPartDescription) {
		this.mtlPartDescription = mtlPartDescription;
	}
	public String getMtlPartClass() {
		return mtlPartClass;
	}
	public void setMtlPartClass(String mtlPartClass) {
		this.mtlPartClass = mtlPartClass;
	}
	public String getMtlPartType() {
		return mtlPartType;
	}
	public void setMtlPartType(String mtlPartType) {
		this.mtlPartType = mtlPartType;
	}
	public String getMtlPartRev() {
		return mtlPartRev;
	}
	public void setMtlPartRev(String mtlPartRev) {
		this.mtlPartRev = mtlPartRev;
	}
	public String getMtlPartProject() {
		return mtlPartProject;
	}
	public void setMtlPartProject(String mtlPartProject) {
		this.mtlPartProject = mtlPartProject;
	}
	public String getInvUM() {
		return invUM;
	}
	public void setInvUM(String invUM) {
		this.invUM = invUM;
	}

	
}