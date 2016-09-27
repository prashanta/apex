package com.gemt.apex.model.bom;

import java.sql.Date;

public class SalesOrder {
	int orderNum;
	int orderLine;
	float quantity;
	Date dueDate;
	
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public int getOrderLine() {
		return orderLine;
	}
	public void setOrderLine(int orderLine) {
		this.orderLine = orderLine;
	}
	public float getQuantity() {
		return quantity;
	}
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
}
