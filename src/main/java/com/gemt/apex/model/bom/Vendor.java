package com.gemt.apex.model.bom;

public class Vendor {
	int id;
	int  vendorNum;
	String vendorID;
	String name;
	String country;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVendorID() {
		return vendorID;
	}
	public void setVendorID(String vendorID) {
		this.vendorID = vendorID;
	}
	public int getVendorNum() {
		return vendorNum;
	}
	public void setVendorNum(int vendorNum) {
		this.vendorNum = vendorNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
