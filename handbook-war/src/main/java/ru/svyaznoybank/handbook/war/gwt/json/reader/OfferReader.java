package ru.svyaznoybank.handbook.war.gwt.json.reader;

import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

import ru.svyaznoybank.handbook.war.gwt.json.Offer;
import ru.svyaznoybank.handbook.war.gwt.json.OfferPagingResult;

public class OfferReader extends IPagingResultReader<Offer, OfferPagingResult> {

	public OfferReader(IPagingResultABF<Offer, ? extends PagingLoadResult<Offer>, ? extends PagingLoadConfig> beanFactory) {
		super(beanFactory, OfferPagingResult.class);
	}
}