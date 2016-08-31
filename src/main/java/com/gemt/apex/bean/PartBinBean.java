package com.gemt.apex.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PartBinBean {
	String binNum;
	float onhandQty;
	
	public String getBinNum() {
		return binNum;
	}
	public void setBinNum(String binNum) {
		this.binNum = binNum;
	}
	public float getOnhandQty() {
		return onhandQty;
	}
	public void setOnhandQty(float onhandQty) {
		this.onhandQty = onhandQty;
	}
}
