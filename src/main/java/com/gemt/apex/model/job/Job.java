package com.gemt.apex.model.job;

import java.sql.Date;

public class Job {

	String jobNum;
	
	String partNum;

	String description;

	float prodQty;

	String ium;

	Date startDate;
	
	Date reqDueDate;
	
	boolean jobClosed;
	
	Date closedDate;
	
	boolean jobComplete;
	
	boolean jobReleased;
	
	Date jobCompletionDate;
	
	Date dueDate;
	
	String ProjectID;
	
	boolean jobFirm;

	public String getJobNum() {
		return jobNum;
	}

	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
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

	public float getProdQty() {
		return prodQty;
	}

	public void setProdQty(float prodQty) {
		this.prodQty = prodQty;
	}

	public String getIum() {
		return ium;
	}

	public void setIum(String ium) {
		this.ium = ium;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getReqDueDate() {
		return reqDueDate;
	}

	public void setReqDueDate(Date reqDueDate) {
		this.reqDueDate = reqDueDate;
	}

	public boolean isJobClosed() {
		return jobClosed;
	}

	public void setJobClosed(boolean jobClosed) {
		this.jobClosed = jobClosed;
	}

	public Date getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	public boolean isJobComplete() {
		return jobComplete;
	}

	public void setJobComplete(boolean jobComplete) {
		this.jobComplete = jobComplete;
	}

	public boolean isJobReleased() {
		return jobReleased;
	}

	public void setJobReleased(boolean jobReleased) {
		this.jobReleased = jobReleased;
	}

	public Date getJobCompletionDate() {
		return jobCompletionDate;
	}

	public void setJobCompletionDate(Date jobCompletionDate) {
		this.jobCompletionDate = jobCompletionDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getProjectID() {
		return ProjectID;
	}

	public void setProjectID(String projectID) {
		ProjectID = projectID;
	}

	public boolean isJobFirm() {
		return jobFirm;
	}

	public void setJobFirm(boolean jobFirm) {
		this.jobFirm = jobFirm;
	}

	
	
}
