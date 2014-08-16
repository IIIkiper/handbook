package ru.svyaznoybank.handbook.ejb.soap;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class HandbookResponse {	
	
//	@XmlElement(required = true)
//	private String responseCode;

//	@XmlElement(required = true)
//	private String responseString;

	@XmlElement(required = true)
	private Date responseDate;

	@XmlElement(required = true)
	private String requestId;

	@XmlElement(required = true)
	private List<HandbookSoap> handbooks = Collections.emptyList();

	@XmlElement(required = true)
	private List<OfferSoap> offers = Collections.emptyList();

	// --- Getters / Setters ---
//	public String getResponseCode() {
//		return responseCode;
//	}
//	public void setResponseCode(String responseCode) {
//		this.responseCode = responseCode;
//	}
//	public String getResponseString() {
//		return responseString;
//	}
//	public void setResponseString(String responseString) {
//		this.responseString = responseString;
//	}

	public Date getResponseDate() {
		return responseDate;
	}
	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public List<HandbookSoap> getHandbooks() {
		return handbooks;
	}
	public void setHandbooks(List<HandbookSoap> handbooks) {
		this.handbooks = handbooks;
	}
	public List<OfferSoap> getOffers() {
		return offers;
	}
	public void setOffers(List<OfferSoap> offers) {
		this.offers = offers;
	}
}