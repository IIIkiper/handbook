package ru.svyaznoybank.handbook.war.gwt.presenter;

import ru.svyaznoybank.handbook.war.gwt.json.Offer;
import ru.svyaznoybank.handbook.war.gwt.json.reader.OfferABF;
import ru.svyaznoybank.handbook.war.gwt.json.reader.OfferReader;
import ru.svyaznoybank.handbook.war.gwt.rest.RestUri;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.data.shared.loader.JsonReader;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public class OfferGridPresenter extends ClientDetailsPresenter<Offer> {

	public OfferGridPresenter(long clientId, AbstractGridPresenter.Display<Offer> view) {
		super(clientId, RestUri.OFFER, view, Offer.class);
	}

	@Override
	protected JsonReader<PagingLoadResult<Offer>, ? extends PagingLoadResult<Offer>> getReader() {
		return new OfferReader(beanFactory);
	}

	@Override
	protected OfferABF getBeanFactory() {
		return GWT.create(OfferABF.class);
	}
}