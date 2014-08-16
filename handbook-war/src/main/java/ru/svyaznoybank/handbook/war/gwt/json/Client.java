package ru.svyaznoybank.handbook.war.gwt.json;

import java.util.Date;

public interface Client extends Dto {
	
	public Long getId();
	public void setId(Long id);
	
	public String getFirstName();
	public void setFirstName(String firstName);
	
	public Long getClientId();
	public void setClientId(Long clientId);
	
	public String getLastName();
	public void setLastName(String lastName);
	
	public String getPatronymic();
	public void setPatronymic(String patronymic);
	
	public Date getBirthday();
	public void setBirthday(Date birthday);
	
	public String getDocSeries();
	public void setDocSeries(String docSeries);
	
	public String getDocNumber();
	public void setDocNumber(String docNumber);
	
	public Date getDocIssueDate();
	public void setDocIssueDate(Date docIssueDate);
	
	public String getRemark();
	public void setRemark(String remark);
}