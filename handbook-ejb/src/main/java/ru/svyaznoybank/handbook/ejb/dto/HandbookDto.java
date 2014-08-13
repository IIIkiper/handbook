package ru.svyaznoybank.handbook.ejb.dto;

import java.util.Date;

import ru.svyaznoybank.handbook.ejb.util.BeanUtil;
import ru.svyaznoybank.handbook.jpa.domain.Handbook;

public class HandbookDto extends EntityDto {
	
	private String value;
	private String remark;
	private Integer paymentDay;
	private String handbookTypeId;
	private String phoneNumber;
	private Date beginDate;
	private Date endDate;
	private String productId;
	private String currencyId;
	
	public HandbookDto(Handbook entity) {
		BeanUtil.copyProperties(entity, this, "client", "product", "currency", "handbookType");
	}

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
	public String getHandbookTypeId() {
		return handbookTypeId;
	}
	public void setHandbookTypeId(String handbookTypeId) {
		this.handbookTypeId = handbookTypeId;
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