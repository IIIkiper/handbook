package ru.svyaznoybank.handbook.war.gwt.json;

import com.sencha.gxt.data.shared.loader.PagingLoadConfig;

/**
 * @author Roman Zaripov
 */
public interface DictionaryLoadConfig extends PagingLoadConfig {
	public String getQuery();
	public void setQuery(String query);
}