package ru.svyaznoybank.handbook.war.gwt.json;

import java.util.Date;

public interface Dto {
	
	 Object getId();
	 
	 Date getCreateDate();
	 void setCreateDate(Date createDate);
	 
	 Date getUpdateDate();
	 void setUpdateDate(Date updateDate);
	 
	 String getCreator();
	 void setCreator(String creator);
	 
	 String getUpdater();
	 void setUpdater(String updater);
}