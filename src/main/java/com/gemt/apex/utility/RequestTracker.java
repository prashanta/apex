package com.gemt.apex.utility;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class RequestTracker {
	
	private Date startDate;
	private Date endDate;
	
	@PostConstruct
    public void init() {
        this.startDate = new Date();
    }
	
	
	
	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	public String getTimeDifference(){
		return "Expecting a time diff";
	}
}
