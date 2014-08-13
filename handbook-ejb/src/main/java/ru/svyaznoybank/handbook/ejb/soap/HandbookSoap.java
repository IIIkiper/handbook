package ru.svyaznoybank.handbook.ejb.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import ru.svyaznoybank.handbook.ejb.util.BeanUtil;
import ru.svyaznoybank.handbook.jpa.domain.Ean;
import ru.svyaznoybank.handbook.jpa.domain.Handbook;

@XmlType(name = "handbook")
@XmlAccessorType(XmlAccessType.FIELD)
public class HandbookSoap {
	
	private String value;
	private String remark;
	private Integer paymentDay;
	
	@XmlElement(name = "code")
	private String handbookTypeId;
	
	public HandbookSoap() { }
	
	public HandbookSoap(Handbook handbook) {
		BeanUtil.copyProperties(handbook, this, 
			"client", 
			"product", 
			"currency", 
			"handbookType"
		);
	}
	
	public HandbookSoap(Ean ean) {
		
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
	public Integer getPaymentDay() {
		return paymentDay;
	}
	public void setPaymentDay(Integer paymentDay) {
		this.paymentDay = paymentDay;
	}
	public String getHandbookTypeId() {
		return handbookTypeId;
	}
	public void setHandbookTypeId(String handbookTypeId) {
		this.handbookTypeId = handbookTypeId;
	}
}
