package ru.svyaznoybank.handbook.war.gwt.json;

/**
 * Model for request log.
 * This class designed sa {@link Client} subclass mostly for code reuse.
 * #creator and #updater properties never use here.
 * @author Roman Zaripov
 */
public interface RequestLog extends Client {
	
	public String getEanId();
	public void setEanId(String eanId);
	
	public String getDocId();
	public void setDocId(String docId);
	
	public Integer getErrorCode();
	public void setErrorCode(Integer errorCode);
	
	public String getResponse();
	public void setResponse(String response);
}