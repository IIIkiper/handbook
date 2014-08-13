package ru.svyaznoybank.handbook.war.gwt.json;

/**
 * Client representation of any {@link ru.svyaznoybank.handbook.jpa.domain.dic.Dictionary} subclass.
 * @author Roman Zaripov
 */
public interface Dictionary extends Dto {
	
	String getId();
	void setId(String id);
	
	String getDescription();
	void setDescription(String description);
}