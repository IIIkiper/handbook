package ru.svyaznoybank.handbook.ejb.dto;

import java.util.Date;

public abstract class ClientDataDto extends EntityDto {
	private String clientId;
	private String firstName;
	private String lastName;
	private String patronymic;
	private Date birthday;
	private String docSeries;
	private String docNumber;
	private Date docIssueDate;
	private String remark;
	private String docId;
	
	// --- Getters / Setters ---
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
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
	public String getPatronymic() {
		return patronymic;
	}
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}	
	
}
