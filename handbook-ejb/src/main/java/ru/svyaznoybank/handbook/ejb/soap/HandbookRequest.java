package ru.svyaznoybank.handbook.ejb.soap;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
	
	@NotNull
	@Size(min = 1)
	@XmlElement(required = true)
	private String firstName;	
	
	@NotNull
	@Size(min = 1)
	@XmlElement(required = true)
	private String lastName;
		
	@Past
	@NotNull
	@XmlElement(required = true)
	private Date birthday;
	
	@NotNull
	@Size(min = 1)
	@XmlElement(required = true)
	private String docType;
	
	@NotNull
	@Size(min = 1)
	@XmlElement(required = true)
	private String docSeries;
	
	@NotNull
	@Size(min = 1)
	@XmlElement(required = true)
	private String docNumber;
	
	@Past
	@NotNull
	@XmlElement(required = true)
	private Date docIssueDate;
	
	@NotNull
	@Size(min = 1)
	@XmlElement(required = true)
	private String productId;
	
	private String remark;
	private String patronymic;
	private String customerId;
	private String ean;
	private String phoneNumber;
	
	// --- Getters / Setters ---
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getEan() {
		return ean;
	}
	public void setEan(String ean) {
		this.ean = ean;
	}
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPatronymic() {
		return patronymic;
	}
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
}