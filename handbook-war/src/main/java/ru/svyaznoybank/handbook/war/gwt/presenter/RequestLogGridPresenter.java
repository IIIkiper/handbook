package ru.svyaznoybank.handbook.war.gwt.presenter;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.data.shared.loader.JsonReader;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

import ru.svyaznoybank.handbook.war.gwt.json.RequestLog;
import ru.svyaznoybank.handbook.war.gwt.json.RequestLogLoadConfig;
import ru.svyaznoybank.handbook.war.gwt.json.reader.RequestLogABF;
import ru.svyaznoybank.handbook.war.gwt.json.reader.RequestLogReader;
import ru.svyaznoybank.handbook.war.gwt.rest.RestUri;

public class RequestLogGridPresenter extends FastSearchGridPresenter<RequestLog, RequestLogLoadConfig, FastSearchGridPresenter.Display<RequestLog>> {

	public RequestLogGridPresenter(Display<RequestLog> view) {
		super(RequestLogLoadConfig.class, RestUri.REQUEST_LOG, view);
	}

	@Override
	protected JsonReader<PagingLoadResult<RequestLog>, ? extends PagingLoadResult<RequestLog>> getReader() {
		return new RequestLogReader(beanFactory);
	}

	@Override
	protected RequestLogABF getBeanFactory() {
		return GWT.create(RequestLogABF.class);
	}
}