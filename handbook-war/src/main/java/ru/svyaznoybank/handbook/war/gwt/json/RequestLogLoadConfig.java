package ru.svyaznoybank.handbook.war.gwt.json;

import java.util.Date;

public interface RequestLogLoadConfig extends RequestLog, DictionaryLoadConfig { 
	
	Date getBeginDate();
	void setBeginDate(Date beginDate);
	
	Date getEndDate();
	void setEndDate(Date endDate);
}