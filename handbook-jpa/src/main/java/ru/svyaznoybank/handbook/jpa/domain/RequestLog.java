package ru.svyaznoybank.handbook.jpa.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "REQUEST")
@EntityListeners(AuditListener.class)
public class RequestLog implements Identity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = AbstractIdentity.SEQ_NAME)
	@Column(name = "ID")
	private Long id;
	
	@Embedded
	private ClientData clientData;
	
	@Column(name = "ERROR_CODE")
	private Integer errorCode;
	
	/*
	 * This field is mapped to DB column with special Oracle 'XMLType' data type.
	 * Application server classloader needs some jar files to properly handle it.
	 * See xdb6.jar, xmlparserv2.jar in domain library folder.
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
//	@Convert(converter = XmlResponseConverter.class)
	@Column(name = "RESPONSE")
	private String response;
	
	@Column(name = "CREATE_TIME", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Column(name = "UPDATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EAN", insertable = false, updatable = false)
	private Ean ean;
	
	@Column(name = "EAN")
	private String eanId;

	// --- Getters / Setters ---
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ClientData getClientData() {
		return clientData;
	}
	public void setClientData(ClientData clientData) {
		this.clientData = clientData;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public Ean getEan() {
		return ean;
	}
	public void setEan(Ean ean) {
		this.ean = ean;
	}
	public String getEanId() {
		return eanId;
	}
	public void setEanId(String eanId) {
		this.eanId = eanId;
	}
}