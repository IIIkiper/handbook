package ru.svyaznoybank.handbook.war.gwt.json.reader;

import ru.svyaznoybank.handbook.war.gwt.json.Dictionary;
import ru.svyaznoybank.handbook.war.gwt.json.DictionaryPagingResult;

import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public class DictionaryReader extends IPagingResultReader<Dictionary, DictionaryPagingResult> {

	public DictionaryReader(IPagingResultABF<Dictionary, ? extends PagingLoadResult<Dictionary>, ? extends PagingLoadConfig> beanFactory) {
		super(beanFactory, DictionaryPagingResult.class);
	}
}