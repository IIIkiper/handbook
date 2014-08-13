package ru.svyaznoybank.handbook.jpa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ru.svyaznoybank.handbook.jpa.domain.dic.Currency;
import ru.svyaznoybank.handbook.jpa.domain.dic.OfferType;
import ru.svyaznoybank.handbook.jpa.domain.dic.Product;
import ru.svyaznoybank.handbook.jpa.domain.dic.UwtType;

@Entity
@Table(name = "PRE_APPROVE")
public class Offer extends AbstractIdentity {

	@Column(name = "BEGIN_DATE")
	@Temporal(TemporalType.DATE)
	private Date beginDate;
	
	@Column(name = "END_DATE")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@Column(name = "CREDIT_LINE")
	private Double creditLine;
	
	@Column(name = "MIN_CREDIT_LINE")
	private Double minCreditLine;
	
	@Column(name = "MAX_CREDIT_LINE")
	private Double maxCreditLine;
		
	@Column(name = "MIN_FIRST_PAYMENT")
	private Double minFirstPayment;
	
	@Column(name = "MAX_FIRST_PAYMENT")
	private Double maxFirstPayment;
	
	@Column(name = "WORKFLOW_CODE")
	private String workflowCode;
	
	@Column(name = "MIN_YIELD")
	private Double minYield;
	
	@Column(name = "MAX_YIELD")
	private Double maxYield;
	
	@Column(name = "RISK_GRADES_BEST")
	private Integer minRisk;
	
	@Column(name = "RISK_GRADES_WORST")
	private Integer maxRisk;
	
	@Column(name = "RISK_GRADES_AVERAGE")
	private Integer averageRisk;
	
	@Column(name = "REMARK")
	private String remark;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID")
	private Client client;
	
	@Column(name = "PERSON_ID", insertable = false, updatable = false)
	private Long clientId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENCY")
	private Currency currency;
	
	@Column(name = "CURRENCY", insertable = false, updatable = false)
	private String currencyId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OFFER_CURRENCY")
	private Currency offerCurrency;
	
	@Column(name = "OFFER_CURRENCY", insertable = false, updatable = false)
	private String offerCurrencyId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	
	@Column(name = "PRODUCT_ID", insertable = false, updatable = false)
	private String productId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UWT_CODE")
	private UwtType uwType;
	
	@Column(name = "UWT_CODE", insertable = false, updatable = false)
	private String uwTypeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIST_CODE")
	private OfferType offerType;
	
	@Column(name = "LIST_CODE", insertable = false, updatable = false)
	private String offerTypeId;

	// --- Getters / Setters ---
	public Date getBeginDate() {
		return beginDate;
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public Currency getOfferCurrency() {
		return offerCurrency;
	}

	public void setOfferCurrency(Currency offerCurrency) {
		this.offerCurrency = offerCurrency;
	}

	public String getOfferCurrencyId() {
		return offerCurrencyId;
	}

	public void setOfferCurrencyId(String offerCurrencyId) {
		this.offerCurrencyId = offerCurrencyId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public UwtType getUwType() {
		return uwType;
	}

	public void setUwtType(UwtType uwType) {
		this.uwType = uwType;
	}

	public String getUwTypeId() {
		return uwTypeId;
	}

	public void setUwTypeId(String uwTypeId) {
		this.uwTypeId = uwTypeId;
	}

	public OfferType getOfferType() {
		return offerType;
	}

	public void setOfferType(OfferType offerType) {
		this.offerType = offerType;
	}

	public String getOfferTypeId() {
		return offerTypeId;
	}

	public void setOfferTypeId(String offerTypeId) {
		this.offerTypeId = offerTypeId;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
}