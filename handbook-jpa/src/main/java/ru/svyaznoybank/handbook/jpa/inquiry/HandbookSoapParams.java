package ru.svyaznoybank.handbook.jpa.inquiry;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "parameters")
@XmlAccessorType(XmlAccessType.FIELD)
public class HandbookSoapParams {
	
	@NotNull
	@Size(min = 1)
	@XmlElement(required = true)
	private String firstName;	
	
	@NotNull
	@Size(min = 1)
	@XmlElement(required = true)
	private String lastName;
		
//	@Past
	@NotNull
	@XmlElement(required = true)
	private Date birthday;
	
	@NotNull
	@Size(min = 1)
	@XmlElement(required = true)
	private String docType;
	
	@NotNull
	@Size(min = 1)
//	@Pattern(regexp = "[0-9]*")
	@XmlElement(required = true)
	private String docSeries;
	
	@NotNull
	@Size(min = 1)
//	@Pattern(regexp = "[0-9]*")
	@XmlElement(required = true)
	private String docNumber;
	
//	@Past
	@NotNull
	@XmlElement(required = true)
	private Date docIssueDate;
	
	@NotNull
	@Size(min = 1)
	@Pattern(regexp = "[0-9]*")
	@XmlElement(required = true)
	private String productId;
	
	@Pattern(regexp = "[0-9]*")
	private String phoneNumber;
	
	@Size(min = 1)
	private String remark;
	
	@Size(min = 1)
	private String patronymic;
	
	@Size(min = 1)
	private String customerId;
	
	@Size(min = 1)
	private String eanId;
	
	@Size(min = 1)
	private String currencyId;
	
	// --- Getters / Setters ---
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getDocSeries() {
		return docSeries;
	}
	public void setDocSeries(String docSeries) {
		this.docSeries = docSeries;
	}
	public String getDocNumber() {
		return docNumber;
	}
	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}
	public Date getDocIssueDate() {
		return docIssueDate;
	}
	public void setDocIssueDate(Date docIssueDate) {
		this.docIssueDate = docIssueDate;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPatronymic() {
		return patronymic;
	}
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getEanId() {
		return eanId;
	}
	public void setEanId(String eanId) {
		this.eanId = eanId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
}