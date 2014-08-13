package ru.svyaznoybank.handbook.war.gwt.json;

import java.util.Date;

public interface ClientPropertyEditable extends Dto {
	
	public Long getId();
	public void setId(Long id);
	
	public Date getBeginDate();
	public void setBeginDate(Date beginDate);
	
	public Date getEndDate();
	public void setEndDate(Date endDate);
	
	public String getRemark();
	public void setRemark(String remark);
}