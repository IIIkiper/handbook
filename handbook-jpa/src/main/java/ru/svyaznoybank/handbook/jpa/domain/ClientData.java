package ru.svyaznoybank.handbook.jpa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.ws.rs.QueryParam;

import ru.svyaznoybank.handbook.jpa.domain.dic.Document;

@Embeddable
public class ClientData {
	
	@QueryParam("clientId")
	@Column(name = "CUSTOMER_ID")
	private String clientId; // WTF?
	
	@QueryParam("firstName")
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@QueryParam("lastName")
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "SECOND_NAME")
	private String patronymic;
	
	@QueryParam("birthday")
	@Column(name = "BIRTHDAY")
	@Temporal(TemporalType.DATE)
	private Date birthday;
		
	@QueryParam("docSeries")
	@Column(name = "DOC_SERIES")
	private String docSeries;
	
	@QueryParam("docNumber")
	@Column(name = "DOC_NUMBER")
	private String docNumber;
	
	@Column(name = "DOC_DATE_DELIVERY")
	@Temporal(TemporalType.DATE)
	private Date docIssueDate;
	
	@Column(name = "REMARK")
	private String comment;
	
	// DOC_TYPE mapping
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOC_TYPE", insertable = false, updatable = false)
	private Document doc;
	
	@Column(name = "DOC_TYPE")
	private String docId;
	
	// --- Gettters / Setters ---
	public Document getDoc() {
		return doc;
	}
	
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
}