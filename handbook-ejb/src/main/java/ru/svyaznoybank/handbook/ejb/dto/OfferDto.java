package ru.svyaznoybank.handbook.ejb.dto;

import java.util.Date;

import ru.svyaznoybank.handbook.ejb.util.BeanUtil;
import ru.svyaznoybank.handbook.jpa.domain.Offer;
public class OfferDto extends EntityDto {
	
	private Date beginDate;
	private Date endDate;
	private Double creditLine;
	private Double minCreditLine;
	private Double maxCreditLine;
	private Double minFirstPayment;
	private Double maxFirstPayment;
	private String workflowCode;
	private Double minYield;
	private Double maxYield;
	private Integer minRisk;
	private Integer maxRisk;
	private Integer averageRisk;
	private String remark;
	private String currencyId;
	private String offerCurrencyId;
	private String productId;
	private String uwTypeId;
	private String offerTypeId;
	private String phoneNumber;
	private Integer paymentDay;
	
	public OfferDto() { }
	
	public OfferDto(Offer entity) {
		BeanUtil.copyProperties(entity, this, 
			"client", 
			"currency", 
			"offerCurrency", 
			"product", 
			"uwType", 
			"offerType"
		);
	}

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getOfferCurrencyId() {
		return offerCurrencyId;
	}

	public void setOfferCurrencyId(String offerCurrencyId) {
		this.offerCurrencyId = offerCurrencyId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getPaymentDay() {
		return paymentDay;
	}

	public void setPaymentDay(Integer paymentDay) {
		this.paymentDay = paymentDay;
	}
}