package ru.svyaznoybank.handbook.war.gwt.json.reader;

import ru.svyaznoybank.handbook.war.gwt.json.Dto;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public interface IPagingResultABF<
	E extends Dto, 
	T extends PagingLoadResult<E>,
	C extends PagingLoadConfig> extends AutoBeanFactory 
{
		AutoBean<T> jsonRoot();
		AutoBean<E> dto();
		AutoBean<E> dto(E dto);
		AutoBean<C> loadConfig();
}