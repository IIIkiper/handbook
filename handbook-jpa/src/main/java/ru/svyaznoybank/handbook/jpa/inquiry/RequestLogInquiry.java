package ru.svyaznoybank.handbook.jpa.inquiry;

import java.util.Date;

import javax.ws.rs.QueryParam;

import ru.svyaznoybank.handbook.jpa.domain.RequestLog;

public class RequestLogInquiry extends DomainInquiry<RequestLog> { 
	
	@QueryParam("beginDate")
	private Date beginDate;
	
	@QueryParam("endDate")
	private Date endDate;

	// --- Getters / Setters ---
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}