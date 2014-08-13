package ru.svyaznoybank.handbook.war.gwt.json;

import com.sencha.gxt.data.shared.loader.PagingLoadConfig;

public interface ClientPropertyConfig extends PagingLoadConfig {
	
	public Long getClientId();
	public void setClientId(Long clientId);
}