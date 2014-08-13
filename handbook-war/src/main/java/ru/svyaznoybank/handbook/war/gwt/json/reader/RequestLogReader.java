package ru.svyaznoybank.handbook.war.gwt.json.reader;

import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

import ru.svyaznoybank.handbook.war.gwt.json.RequestLog;
import ru.svyaznoybank.handbook.war.gwt.json.RequestLogPagingResult;

public class RequestLogReader extends IPagingResultReader<RequestLog, RequestLogPagingResult> {

	public RequestLogReader(IPagingResultABF<RequestLog, ? extends PagingLoadResult<RequestLog>, ? extends PagingLoadConfig> beanFactory) {
		super(beanFactory, RequestLogPagingResult.class);
	}
}