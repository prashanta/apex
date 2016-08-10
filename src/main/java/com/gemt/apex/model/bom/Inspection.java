package com.gemt.apex.model.bom;

import java.sql.Date;

public class Inspection {
	String packSlip;
	String binNum;
	boolean receivedComplete;
	int poNum;
	float vendorQty;
	String receivedTo;
	Date receiptDate;
	
	public String getPackSlip() {
		return packSlip;
	}
	public void setPackSlip(String packSlip) {
		this.packSlip = packSlip;
	}
	public String getBinNum() {
		return binNum;
	}
	public void setBinNum(String binNum) {
		this.binNum = binNum;
	}
	public boolean isReceivedComplete() {
		return receivedComplete;
	}
	public void setReceivedComplete(boolean receivedComplete) {
		this.receivedComplete = receivedComplete;
	}	
	public int getPoNum() {
		return poNum;
	}
	public void setPoNum(int poNum) {
		this.poNum = poNum;
	}
	public float getVendorQty() {
		return vendorQty;
	}
	public void setVendorQty(float vendorQty) {
		this.vendorQty = vendorQty;
	}
	public String getReceivedTo() {
		return receivedTo;
	}
	public void setReceivedTo(String receivedTo) {
		this.receivedTo = receivedTo;
	}
	public Date getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}
	
	
}
