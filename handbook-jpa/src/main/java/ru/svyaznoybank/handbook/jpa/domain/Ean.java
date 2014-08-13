package ru.svyaznoybank.handbook.jpa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EAN")
@EntityListeners(AuditListener.class)
public class Ean implements Identity {
	
	@Id
	@Column(name = "EAN")
	private String id;
	
	@Column(name = "SCLUB_ACCOUNT_ID")
	private String sclubAccountId;
	
	@Column(name = "SCLUB_PERSON_ID")
	private String sclubPersonId;
		
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "PATRONYMIC_NAME")
	private String patronymic;
	
	@Column(name = "REGION_CODE")
	private String regionCode;
	
	@Column(name = "REMARK")
	private String comment;
	
	@Column(name = "L01")
	private Integer l01;
	
	@Column(name = "L02")
	private Integer l02;

	@Column(name = "L03")
	private Integer l03;

	@Column(name = "L04")
	private Integer l04;

	@Column(name = "L05")
	private Integer l05;

	@Column(name = "L06")
	private Integer l06;

	@Column(name = "L07")
	private Integer l07;

	@Column(name = "L08")
	private Integer l08;

	@Column(name = "L09")
	private Integer l09;

	@Column(name = "L10")
	private Integer l10;

	@Column(name = "L11")
	private Integer l11;

	@Column(name = "L12")
	private Integer l12;

	@Column(name = "L13")
	private Integer l13;

	@Column(name = "L14")
	private Integer l14;

	@Column(name = "L15")
	private Integer l15;

	@Column(name = "L16")
	private Integer l16;

	@Column(name = "L17")
	private Integer l17;

	@Column(name = "L18")
	private Integer l18;

	@Column(name = "L19")
	private Integer l19;

	@Column(name = "L20")
	private Integer l20;
		
	@Column(name = "CREATE_TIME", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Column(name = "UPDATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	
	// --- Getters / Setters --
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSclubAccountId() {
		return sclubAccountId;
	}
	public void setSclubAccountId(String sclubAccountId) {
		this.sclubAccountId = sclubAccountId;
	}
	public String getSclubPersonId() {
		return sclubPersonId;
	}
	public void setSclubPersonId(String sclubPersonId) {
		this.sclubPersonId = sclubPersonId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getPatronymic() {
		return patronymic;
	}
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getL01() {
		return l01;
	}
	public void setL01(Integer l01) {
		this.l01 = l01;
	}
	public Integer getL02() {
		return l02;
	}
	public void setL02(Integer l02) {
		this.l02 = l02;
	}
	public Integer getL03() {
		return l03;
	}
	public void setL03(Integer l03) {
		this.l03 = l03;
	}
	public Integer getL04() {
		return l04;
	}
	public void setL04(Integer l04) {
		this.l04 = l04;
	}
	public Integer getL05() {
		return l05;
	}
	public void setL05(Integer l05) {
		this.l05 = l05;
	}
	public Integer getL06() {
		return l06;
	}
	public void setL06(Integer l06) {
		this.l06 = l06;
	}
	public Integer getL07() {
		return l07;
	}
	public void setL07(Integer l07) {
		this.l07 = l07;
	}
	public Integer getL08() {
		return l08;
	}
	public void setL08(Integer l08) {
		this.l08 = l08;
	}
	public Integer getL09() {
		return l09;
	}
	public void setL09(Integer l09) {
		this.l09 = l09;
	}
	public Integer getL10() {
		return l10;
	}
	public void setL10(Integer l10) {
		this.l10 = l10;
	}
	public Integer getL11() {
		return l11;
	}
	public void setL11(Integer l11) {
		this.l11 = l11;
	}
	public Integer getL12() {
		return l12;
	}
	public void setL12(Integer l12) {
		this.l12 = l12;
	}
	public Integer getL13() {
		return l13;
	}
	public void setL13(Integer l13) {
		this.l13 = l13;
	}
	public Integer getL14() {
		return l14;
	}
	public void setL14(Integer l14) {
		this.l14 = l14;
	}
	public Integer getL15() {
		return l15;
	}
	public void setL15(Integer l15) {
		this.l15 = l15;
	}
	public Integer getL16() {
		return l16;
	}
	public void setL16(Integer l16) {
		this.l16 = l16;
	}
	public Integer getL17() {
		return l17;
	}
	public void setL17(Integer l17) {
		this.l17 = l17;
	}
	public Integer getL18() {
		return l18;
	}
	public void setL18(Integer l18) {
		this.l18 = l18;
	}
	public Integer getL19() {
		return l19;
	}
	public void setL19(Integer l19) {
		this.l19 = l19;
	}
	public Integer getL20() {
		return l20;
	}
	public void setL20(Integer l20) {
		this.l20 = l20;
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
}
