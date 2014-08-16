package ru.svyaznoybank.handbook.ejb.soap;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ru.svyaznoybank.handbook.jpa.inquiry.HandbookSoapParams;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class HandbookRequest {
	
	@NotNull
	@Size(min = 1)
	@XmlElement(required = true)	
	private String requestId;
	
	@NotNull
	@XmlElement(required = true)
	private Date requestDate;
	
	@Valid
	@NotNull
	@XmlElement(required = true)
	private HandbookSoapParams parameters;
	
	// --- Getters / Setters ---
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public HandbookSoapParams getParameters() {
		return parameters;
	}
	public void setParameters(HandbookSoapParams parameters) {
		this.parameters = parameters;
	}
}