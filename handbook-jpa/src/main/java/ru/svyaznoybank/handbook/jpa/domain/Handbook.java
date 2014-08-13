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
import ru.svyaznoybank.handbook.jpa.domain.dic.HandbookType;
import ru.svyaznoybank.handbook.jpa.domain.dic.Product;

@Entity
@Table(name = "HANDBOOK")
public class Handbook extends AbstractIdentity {

	@Column(name = "VALUE")
	private String value;
	
	@Column(name = "REMARK")
	private String remark;
	
	@Column(name = "BEGIN_DATE")
	@Temporal(TemporalType.DATE)
	private Date beginDate;

	@Column(name = "END_DATE")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID")
	private Client client;
	
	@Column(name = "PERSON_ID", insertable = false, updatable = false)
	private Long clientId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	
	@Column(name = "PRODUCT_ID", insertable = false, updatable = false)
	private String productId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENCY")
	private Currency currency;
	
	@Column(name = "CURRENCY", insertable = false, updatable = false)
	private String currencyId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPE_ID")
	private HandbookType handbookType;
	
	@Column(name = "TYPE_ID", insertable = false, updatable = false)
	private String handbookTypeId;

	// --- Getters / Setters ---
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
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
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public HandbookType getHandbookType() {
		return handbookType;
	}
	public void setHandbookType(HandbookType handbookType) {
		this.handbookType = handbookType;
	}
	public String getHandbookTypeId() {
		return handbookTypeId;
	}
	public void setHandbookTypeId(String handbookTypeId) {
		this.handbookTypeId = handbookTypeId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
}