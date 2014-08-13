package ru.svyaznoybank.handbook.ejb.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import ru.svyaznoybank.handbook.ejb.util.BeanUtil;
import ru.svyaznoybank.handbook.jpa.domain.Offer;

@XmlType(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferSoap {
	
	private String remark;
	private Double creditLine;
	private Double minCreditLine;
	private Double maxCreditLine;
	private Integer paymentDay;
	private Double minFirstPayment;
	private Double maxFirstPayment;
	private String workflowCode;
	private Double minYield;
	private Double maxYield;
	private Integer minRisk;
	private Integer maxRisk;
	private Integer averageRisk;
	
	@XmlElement(name = "currency")
	private String currencyId;
	
	@XmlElement(name = "uwType")
	private String uwTypeId;
	
	@XmlElement(name = "listCode")
	private String offerTypeId;
	
	@XmlElement(name = "code")
	private String productId;
	
	public OfferSoap() { }
	
	public OfferSoap(Offer offer) {
		BeanUtil.copyProperties(offer, this, 
			"client", 
			"currency", 
			"offerCurrency", 
			"product", 
			"uwType", 
			"offerType"
		);
	}

	// --- Getters / Setters ---
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getCreditLine() {
		return creditLine;
	}

	public void setCreditLine(Double creditLine) {
		this.creditLine = creditLine;
	}

	public Double getMinCreditLine() {
		return minCreditLine;
	}

	public void setMinCreditLine(Double minCreditLine) {
		this.minCreditLine = minCreditLine;
	}

	public Double getMaxCreditLine() {
		return maxCreditLine;
	}

	public void setMaxCreditLine(Double maxCreditLine) {
		this.maxCreditLine = maxCreditLine;
	}

	public Integer getPaymentDay() {
		return paymentDay;
	}

	public void setPaymentDay(Integer paymentDay) {
		this.paymentDay = paymentDay;
	}

	public Double getMinFirstPayment() {
		return minFirstPayment;
	}

	public void setMinFirstPayment(Double minFirstPayment) {
		this.minFirstPayment = minFirstPayment;
	}

	public Double getMaxFirstPayment() {
		return maxFirstPayment;
	}

	public void setMaxFirstPayment(Double maxFirstPayment) {
		this.maxFirstPayment = maxFirstPayment;
	}

	public String getWorkflowCode() {
		return workflowCode;
	}

	public void setWorkflowCode(String workflowCode) {
		this.workflowCode = workflowCode;
	}

	public Double getMinYield() {
		return minYield;
	}

	public void setMinYield(Double minYield) {
		this.minYield = minYield;
	}

	public Double getMaxYield() {
		return maxYield;
	}

	public void setMaxYield(Double maxYield) {
		this.maxYield = maxYield;
	}

	public Integer getMinRisk() {
		return minRisk;
	}

	public void setMinRisk(Integer minRisk) {
		this.minRisk = minRisk;
	}

	public Integer getMaxRisk() {
		return maxRisk;
	}

	public void setMaxRisk(Integer maxRisk) {
		this.maxRisk = maxRisk;
	}

	public Integer getAverageRisk() {
		return averageRisk;
	}

	public void setAverageRisk(Integer averageRisk) {
		this.averageRisk = averageRisk;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getUwTypeId() {
		return uwTypeId;
	}

	public void setUwTypeId(String uwTypeId) {
		this.uwTypeId = uwTypeId;
	}

	public String getOfferTypeId() {
		return offerTypeId;
	}

	public void setOfferTypeId(String offerTypeId) {
		this.offerTypeId = offerTypeId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
}