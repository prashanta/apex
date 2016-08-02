package com.gemt.apex.model.bom;

public class PartPlant{
	
	String partNum;
	
	float minimumQty;
	
	float maximumQty;

	float safetyQty;
	
	float minOrderQty;

	int leadtime;
		
	float minMfgLotSize;
	
	String minAbc;
	
	float mfgLotSize;
	
	float maxMfgLotSize;
	
	boolean processMRP;
	
	boolean generateSugg;
	
	int daysOfSupply;	

	public String getPartNum() {
		return partNum;
	}

	public void setPartNum(String partNum) {
		this.partNum = partNum;
	}


	public float getMinimumQty() {
		return minimumQty;
	}

	public void setMinimumQty(float minimumQty) {
		this.minimumQty = minimumQty;
	}

	public float getMaximumQty() {
		return maximumQty;
	}

	public void setMaximumQty(float maximumQty) {
		this.maximumQty = maximumQty;
	}

	public float getSafetyQty() {
		return safetyQty;
	}

	public void setSafetyQty(float safetyQty) {
		this.safetyQty = safetyQty;
	}

	public float getMinOrderQty() {
		return minOrderQty;
	}

	public void setMinOrderQty(float minOrderQty) {
		this.minOrderQty = minOrderQty;
	}

	public int getLeadtime() {
		return leadtime;
	}

	public void setLeadtime(int leadtime) {
		this.leadtime = leadtime;
	}

	public float getMinMfgLotSize() {
		return minMfgLotSize;
	}

	public void setMinMfgLotSize(float minMfgLotSize) {
		this.minMfgLotSize = minMfgLotSize;
	}

	public String getMinAbc() {
		return minAbc;
	}

	public void setMinAbc(String minAbc) {
		this.minAbc = minAbc;
	}

	public float getMfgLotSize() {
		return mfgLotSize;
	}

	public void setMfgLotSize(float mfgLotSize) {
		this.mfgLotSize = mfgLotSize;
	}

	public float getMaxMfgLotSize() {
		return maxMfgLotSize;
	}

	public void setMaxMfgLotSize(float maxMfgLotSize) {
		this.maxMfgLotSize = maxMfgLotSize;
	}

	public boolean isProcessMRP() {
		return processMRP;
	}

	public void setProcessMRP(boolean processMRP) {
		this.processMRP = processMRP;
	}

	public boolean isGenerateSugg() {
		return generateSugg;
	}

	public void setGenerateSugg(boolean generateSugg) {
		this.generateSugg = generateSugg;
	}

	public int getDaysOfSupply() {
		return daysOfSupply;
	}

	public void setDaysOfSupply(int daysOfSupply) {
		this.daysOfSupply = daysOfSupply;
	}
	
	

}
