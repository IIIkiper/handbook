package ru.svyaznoybank.handbook.war.gwt.json.reader;

import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

import ru.svyaznoybank.handbook.war.gwt.json.Handbook;
import ru.svyaznoybank.handbook.war.gwt.json.HandbookPagingResult;

public class HandbookReader extends IPagingResultReader<Handbook, HandbookPagingResult> {

	public HandbookReader(IPagingResultABF<Handbook, ? extends PagingLoadResult<Handbook>, ? extends PagingLoadConfig> beanFactory) {
		super(beanFactory, HandbookPagingResult.class);
	}
}