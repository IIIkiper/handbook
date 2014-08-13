package ru.svyaznoybank.handbook.war.gwt.presenter;

import ru.svyaznoybank.handbook.war.gwt.json.Handbook;
import ru.svyaznoybank.handbook.war.gwt.json.reader.HandbookABF;
import ru.svyaznoybank.handbook.war.gwt.json.reader.HandbookReader;
import ru.svyaznoybank.handbook.war.gwt.rest.RestUri;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.data.shared.loader.JsonReader;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public class HandbookGridPresenter extends ClientDetailsPresenter<Handbook> {

	public HandbookGridPresenter(long clientId, AbstractGridPresenter.Display<Handbook> view) {
		super(clientId, RestUri.HANDBOOK, view, Handbook.class);
	}

	@Override
	protected JsonReader<PagingLoadResult<Handbook>, ? extends PagingLoadResult<Handbook>> getReader() {
		return new HandbookReader(beanFactory);
	}

	@Override
	protected HandbookABF getBeanFactory() {
		return GWT.create(HandbookABF.class);
	}
}