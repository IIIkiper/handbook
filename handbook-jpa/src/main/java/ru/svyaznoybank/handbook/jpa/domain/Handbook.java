package ru.svyaznoybank.handbook.jpa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ru.svyaznoybank.handbook.jpa.domain.dic.Currency;
import ru.svyaznoybank.handbook.jpa.domain.dic.HandbookType;
import ru.svyaznoybank.handbook.jpa.domain.dic.Product;

@Entity
@Table(name = "HANDBOOK")
@NamedNativeQuery(
	name = Handbook.LAST_HANDBOOKS_IN_TYPE_ID_GROUPS_QUERY, 
	query = 
//		"SELECT id, code, value, remark FROM ( " + 
		"SELECT * FROM ( " + 
		"	SELECT h.*, MAX(h.update_time) OVER (PARTITION BY h.type_id) AS max_update_time FROM handbook h " +
		"	INNER JOIN person p ON p.id = h.person_id " +
		"	WHERE ( " +
		"		( " +
//		"			UPPER(p.last_name) IN (SUBSTR(UPPER(:LAST_NAME), 1, INSTR(UPPER(:LAST_NAME), ';') - 1), SUBSTR(UPPER(:LAST_NAME), INSTR(UPPER(:LAST_NAME), ';') + 1)) " + 
		"			UPPER(p.last_name) = UPPER(:LAST_NAME) " + 
		"			AND UPPER(p.first_name) = UPPER(:FIRST_NAME) " +
		"			AND (UPPER(p.second_name) = UPPER(:SECOND_NAME) OR (p.second_name IS NULL AND :SECOND_NAME IS NULL)) " +
		"			AND TRUNC(p.birthday) = TRUNC(:BIRTHDAY) " +
		"			AND (p.phone_number = :PHONE_NUMBER OR p.phone_number IS NULL OR :PHONE_NUMBER IS NULL) " +
		"		) " +
		"		OR " +
		"		( " +
		"			p.doc_type = :DOC_TYPE " +
		"			AND p.doc_series = :DOC_SERIES " +
		"			AND p.doc_number = :DOC_NUMBER " +
		"		) " +
		"		OR p.customer_id = :CUSTOMER_ID " +
		"	) " + 
		"	AND (h.product_id = :PRODUCT_ID OR h.product_id IS NULL) " +
		"	AND (h.currency = :CURRENCY_ID OR h.currency IS NULL OR :CURRENCY_ID IS NULL) " +
		"	AND (h.end_date IS NULL OR TRUNC(h.end_date) >= TRUNC(:REQUEST_DATE)) " +
		"	AND (h.begin_date IS NULL OR TRUNC(h.begin_date) <= TRUNC(:REQUEST_DATE)) " +
		") " +
		"WHERE update_time = max_update_time;"
)
public class Handbook extends AbstractIdentity {
	
	public static final String LAST_HANDBOOKS_IN_TYPE_ID_GROUPS_QUERY = "LAST_HANDBOOKS_IN_TYPE_ID_GROUPS_QUERY";

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